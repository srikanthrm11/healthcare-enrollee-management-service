package com.healthcare.management.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.management.dto.Enrollee;

@Repository
public interface EnrolleeRepository extends MongoRepository<Enrollee, String> {

}
