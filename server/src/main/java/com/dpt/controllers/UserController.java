/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.controllers;

import com.dpt.pojo.User;
import com.dpt.service.RoleService;
import com.dpt.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", this.userService.getUsers());
        return "user";
    }

    @GetMapping("/signup")
    public String view(Model model) {
        model.addAttribute("user", new User());
        return "user-details";
    }

    @PostMapping("/signup")
    public String add(Model model, @ModelAttribute(value = "user") @Valid User user, BindingResult result) {
        String msg;

        if (this.userService.getByUsername(user.getUsername()) != null) {
            msg = "Tài khoản đã được sử dụng...";
            model.addAttribute("msg", msg);
            return "user-details";
        }

        if (!user.getPassword().equals(user.getConfirm())) {
            msg = "Mật khẩu không khớp, vui lòng thử lại...";
            model.addAttribute("msg", msg);
            return "user-details";
        }

        if (!result.hasErrors()) {
            if (this.userService.addOrUpdate(user) != null) {
                return "redirect:/login";
            }
        }

        msg = "Có lỗi xảy ra, vui lòng thử lại";
        model.addAttribute("msg", msg);
        return "user-details";
    }

    @GetMapping("/users/{id}")
    public String details(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", this.userService.getById(id));
        model.addAttribute("roles", this.roleService.getRoles());
        return "user-details";
    }

    @PostMapping("/users/{id}")
    public String update(Model model, @ModelAttribute(value = "user") @Valid User user, BindingResult result) {
        if (!result.hasErrors()) {
            if (this.userService.addOrUpdate(user) != null) {
                return "redirect:/users";
            }
        }
        model.addAttribute("msg", "Có lỗi xảy ra, vui lòng thử lại..." + result);
        return "user-details";
    }
}
