package com.primus.eazyschool.service;

import com.primus.eazyschool.constants.EazySchoolConstants;
import com.primus.eazyschool.model.Address;
import com.primus.eazyschool.model.Person;
import com.primus.eazyschool.model.Profile;
import com.primus.eazyschool.model.Roles;
import com.primus.eazyschool.repository.PersonRepository;
import com.primus.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, RolesRepository rolesRepository){
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
    }

    public boolean createNewPerson(Person person){
        boolean personSaved = false;

        Roles role = rolesRepository.findByRoleName(EazySchoolConstants.STUDENT_ROLE);

        person.setRoles(role);

        //encoding password using BCrypt
        person.setPwd(passwordEncoder.encode(person.getPwd()));

        Person savePerson = personRepository.save(person);

        if(savePerson.getPersonId()>0){
            personSaved =true;
        }

        return personSaved;

    }

    public Person updateProfile(Profile profile,Person person){

        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if(person.getAddress() ==null || !(person.getAddress().getAddressId()>0)){
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
        return personRepository.save(person);
    }

}
