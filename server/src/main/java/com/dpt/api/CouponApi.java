/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.dto.CouponDTO;
import com.dpt.service.CouponService;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dptuy
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class CouponApi {

    @Autowired
    private CouponService couponService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/coupons")
    public ResponseEntity<List<CouponDTO>> list() {
        List coupons = this.couponService.getAvailbleCoupons();
        List couponsDTO = Arrays.asList(modelMapper.map(coupons, CouponDTO[].class));

        return new ResponseEntity<>(couponsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/coupons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@PathVariable(value = "id") int id) {
        return this.couponService.delete(id);
    }
}
