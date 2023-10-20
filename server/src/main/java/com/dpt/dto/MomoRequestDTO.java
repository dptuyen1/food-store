/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.dto;

import lombok.Data;

@Data
public class MomoRequestDTO {

    private String partnerCode;
    private String orderId;
    private String storeId;
    private String redirectUrl;
    private String ipnUrl;
    private String requestId;
    private String requestType;
    private String amount;
    private String partnerUserId;
    private String orderType;
    private String transId;
    private String resultCode;
    private String message;
    private String payType;
    private String responseTime;
    private String orderInfo;
    private String extraData;
    private String signature;
}
