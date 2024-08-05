package com.stella.restApiApp.service;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public JournalEntity saveEntry(JournalEntity journalEntity){
        journalEntryRepo.save(journalEntity);
        return journalEntity;
    }

    public List<JournalEntity> getAllEntries(){
        return journalEntryRepo.findAll();
    }

}
