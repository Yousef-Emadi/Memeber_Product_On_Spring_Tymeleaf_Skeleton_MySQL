package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig
{
    @Bean
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@gmail.com");
        message.setFrom("yousef.emadi.java.spring@gmail.com");
        message.setSubject("Multi-Ecoute Centre de Comminucataire");
        message.setText("FATAL - Application crash. Save your job !!");
        return message;
    }
}