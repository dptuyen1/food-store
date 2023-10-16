/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Promotion;
import com.dpt.repository.PromotionRepository;
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
public class PromotionRepositoryImpl implements PromotionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Promotion addOrUpdate(Promotion promotion) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (promotion.getId() == null) {
                session.save(promotion);
            } else {
                session.update(promotion);
            }
            return promotion;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
