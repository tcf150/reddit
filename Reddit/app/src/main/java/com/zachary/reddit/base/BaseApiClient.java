package com.zachary.reddit.base;

import com.zachary.reddit.service.RedditService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 10/5/2017.
 */

public class BaseApiClient {

    public static RedditService getTopicService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zacharytongreddit.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RedditService service = retrofit.create(RedditService.class);
        return service;
    }
}
