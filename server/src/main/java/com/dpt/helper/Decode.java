/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.helper;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

public class Decode {

    public static String encode(String key, String data) {
        try {
            byte[] code = key.getBytes();
            HmacUtils hm256 = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, code);
            String hmac = hm256.hmacHex(data);
            return hmac;
        } catch (Exception e) {
        }
        return null;
    }
}
