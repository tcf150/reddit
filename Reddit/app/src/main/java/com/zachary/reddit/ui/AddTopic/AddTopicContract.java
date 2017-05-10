package com.zachary.reddit.ui.AddTopic;

import com.zachary.reddit.base.BasePresenter;
import com.zachary.reddit.base.BaseView;

/**
 * Created by user on 10/5/2017.
 */

public class AddTopicContract {
    interface View extends BaseView<Presenter> {
        void setWordCount(int count);
        void showEmptyTextToast();
        void addTopicSuccessful(String message);
        void showErrorToast(String message);
    }

    interface Presenter extends BasePresenter {
        void onTextChanged(String text);
        void addTopic();
    }
}
