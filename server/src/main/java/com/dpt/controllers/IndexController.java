/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.controllers;

import com.dpt.pojo.Invoice;
import com.dpt.service.InvoiceService;
import com.dpt.service.StatsService;
import com.dpt.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dptuy
 */
@Controller
public class IndexController {

    @Autowired
    private StatsService statsService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceSerivce;

    @RequestMapping("/")
    public String index(@RequestParam Map<String, String> params, Model model) {
        List<Integer> years = this.statsService.getYears();
        List<Object> invoices = this.statsService.invoiceStats(params);
        List<Object> products = this.statsService.productStats(params);

        model.addAttribute("years", years);
        model.addAttribute("invoices", invoices);
        model.addAttribute("products", products);

        return "index";
    }

    @RequestMapping("/pos")
    public String pos(Model model, @RequestParam Map<String, String> params) {
        List<Invoice> invoices = this.invoiceSerivce.getInvoices(params);

        model.addAttribute("invoices", invoices);
        return "pos";
    }
}
