package com.shieldme.journal.controller;


import com.shieldme.journal.model.Journal;
import com.shieldme.journal.service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journals")
@CrossOrigin(origins = "*")
public class JournalController {

    private final JournalService journalService;
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    // Fetch all journals
    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournals(@RequestHeader("userId") String userId) {
        System.out.println(userId);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Journal> journals = journalService.getAllJournals(userId);
        System.out.println(journals);
        return ResponseEntity.ok(journals);
    }

    // Fetch a specific journal by ID
    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable String id) {
        Optional<Journal> journal = journalService.getJournalById(id);
        return journal.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new journal
    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestBody Journal journal, @RequestHeader("userId") String userId) {
        if (journal.getCreatedAt() == null) {
            journal.setCreatedAt(LocalDateTime.now());
        }
        journal.setUserId(userId);
        return ResponseEntity.ok(journalService.createJournal(journal));
    }

    // Delete a journal by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable String id) {
        journalService.deleteJournal(id);
        return ResponseEntity.ok("Journal deleted successfully.");
    }

    // Update a journal by ID
    @PutMapping("/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable String id, @RequestBody Journal updatedJournal) {
        Optional<Journal> existingJournal = journalService.getJournalById(id);
        if (existingJournal.isPresent()) {
            updatedJournal.setId(id);
            return ResponseEntity.ok(journalService.updateJournal(updatedJournal));
        }
        return ResponseEntity.notFound().build();
    }
}
