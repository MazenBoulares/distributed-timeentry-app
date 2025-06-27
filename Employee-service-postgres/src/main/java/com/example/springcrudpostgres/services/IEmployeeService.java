package com.example.springcrudpostgres.services;

import com.example.springcrudpostgres.dto.EmployeeDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IEmployeeService {

    EmployeeDto add(EmployeeDto employeeDto);

    EmployeeDto update(String idEmployee, Map<Object,Object> fields);

    boolean delete(String idEmployee);


    Page<EmployeeDto> getEmployees(int pageNbr, int pageSize);

    EmployeeDto getEmployee(String id);

    EmployeeDto getEmployeeByName(String name);
}