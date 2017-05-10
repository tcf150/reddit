package com.zachary.reddit.ui.Main;

import android.support.annotation.NonNull;

/**
 * Created by user on 10/5/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;

    public MainPresenter(@NonNull MainContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
