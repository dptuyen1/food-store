/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Product;
import com.dpt.repository.ProductRepository;
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
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), Double.valueOf(fromPrice)));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), Double.valueOf(toPrice)));
            }

            String categoryId = params.get("categoryId");
            if (categoryId != null && !categoryId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("categoryId"), Integer.valueOf(categoryId)));
            }

            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }

//        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Product getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        return session.get(Product.class, id);
    }

    @Override
    public Product addOrUpdate(Product product) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (product.getId() == null) {
                session.save(product);
            } else {
                session.update(product);
            }
            return product;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int delete(Product product) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.delete(product);
            return product.getId();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
