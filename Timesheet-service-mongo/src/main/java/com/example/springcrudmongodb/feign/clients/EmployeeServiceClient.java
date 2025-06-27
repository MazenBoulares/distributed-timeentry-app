package com.example.springcrudmongodb.feign.clients;

import com.example.springcrudmongodb.dto.external.EmployeeDto;
import com.example.springcrudmongodb.feign.fallback.EmployeeServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(
        name = "employee-service", //eureka name
        contextId = "employeeServiceClient",
        fallback = EmployeeServiceFallback.class
)
public interface EmployeeServiceClient {

    @PostMapping("/api/postgres/employees")
    EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto);

    @PatchMapping("/api/postgres/employees/{id}")
    EmployeeDto patchUpdateEmployee(
            @RequestBody Map<Object, Object> fields,
            @PathVariable String id
    );

    @DeleteMapping("/api/postgres/employees/{id}")
    boolean deleteEmployee(@PathVariable String id);

    @GetMapping("/api/postgres/employees")
    Page<EmployeeDto> getEmployees(
            @RequestParam int pageNbr,
            @RequestParam int pageSize
    );

    @GetMapping("/api/postgres/employees/{id}")
    EmployeeDto getEmployee(@PathVariable String id);

    @GetMapping("/api/postgres/employees/name/{name}")
    EmployeeDto getEmployeeByName(@PathVariable String name);
}