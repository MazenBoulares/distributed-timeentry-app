package com.example.springcrudpostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan("com.example.springcrudmongodb.entities")
public class EmployeeServiceMongoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceMongoDBApplication.class, args);
    }

}
