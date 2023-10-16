/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.User;
import com.dpt.repository.UserRepository;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dptuy
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<User> getUsers() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM User");

        return query.getResultList();
    }

    @Override
    public User getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        return session.get(User.class, id);
    }

    @Override
    public User getByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM User where username=:username");
        query.setParameter("username", username);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User addOrUpdate(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (user.getId() == null) {
                session.save(user);
            } else {
                session.update(user);
            }
            return user;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int delete(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.delete(user);
            return user.getId();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
