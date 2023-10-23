/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository.impl;

import com.dpt.pojo.Details;
import com.dpt.pojo.Invoice;
import com.dpt.pojo.Product;
import com.dpt.repository.StatsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Integer> getYears() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("select distinct year(createdDate) from Invoice order by year(createdDate) desc");

        return query.getResultList();
    }

    @Override
    public List<Object> invoiceStats(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root invoice = criteriaQuery.from(Invoice.class);

        Expression<Integer> year = criteriaBuilder.function("YEAR", Integer.class, invoice.get("createdDate"));

        criteriaQuery.multiselect(year, criteriaBuilder.count(year), criteriaBuilder.sum(invoice.get("totalQuantity")), criteriaBuilder.sum(invoice.get("totalPrice")));

        List<Predicate> predicates = new ArrayList<>();

        if (params != null && !params.isEmpty()) {

            String month = params.get("month");
            if (month != null && !month.isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("MONTH", Integer.class, invoice.get("createdDate")), Integer.valueOf(month)));
            }

            String quarter = params.get("quarter");
            if (quarter != null && !quarter.isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("QUARTER", Integer.class, invoice.get("createdDate")), Integer.valueOf(quarter)));
            }
        }

        predicates.add(criteriaBuilder.equal(invoice.get("statusId"), 2));

        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        criteriaQuery.groupBy(year);
        criteriaQuery.orderBy(criteriaBuilder.desc(year));

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Object> productStats(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

//        Root product = criteriaQuery.from(Product.class);
//        Root invoice = criteriaQuery.from(Invoice.class);
        Root details = criteriaQuery.from(Details.class);
        Join<Details, Product> product = details.join("productId", JoinType.INNER);
        Join<Details, Invoice> invoice = details.join("invoiceId", JoinType.INNER);

        Expression<Long> sum = criteriaBuilder.sum(details.get("quantity"));

        criteriaQuery.multiselect(product.get("name"), sum);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null && !params.isEmpty()) {

            String year = params.get("year");
            if (year != null && !year.isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("YEAR", Integer.class, invoice.get("createdDate")),
                        Integer.valueOf(year)
                ));
            }

            String month = params.get("month");
            if (month != null && !month.isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("MONTH", Integer.class, invoice.get("createdDate")),
                        Integer.valueOf(month)
                ));
            }

            String quarter = params.get("quarter");
            if (quarter != null && !quarter.isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("QUARTER", Integer.class, invoice.get("createdDate")),
                        Integer.valueOf(quarter)
                ));
            }

//            criteriaQuery.where(criteriaBuilder.equal(invoice.get("id"), details.get("invoiceId")));
        }

        predicates.add(criteriaBuilder.equal(invoice.get("statusId"), 2));

        criteriaQuery.where(predicates.toArray(Predicate[]::new));

//        criteriaQuery.where(criteriaBuilder.equal(product.get("id"), details.get("productId")));
        criteriaQuery.groupBy(product.get("id"));
        criteriaQuery.orderBy(criteriaBuilder.asc(product.get("id")));

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
