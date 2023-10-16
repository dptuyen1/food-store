/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.dto.PromotionDTO;
import com.dpt.pojo.Promotion;
import com.dpt.service.PromotionService;
import com.dpt.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dptuy
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class PromotionApi {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/promotions")
    public ResponseEntity<PromotionDTO> add(@RequestBody Promotion promotion) {
        Promotion p = this.promotionService.add(promotion);
        PromotionDTO promotionDTO = modelMapper.map(p, PromotionDTO.class);

        return new ResponseEntity<>(promotionDTO, HttpStatus.CREATED);
    }
}
