/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.dpt.pojo.Promotion;
import com.dpt.repository.PromotionRepository;
import com.dpt.service.PromotionService;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public Promotion add(Promotion promotion) {
        promotion.setCreatedDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);

        promotion.setExpiredDate(calendar.getTime());

        return this.promotionRepository.addOrUpdate(promotion);
    }

}
