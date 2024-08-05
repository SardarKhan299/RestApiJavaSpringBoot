package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public JournalEntity getEntryById(@PathVariable ObjectId myId){
        return null;
        //return listOfEntries.get(myId);
    }

    @PostMapping("/add")
    public JournalEntity createEntry(@RequestBody JournalEntity entry){
        entry.setDate(LocalDateTime.now());
        return journalEntryService.saveEntry(entry);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntity deleteEntryById(@PathVariable ObjectId myId){
        return null;
        //return listOfEntries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public JournalEntity updateGeneral(@PathVariable ObjectId myId, @RequestBody JournalEntity entry){
        return null;
        //return listOfEntries.put(myId,entry);
    }


}
