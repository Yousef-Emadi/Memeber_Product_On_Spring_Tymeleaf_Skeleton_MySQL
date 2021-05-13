package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


/**
 * Project: Member Management System
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on:  Depends on: Maven, Spring JPA, Spring boot, Hibernate, Thymeleaf, MySQL, Lombok, Skeleton
 */


@Controller
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // get error status
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // TODO: log error details
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // display specific error page
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("localImageUrl", "images/404.jpg");

                return "error";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("localImageUrl", "images/sadness.png");

                return "error";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("localImageUrl", "images/403.png");

                return "error";
            }
        }

        // display generic error
        model.addAttribute("localImageUrl", "images/sadness.png");

        return "error";
    }
}
