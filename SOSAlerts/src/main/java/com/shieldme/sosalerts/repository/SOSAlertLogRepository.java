package com.shieldme.sosalerts.repository;

import com.shieldme.sosalerts.model.SOSAlertLog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SOSAlertLogRepository extends MongoRepository<SOSAlertLog, String> {
    List<SOSAlertLog> findByUserId(String userId);
}
