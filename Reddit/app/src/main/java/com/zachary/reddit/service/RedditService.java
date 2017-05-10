package com.zachary.reddit.service;

import com.zachary.reddit.service.model.CreateTopicResponse;
import com.zachary.reddit.service.model.DownVoteResponse;
import com.zachary.reddit.service.model.GetTopicListResponse;
import com.zachary.reddit.service.model.UpVoteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 10/5/2017.
 */

public interface RedditService {
    @GET("/getTopicList.php")
    Call<GetTopicListResponse> getTopicList(@Query("limit") int limit, @Query("offset") int offset);

    @POST("/createTopic.php")
    Call<CreateTopicResponse> createTopic(@Body String topicTitle);

    @POST("/upVote.php")
    Call<UpVoteResponse> upVote(@Body int topicId);

    @POST("/downVote.php")
    Call<DownVoteResponse> downVote(@Body int topicId);
}
