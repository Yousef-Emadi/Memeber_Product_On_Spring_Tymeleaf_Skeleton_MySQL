package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping
public class MainController {

    @Autowired
    private IMemberRepository memberRepository;

    @GetMapping("/create")
    @ResponseBody
    public String addNewMember(@RequestParam String username, @RequestParam String password, @RequestParam String name_first, @RequestParam String name_last, @RequestParam String phone, @RequestParam String email) {
        Member newMember = new Member();
        newMember.setName_first(name_first);
        newMember.setName_last(name_last);
        newMember.setUsername(username);
        newMember.setPassword(password);
        newMember.setPhone(phone);
        newMember.setEmail(email);
        newMember.setBalance(100);
        memberRepository.save(newMember);
        return "Now You are a part of our members family";
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Member> listAllMembers(){
        return memberRepository.findAll();
    }
}
