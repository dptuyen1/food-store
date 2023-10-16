/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.dto;

import java.util.Map;
import lombok.Data;

/**
 *
 * @author dptuy
 */
@Data
public class ReceiptDTO {

    private int invoiceId;
    private Map<String, CartDTO> carts;
}
