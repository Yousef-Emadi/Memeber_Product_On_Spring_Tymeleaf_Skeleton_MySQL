package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Member;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IMemberRepository;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Project: Member Database with Hibernate
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on: Maven, Spring, Hibernate, Thymeleaf, MySQL
 */

@Controller
//@RequestMapping
public class MainController {

    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private IServiceRepository serviceRepository;


    /** Create new User Panel */

    @GetMapping("/create")
    @ResponseBody
    public String addNewMember(@RequestParam String username, @RequestParam String password, @RequestParam String name_first, @RequestParam String name_last, @RequestParam String phone, @RequestParam String email) {
        Member newMember = new Member();
        newMember.setName_first(name_first.toLowerCase());
        newMember.setName_last(name_last.toLowerCase());
        newMember.setUsername(username.toLowerCase());
        newMember.setPassword(password);
        newMember.setPhone(phone);
        newMember.setEmail(email.toLowerCase());
        newMember.setBalance(100);
        newMember.setStaff(false);
        memberRepository.save(newMember);
        return "Welcome dear " + name_first +"! \nFrom now on, You are a part of our family. &#129303";
    }




    /** Login Panel */

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password){
        for (Member member: memberRepository.findAll()
             ) {
            if ((member.username.compareToIgnoreCase(username) == 0) && (member.password.compareTo(password) == 0)){
                return "welcome Back " + member.name_first + " ! &#128522"  ;
            }
        }
        return "Username or password is incorrect! &#129300";
    }




    /** Edit Member Module */

    @GetMapping("/editMemberButton")
    public String editMemberButtonHandler(ModelMap model){
        model.addAttribute("myMembers", memberRepository.findAll());
        return "dropdown_list_of_members.html";
    }

    @GetMapping("/showEditingForm")
    public String showEditingForm (int id, ModelMap model){
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()){
            Member selectedMember = result.get();
            model.addAttribute("selectedMember", selectedMember);
            return "member_editing_form.html";
        }
            return "user_not_found.html";
    }

    @GetMapping("/creatMemberbyThymeleaf")
    @ResponseBody
    public String updateMemberHandler(@ModelAttribute("member") Member member){
        memberRepository.save(member);
        return "Modification has been done successfully. &#128522 ";
    }






    /** Delete Member  Module */

    @PostMapping("/deleteMemberButton")
    public String deleteMemberButtonHandler(ModelMap model){
        model.addAttribute("myMembers", memberRepository.findAll());
        return "dropdown_list_of_members_to_delete.html";
    }

    @GetMapping("/deleteMemeberRecord")
    @ResponseBody
    public String showEditingForm (int id){
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()){
            Member selectedMember = result.get();
            memberRepository.delete(selectedMember);
            return "Member deleted Successfuly";
//            return "temp";

        }
        return "user_not_found.html";
    }



}
