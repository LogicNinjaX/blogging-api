package com.example.blogging_api.repository;

import com.example.blogging_api.entity.User;
import com.example.blogging_api.exception.UserNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByUsername(String username) throws UserNotFoundException;
}
