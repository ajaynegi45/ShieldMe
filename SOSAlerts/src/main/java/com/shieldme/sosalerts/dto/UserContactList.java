package com.shieldme.sosalerts.dto;

import java.util.List;

public class UserContactList {
    private String userId;
    private List<Contact> contacts;

    public UserContactList() {
    }

    public UserContactList(String userId, List<Contact> contacts) {
        this.userId = userId;
        this.contacts = contacts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
