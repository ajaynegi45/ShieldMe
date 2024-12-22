package com.shieldme.positiveaffirmations.service;

import com.shieldme.positiveaffirmations.entity.Shlok;
import com.shieldme.positiveaffirmations.repository.ShlokRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ShlokService {

    private final ShlokRepository shlokRepository;
    private final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(ShlokService.class);
    public ShlokService(ShlokRepository shlokRepository) {
        this.shlokRepository = shlokRepository;
    }

    // Add Single Affirmation
    public Shlok addAffirmation(Shlok shlok) {
        logger.info("Adding a new affirmation: {}", shlok);
        return shlokRepository.save(shlok);
    }

    // Add Multiple Affirmations
    @Transactional
    public List<Shlok> addMultipleAffirmations(List<Shlok> shloks) {
        logger.info("Adding multiple affirmations: {}", shloks.size());
        return shlokRepository.saveAll(shloks);
    }

    // Get Random Affirmation
    public Optional<Shlok> getRandomAffirmation() {
        List<Shlok> affirmations = shlokRepository.findAll();
        if (affirmations.isEmpty()) {
            logger.warn("No affirmations available in the database.");
            return Optional.empty();
        }
        Shlok randomAffirmation = affirmations.get(random.nextInt(affirmations.size()));
        logger.info("Returning random affirmation: {}", randomAffirmation);
        return Optional.of(randomAffirmation);
    }

    // Get All Affirmations
    public List<Shlok> getAllAffirmations() {
        logger.info("Fetching all affirmations.");
        return shlokRepository.findAll();
    }

    // Get Affirmation by ID
    public Optional<Shlok> getAffirmationById(String id) {
        logger.info("Fetching affirmation by ID: {}", id);
        return shlokRepository.findById(id);
    }
}

