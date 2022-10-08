package com.primus.eazyschool.controller;

import com.primus.eazyschool.model.Person;
import com.primus.eazyschool.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("public/")
public class SignUpController {

    private PersonService personService;

    public SignUpController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping(value = "register")
    public String displayRegisterPage(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("createUser")
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors){
        if(errors.hasErrors()){
            return "register";
        }

        boolean isSaved = personService.createNewPerson(person);

        if(isSaved){
            return "redirect:/login?register=true";
        }else {
            return "register";
        }
    }
}
