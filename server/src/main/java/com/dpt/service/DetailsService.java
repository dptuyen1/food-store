/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service;

import com.dpt.dto.CartDTO;
import com.dpt.pojo.Details;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dptuy
 */
public interface DetailsService {

    List<Details> getDetails(Map<String, String> params);

//    Client
    void add(Map<String, CartDTO> carts, int id);

}
