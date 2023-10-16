/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.controllers;

import com.dpt.pojo.Role;
import com.dpt.service.RoleService;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public String index(Model model) {
        model.addAttribute("roles", this.roleService.getRoles());
        return "role";
    }

    @GetMapping("/roles/add-or-update")
    public String view(Model model) {
        model.addAttribute("role", new Role());
        return "role-details";
    }

    @PostMapping("/roles/add-or-update")
    public String add(Model model, @ModelAttribute(value = "role") @Valid Role role, BindingResult result) {
        if (!result.hasErrors()) {
            if (this.roleService.addOrUpdate(role) != null) {
                return "redirect:/roles";
            }
        }
        model.addAttribute("msg", "Có lỗi xảy ra, vui lòng thử lại...");
        return "role-details";
    }

    @GetMapping("/roles/{id}")
    public String details(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("role", this.roleService.getById(id));
        return "role-details";
    }
}
