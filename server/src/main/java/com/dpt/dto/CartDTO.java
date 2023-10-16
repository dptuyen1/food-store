/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author dptuy
 */
@Data
public class CartDTO {

    private int id;
    private String image;
    private String name;
    private BigDecimal price;
    private int quantity;
}
