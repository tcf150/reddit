package com.zachary.reddit.ui.Main;

import com.zachary.reddit.base.BasePresenter;
import com.zachary.reddit.base.BaseView;
import com.zachary.reddit.model.Topic;

import java.util.List;

/**
 * Created by user on 10/5/2017.
 */

public class MainContract {
    interface View extends BaseView<Presenter> {
        void notifyTopicListUpdate();
        void showErrorToast(String message);
    }

    interface Presenter extends BasePresenter {
        List<Topic> getCurrentTopicList();
        void upVote(int topicId);
        void downVote(int topicId);
    }
}
