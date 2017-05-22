package com.taxsys.dao;

import com.taxsys.model.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 刘华鑫 on 2017/4/19.
 */
public interface UserDao {

    User getUser(String id);

    LinkedList<User> getUsers();

    /**
     * 创建用户
     * @param user
     * @return
     */
    boolean createUser(User user);

    /**
     * 通过用户账户得到用户信息
     * @param number
     * @return
     */
    User getUserByNumber(String number);

    /**
     * 更新密码
     * @param id 用户Id
     * @param md5NewPassword 新密码
     * @return
     */
    boolean updatePassword(String id, String md5NewPassword);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);

    /**
     * 分页获取用户id
     * @param offset 偏移量
     * @param limit 一次获取的数据
     * @return
     */
    List getUserList(Integer offset, Integer limit);
}
