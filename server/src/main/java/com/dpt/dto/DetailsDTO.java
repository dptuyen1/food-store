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
public class DetailsDTO {

    private int id;
    private BigDecimal unitPrice;
    private int quantity;
    private ProductDTO productId;
}
