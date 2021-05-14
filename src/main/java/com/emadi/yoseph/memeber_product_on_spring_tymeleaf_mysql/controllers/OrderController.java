package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.controllers;

import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.dto.OrderRequest;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Member;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Service;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IMemberRepository;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderController {

    /**
     * <<<<<<<<<<<<<<<<<<<<<<<     Fields     >>>>>>>>>>>>>>>>>>>>>>>>>>
     */

    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private IServiceRepository serviceRepository;

    Member loggedMember; //This variable will be used further in booking services module when member start to buy services. initialize in login process



//    @GetMapping("/bookService")
    public Member bookOrder(@RequestParam OrderRequest request){
        return memberRepository.save(request.member);
    }

    @GetMapping("/findAllOrders")
    public Iterable<Member> findAllOrders(){
        return memberRepository.findAll();
    }

}
