package com.stella.restApiApp.service;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntity journalEntity){
        journalEntryRepo.save(journalEntity);
    }

}
