package com.example.springcrudmongodb.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;




@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheet implements Serializable {

    @Id
    @Indexed
    @Setter(AccessLevel.MODULE)
    String id;

    String employeeId;             // Who worked
    LocalDateTime startTime;       // Start of work
    LocalDateTime endTime;         // End of work
    String taskDescription;        // What was worked on

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}










//@Document
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class TimeSheet implements Serializable {
//
//    @Id
//    @Indexed
//            @Setter(AccessLevel.MODULE)
//    String id;
//    String name;
//    int quantity;
//    LocalDateTime createdAt;
//    LocalDateTime updatedAt;
//}
