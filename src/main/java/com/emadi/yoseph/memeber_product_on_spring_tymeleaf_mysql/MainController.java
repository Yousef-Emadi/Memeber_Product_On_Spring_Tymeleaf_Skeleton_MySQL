package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.email.EmailService;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Member;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Order;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Service;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IMemberRepository;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IOrderRepository;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * <<<<<<<<<<<<<<<<<<<<<<<     Fields     >>>>>>>>>>>>>>>>>>>>>>>>>>
     */

    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private IServiceRepository serviceRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    Member loggedMember; //This variable will be used further in booking services module when member start to buy services. initialize in login process




    /** <<<<<<<<<<<<<<<<<<<<<<<<      Login Panel     >>>>>>>>>>>>>>>>>>>>>>>>>>*/



    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, ModelMap model) {
        for (Member member : memberRepository.findAll()
        ) {
            if ((member.username.compareToIgnoreCase(username) == 0) && (member.password.compareTo(password) == 0)) {
                loggedMember = member;
                model.addAttribute("loggedMember", loggedMember);
                return "member_landing_page.html";
            }
        }
        return "username_password_incorrect.html";
    }


    @GetMapping("/backToMemberAreaButton")
    public String backToMemberAreaButton(ModelMap model){
        model.addAttribute("loggedMember", loggedMember);
        return "member_landing_page.html";
    }


/** <<<<<<<<<<<<<<<<<<<<<<<     Member Management     >>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /**
     * Create new Member Panel
     */

    @GetMapping("/createMemberButton")
    public String createMemberButtonHandler() {
        return "redirect: create_member.html";
    }

    @GetMapping("/createMember")
    public String addNewMember(@RequestParam String username, @RequestParam String password, @RequestParam String name_first, @RequestParam String name_last, @RequestParam String phone, @RequestParam String email, ModelMap model) {
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
        loggedMember = newMember;
        model.addAttribute("loggedMember", loggedMember);
        return "member_landing_page.html";
    }



    /**
     * Edit Member Module
     */

    @GetMapping("/editMemberButton")
    public String editMemberButtonHandler(ModelMap model) {
        model.addAttribute("myMembers", memberRepository.findAll());
        return "dropdown_list_of_members.html";
    }

    @GetMapping("/showEditingForm")
    public String showEditingForm(int id, ModelMap model) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            Member selectedMember = result.get();
            model.addAttribute("selectedMember", selectedMember);
            return "member_editing_form.html";
        }
        return "requested_object_not_found.html";
    }

    @GetMapping("/creatMemberbyThymeleaf")
    public String updateMemberHandler(@ModelAttribute("member") Member member) {
        memberRepository.save(member);
        return "redirect: index.html";
    }


    /**
     * Delete Member  Module
     */

    @PostMapping("/deleteMemberButton")
    public String deleteMemberButtonHandler(ModelMap model) {
        model.addAttribute("myMembers", memberRepository.findAll());
        return "dropdown_list_of_members_to_delete.html";
    }

    @GetMapping("/deleteMemeberRecord")
    @ResponseBody
    public String showEditingForm(int id) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            Member selectedMember = result.get();
            memberRepository.delete(selectedMember);
            return "Member deleted Successfuly";
        }
        return "requested_object_not_found.html";
    }

/** <<<<<<<<<<<<<<<<<<<<<<<     Service Management     >>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /**
     * Create a new Service
     */

    @PostMapping("/addServiceButton")
    public String addServiceButtonHandler() {
        return "redirect: create_service.html";
    }

    @PostMapping("/createService")
    public String createServiceHandler(@RequestParam String name, @RequestParam String category, @RequestParam String description, @RequestParam double price, ModelMap model) {

        Service newService = new Service();
        newService.setName(name.toLowerCase());
        newService.setCategory(category.toLowerCase());
        newService.setDescription(description.toLowerCase().trim());
        newService.setPrice(price);
        serviceRepository.save(newService);
        model.addAttribute("newService", newService);
        return "service_created_result.html";
    }


    /**
     * Edit a Service
     */

    @GetMapping("/editServiceButton")
    public String editServiceButtonHandler(ModelMap model) {
        model.addAttribute("myServices", serviceRepository.findAll());
        return "table_list_of_services.html";
    }

    @GetMapping("/showServiceEditForm")
    public String showServiceEditForm(int id, ModelMap model) {
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {
            Service selectedService = result.get();
            model.addAttribute("selectedService", selectedService);
            return "service_editing_form.html";
        }
        return "requested_object_not_found.html";
    }

    @GetMapping("/creatServiceByThymeleaf")
    @ResponseBody
    public String updateServiceHandler(@ModelAttribute("selectedService") Service service) {
        serviceRepository.save(service);
        return "Service modification has been done successfully. &#128522 ";
    }

    @GetMapping("/backToHomepageButton")
    public String backToHomepageButtonHandler() {
        return "redirect: index.html";
    }

    /**
     * Delete a Service
     */

    @GetMapping("/deleteServiceButton")
    @ResponseBody
    public String deleteServiceButtoHandler(int id) {
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {
            Service selectedService = result.get();
            serviceRepository.delete(selectedService);
            return "Service deleted Successfuly";
        }
        return "requested_object_not_found.html";
    }




    /** <<<<<<<<<<<<<<<<<<<<<<<     Edit your profile     >>>>>>>>>>>>>>>>>>>>>>>>>> */


    @GetMapping("/editProfile")
    public String editProfileButtonHandler(ModelMap model) {
            model.addAttribute("selectedMember", loggedMember);
            return "member_editing_form.html";
        }


    /** <<<<<<<<<<<<<<<<<<<<<<<     Book Services     >>>>>>>>>>>>>>>>>>>>>>>>>> */

    @GetMapping("/listServiceButton")
    public String listServiceButton(ModelMap model) {
        model.addAttribute("myServices", serviceRepository.findAll());

        if (loggedMember != null) {
        }else{
            return "you_need_to_loggin_before_booking_any_service.html";
        }
        return "button_list_of_services.html";
    }

    @GetMapping("/showServiceDetailToBuy")
    public String showServiceDetailToBuyHandler(int id, ModelMap model){
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {
            Service selectedService = result.get();
            model.addAttribute("selectedService", selectedService);
            return "service_detail_to_buy.html";
        }
        return "requested_object_not_found.html";
    }

    @GetMapping("/bookService")
    public String bookService(int id, ModelMap model1, ModelMap model2) {
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {

            Service selectedService = result.get();

            List<Service> memberCart = new ArrayList<>();

            Order newOrder = new Order("no comment", memberCart);

            loggedMember.orders = new ArrayList<>();

            newOrder.services.add(selectedService);
            loggedMember.orders.add(newOrder);
            for (Service service: newOrder.services
                 ) {
                loggedMember.balance -= service.getPrice();
            }
            orderRepository.save(newOrder);
            memberRepository.save(loggedMember);
            model1.addAttribute("loggedMember", loggedMember);
            model2.addAttribute("selectedService", selectedService);
            return "booking_result";
        }
        return "requested_object_not_found.html";
    }


    /** <<<<<<<<<<<<<<<<<<<<<<<     Email Services     >>>>>>>>>>>>>>>>>>>>>>>>>> */

    @GetMapping("/sendEmailButton")
    @ResponseBody
    public String sendEmailButton(){
        emailService.sendMail("usef.emadi@gmail.com", "Test Email", "Test message from Member application");
        return "Email has been sent!";
    }

}
