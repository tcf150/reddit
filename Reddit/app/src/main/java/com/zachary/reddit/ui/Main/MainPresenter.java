package com.zachary.reddit.ui.Main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zachary.reddit.base.BaseApiClient;
import com.zachary.reddit.data.DataManager;
import com.zachary.reddit.model.Topic;
import com.zachary.reddit.service.RedditService;
import com.zachary.reddit.service.model.DownVoteResponse;
import com.zachary.reddit.service.model.GetTopicListResponse;
import com.zachary.reddit.service.model.UpVoteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 10/5/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    private final static String TAG = "MainPresenter";

    private final MainContract.View mView;
    private RedditService service;

    public MainPresenter(@NonNull MainContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (service == null){
            service = BaseApiClient.getTopicService();
        }

        getTopicList(true);
    }

    @Override
    public List<Topic> getCurrentTopicList() {
        return DataManager.getInstance().getmTopicList();
    }

    @Override
    public void upVote(final int topicId) {
        //invalid topic id checking
        if (topicId < 0) return;
        Log.d(TAG,"Begin up vote");
        //fire up vote service
        Call<UpVoteResponse> call = service.upVote(topicId);
        call.enqueue(new Callback<UpVoteResponse>() {
            @Override
            public void onResponse(Call<UpVoteResponse> call, Response<UpVoteResponse> response) {
                UpVoteResponse upVoteResponse = response.body();
                Log.d(TAG,"complete up vote");
                if (upVoteResponse != null){
                    if (upVoteResponse.getStatus().isSuccess()){
                        Log.d(TAG,"up vote success");
                        //update DataManager
                        DataManager.getInstance().updateTopicCount(topicId,upVoteResponse.getCount());
                        //notify ui
                        mView.notifyTopicListUpdate();
                    }else{
                        Log.d(TAG,"up vote fail");
                        mView.showErrorToast(upVoteResponse.getStatus().getStatusDesc());
                    }
                }
            }

            @Override
            public void onFailure(Call<UpVoteResponse> call, Throwable t) {
                t.printStackTrace();
                mView.showErrorToast(t.getMessage());

            }
        });
    }

    @Override
    public void downVote(final int topicId) {
        //invalid topic id checking
        if (topicId < 0) return;
        Log.d(TAG,"begin down vote");
        //fire down vote service
        Call<DownVoteResponse> call = service.downVote(topicId);
        call.enqueue(new Callback<DownVoteResponse>() {
            @Override
            public void onResponse(Call<DownVoteResponse> call, Response<DownVoteResponse> response) {
                DownVoteResponse downVoteResponse = response.body();
                Log.d(TAG,"down vote complete");
                if (downVoteResponse != null){
                    if (downVoteResponse.getStatus().isSuccess()){
                        Log.d(TAG,"down vote success");
                        //update DataManager
                        DataManager.getInstance().updateTopicCount(topicId,downVoteResponse.getCount());
                        //notify ui
                        mView.notifyTopicListUpdate();
                    }else{
                        Log.d(TAG,"down vote fail");
                        mView.showErrorToast(downVoteResponse.getStatus().getStatusDesc());
                    }
                }
            }

            @Override
            public void onFailure(Call<DownVoteResponse> call, Throwable t) {
                t.printStackTrace();
                mView.showErrorToast(t.getMessage());

            }
        });

    }

    @Override
    public void getTopicList(boolean forceRefresh){
        //clear list if force refresh data
        if (forceRefresh){
            DataManager.getInstance().clearTopicLis();
            mView.notifyTopicListUpdate();
        }
        Log.d(TAG,"begin get topic list");
        //fire getTopicList
        Call<GetTopicListResponse> call = service.getTopicList(20,0);
        call.enqueue(new Callback<GetTopicListResponse>() {
            @Override
            public void onResponse(Call<GetTopicListResponse> call, Response<GetTopicListResponse> response) {
                GetTopicListResponse getTopicListResponse = response.body();
                Log.d(TAG,"get topic list complete");
                if (getTopicListResponse != null){
                    if (getTopicListResponse.getStatus().isSuccess()){
                        //update data manager and notify ui
                        Log.d(TAG,"get topic list success");
                        DataManager.getInstance().addAllTopic(getTopicListResponse.getTopicList());
                        mView.notifyTopicListUpdate();
                    }else{
                        Log.d(TAG,"get topic list fail");
                        mView.showErrorToast(getTopicListResponse.getStatus().getStatusDesc());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTopicListResponse> call, Throwable t) {
                t.printStackTrace();
                mView.showErrorToast(t.getMessage());
            }
        });
    }
}
