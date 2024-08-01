package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class AppEntryController {

    private Map<Long,JournalEntity> listOfEntries = new HashMap<>();

    @GetMapping("/getAll")
    public List<JournalEntity> getAll(){
        return new ArrayList<>(listOfEntries.values());
    }

    @GetMapping("/id/{myId}")
    public JournalEntity getEntryById(@PathVariable long myId){
        return listOfEntries.get(myId);
    }

    @PostMapping("/add")
    public void createEntry(@RequestBody JournalEntity entry){
        listOfEntries.put(entry.getId(),entry);
    }



}
