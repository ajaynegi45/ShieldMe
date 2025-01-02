package com.shieldme.positiveaffirmations.repository;

import com.shieldme.positiveaffirmations.entity.Shlok;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShlokRepository extends MongoRepository<Shlok, String> {}