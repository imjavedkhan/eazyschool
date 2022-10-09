package com.primus.eazyschool.controller;

import com.primus.eazyschool.model.Address;
import com.primus.eazyschool.model.Person;
import com.primus.eazyschool.model.Profile;
import com.primus.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress() !=null && person.getAddress().getAddressId()>0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile",profile);

        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, HttpSession session,
                                Errors errors){
        if(errors.hasErrors()){
            return "profile";
        }

        Person person = (Person) session.getAttribute("loggedInPerson");

        person.setMobileNumber(profile.getMobileNumber());

        if(person.getAddress() == null && !(person.getAddress().getAddressId()>0)){
            person.setAddress(new Address());
        }

        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        Person saveProfile = personRepository.save(person);

        session.setAttribute("loggedInPerson",saveProfile);

        return "redirect:/displayProfile";



    }


}