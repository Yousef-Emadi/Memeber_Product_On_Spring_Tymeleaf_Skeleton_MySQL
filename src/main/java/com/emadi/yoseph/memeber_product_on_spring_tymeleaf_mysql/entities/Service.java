package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities;

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
    String category;
    String description;
    double price;

    //Constructors:
    public Service() {
    }

    //getters and setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


