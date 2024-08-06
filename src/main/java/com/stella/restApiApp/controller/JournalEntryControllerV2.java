package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.entity.User;
import com.stella.restApiApp.service.JournalEntryService;
import com.stella.restApiApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntity> allEntries = user.getJournalEntities();
        if(allEntries!=null && !allEntries.isEmpty()){
            return new ResponseEntity<>(allEntries,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(allEntries,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntity> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity entry,@PathVariable String userName){
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId , @PathVariable String userName){
       journalEntryService.deleteEntryById(myId,userName);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<JournalEntity> updateGeneral(@PathVariable ObjectId myId,
                                                       @RequestBody JournalEntity newEntry,
                                                       @PathVariable String userName){
        JournalEntity oldEntity = journalEntryService.findById(myId).orElse(null);
        if(oldEntity!=null){
            oldEntity.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty()
                    ?newEntry.getTitle():oldEntity.getTitle());
            oldEntity.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty()
                    ?newEntry.getContent():oldEntity.getContent());
            journalEntryService.saveEntryUpdate(oldEntity);
            return new ResponseEntity<>(oldEntity, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }


}
