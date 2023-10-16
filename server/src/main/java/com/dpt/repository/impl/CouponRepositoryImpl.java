/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Coupon;
import com.dpt.pojo.Promotion;
import com.dpt.pojo.User;
import com.dpt.repository.CouponRepository;
import com.dpt.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dptuy
 */
@Repository
@Transactional
public class CouponRepositoryImpl implements CouponRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Coupon> getCoupons() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Coupon");

        return query.getResultList();
    }

    @Override
    public List<Coupon> getAvailbleCoupons() {
        Session session = this.factory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.getByUsername(authentication.getName());

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root promotion = criteriaQuery.from(Promotion.class);
        Root coupon = criteriaQuery.from(Coupon.class);

        criteriaQuery.select(coupon);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(promotion.get("userId"), user.getId()));
        predicates.add(criteriaBuilder.equal(promotion.get("couponId"), coupon.get("id")));
        predicates.add(criteriaBuilder.greaterThan(promotion.get("expiredDate"), new Date()));

        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Coupon getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        return session.get(Coupon.class, id);
    }

    @Override
    public Coupon addOrUpdate(Coupon coupon) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (coupon.getId() == null) {
                session.save(coupon);
            } else {
                session.update(coupon);
            }
            return coupon;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int delete(Coupon coupon) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.delete(coupon);
            return coupon.getId();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
