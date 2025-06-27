package com.example.springcrudmongodb.services;

import com.example.springcrudmongodb.dto.TimeSheetDto;
import com.example.springcrudmongodb.dto.external.EmployeeDto;
import com.example.springcrudmongodb.dto.response.EmployeeWithTimeSheetsDto;
import com.example.springcrudmongodb.entities.TimeSheet;
import com.example.springcrudmongodb.feign.clients.EmployeeServiceClient;
import com.example.springcrudmongodb.mappers.TimeSheetMapper;
import com.example.springcrudmongodb.repositories.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ITimeSheetServiceImp implements ITimeSheetService {

    private final TimeSheetRepository timeSheetRepository;
    private final TimeSheetMapper timeSheetMapper;



    private final EmployeeServiceClient employeeServiceClient;


    @Override
    public TimeSheetDto getTimeSheetWithEmployee(String id) {
        TimeSheetDto dto = getTimeSheet(id);
        EmployeeDto employee = employeeServiceClient.getEmployee(dto.employeeId());

        String fullName = employee.firstName() + " " + employee.lastName();
        return TimeSheetDto.withEmployeeName(dto, fullName);
    }

    @Override
    public EmployeeWithTimeSheetsDto getEmployeeWithTimeSheets(String employeeId) {
        EmployeeDto employee = employeeServiceClient.getEmployee(employeeId);

        List<TimeSheetDto> timeSheets = timeSheetRepository
                .findAll()
                .stream()
                .filter(t -> employeeId.equals(t.getEmployeeId()))
                .map(timeSheetMapper::mapToDto)
                .toList();

        return new EmployeeWithTimeSheetsDto(employee, timeSheets);
    }


    @Override
    public TimeSheetDto add(TimeSheetDto timeSheetDto) {
        TimeSheet timeSheet = timeSheetMapper.mapToEntity(timeSheetDto);
        timeSheet.setCreatedAt(LocalDateTime.now());
        timeSheet.setUpdatedAt(LocalDateTime.now());
        return timeSheetMapper.mapToDto(timeSheetRepository.save(timeSheet));
    }

//    @Override
//    public TimeSheetDto addWithEmployee(TimeSheetDto timeSheetDto, String employeeId) {
//        return null;
//    }

    @Override
    public TimeSheetDto update(String idTimeSheet, Map<Object, Object> fields) {
        TimeSheet timeSheet = timeSheetRepository.findById(idTimeSheet)
                .orElseThrow(() -> new IllegalArgumentException("TimeSheet not found: " + idTimeSheet));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(TimeSheet.class, (String) key);
            if (field != null) {
                field.setAccessible(true);
                if (field.getType().equals(LocalDateTime.class) && value instanceof String) {
                    try {
                        LocalDateTime parsed = LocalDateTime.parse((String) value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        ReflectionUtils.setField(field, timeSheet, parsed);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid date format for field: " + key);
                    }
                } else {
                    ReflectionUtils.setField(field, timeSheet, value);
                }
            }
        });

        timeSheet.setUpdatedAt(LocalDateTime.now());
        return timeSheetMapper.mapToDto(timeSheetRepository.save(timeSheet));
    }

    @Override
    public boolean delete(String idTimeSheet) {
        timeSheetRepository.deleteById(idTimeSheet);
        return !timeSheetRepository.existsById(idTimeSheet);
    }

    @Override
    public Page<TimeSheetDto> getTimeSheets(int pageNbr, int pageSize) {
        return timeSheetRepository.findAll(PageRequest.of(pageNbr, pageSize))
                .map(timeSheetMapper::mapToDto);
    }

    @Override
    public TimeSheetDto getTimeSheet(String id) {
        return timeSheetRepository.findById(id)
                .map(timeSheetMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("TimeSheet not found"));
    }




}
