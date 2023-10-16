/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service;

import com.dpt.pojo.Invoice;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dptuy
 */
public interface InvoiceService {

    List<Invoice> getInvoices(Map<String, String> params);

    List<Invoice> getByUser();

    Invoice getById(int id);

//    Client
    Invoice add(Invoice invoice);

    Invoice update(int id, Invoice invoice);

    int delete(int id);
}
