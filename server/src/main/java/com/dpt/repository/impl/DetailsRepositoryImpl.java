/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Details;
import com.dpt.repository.DetailsRepository;
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
public class DetailsRepositoryImpl implements DetailsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Details> getDetails(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Details> criteriaQuery = criteriaBuilder.createQuery(Details.class);

        Root root = criteriaQuery.from(Details.class);
        criteriaQuery.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String invoiceId = params.get("invoiceId");
            if (invoiceId != null && !invoiceId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("invoiceId"), Integer.valueOf(invoiceId)));
            }

            String productId = params.get("productId");
            if (productId != null && !productId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("productId"), Integer.valueOf(productId)));
            }

            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Details addOrUpdate(Details details) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (details.getId() == null) {
                session.save(details);
            } else {
                session.update(details);
            }
            return details;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
