package com.tlimskech.marketplace.core.service;

import com.tlimskech.marketplace.global.contact.Contact;
import com.tlimskech.marketplace.global.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class GlobalService {

    @Autowired
    private ContactRepository contactRepository;

    protected Contact createContact(Contact contact) {
        Optional<Contact> optionalContact = contactRepository.findByNameAndPhoneNumberOrEmail(contact.getName(), contact.getPhoneNumber(), contact.getEmail());
        return optionalContact.orElseGet(() -> contactRepository.save(contact));
    }
}
