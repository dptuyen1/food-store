/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service;

import com.dpt.pojo.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dptuy
 */
public interface ProductService {

    List<Product> getProducts(Map<String, String> params);

    Product getById(int id);

    Product addOrUpdate(Product product);

    int delete(int id);
}
