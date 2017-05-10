package com.zachary.reddit.ui.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zachary.reddit.R;
import com.zachary.reddit.base.BaseFragment;
import com.zachary.reddit.model.Topic;

/**
 * Created by user on 10/5/2017.
 */

public class MainFragment extends BaseFragment implements MainContract.View,TopicAdapter.OnClickListener{
    private MainContract.Presenter mPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvTopic;
    private TopicAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        swipeRefreshLayout = getViewByView(rootView,R.id.swipeRefreshLayout);
        rvTopic = getViewByView(rootView,R.id.rvTopic);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTopic.setLayoutManager(linearLayoutManager);

        adapter = new TopicAdapter(mPresenter.getCurrentTopicList());
        adapter.setOnClickListener(this);
        rvTopic.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onUpClick(int topicId) {
        mPresenter.upVote(topicId);
    }

    @Override
    public void onDownClick(int topicId) {
        mPresenter.downVote(topicId);
    }

    @Override
    public void notifyTopicListUpdate() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
