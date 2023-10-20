/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.dpt.pojo.Invoice;
import com.dpt.pojo.User;
import com.dpt.repository.InvoiceRepository;
import com.dpt.repository.UserRepository;
import com.dpt.service.InvoiceService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Invoice> getInvoicesOfCurrentDay() {
        return this.invoiceRepository.getInvoicesOfCurrentDay();
    }

    @Override
    public List<Invoice> getPendingInvocies() {
        return this.invoiceRepository.getPendingInvocies();
    }

    @Override
    public List<Invoice> getByUser() {
        return this.invoiceRepository.getByUser();
    }

    @Override
    public Invoice getById(int id) {
        return this.invoiceRepository.getById(id);
    }

    @Override
    public Invoice add(Invoice invoice) {
        if (invoice.getUserId() == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = this.userRepository.getByUsername(authentication.getName());

            invoice.setUserId(user);
        }

        invoice.setCreatedDate(new Date());

        return this.invoiceRepository.addOrUpdate(invoice);
    }

    @Override
    public Invoice update(int id, Invoice invoice) {
        Invoice i = this.invoiceRepository.getById(id);

        i.setStatusId(invoice.getStatusId());
        i.setUserId(invoice.getUserId());

        return this.invoiceRepository.addOrUpdate(i);
    }

    @Override
    public int delete(int id) {
        Invoice invoice = this.invoiceRepository.getById(id);

        return this.invoiceRepository.delete(invoice);
    }
}
