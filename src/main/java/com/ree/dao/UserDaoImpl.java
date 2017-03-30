package com.ree.dao;

import com.ree.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ree-natt on 30.03.17.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User ");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllPage(int begin, int num) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User ");
        List<User> users = query.list();
        List<User> users_end = new ArrayList<User>();

        if (users.size() <= begin) {
            return users;
        } else if (begin < users.size() && users.size() < (begin + num)) {
            for (int i = begin; i < users.size(); i++) {
                users_end.add(users.get(i));
            }
            return users_end;
        } else {
            for (int i = begin; i < begin + num; i++) {
                users_end.add(users.get(i));
            }
            return users_end;
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> getNotAll(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Query hql = session.createQuery("SELECT e FROM User e WHERE e.name = :name");
        hql.setParameter("name", name);
        return hql.list();
    }

    public User get(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        return user;
    }

    public void add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void delete(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
    }

    public void edit(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        User existingUser = (User) session.get(User.class, user.getId());
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setAdmin(user.isAdmin());
        existingUser.setDate(user.getDate());
        session.save(existingUser);
    }
}
