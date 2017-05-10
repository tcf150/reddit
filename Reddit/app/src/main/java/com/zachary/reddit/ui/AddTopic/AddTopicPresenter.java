package com.zachary.reddit.ui.AddTopic;

import android.util.Log;

import com.zachary.reddit.base.BaseApiClient;
import com.zachary.reddit.data.DataManager;
import com.zachary.reddit.service.RedditService;
import com.zachary.reddit.service.model.CreateTopicResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 10/5/2017.
 */

public class AddTopicPresenter implements AddTopicContract.Presenter {
    private final static String TAG = "AddTopicPresenter";
    private AddTopicContract.View mView;

    private String mText = "";

    public AddTopicPresenter(AddTopicContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setWordCount(mText.length());
    }

    @Override
    public void onTextChanged(String text) {
        mText = text;
        mView.setWordCount(mText.length());
    }

    @Override
    public void addTopic() {
        if (mText.length() == 0) {
            mView.showEmptyTextToast();
            return;
        }

        Log.d(TAG,"Begin up vote");
        //fire up vote service
        Call<CreateTopicResponse> call = BaseApiClient.getTopicService().createTopic(mText);
        call.enqueue(new Callback<CreateTopicResponse>() {
            @Override
            public void onResponse(Call<CreateTopicResponse> call, Response<CreateTopicResponse> response) {
                CreateTopicResponse createTopicResponse = response.body();
                Log.d(TAG,"complete up vote");
                if (createTopicResponse != null){
                    if (createTopicResponse.getStatus().isSuccess()){
                        Log.d(TAG,"up vote success");
                        mView.addTopicSuccessful(createTopicResponse.getStatus().getStatusDesc());
                    }else{
                        Log.d(TAG,"up vote fail");
                        mView.showErrorToast(createTopicResponse.getStatus().getStatusDesc());
                    }
                }
            }

            @Override
            public void onFailure(Call<CreateTopicResponse> call, Throwable t) {
                t.printStackTrace();
                mView.showErrorToast(t.getMessage());

            }
        });
    }
}
