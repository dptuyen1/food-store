/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.controllers;

import com.dpt.pojo.Product;
import com.dpt.service.CategoryService;
import com.dpt.service.ProductService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dptuy
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("products", this.productService.getProducts(params));
        return "product";
    }

    @GetMapping("/products/add-or-update")
    public String view(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", this.categoryService.getCategories());
        return "product-details";
    }

    @PostMapping("/products/add-or-update")
    public String add(Model model, @ModelAttribute(value = "product") @Valid Product product, BindingResult result) {
        if (!result.hasErrors()) {
            if (this.productService.addOrUpdate(product) != null) {
                return "redirect:/products";
            }
        }
        model.addAttribute("msg", "Có lỗi xảy ra, vui lòng thử lại..." + result.toString());
        return "product-details";
    }

    @GetMapping("/products/{id}")
    public String details(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("product", this.productService.getById(id));
        model.addAttribute("categories", this.categoryService.getCategories());
        return "product-details";
    }
}
