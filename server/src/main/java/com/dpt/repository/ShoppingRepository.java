/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository;

import com.dpt.pojo.Shopping;
import java.util.List;

/**
 *
 * @author dptuy
 */
public interface ShoppingRepository {

    List<Shopping> getShopping();

    Shopping getById(int id);

    Shopping addOrUpdate(Shopping shopping);

    int delete(Shopping shopping);
}
