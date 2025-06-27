package com.example.springcrudmongodb.services;

import com.example.springcrudmongodb.dto.TimeSheetDto;
import com.example.springcrudmongodb.dto.response.EmployeeWithTimeSheetsDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ITimeSheetService {
    TimeSheetDto add(TimeSheetDto timeSheetDto);
//    TimeSheetDto addWithEmployee(TimeSheetDto timeSheetDto, String employeeId);
    TimeSheetDto update(String idTimeSheet, Map<Object, Object> fields);
    boolean delete(String idTimeSheet);
    Page<TimeSheetDto> getTimeSheets(int pageNbr, int pageSize);
    TimeSheetDto getTimeSheet(String id);
    TimeSheetDto getTimeSheetWithEmployee(String id);

    EmployeeWithTimeSheetsDto getEmployeeWithTimeSheets(String employeeId);
}
