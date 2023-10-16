/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service;

import com.dpt.pojo.Category;
import java.util.List;

/**
 *
 * @author dptuy
 */
public interface CategoryService {

    List<Category> getCategories();

    Category getById(int id);

    Category addOrUpdate(Category category);

    int delete(int id);
}
