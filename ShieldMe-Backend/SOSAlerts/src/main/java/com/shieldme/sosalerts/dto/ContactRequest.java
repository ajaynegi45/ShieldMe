package com.shieldme.sosalerts.dto;

import org.bson.types.ObjectId;

public class ContactRequest {
    private String userId;
    private Contact contact;

    public ContactRequest() {
    }

    public ContactRequest(String userId, Contact contact) {
        this.userId = userId;
        this.contact = contact;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}

