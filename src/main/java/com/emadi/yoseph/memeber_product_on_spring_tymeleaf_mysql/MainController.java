package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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


    @GetMapping("/editMemberButton")
    public String editMemberButtonHandler(ModelMap model){
        model.addAttribute("myMembers", memberRepository.findAll());
        return "dropdown_list_of_members.html";
    }

    @GetMapping("/showEditingForm")
    public String showEditingForm (Member mmbr){

        return "member_editing_form.html";
    }


}
