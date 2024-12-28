package com.shieldme.sosalerts.service;

import com.shieldme.sosalerts.model.SOSAlertLog;
import com.shieldme.sosalerts.repository.SOSAlertLogRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SOSAlertLogService {
    private final SOSAlertLogRepository alertLogRepository;
    public SOSAlertLogService(SOSAlertLogRepository alertLogRepository) {
        this.alertLogRepository = alertLogRepository;
    }

    public List<SOSAlertLog> getLogsByUserId(ObjectId userId) {
        return alertLogRepository.findByUserId(userId);
    }
}