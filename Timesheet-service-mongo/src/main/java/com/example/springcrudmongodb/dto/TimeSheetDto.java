package com.example.springcrudmongodb.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing TimeSheet information.
 *
 * <p>
 * A DTO is a design pattern used to transfer data between different layers of an application,
 * such as between the service layer and the presentation layer. It encapsulates data and
 * reduces the number of method calls, improving performance and decoupling the layers.
 *
 * <p>
 * This DTO uses the {@code record} feature introduced in Java 14. A {@code record} is a
 * concise way to define immutable data-carrying classes. It automatically provides
 * implementations for {@code equals()}, {@code hashCode()}, {@code toString()}, and getter
 * methods, reducing boilerplate code and ensuring immutability.
 * <p>
 *
 * The use of {@code record} in this DTO ensures that the data is immutable, thread-safe,
 * and easy to work with, while maintaining a clean and simple structure.
 *
 * @param id The unique identifier for the TimeSheet.
 * @param employeeId The ID of the employee who worked.
 * @param startTime The start time of the work session.
 * @param endTime The end time of the work session.
 * @param taskDescription A description of the task worked on.
 * @param createdAt The timestamp when the TimeSheet was created.
 * @param updatedAt The timestamp when the TimeSheet was last updated.
 */

public record TimeSheetDto(
        String id,
        String employeeId,
        String employeeName, // <-- ajouté ici
        LocalDateTime startTime,
        LocalDateTime endTime,
        String taskDescription,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static TimeSheetDto withEmployeeName(TimeSheetDto dto, String employeeName) {
        return new TimeSheetDto(
                dto.id(),
                dto.employeeId(),
                employeeName,
                dto.startTime(),
                dto.endTime(),
                dto.taskDescription(),
                dto.createdAt(),
                dto.updatedAt()
        );
    }
}


//public record TimeSheetDto(
//        String id,
//        String employeeId,
//
//        String employeeName,
//
//
//        LocalDateTime startTime,
//        LocalDateTime endTime,
//        String taskDescription,
//        LocalDateTime createdAt,
//        LocalDateTime updatedAt
//) {
//    // No additional methods needed; record provides getters, equals, hashCode, toString.
//}
//
//
//    public static TimeSheetDto withEmployeeName(TimeSheetDto dto, String employeeName) {
//        return new TimeSheetDto(
//                dto.id(),
//                dto.employeeId(),
//                employeeName,
//                dto.startTime(),
//                dto.endTime(),
//                dto.taskDescription(),
//                dto.createdAt(),
//                dto.updatedAt()
//        );
//    }