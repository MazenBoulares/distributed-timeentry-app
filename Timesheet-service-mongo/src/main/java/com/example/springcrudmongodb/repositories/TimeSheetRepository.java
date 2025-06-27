package com.example.springcrudmongodb.repositories;


import com.example.springcrudmongodb.entities.TimeSheet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TimeSheetRepository extends MongoRepository<TimeSheet, String> {

    Optional<TimeSheet> findByTaskDescription(String taskDescription);
}