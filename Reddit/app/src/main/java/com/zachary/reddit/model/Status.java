package com.zachary.reddit.model;

import com.zachary.reddit.base.BaseModel;
import com.zachary.reddit.service.StatusCode;

/**
 * Created by user on 10/5/2017.
 */

public class Status extends BaseModel {
    private int statusCode;
    private String statusDesc;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public boolean isSuccess(){
        return  (statusCode == StatusCode.SUCCESS);
    }
}
