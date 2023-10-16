/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service;

import com.dpt.pojo.Coupon;
import java.util.List;

/**
 *
 * @author dptuy
 */
public interface CouponService {

    List<Coupon> getCoupons();

    List<Coupon> getAvailbleCoupons();

    Coupon getById(int id);

    Coupon addOrUpdate(Coupon coupon);

    int delete(int id);
}
