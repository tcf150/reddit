package com.zachary.reddit.base;

import com.zachary.reddit.model.Status;

/**
 * Created by user on 10/5/2017.
 */

public class BaseResponse {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
