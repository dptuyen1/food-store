/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.dpt.dto.CartDTO;
import com.dpt.pojo.Details;
import com.dpt.pojo.Invoice;
import com.dpt.pojo.Product;
import com.dpt.repository.DetailsRepository;
import com.dpt.repository.InvoiceRepository;
import com.dpt.repository.ProductRepository;
import com.dpt.service.DetailsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Details> getDetails(Map<String, String> params) {
        return this.detailsRepository.getDetails(params);
    }

    @Override
    public void add(Map<String, CartDTO> carts, int id) {
        Invoice invoice = this.invoiceRepository.getById(id);

        for (CartDTO cart : carts.values()) {
            Details details = new Details();

            details.setInvoiceId(invoice);

            Product product = this.productRepository.getById(cart.getId());
            details.setProductId(product);

            details.setQuantity(cart.getQuantity());
            details.setUnitPrice(cart.getPrice());

            this.detailsRepository.addOrUpdate(details);
        }
    }

}
