package com.shieldme.sosalerts.service;

import com.shieldme.sosalerts.exception.InvalidContactException;
import com.shieldme.sosalerts.dto.Contact;
import com.shieldme.sosalerts.dto.ContactRequest;
import com.shieldme.sosalerts.dto.UserContactListResponse;
import com.shieldme.sosalerts.model.UserContact;
import com.shieldme.sosalerts.repository.UserContactRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class ContactService {

    private final UserContactRepository contactRepository;
    public ContactService(UserContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public void saveContact(ContactRequest contactRequest) {
        try {
            UserContact userContact = contactRepository.findByUserId(contactRequest.getUserId())
                    .orElseGet(() -> {
                        UserContact newUserContact = new UserContact();
                        newUserContact.setUserId(contactRequest.getUserId());
                        newUserContact.setContacts(new ArrayList<>());
                        return newUserContact;
                    });

            if (contactRequest.getContact() == null) {
                throw new InvalidContactException("Contact details cannot be null.");
            }

            Contact newContact = contactRequest.getContact();
            if (newContact.getEmail() == null && newContact.getPhoneNumber() == null) {
                throw new InvalidContactException("At least one contact detail (email or phone number) is required.");
            }
            userContact.getContacts().add(newContact);
            contactRepository.save(userContact);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save contact: " + e.getMessage());
        }
    }


    public UserContactListResponse getContactDetails(String userId) {
        UserContact userContact = contactRepository.findByUserId(userId)
                .orElseThrow(() -> new InvalidContactException("User not found with ID: " + userId));

        UserContactListResponse contactDetailsDTO = new UserContactListResponse();
        contactDetailsDTO.setUserId(userContact.getUserId());
        contactDetailsDTO.setContacts(userContact.getContacts());
        return contactDetailsDTO;
    }




    public boolean deleteContact(String userId, String email, String phoneNumber) {
        // Fetch the user's contact list or throw an exception if the user is not found
        UserContact userContact = contactRepository.findByUserId(userId)
                .orElseThrow(() -> new InvalidContactException("User not found with ID: " + userId));

        boolean isDeleted = false;

        // Remove the contact if either the email or phone number matches
        if ((email != null && !email.isBlank()) || (phoneNumber != null && !phoneNumber.isBlank())) {
            Iterator<Contact> iterator = userContact.getContacts().iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                if ((email != null && email.equals(contact.getEmail())) ||
                        (phoneNumber != null && phoneNumber.equals(contact.getPhoneNumber()))) {
                    iterator.remove();
                    isDeleted = true;
                }
            }
        } else {
            throw new InvalidContactException("Either email or phone number must be provided for deletion.");
        }

        if (isDeleted) {
            contactRepository.save(userContact); // Save the updated contact list
        }

        return isDeleted;
    }

}
