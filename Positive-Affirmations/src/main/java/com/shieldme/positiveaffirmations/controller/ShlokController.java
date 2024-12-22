package com.shieldme.positiveaffirmations.controller;

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

    // Add Single Affirmation
    @PostMapping("/add")
    public ResponseEntity<Shlok> addAffirmation(@RequestBody Shlok shlok) {
        Shlok savedAffirmation = shlokService.addAffirmation(shlok);
        return ResponseEntity.ok(savedAffirmation);
    }

    // Add Multiple Affirmations
    @PostMapping("/add-multiple")
    public ResponseEntity<List<Shlok>> addMultipleAffirmations(@RequestBody List<Shlok> shloks) {
        List<Shlok> savedAffirmations = shlokService.addMultipleAffirmations(shloks);
        return ResponseEntity.ok(savedAffirmations);
    }

    // Get Random Affirmation
    @GetMapping("/random")
    public ResponseEntity<Shlok> getRandomAffirmation() {
        return shlokService.getRandomAffirmation()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    // Get All Affirmations
    @GetMapping("/all")
    public ResponseEntity<List<Shlok>> getAllAffirmations() {
        List<Shlok> affirmations = shlokService.getAllAffirmations();
        if (affirmations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(affirmations);
    }

    // Get Affirmation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shlok> getAffirmationById(@PathVariable String id) {
        return shlokService.getAffirmationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

