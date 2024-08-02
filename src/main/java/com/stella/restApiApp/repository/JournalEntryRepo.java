package com.stella.restApiApp.repository;

import com.stella.restApiApp.entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface JournalEntryRepo extends MongoRepository<JournalEntity,String> {

}
