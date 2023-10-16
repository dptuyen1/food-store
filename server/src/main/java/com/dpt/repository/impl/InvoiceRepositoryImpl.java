/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Invoice;
import com.dpt.pojo.User;
import com.dpt.repository.InvoiceRepository;
import com.dpt.repository.ProductRepository;
import com.dpt.repository.UserRepository;
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
public class InvoiceRepositoryImpl implements InvoiceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Invoice> getInvoices(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteriaQuery = criteriaBuilder.createQuery(Invoice.class);

        Root root = criteriaQuery.from(Invoice.class);
        criteriaQuery.select(root);

        if (params != null && !params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();

            String statusId = params.get("statusId");
            if (statusId != null && !statusId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("statusId"), Integer.valueOf(statusId)));
            }

            String shoppingId = params.get("shoppingId");
            if (shoppingId != null && !shoppingId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("shoppingId"), Integer.valueOf(shoppingId)));
            }

            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Invoice> getByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.getByUsername(authentication.getName());

        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Invoice where userId=:user");
        query.setParameter("user", user);

        return query.getResultList();
    }

    @Override
    public Invoice getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        return session.get(Invoice.class, id);
    }

    @Override
    public Invoice addOrUpdate(Invoice invoice) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (invoice.getId() == null) {
                session.save(invoice);
            } else {
                session.update(invoice);
            }
            return invoice;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int delete(Invoice invoice) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.delete(invoice);
            return invoice.getId();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
