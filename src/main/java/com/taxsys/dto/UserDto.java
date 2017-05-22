package com.taxsys.dto;

import com.taxsys.model.User;

/**
 * Created by 刘华鑫 on 2017/5/22.
 */
public class UserDto {
    // 用户是否创建成功
    private boolean isSuccess;

    // 错误信息
    private String message;

    // 用户信息
    private User user;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public UserDto(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public UserDto(boolean isSuccess, User user) {
        this.isSuccess = isSuccess;
        this.user = user;
    }
}
