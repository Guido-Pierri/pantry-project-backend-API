package com.guidopierri.pantryapi.repository;

import com.guidopierri.pantryapi.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> getUserByEmailAndPassword(String email,String password);
}
