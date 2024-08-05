package com.stella.restApiApp.repository;

import com.stella.restApiApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId> {

}
