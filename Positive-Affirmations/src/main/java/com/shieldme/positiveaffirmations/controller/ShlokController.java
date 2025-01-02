package com.shieldme.positiveaffirmations.controller;

import com.shieldme.positiveaffirmations.dto.ShlokRequest;
import com.shieldme.positiveaffirmations.entity.Shlok;
import com.shieldme.positiveaffirmations.service.ShlokService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shlok")
@CrossOrigin("*")
public class ShlokController {

    private final ShlokService shlokService;

    public ShlokController(ShlokService shlokService) {
        this.shlokService = shlokService;
    }

    // Add a single affirmation to the database and return the saved affirmation.
    @PostMapping("/add")
    public ResponseEntity<Shlok> addAffirmation(@RequestBody ShlokRequest shlokRequest) {
        Shlok savedAffirmation = shlokService.addAffirmation(shlokRequest);
        return ResponseEntity.ok(savedAffirmation);
    }

    // Add multiple affirmations to the database and return a list of saved affirmations.
    @PostMapping("/add-multiple")
    public ResponseEntity<List<Shlok>> addMultipleAffirmations(@RequestBody List<ShlokRequest> shlokRequests) {
        List<Shlok> savedAffirmations = shlokService.addMultipleAffirmations(shlokRequests);
        return ResponseEntity.ok(savedAffirmations);
    }

    // Retrieve a random affirmation from the database.
    @GetMapping("/random")
    public ResponseEntity<Shlok> getRandomAffirmation() {
        return shlokService.getRandomAffirmation()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    // Retrieve all affirmations from the database.
    @GetMapping("/all")
    public ResponseEntity<List<Shlok>> getAllAffirmations() {
        List<Shlok> affirmations = shlokService.getAllAffirmations();
        if (affirmations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(affirmations);
    }

    // Retrieve an affirmation by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Shlok> getAffirmationById(@PathVariable String id) {
        return shlokService.getAffirmationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
