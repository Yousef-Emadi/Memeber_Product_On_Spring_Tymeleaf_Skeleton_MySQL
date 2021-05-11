package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities;

import javax.persistence.*;
import java.util.List;


/**
 * Project: Member Database with Hibernate
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on: Maven, Spring, Hibernate, Thymeleaf, MySQL
 */

@Entity
@Table(name = "Members")
public class Member {
    // Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;
    public String username;
    public String password;
    public String name_first;
    public String name_last;
    public String phone;
    public String email;
    public double balance;
    public boolean isStaff;
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    public List<Order> orders;

    //Constructors:
    public Member() {
    }

    public Member(String username, String password, String name_first, String name_last, String phone, String email, double balance, boolean staff) {
        this.username = username;
        this.password = password;
        this.name_first = name_first;
        this.name_last = name_last;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
        this.isStaff = staff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName_first() {
        return name_first;
    }

    public void setName_first(String name_first) {
        this.name_first = name_first;
    }

    public String getName_last() {
        return name_last;
    }

    public void setName_last(String name_last) {
        this.name_last = name_last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}