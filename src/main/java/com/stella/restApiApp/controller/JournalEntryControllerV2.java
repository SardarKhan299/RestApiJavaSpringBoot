package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.service.JournalEntryService;
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

    @GetMapping("/getAll")
    public List<JournalEntity> getAll(){
        return journalEntryService.getAllEntries();
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

    @PostMapping("/add")
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity entry){
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){
       journalEntryService.deleteEntryById(myId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myId}")
    public JournalEntity updateGeneral(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntry){
        JournalEntity oldEntity = journalEntryService.findById(myId).orElse(null);
        if(oldEntity!=null){
            oldEntity.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty()
                    ?newEntry.getTitle():oldEntity.getTitle());
            oldEntity.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty()
                    ?newEntry.getContent():oldEntity.getContent());
        }
        return journalEntryService.saveEntry(oldEntity);

    }


}
