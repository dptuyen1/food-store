/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.utils;

import com.dpt.dto.MomoRequestDTO;
import com.dpt.dto.MomoResponseDTO;
import com.dpt.helper.Constant;
import com.dpt.helper.Decode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dptuy
 */
public class MomoUtil {

    public MomoResponseDTO pay(BigDecimal price) throws JsonProcessingException {
        String requestId = UUID.randomUUID().toString();
        String orderId = UUID.randomUUID().toString();
        String orderInfo = "Thanh toán hóa đơn";

        ObjectMapper mapper = new ObjectMapper();
        MomoRequestDTO momoRequest = new MomoRequestDTO();

        momoRequest.setPartnerCode(Constant.partnerCode);
        momoRequest.setOrderId(orderId);
        momoRequest.setStoreId(orderId);
        momoRequest.setRedirectUrl(Constant.redirectUrl);
        momoRequest.setIpnUrl(Constant.ipnUrl);
        momoRequest.setAmount(String.valueOf(price));
        momoRequest.setOrderInfo(orderInfo);
        momoRequest.setRequestId(requestId);
        momoRequest.setOrderType(Constant.orderType);
        momoRequest.setRequestType(Constant.requestType);
        momoRequest.setTransId("1");
        momoRequest.setResultCode("200");
        momoRequest.setMessage("");
        momoRequest.setPayType(Constant.payType);
        momoRequest.setResponseTime("300000");
        momoRequest.setExtraData("");

        String decode = "accessKey=" + Constant.accessKey + "&amount=" + price.toString()
                + "&extraData=" + momoRequest.getExtraData() + "&ipnUrl=" + Constant.ipnUrl + "&orderId=" + orderId
                + "&orderInfo=" + orderInfo + "&partnerCode=" + Constant.partnerCode + "&redirectUrl=" + Constant.redirectUrl
                + "&requestId=" + requestId + "&requestType=" + Constant.requestType;

        String signature = Decode.encode(Constant.serectKey, decode);
        momoRequest.setSignature(signature);

        String json = mapper.writeValueAsString(momoRequest);
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(Constant.endpoint))
                    .POST(HttpRequest.BodyPublishers.ofString(json)).headers("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            MomoResponseDTO res = mapper.readValue(response.body(), MomoResponseDTO.class);

            return res;
        } catch (InterruptedException | URISyntaxException e) {
        } catch (IOException ex) {
            Logger.getLogger(MomoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
