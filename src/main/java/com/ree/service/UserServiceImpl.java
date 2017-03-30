package com.ree.service;

import com.ree.dao.UserDao;
import com.ree.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ree-natt on 30.03.17.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public List<User> getAll() {
        return this.userDao.getAll();
    }

    @Transactional
    public List<User> getAllPage(int begin, int num) {
        return this.userDao.getAllPage(begin, num);
    }

    @Transactional
    public List<User> getNotAll(String name) {
        return this.userDao.getNotAll(name);
    }

    @Transactional
    public User get(Integer id) {
        return this.userDao.get(id);
    }

    @Transactional
    public void add(User user) {
        this.userDao.add(user);
    }

    @Transactional
    public void delete(Integer id) {
        this.userDao.delete(id);
    }

    @Transactional
    public void edit(User user) {
        this.userDao.edit(user);
    }
}
