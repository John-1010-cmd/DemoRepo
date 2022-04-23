package com.example.mongospringbootdemo.repository;

import com.example.mongospringbootdemo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends MongoRepository<User, String> {
}
