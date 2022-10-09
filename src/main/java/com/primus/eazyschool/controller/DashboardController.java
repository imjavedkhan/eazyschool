package com.primus.eazyschool.controller;

import com.primus.eazyschool.model.Person;
import com.primus.eazyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){

        Person person = personRepository.findByEmail(authentication.getName());
        model.addAttribute("username",person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInPerson",person);
        return "dashboard";
    }
}
