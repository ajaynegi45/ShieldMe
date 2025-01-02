package com.shieldme.journal.service;

import com.shieldme.journal.model.Journal;
import com.shieldme.journal.repository.JournalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    private final JournalRepository journalRepository;
    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public List<Journal> getAllJournals(String userId) {
        return journalRepository.findByUserId(userId);
    }


    public Optional<Journal> getJournalById(String id) {
        return journalRepository.findById(id);
    }

    public Journal createJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    public void deleteJournal(String id) {
        journalRepository.deleteById(id);
    }

    public Journal updateJournal(Journal updatedJournal) {
        return journalRepository.save(updatedJournal);
    }

    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

}
