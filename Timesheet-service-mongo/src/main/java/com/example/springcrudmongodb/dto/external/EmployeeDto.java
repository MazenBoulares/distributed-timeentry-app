package com.example.springcrudmongodb.dto.external;

import lombok.Data;

import java.time.LocalDateTime;


public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        String email,
        String department,
        String role
) {}