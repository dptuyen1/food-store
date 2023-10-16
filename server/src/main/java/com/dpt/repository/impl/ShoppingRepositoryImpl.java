/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Shopping;
import com.dpt.repository.ShoppingRepository;
import java.util.List;
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
public class ShoppingRepositoryImpl implements ShoppingRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Shopping> getShopping() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Shopping");

        return query.getResultList();
    }

    @Override
    public Shopping getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        return session.get(Shopping.class, id);
    }

    @Override
    public Shopping addOrUpdate(Shopping shopping) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (shopping.getId() == null) {
                session.save(shopping);
            } else {
                session.update(shopping);
            }
            return shopping;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int delete(Shopping shopping) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.delete(shopping);
            return shopping.getId();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
