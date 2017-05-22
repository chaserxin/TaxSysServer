package com.taxsys.service;

import com.taxsys.dto.UserDto;
import com.taxsys.model.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 刘华鑫 on 2017/4/19.
 */
public interface UserService {

    User getUser(String id);
    LinkedList getUsers();

    /**
     * 注册接口
     * @param u
     * @return
     */
    UserDto register(User u);

    /**
     * 登录接口
     * @param number 用户账号
     * @param password 用户密码
     * @return
     */
    UserDto login(String number, String password);

    /**
     * 修改密码
     * @param id 用户id
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     * @return
     */
    UserDto modifyPassword(String id, String oldPassword, String newPassword);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    UserDto modifyUserInfo(User user);

    /**
     * 分页获取用户id
     * @param offset 偏移量
     * @param limit 一次获取的数据
     * @return
     */
    List<String> getUserList(Integer offset, Integer limit);
}
