/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.dpt.pojo.Shopping;
import com.dpt.repository.ShoppingRepository;
import com.dpt.service.ShoppingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingRepository repository;

    @Override
    public List<Shopping> getShopping() {
        return this.repository.getShopping();
    }

    @Override
    public Shopping getById(int id) {
        return this.repository.getById(id);
    }

    @Override
    public Shopping addOrUpdate(Shopping shopping) {
        return this.repository.addOrUpdate(shopping);
    }

    @Override
    public int delete(int id) {
        Shopping shopping = this.repository.getById(id);

        return this.repository.delete(shopping);
    }

}
