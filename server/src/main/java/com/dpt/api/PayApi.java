/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.utils.MomoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dptuy
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class PayApi {

    @Autowired
    private MomoUtil momoUtils;

    @PostMapping(path = "/pay")
    public ResponseEntity<?> pay(@RequestParam BigDecimal price) {
        try {
            return new ResponseEntity<>(this.momoUtils.pay(price), HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PayApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
