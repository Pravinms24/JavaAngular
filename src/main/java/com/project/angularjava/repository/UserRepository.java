package com.project.angularjava.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.angularjava.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
}