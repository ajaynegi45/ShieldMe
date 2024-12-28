package com.shieldme.sosalerts.model;

import com.shieldme.sosalerts.dto.Contact;
import jakarta.validation.constraints.NotEmpty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_contacts")
public class UserContact {

    @Id
    private ObjectId contactId;

    @Indexed(unique = true)
    @NotEmpty(message = "UserId is nor present")
    private ObjectId userId;

    private List<Contact> contacts = new ArrayList<>();


    public UserContact() {
    }

    public UserContact(ObjectId contactId, ObjectId userId, List<Contact> contacts) {
        this.contactId = contactId;
        this.userId = userId;
        this.contacts = contacts;
    }

    public ObjectId getContactId() {
        return contactId;
    }

    public void setContactId(ObjectId contactId) {
        this.contactId = contactId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
