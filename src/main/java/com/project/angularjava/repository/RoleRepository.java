package com.project.angularjava.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.angularjava.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}