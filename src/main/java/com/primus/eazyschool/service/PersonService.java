package com.primus.eazyschool.service;

import com.primus.eazyschool.constants.EazySchoolConstants;
import com.primus.eazyschool.model.Person;
import com.primus.eazyschool.model.Roles;
import com.primus.eazyschool.repository.PersonRepository;
import com.primus.eazyschool.repository.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final RolesRepository rolesRepository;

    public PersonService(PersonRepository personRepository, RolesRepository rolesRepository){
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
    }

    public boolean createNewPerson(Person person){
        boolean personSaved = false;

        Roles role = rolesRepository.findByRoleName(EazySchoolConstants.STUDENT_ROLE);

        person.setRoles(role);

        Person savePerson = personRepository.save(person);

        if(savePerson.getPersonId()>0){
            personSaved =true;
        }

        return personSaved;

    }

}
