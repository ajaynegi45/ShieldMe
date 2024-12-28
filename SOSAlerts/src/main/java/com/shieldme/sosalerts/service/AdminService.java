package com.shieldme.sosalerts.service;

import com.shieldme.sosalerts.model.SOSAlertLog;
import com.shieldme.sosalerts.model.UserContact;
import com.shieldme.sosalerts.repository.SOSAlertLogRepository;
import com.shieldme.sosalerts.repository.UserContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserContactRepository userContactRepository;
    private final SOSAlertLogRepository alertLogRepository;

    public AdminService(UserContactRepository userContactRepository, SOSAlertLogRepository alertLogRepository) {
        this.userContactRepository = userContactRepository;
        this.alertLogRepository = alertLogRepository;
    }

    public List<UserContact> getAllContacts(){
        return userContactRepository.findAll();
    }

    public List<SOSAlertLog> getAllLogs() {
        return alertLogRepository.findAll();
    }
}
