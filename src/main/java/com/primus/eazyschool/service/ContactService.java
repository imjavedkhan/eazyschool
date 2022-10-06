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
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());

        int result = contactRepository.saveContactMsg(contact);

        if(result>0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgsList = contactRepository.findMsgsWithStatus(EazySchoolConstants.OPEN);
        return contactMsgsList;
    }

    public boolean updateMsgStatus(int id, String updatedBy){

        boolean isUpdated =  false;

        int result = contactRepository.updateMsgStatus(id,EazySchoolConstants.CLOSE,updatedBy);

        if(result>0){
            isUpdated = true;
        }

        return isUpdated;

    }
}
