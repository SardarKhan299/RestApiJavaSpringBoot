package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public JournalEntity getEntryById(@PathVariable long myId){
        return null;
        //return listOfEntries.get(myId);
    }

    @PostMapping("/add")
    public void createEntry(@RequestBody JournalEntity entry){
        journalEntryService.saveEntry(entry);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntity deleteEntryById(@PathVariable long myId){
        return null;
        //return listOfEntries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public JournalEntity updateGeneral(@PathVariable long myId, @RequestBody JournalEntity entry){
        return null;
        //return listOfEntries.put(myId,entry);
    }


}
