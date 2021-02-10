package com.project.angularjava.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.angularjava.model.Products;

public interface ProductRepository extends MongoRepository<Products, String> {
	
	@Override
    void delete(Products deleted);
}