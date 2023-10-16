/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.controllers;

import com.dpt.pojo.Coupon;
import com.dpt.service.CouponService;
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
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/coupons")
    public String index(Model model) {
        model.addAttribute("coupons", this.couponService.getCoupons());
        return "coupon";
    }

    @GetMapping("/coupons/add-or-update")
    public String view(Model model) {
        model.addAttribute("coupon", new Coupon());
        return "coupon-details";
    }

    @PostMapping("/coupons/add-or-update")
    public String add(Model model, @ModelAttribute(value = "coupon") @Valid Coupon coupon, BindingResult result) {
        if (!result.hasErrors()) {
            if (this.couponService.addOrUpdate(coupon) != null) {
                return "redirect:/coupons";
            }
        }
        model.addAttribute("msg", "Có lỗi xảy ra, vui lòng thử lại...");
        return "coupon-details";
    }

    @GetMapping("/coupons/{id}")
    public String details(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("coupon", this.couponService.getById(id));
        return "coupon-details";
    }
}
