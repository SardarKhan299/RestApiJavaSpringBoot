package com.stella.restApiApp.service;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.entity.User;
import com.stella.restApiApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public JournalEntity saveEntry(JournalEntity journalEntity, String userName){
        User user = userService.findByUserName(userName);
        JournalEntity savedEntity = journalEntryRepo.save(journalEntity);
        user.getJournalEntities().add(savedEntity);
        userService.saveNewUser(user);
        return journalEntity;
    }

    public JournalEntity saveEntryUpdate(JournalEntity journalEntity){
        journalEntryRepo.save(journalEntity);
        return journalEntity;
    }


    public List<JournalEntity> getAllEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteEntryById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntities().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveNewUser(user);
                journalEntryRepo.deleteById(id);
            }
            return removed;
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error Occured While Deleting the Entry "+e);
        }
    }

}
