package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Member Database with Hibernate
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on: Maven, Spring, Hibernate, Thymeleaf, MySQL
 */

@Entity
@Table(name = "Orders")
public class Order {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;
    public LocalDateTime date;
    public String comment;
    @ManyToAny(metaColumn = null, fetch = FetchType.EAGER)
    public Service service;


    //Constructors:
    public Order() {
    }

    public Order(String comment, Service service) {
        this.date = LocalDateTime.now();
        this.comment = comment;
        this.service = service;
    }



    //getters and setters:


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}//end of class