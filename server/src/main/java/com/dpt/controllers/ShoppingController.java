/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.controllers;

import com.dpt.pojo.Shopping;
import com.dpt.service.ShoppingService;
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
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/shopping")
    public String index(Model model) {
        model.addAttribute("shopping", this.shoppingService.getShopping());
        return "shopping";
    }

    @GetMapping("/shopping/add-or-update")
    public String view(Model model) {
        model.addAttribute("shopping", new Shopping());
        return "shopping-details";
    }

    @PostMapping("/shopping/add-or-update")
    public String add(Model model, @ModelAttribute(value = "shopping") @Valid Shopping shopping, BindingResult result) {
        if (!result.hasErrors()) {
            if (this.shoppingService.addOrUpdate(shopping) != null) {
                return "redirect:/shopping";
            }
        }
        model.addAttribute("msg", "Có lỗi xảy ra, vui lòng thử lại...");
        return "shopping-details";
    }

    @GetMapping("/shopping/{id}")
    public String details(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("shopping", this.shoppingService.getById(id));
        return "shopping-details";
    }
}
