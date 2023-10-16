/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author dptuy
 */
@Data
public class InvoiceDTO {

    private int id;
    private BigDecimal totalPrice;
    private int totalQuantity;
    private BigDecimal discountPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Date createdDate;
}
