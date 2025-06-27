package com.example.springcrudpostgres.services;

import com.example.springcrudpostgres.dto.EmployeeDto;
import com.example.springcrudpostgres.entities.Employee;
import com.example.springcrudpostgres.mappers.EmployeeMapper;
import com.example.springcrudpostgres.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IEmployeeServiceImp implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto add(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapToEntity(employeeDto);
        // No need to manually set timestamps — handled by @PrePersist
        return employeeMapper.mapToDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto update(String idEmployee, Map<Object, Object> fields) {
        Long id = Long.valueOf(idEmployee);
        Employee employee = employeeRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + idEmployee));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Employee.class, (String) key);
            if (field != null) {
                field.setAccessible(true);
                if (field.getType().equals(LocalDateTime.class) && value instanceof String) {
                    LocalDateTime parsed = LocalDateTime.parse((String) value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    ReflectionUtils.setField(field, employee, parsed);
                } else {
                    ReflectionUtils.setField(field, employee, value);
                }
            }
        });

        // updatedAt is handled by @PreUpdate
        return employeeMapper.mapToDto(employeeRepository.save(employee));
    }

    @Override
    public boolean delete(String idEmployee) {
        Long id = Long.valueOf(idEmployee);
        employeeRepository.deleteById(String.valueOf(id));
        return !employeeRepository.existsById(String.valueOf(id));
    }

    @Override
    public Page<EmployeeDto> getEmployees(int pageNbr, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageNbr, pageSize))
                .map(employeeMapper::mapToDto);
    }

    @Override
    public EmployeeDto getEmployee(String id) {
        Long longId = Long.valueOf(id);
        return employeeRepository.findById(String.valueOf(longId))
                .map(employeeMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    @Override
    public EmployeeDto getEmployeeByName(String name) {
        throw new UnsupportedOperationException("Use getEmployeeByFirstAndLastName instead — 'name' is not a field.");
    }

    // Optional suggestion: Replace `getEmployeeByName()` with this:
    public EmployeeDto getEmployeeByFirstAndLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(employeeMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with given name"));
    }
}
