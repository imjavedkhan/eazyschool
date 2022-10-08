package com.primus.eazyschool.service;

import com.primus.eazyschool.constants.EazySchoolConstants;
import com.primus.eazyschool.model.Contact;
import com.primus.eazyschool.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);


    public boolean saveMessageDetails(Contact contact){
        boolean isSaved=false;

        log.info(contact.toString());

        contact.setStatus(EazySchoolConstants.OPEN);
       /* contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());*/

        Contact result = contactRepository.save(contact);

        if(result.getContactId()>0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgsList = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgsList;
    }

    public boolean updateMsgStatus(int id){

        boolean isUpdated =  false;

        Optional<Contact> contactOptional = contactRepository.findById(id);

        contactOptional.ifPresent(contact -> {
            contact.setStatus(EazySchoolConstants.CLOSE);
            /*contact.setUpdatedBy(updatedBy);
            contact.setUpdatedAt(LocalDateTime.now());*/
        });

        Contact result = contactRepository.save(contactOptional.get());

        if(result.getUpdatedBy() != null){
            isUpdated = true;
        }
        return isUpdated;
    }
}
