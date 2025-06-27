package com.example.springcrudmongodb.services;

import com.example.springcrudmongodb.dto.TimeSheetDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ITimeSheetService {

    TimeSheetDto add(TimeSheetDto timeSheetDto);

    TimeSheetDto update(String idTimeSheet, Map<Object, Object> fields);

    boolean delete(String idTimeSheet);

    Page<TimeSheetDto> getTimeSheets(int pageNbr, int pageSize);

    TimeSheetDto getTimeSheet(String id);

    // Optional: replace this if needed
    // TimeSheetDto getTimeSheetByEmployeeId(String employeeId);
}
