package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project: Member Management System
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on:  Depends on: Maven, Spring JPA, Spring boot, Hibernate, Thymeleaf, MySQL, Lombok, Skeleton
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Services")
public class Service {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String name;
    public String category;
    public String description;
    public double price;

}


