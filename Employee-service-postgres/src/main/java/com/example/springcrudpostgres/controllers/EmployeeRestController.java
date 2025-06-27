package com.example.springcrudpostgres.controllers;

import com.example.springcrudpostgres.dto.EmployeeDto;
import com.example.springcrudpostgres.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeRestController{

    private final IEmployeeService EmployeeService;

    @PostMapping
    public EmployeeDto add(@RequestBody EmployeeDto EmployeeDto) {
        return EmployeeService.add(EmployeeDto);
    }

    @PatchMapping("{id}")
    public EmployeeDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return EmployeeService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable String id){
        return EmployeeService.delete(id);
    }


    @GetMapping
    public Page<EmployeeDto> getEmployees(int pageNbr,int pageSize){
        return EmployeeService.getEmployees(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public EmployeeDto getEmployee(@PathVariable String id){
        return EmployeeService.getEmployee(id);
    }

    @GetMapping("name/{name}")
    public EmployeeDto getEmployeeByName(@PathVariable String name){
        return EmployeeService.getEmployeeByName(name);
    }







}