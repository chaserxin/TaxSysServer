package com.taxsys.service.impl;

import com.taxsys.dao.impl.UserDaoImpl;
import com.taxsys.dto.UserDto;
import com.taxsys.model.User;
import com.taxsys.service.UserService;
import com.taxsys.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 刘华鑫 on 2017/4/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDaoImpl userDao;

    public User getUser(String id) {
        return userDao.getUser(id);
    }

    public LinkedList<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    public UserDto register(User user) {
        UserDto createUserDto;

        // 密码使用MD5加密
        String md5Password = MD5Util.getMD5(user.getPassword());
        user.setPassword(md5Password);

        // 检查用户账户查重
        if (userDao.getUserByNumber(user.getNumber()) != null) {
            createUserDto = new UserDto(false, "用户已存在");
            return createUserDto;
        }

        // 插入数据库失败
        if (!userDao.createUser(user)) {
            createUserDto = new UserDto(false, "创建失败");
            return createUserDto;
        }

        // 成功创建
        createUserDto = new UserDto(true, user);
        return createUserDto;
    }

    @Transactional
    public UserDto login(String number, String password) {
        UserDto createUserDto;

        // 密码使用MD5加密
        String md5Password = MD5Util.getMD5(password);

        User loginUser;
        // 检查用户是否已经注册过
        loginUser = userDao.getUserByNumber(number);
        if (loginUser == null) {
            createUserDto = new UserDto(false, "用户未注册");
            return createUserDto;
        }

        // 检查密码是否正确
        if (!md5Password.equals(loginUser.getPassword())) {
            createUserDto = new UserDto(false, "密码不正确");
            return createUserDto;
        }

        // 成功登录返回用户信息
        createUserDto = new UserDto(true, loginUser);
        return createUserDto;
    }

    @Transactional
    public UserDto modifyPassword(String id, String oldPassword, String newPassword) {
        UserDto createUserDto;

        // 密码使用MD5加密
        String md5OldPassword = MD5Util.getMD5(oldPassword);
        String md5NewPassword = MD5Util.getMD5(newPassword);


        // 检查原始密码是否正确
        User user = userDao.getUser(id);
        if (user == null) {
            createUserDto = new UserDto(false, "用户不存在");
            return createUserDto;
        }
        // 检查原始密码是否正确
        if (!md5OldPassword.equals(user.getPassword())) {
            createUserDto = new UserDto(false, "密码不正确");
            return createUserDto;
        }
        // 修改成功
        user.setPassword(md5NewPassword);
        if(!userDao.updatePassword(id, md5NewPassword)) {
            createUserDto = new UserDto(false, "更新出错");
            return createUserDto;
        }
        createUserDto = new UserDto(true, user);
        return createUserDto;
    }

    public UserDto modifyUserInfo(User user) {
        User oldUser = userDao.getUser(user.getId());
        if(oldUser == null) {
            return new UserDto(false, "用户不存在");
        }
        if(oldUser.getGender() != user.getGender()) {
            oldUser.setGender(user.getGender());
        }
        if(oldUser.getNickname() != null && !oldUser.getNickname().equals(user.getNickname())) {
            oldUser.setNickname(user.getNickname());
        }
        if(oldUser.getAvatar() != null && !oldUser.getAvatar().equals(user.getAvatar())) {
            oldUser.setAvatar(user.getAvatar());
        }
        if(!userDao.updateUserInfo(oldUser)) {
            return new UserDto(false, "更新用户信息失败");
        }
        return new UserDto(true, oldUser);
    }

    public List<String> getUserList(Integer offset, Integer limit) {
        return userDao.getUserList(offset, limit);
    }
}
