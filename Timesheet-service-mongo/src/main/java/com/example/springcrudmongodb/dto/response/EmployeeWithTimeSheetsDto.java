package com.example.springcrudmongodb.dto.response;


import com.example.springcrudmongodb.dto.TimeSheetDto;
import com.example.springcrudmongodb.dto.external.EmployeeDto;

import java.util.List;

public record EmployeeWithTimeSheetsDto(
        EmployeeDto employee,
        List<TimeSheetDto> timeSheets
) {}