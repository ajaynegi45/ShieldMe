package com.shieldme.journal.repository;

import com.shieldme.journal.model.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends MongoRepository<Journal, String> {
    List<Journal> findByUserId(String userId);
}
