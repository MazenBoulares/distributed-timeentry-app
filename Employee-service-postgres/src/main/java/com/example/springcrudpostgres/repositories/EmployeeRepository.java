package com.example.springcrudpostgres.repositories;

import com.example.springcrudpostgres.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}