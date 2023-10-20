/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository;

import com.dpt.pojo.Invoice;
import java.util.List;

/**
 *
 * @author dptuy
 */
public interface InvoiceRepository {

    List<Invoice> getInvoicesOfCurrentDay();

    List<Invoice> getPendingInvocies();

    List<Invoice> getByUser();

    Invoice getById(int id);

    Invoice addOrUpdate(Invoice invoice);

    int delete(Invoice invoice);
}
