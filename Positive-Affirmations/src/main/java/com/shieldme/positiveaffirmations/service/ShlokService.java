package com.shieldme.positiveaffirmations.service;

import com.shieldme.positiveaffirmations.dto.ShlokRequest;
import com.shieldme.positiveaffirmations.entity.Shlok;
import com.shieldme.positiveaffirmations.repository.ShlokRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    // Add a single affirmation to the database and return the saved affirmation.
    public Shlok addAffirmation(ShlokRequest shlokRequest) {
        logger.info("Adding a new affirmation: {}", shlokRequest);

        // Map DTO to Entity
        Shlok shlok = new Shlok();
        shlok.setSanskritShlok(shlokRequest.sanskritShlok());
        shlok.setEnglishShlok(shlokRequest.englishShlok());
        shlok.setHindiMeaning(shlokRequest.hindiMeaning());
        shlok.setEnglishMeaning(shlokRequest.englishMeaning());

        // Save entity to database
        Shlok savedShlok = shlokRepository.save(shlok);
        logger.info("Affirmation added successfully with ID: {}", savedShlok.getShlokId());
        return savedShlok;
    }

    // Add multiple affirmations to the database and return a list of saved affirmations.
    public List<Shlok> addMultipleAffirmations(List<ShlokRequest> shlokRequests) {
        logger.info("Adding multiple affirmations: {}", shlokRequests.size());

        // Map DTOs to Entities
        List<Shlok> shloks = shlokRequests.stream()
                .map(request -> new Shlok(null, request.sanskritShlok(), request.englishShlok(),
                        request.hindiMeaning(), request.englishMeaning()))
                .toList();

        // Save entities to database
        List<Shlok> savedShloks = shlokRepository.saveAll(shloks);
        logger.info("Successfully added {} affirmations.", savedShloks.size());
        return savedShloks;
    }

    // Retrieve a random affirmation from the database.
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

    // Retrieve all affirmations from the database.
    public List<Shlok> getAllAffirmations() {
        logger.info("Fetching all affirmations.");
        return shlokRepository.findAll();
    }

    // Retrieve an affirmation by its ID.
    public Optional<Shlok> getAffirmationById(String id) {
        logger.info("Fetching affirmation by ID: {}", id);
        return shlokRepository.findById(id);
    }
}
