package com.zachary.reddit.data;

import com.zachary.reddit.model.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/5/2017.
 */

public class DataManager {
    private static final DataManager mDataManager = new DataManager();
    private List<Topic> mTopicList = new ArrayList<>();

    public static DataManager getInstance() {
        return mDataManager;
    }

    public static DataManager getmDataManager() {
        return mDataManager;
    }

    public List<Topic> getmTopicList() {
        return mTopicList;
    }

    public void clearTopicLis(){
        mTopicList.clear();
    }

    public void addTopic(Topic topic){
        mTopicList.add(topic);
    }

    public void addAllTopic(List<Topic> topicList){
        mTopicList.addAll(topicList);
    }

    public void updateTopicCount(int topicId,int count){
        for (Topic topic : mTopicList){
            if (topic.equalId(topicId)){
                topic.setCount(count);
                break;
            }
        }
    }
}
