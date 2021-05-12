package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project: Member Database with Hibernate
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on: Maven, Spring, Hibernate, Thymeleaf, MySQL
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


