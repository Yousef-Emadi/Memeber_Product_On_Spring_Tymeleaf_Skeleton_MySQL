package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String userName;
    String password;
    String name_first;
    String name_last;
    String phone;
    String email;
    String dob;
    String address;
    double balance;

//    @OneToMany
//    List<Purchase> purchases;

    //Constructors:
    public Member() {
    }

    public Member(String userName, String password, String name_first, String name_last, String phone, String email, String dob, String address, double balance) {
        this.userName = userName;
        this.password = password;
        this.name_first = name_first;
        this.name_last = name_last;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    public List<Purchase> getPurchases() {
//        return purchases;
//    }
//
//    public void setPurchases(List<Purchase> purchases) {
//        this.purchases = purchases;
//    }

}