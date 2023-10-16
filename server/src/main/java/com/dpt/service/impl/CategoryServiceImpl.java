/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.dpt.pojo.Category;
import com.dpt.repository.CategoryRepository;
import com.dpt.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getCategories() {
        return this.repository.getCategories();
    }

    @Override
    public Category getById(int id) {
        return this.repository.getById(id);
    }

    @Override
    public Category addOrUpdate(Category category) {
        return this.repository.addOrUpdate(category);
    }

    @Override
    public int delete(int id) {
        Category category = this.repository.getById(id);

        return this.repository.delete(category);
    }

}
