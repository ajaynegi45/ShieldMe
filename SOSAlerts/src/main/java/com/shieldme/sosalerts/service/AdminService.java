package com.shieldme.sosalerts.service;

import com.shieldme.sosalerts.model.UserContact;
import com.shieldme.sosalerts.repository.UserContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserContactRepository userContactRepository;

    public AdminService(UserContactRepository userContactRepository) {
        this.userContactRepository = userContactRepository;
    }

    public List<UserContact> getAllContacts(){
        return userContactRepository.findAll();
    }
}
