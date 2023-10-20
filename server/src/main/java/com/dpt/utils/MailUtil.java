/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.utils;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author dptuy
 */
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage mailMessage;

    public void sendMail(String to, String subject, String text) {
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    public void sendMail(String to, BigDecimal price, String payment) {
        mailMessage.setTo(to);
        String text = String.format(mailMessage.getText(), price, payment);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }
}
