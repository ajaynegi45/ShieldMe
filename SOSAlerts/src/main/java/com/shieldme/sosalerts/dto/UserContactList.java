package com.shieldme.sosalerts.dto;

import org.bson.types.ObjectId;

import java.util.List;

public class UserContactList {
    private ObjectId userId;
    private List<Contact> contacts;

    public UserContactList() {
    }

    public UserContactList(ObjectId userId, List<Contact> contacts) {
        this.userId = userId;
        this.contacts = contacts;
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
