/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.configs;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author dptuy
 */
@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));
        mailSender.setDefaultEncoding(env.getProperty("mail.encoding"));
        mailSender.setJavaMailProperties(mailProperties());

        return mailSender;
    }

    private Properties mailProperties() {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.debug", "true");

        return props;
    }

    @Bean
    public SimpleMailMessage mailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(env.getProperty("mail.username"));
        message.setSubject(env.getProperty("mail.subject"));
        message.setText(env.getProperty("mail.message"));

        return message;
    }
}
