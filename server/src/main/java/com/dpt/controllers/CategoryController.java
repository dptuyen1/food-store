/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.controllers;

import com.dpt.pojo.Category;
import com.dpt.service.CategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author dptuy
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String index(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
        return "category";
    }

    @GetMapping("/categories/add-or-update")
    public String view(Model model) {
        model.addAttribute("category", new Category());
        return "category-details";
    }

    @PostMapping("/categories/add-or-update")
    public String add(Model model, @ModelAttribute(value = "category") @Valid Category category, BindingResult result) {
        if (!result.hasErrors()) {
            if (this.categoryService.addOrUpdate(category) != null) {
                return "redirect:/categories";
            }
        }
        model.addAttribute("msg", "Có lỗi xảy ra, vui lòng thử lại...");
        return "category-details";
    }

    @GetMapping("/categories/{id}")
    public String details(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("category", this.categoryService.getById(id));
        return "category-details";
    }
}
