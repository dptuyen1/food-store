/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Category;
import com.dpt.repository.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Category> getCategories() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Category");

        return query.getResultList();
    }

    @Override
    public Category getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        return session.get(Category.class, id);
    }

    @Override
    public Category addOrUpdate(Category category) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (category.getId() == null) {
                session.save(category);
            } else {
                session.update(category);
            }
            return category;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int delete(Category category) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.delete(category);
            return category.getId();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
