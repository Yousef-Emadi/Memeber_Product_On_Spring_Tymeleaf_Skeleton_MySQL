package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
@Table(name = "Members")
public class Member {
    // Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String username;
    public String password;
    public String name_first;
    public String name_last;
    public String phone;
    public String email;
    public double balance;
    public boolean admin;
    @OneToMany(fetch= FetchType.EAGER)
    public List<Service> services = new ArrayList<>();

    //Constructors:
    public Member(String username, String password, String name_first, String name_last, String phone, String email, double balance, boolean admin) {
        this.username = username;
        this.password = password;
        this.name_first = name_first;
        this.name_last = name_last;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
        this.admin = admin;
    }

}