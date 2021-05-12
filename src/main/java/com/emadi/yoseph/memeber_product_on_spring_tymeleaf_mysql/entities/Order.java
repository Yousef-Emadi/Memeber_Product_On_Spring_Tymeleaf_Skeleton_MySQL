//package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Project: Member Database with Hibernate
// * Developed by: Yousef Emadi
// * Date: APR 2021
// * Depends on: Maven, Spring, Hibernate, Thymeleaf, MySQL
// */
//
//@Entity
//@Table(name = "Orders")
//public class Order {
//    //Fields:
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    public int id;
//    public LocalDateTime date;
//    public String comment;
//    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
//    public List<Service> services;
//
//
//    //Constructors:
//    public Order() {
//    }
//
//    public Order(String comment, List<Service> services) {
//        this.date = LocalDateTime.now();
//        this.comment = comment;
//        this.services = services;
//    }
//
//
//
//    //getters and setters:
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public List<Service> getServices() {
//        return services;
//    }
//
//    public void setServices(List<Service> services) {
//        this.services = services;
//    }
//}//end of class