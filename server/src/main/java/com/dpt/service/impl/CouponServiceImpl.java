/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.dpt.pojo.Coupon;
import com.dpt.repository.CouponRepository;
import com.dpt.service.CouponService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository repository;

    @Override
    public List<Coupon> getCoupons() {
        return this.repository.getCoupons();
    }

    @Override
    public List<Coupon> getAvailbleCoupons() {
        return this.repository.getAvailbleCoupons();
    }

    @Override
    public Coupon getById(int id) {
        return this.repository.getById(id);
    }

    @Override
    public Coupon addOrUpdate(Coupon coupon) {
        return this.repository.addOrUpdate(coupon);
    }

    @Override
    public int delete(int id) {
        Coupon coupon = this.repository.getById(id);

        return this.repository.delete(coupon);
    }
}
