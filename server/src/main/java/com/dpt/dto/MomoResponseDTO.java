/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.dto;

import lombok.Data;

@Data
public class MomoResponseDTO {

    private String partnerCode;
    private String requestId;
    private String deeplink;
    private String deeplinkMiniApp;
    private String orderId;
    private String amount;
    private String responseTime;
    private String message;
    private String resultCode;
    private String payUrl;
    private String qrCodeUrl;
}
