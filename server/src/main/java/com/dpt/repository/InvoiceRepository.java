/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository;

import com.dpt.pojo.Invoice;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dptuy
 */
public interface InvoiceRepository {

    List<Invoice> getInvoices(Map<String, String> params);

    List<Invoice> getByUser();

    Invoice getById(int id);

    Invoice addOrUpdate(Invoice invoice);

    int delete(Invoice invoice);
}
