package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import javax.persistence.*;

/**
 * Project: Member Database with Hibernate
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on: Maven, Spring, Hibernate, Thymeleaf, MySQL
 */

@Entity
@Table(name = "Services")
public class Service {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String description;
    String category;
    double price;
}
