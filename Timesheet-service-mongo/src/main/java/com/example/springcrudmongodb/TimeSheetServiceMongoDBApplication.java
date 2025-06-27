package com.example.springcrudmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
public class TimeSheetServiceMongoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeSheetServiceMongoDBApplication.class, args);
    }

}
