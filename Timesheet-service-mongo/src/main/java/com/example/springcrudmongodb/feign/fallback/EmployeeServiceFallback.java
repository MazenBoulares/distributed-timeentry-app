package com.example.springcrudmongodb.feign.fallback;

import com.example.springcrudmongodb.dto.external.EmployeeDto;
import com.example.springcrudmongodb.feign.clients.EmployeeServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;


public class EmployeeServiceFallback implements EmployeeServiceClient {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceFallback.class);

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        log.warn("Fallback triggered for addEmployee - Service Unavailable");
        return createFallbackEmployee("POST operation failed");
    }

    @Override
    public EmployeeDto patchUpdateEmployee(Map<Object, Object> fields, String id) {
        log.warn("Fallback triggered for patchUpdateEmployee(id: {}) - Service Unavailable", id);
        return createFallbackEmployee("PATCH operation failed");
    }

    @Override
    public boolean deleteEmployee(String id) {
        log.warn("Fallback triggered for deleteEmployee(id: {}) - Service Unavailable", id);
        return false;
    }

    @Override
    public Page<EmployeeDto> getEmployees(int pageNbr, int pageSize) {
        log.warn("Fallback triggered for getEmployees(page: {}, size: {}) - Service Unavailable", pageNbr, pageSize);
        return new PageImpl<>(Collections.singletonList(
                createFallbackEmployee("GET all operation failed")
        ));
    }

    @Override
    public EmployeeDto getEmployee(String id) {
        log.warn("Fallback triggered for getEmployee(id: {}) - Service Unavailable", id);
        return createFallbackEmployee("GET by ID operation failed");
    }

    @Override
    public EmployeeDto getEmployeeByName(String name) {
        log.warn("Fallback triggered for getEmployeeByName(name: {}) - Service Unavailable", name);
        return createFallbackEmployee("GET by name operation failed");
    }

    private EmployeeDto createFallbackEmployee(String reason) {
        return new EmployeeDto(
                "fallback-0000",
                "Service Unavailable",
                reason,
                "fallback@example.com",
                "UNKNOWN",
                "FALLBACK_ROLE"
        );
    }
}