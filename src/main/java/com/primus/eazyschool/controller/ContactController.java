package com.primus.eazyschool.controller;

import com.primus.eazyschool.model.Contact;
import com.primus.eazyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {


    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    /*@PostMapping("/saveMsg")
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject,
                                    @RequestParam String message){
        log.info("Name: " + name);
        log.info("Mobile No. : " + mobileNum);
        log.info("Email : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);

        return new ModelAndView("redirect:/contact");
    }*/

    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){

        if(errors.hasErrors()) {
            log.error("Contact form validation failed " + errors.toString());
            return "contact";
        }
        contactService.saveMessageDetails(contact);
        /*contactService.setCounter(contactService.getCounter()+1);

        log.info("Number of time contact form is submnitted : : " + contactService.getCounter());*/

        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs", contactMsgs);

        return modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeMsg(@RequestParam int id){
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }
}
