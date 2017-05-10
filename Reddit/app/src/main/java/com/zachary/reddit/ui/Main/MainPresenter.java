package com.zachary.reddit.ui.Main;

import android.support.annotation.NonNull;

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

        if (DataManager.getInstance().getmTopicList().size() == 0){
            getTopicList(true);
        }
    }

    @Override
    public List<Topic> getCurrentTopicList() {
        return DataManager.getInstance().getmTopicList();
    }

    @Override
    public void upVote(final int topicId) {
        if (topicId < 0) return;
        Call<UpVoteResponse> call = service.upVote(topicId);
        call.enqueue(new Callback<UpVoteResponse>() {
            @Override
            public void onResponse(Call<UpVoteResponse> call, Response<UpVoteResponse> response) {
                UpVoteResponse upVoteResponse = response.body();
                if (upVoteResponse != null){
                    if (upVoteResponse.getStatus().isSuccess()){
                        DataManager.getInstance().updateTopicCount(topicId,upVoteResponse.getCount());
                        mView.notifyTopicListUpdate();
                    }else{
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
        if (topicId < 0) return;
        Call<DownVoteResponse> call = service.downVote(topicId);
        call.enqueue(new Callback<DownVoteResponse>() {
            @Override
            public void onResponse(Call<DownVoteResponse> call, Response<DownVoteResponse> response) {
                DownVoteResponse downVoteResponse = response.body();
                if (downVoteResponse != null){
                    if (downVoteResponse.getStatus().isSuccess()){
                        DataManager.getInstance().updateTopicCount(topicId,downVoteResponse.getCount());
                        mView.notifyTopicListUpdate();
                    }else{
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

    private void getTopicList(boolean forceRefresh){
        if (forceRefresh){
            DataManager.getInstance().clearTopicLis();
            mView.notifyTopicListUpdate();
        }

        Call<GetTopicListResponse> call = service.getTopicList(20,0);
        call.enqueue(new Callback<GetTopicListResponse>() {
            @Override
            public void onResponse(Call<GetTopicListResponse> call, Response<GetTopicListResponse> response) {
                GetTopicListResponse getTopicListResponse = response.body();
                if (getTopicListResponse != null){
                    if (getTopicListResponse.getStatus().isSuccess()){
                        DataManager.getInstance().addAllTopic(getTopicListResponse.getTopicList());
                        mView.notifyTopicListUpdate();
                    }else{
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
