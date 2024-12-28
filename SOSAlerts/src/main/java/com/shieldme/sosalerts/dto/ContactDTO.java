package com.shieldme.sosalerts.dto;


import org.bson.types.ObjectId;

public class ContactDTO {
    private ObjectId userId;
    private Contact contact;

    @Override
    public String toString() {
        return "ContactDTO{" +
                "userId='" + userId + '\'' +
                ", contact=" + contact +
                '}';
    }

    public ContactDTO() {
    }

    public ContactDTO(ObjectId userId, Contact contact) {
        this.userId = userId;
        this.contact = contact;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}

