/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dpt.pojo.Product;
import com.dpt.repository.ProductRepository;
import com.dpt.service.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.repository.getProducts(params);
    }

    @Override
    public Product getById(int id) {
        return this.repository.getById(id);
    }

    @Override
    public Product addOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setActive(true);
        }
        if (product.getFile() != null && !product.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(product.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                product.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.repository.addOrUpdate(product);
    }

    @Override
    public int delete(int id) {
        Product product = this.repository.getById(id);

        return this.repository.delete(product);
    }

}
