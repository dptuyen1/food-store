/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Review;
import com.dpt.repository.ReviewRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class ReviewRepositoryImpl implements ReviewRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Review> getReviews(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = criteriaBuilder.createQuery(Review.class);

        Root root = criteriaQuery.from(Review.class);
        criteriaQuery.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String userId = params.get("userId");
            if (userId != null && !userId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), Integer.valueOf(userId)));
            }

            String productId = params.get("productId");
            if (productId != null && !productId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("productId"), Integer.valueOf(productId)));
            }

            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Review getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        return session.get(Review.class, id);
    }

    @Override
    public Review addOrUpdate(Review review) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (review.getId() == null) {
                session.save(review);
            } else {
                session.update(review);
            }
            return review;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int delete(Review review) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.delete(review);
            return review.getId();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
