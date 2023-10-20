/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service;

import com.dpt.pojo.Invoice;
import java.util.List;

/**
 *
 * @author dptuy
 */
public interface InvoiceService {

    List<Invoice> getInvoicesOfCurrentDay();

    List<Invoice> getPendingInvocies();

    List<Invoice> getByUser();

    Invoice getById(int id);

//    Client
    Invoice add(Invoice invoice);

    Invoice update(int id, Invoice invoice);

    int delete(int id);
}
