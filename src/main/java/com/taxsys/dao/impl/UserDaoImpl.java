package com.taxsys.dao.impl;

import com.taxsys.dao.UserDao;
import com.taxsys.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by 刘华鑫 on 2017/4/19.
 */
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 根据用户id查询用户
     */
    public User getUser(String id) {

        String hql = "from User u where u.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, id);
        return (User) query.uniqueResult();
    }

    public LinkedList<User> getUsers() {
        return null;
    }

    public boolean createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Integer result = (Integer)session.save(user);
        session.flush();
        if(result > 0){
            return true;
        }else{
            return false;
        }

    }

    public User getUserByNumber(String number) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u WHERE u.number = ?";
        Query query = session.createQuery(hql);
        query.setString(0, number);
        User user = (User)query.uniqueResult();
        return user;
    }

    public boolean updatePassword(String id, String md5NewPassword) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update User u set u.password = ? where u.id = ?";
        Query query = session.createQuery(hql);
        query.setString(0, md5NewPassword);
        query.setString(1, id);
        if(query.executeUpdate() == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean updateUserInfo(User user) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update User u set u.gender = ?, u.nickname = ?, u.avatar = ? where u.id = ?";
        Query query = session.createQuery(hql);
        query.setInteger(0, user.getGender());
        query.setString(1, user.getNickname());
        query.setString(2, user.getAvatar());
        query.setString(3, user.getId());
        if(query.executeUpdate() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getUserList(Integer offset, Integer limit) {
        String hql = "from User u order by u.createTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);
        return query.list();
    }
}
