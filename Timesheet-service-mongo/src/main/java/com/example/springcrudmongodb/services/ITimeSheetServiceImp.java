package com.example.springcrudmongodb.services;

import com.example.springcrudmongodb.dto.TimeSheetDto;
import com.example.springcrudmongodb.entities.TimeSheet;
import com.example.springcrudmongodb.mappers.TimeSheetMapper;
import com.example.springcrudmongodb.repositories.TimeSheetRepository;
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
public class ITimeSheetServiceImp implements ITimeSheetService {

    private final TimeSheetRepository timeSheetRepository;
    private final TimeSheetMapper timeSheetMapper;

    @Override
    public TimeSheetDto add(TimeSheetDto timeSheetDto) {
        TimeSheet timeSheet = timeSheetMapper.mapToEntity(timeSheetDto);
        timeSheet.setCreatedAt(LocalDateTime.now());
        timeSheet.setUpdatedAt(LocalDateTime.now());
        return timeSheetMapper.mapToDto(timeSheetRepository.save(timeSheet));
    }

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

    // ❌ Removed `getTimeSheetByName` because `name` no longer exists in TimeSheet
}
