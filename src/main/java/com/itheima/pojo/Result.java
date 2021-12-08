package com.itheima.pojo;

import lombok.Data;

@Data
public class Result {

    private boolean status;
    private String message;
    private String userAccount;

    public Result(boolean status, String message, String userAccount) {
        this.status = status;
        this.message = message;
        this.userAccount = userAccount;
    }
    public Result() {
    }
}
