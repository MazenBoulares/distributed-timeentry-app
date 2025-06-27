package com.example.springcrudpostgres.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing Employee information.
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
 * @param id The unique identifier for the Employee.
 * @param firstName The first name of the Employee.
 * @param lastName The last name of the Employee.
 * @param email The email of the Employee.
 * @param department The department the Employee belongs to.
 * @param role The role of the Employee (e.g., Developer, QA, Manager).
// * @param createdAt The creation timestamp.
// * @param updatedAt The last update timestamp.
 */
public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        String email,
        String department,
        String role
//        LocalDateTime createdAt,
//        LocalDateTime updatedAt
) {
    // Record automatically provides getters, equals, hashCode, and toString methods.
}
