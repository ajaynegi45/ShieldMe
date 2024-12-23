package com.shieldme.positiveaffirmations.repository;

import com.shieldme.positiveaffirmations.entity.Shlok;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShlokRepository extends MongoRepository<Shlok, String> {}
