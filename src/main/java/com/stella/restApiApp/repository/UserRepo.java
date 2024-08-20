package com.stella.restApiApp.repository;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);

    void deleteByUserName(String name);
}
