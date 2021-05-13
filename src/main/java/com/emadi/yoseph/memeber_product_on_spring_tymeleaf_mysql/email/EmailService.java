package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Project: Member Management System
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on:  Depends on: Maven, Spring JPA, Spring boot, Hibernate, Thymeleaf, MySQL, Lombok, Skeleton
 */

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String toEmail, String subject, String message) {

        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("yousef.emadi@gmail.com");

        javaMailSender.send(mailMessage);
    }
}