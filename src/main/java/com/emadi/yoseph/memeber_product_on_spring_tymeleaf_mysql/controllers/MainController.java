package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.controllers;

import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.email.EmailService;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Member;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Service;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IMemberRepository;
import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories.IServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Project: Member Management System
 * Developed by: Yousef Emadi
 * Date: APR 2021
 *  Depends on: Maven, Spring JPA, Spring boot, Hibernate, Thymeleaf, MySQL, Lombok, Skeleton
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

//    @Autowired
//    private IOrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    Member loggedMember; //This variable will be used further in booking services module when member start to buy services. initialize in login process


    /**
     * <<<<<<<<<<<<<<<<<<<<<<<<      Login Panel     >>>>>>>>>>>>>>>>>>>>>>>>>>
     */


    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, ModelMap model) {
        for (Member member : memberRepository.findAll()
        ) {
            if ((member.username.compareToIgnoreCase(username) == 0) && (member.password.compareTo(password) == 0) && member.admin) {
                loggedMember = member;
                model.addAttribute("loggedMember", loggedMember);
                return "landing_page_admin.html";
            }
            if ((member.username.compareToIgnoreCase(username) == 0) && (member.password.compareTo(password) == 0)) {
                loggedMember = member;
                model.addAttribute("loggedMember", loggedMember);
                return "landing_page_member.html";
            }
            if ((member.username.compareToIgnoreCase(username) == 0)) {
                loggedMember = member;
                model.addAttribute("loggedMember", loggedMember);
                return "wrong_password.html";
            }
        }
        return "wrong_username.html";
    }


    @GetMapping("/backToUserControlPanel")
    public String backToMemberAreaButton(ModelMap model) {
        model.addAttribute("loggedMember", loggedMember);
        if (loggedMember.admin) return "landing_page_admin.html";
        return "landing_page_member.html";
    }

    @GetMapping("/logout")
    public String logoutHandler() {
        loggedMember = null;
        return "redirect: index.html";
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
        newMember.setAdmin(false);
        memberRepository.save(newMember);
        loggedMember = newMember;
        model.addAttribute("loggedMember", loggedMember);
        return "landing_page_member.html";
    }


    /**
     * Edit Member Module
     */

    @GetMapping("/editMemberButton")
    public String editMemberButtonHandler(ModelMap model) {
        model.addAttribute("myMembers", memberRepository.findAll());
        return "list_table_members.html";
    }

    @GetMapping("/showEditingForm")
    public String showEditingForm(int id, ModelMap model) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            Member selectedMember = result.get();
            model.addAttribute("selectedMember", selectedMember);
            return "editing_form_member.html";
        }
        return "error_requested_object_not_found.html";
    }

    @GetMapping("/creatMemberbyThymeleaf")
    public String updateMemberHandler(@ModelAttribute("member") Member member) {
        memberRepository.save(member);
        return "result_member_modified.html";
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
    public String showEditingForm(int id, Model model) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            Member selectedMember = result.get();
            memberRepository.delete(selectedMember);
            model.addAttribute("loggedMember", loggedMember);
            if (loggedMember.admin) return "landing_page_admin.html";
            return "result_member_deleted.html";
        }
        return "error_requested_object_not_found.html";
    }

    /**
     * <<<<<<<<<<<<<<<<<<<<<<<     Service Management     >>>>>>>>>>>>>>>>>>>>>>>>>>
     */


    @GetMapping("/listBookedServices")
    public String listBookedServicesHandler(ModelMap model) {
        model.addAttribute("myMembers", memberRepository.findAll());
        return "list_table_booked_services.html";
    }

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
        return "result_service_created.html";
    }


    /**
     * Edit a Service
     */

    @GetMapping("/editServiceButton")
    public String editServiceButtonHandler(ModelMap model) {
        model.addAttribute("myServices", serviceRepository.findAll());
        return "list_table_services.html";
    }

    @GetMapping("/showServiceEditForm")
    public String showServiceEditForm(int id, ModelMap model) {
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {
            Service selectedService = result.get();
            model.addAttribute("selectedService", selectedService);
            return "editing_form_service.html";
        }
        return "error_requested_object_not_found.html";
    }

    @GetMapping("/creatServiceByThymeleaf")
    public String updateServiceHandler(@ModelAttribute("selectedService") Service service) {
        serviceRepository.save(service);
        return "result_service_modified.html";
    }

    @GetMapping("/backToHomepageButton")
    public String backToHomepageButtonHandler() {
        return "redirect: index.html";
    }

    /**
     * Delete a Service
     */

    @GetMapping("/deleteServiceButton")
    public String deleteServiceButtoHandler(int id) {
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {
            Service selectedService = result.get();
            serviceRepository.delete(selectedService);
            return "result_member_modified.html";
        }
        return "error_requested_object_not_found.html";
    }


    /**
     * <<<<<<<<<<<<<<<<<<<<<<<     Edit your profile     >>>>>>>>>>>>>>>>>>>>>>>>>>
     */


    @GetMapping("/editProfile")
    public String editProfileButtonHandler(ModelMap model) {
        model.addAttribute("selectedMember", loggedMember);
        return "editing_form_member.html";
    }


    /**
     * <<<<<<<<<<<<<<<<<<<<<<<     Book Services     >>>>>>>>>>>>>>>>>>>>>>>>>>
     */

    @GetMapping("/listServiceButton")
    public String listServiceButton(ModelMap model) {
        model.addAttribute("myServices", serviceRepository.findAll());

        if (loggedMember == null) {
            Model model1;
            model.addAttribute("localImageUrl", "images/please.gif");
            return "warning_loggin_before_booking_any_service.html";
        }
        return "list_buttons_of_services.html";
    }

    @GetMapping("/showServiceDetailToBuy")
    public String showServiceDetailToBuyHandler(int id, ModelMap model) {
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {
            Service selectedService = result.get();
            model.addAttribute("selectedService", selectedService);
            return "service_detail_to_buy.html";
        }
        return "error_requested_object_not_found.html";
    }

    @GetMapping("/bookService")
    public String bookService(int id, ModelMap model1, ModelMap model2) {
        Service selectedService = null;
        Optional<Service> result = serviceRepository.findById(id);
        if (result.isPresent()) {

            selectedService = result.get();
            if (loggedMember.services.contains(selectedService)) {
                return "warning_alredy_booked_service.html";
            }
            loggedMember.services.add(selectedService);
            loggedMember.balance = loggedMember.balance - selectedService.price;
            memberRepository.save(loggedMember);
            model1.addAttribute("loggedMember", loggedMember);
            model2.addAttribute("selectedService", selectedService);
            return "result_booking";
        }
        return "error_requested_object_not_found.html";
    }


    /**
     * <<<<<<<<<<<<<<<<<<<<<<<     Email Services (TO BE DEVELOPED)     >>>>>>>>>>>>>>>>>>>>>>>>>>
     */

    @GetMapping("/sendEmailButton")
    @ResponseBody
    public String sendEmailButton() {

        emailService.sendMail(
                "usef.emadi@gmail.com",
                "Test Email",
                "Test message from Member Management system"
        );
        return "Email has been sent!";
    }

}
