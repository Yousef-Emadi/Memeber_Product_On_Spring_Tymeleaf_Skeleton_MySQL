package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping
public class MainController {

    @Autowired
    private IMemberRepository memberRepository;

    @PostMapping("/add")
    public @ResponseBody String addNewMember(@RequestParam String username, @RequestParam String password) {
        Member newMember = new Member();
        newMember.setUserName(username);
        newMember.setPassword(password);
        memberRepository.save(newMember);
        return "Now he/she is a part of our members family";
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Member> listAllMembers(){
        return memberRepository.findAll();
    }
}
