package com.shieldme.sosalerts.repository;

import com.shieldme.sosalerts.model.UserContact;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserContactRepository extends MongoRepository<UserContact, ObjectId> {
    Optional<UserContact> findByContactId(ObjectId contactId);

    Optional<UserContact> findByUserId(ObjectId userId);
}

