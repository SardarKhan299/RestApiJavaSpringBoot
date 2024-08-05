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
        return journalEntryService.findById(myId).orElse(null);
    }

    @PostMapping("/add")
    public JournalEntity createEntry(@RequestBody JournalEntity entry){
        entry.setDate(LocalDateTime.now());
        return journalEntryService.saveEntry(entry);
    }

    @DeleteMapping("/id/{myId}")
    public Boolean deleteEntryById(@PathVariable ObjectId myId){
       journalEntryService.deleteEntryById(myId);
       return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntity updateGeneral(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntry){
        JournalEntity oldEntity = journalEntryService.findById(myId).orElse(null);
        if(oldEntity!=null){
            oldEntity.setTitle(newEntry.getTitle()!=null && newEntry.getTitle().equals("")
                    ?newEntry.getTitle():oldEntity.getTitle());
            oldEntity.setContent(newEntry.getContent()!=null && newEntry.getContent().equals("")
                    ?newEntry.getContent():oldEntity.getContent());
        }
        return journalEntryService.saveEntry(oldEntity);

    }


}
