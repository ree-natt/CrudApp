package com.ree.service;

import com.ree.model.User;

import java.util.List;

/**
 * Created by ree-natt on 30.03.17.
 */
public interface UserService {
    public List<User> getAll();

    public List<User> getAllPage(int begin, int num);

    public List<User> getNotAll(String name);

    public User get(Integer id);

    public void add(User user);

    public void delete(Integer id);

    public void edit(User user);
}
