package com.primus.eazyschool.service;

import com.primus.eazyschool.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;


@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    private int counter =0;

    public ContactService() {
        System.out.println("Counter service started.....");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved=true;

        log.info(contact.toString());

        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
