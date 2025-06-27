package com.example.springcrudmongodb.controllers;

import com.example.springcrudmongodb.dto.TimeSheetDto;
import com.example.springcrudmongodb.services.ITimeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/TimeSheets")
@RequiredArgsConstructor
public class TimeSheetRestController{

    private final ITimeSheetService TimeSheetService;

    @PostMapping
    public TimeSheetDto add(@RequestBody TimeSheetDto TimeSheetDto) {
        return TimeSheetService.add(TimeSheetDto);
    }

    @PatchMapping("{id}")
    public TimeSheetDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return TimeSheetService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable String id){
        return TimeSheetService.delete(id);
    }


    @GetMapping
    public Page<TimeSheetDto> getTimeSheets(int pageNbr,int pageSize){
        return TimeSheetService.getTimeSheets(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public TimeSheetDto getTimeSheet(@PathVariable String id){
        return TimeSheetService.getTimeSheet(id);
    }

//    @GetMapping("name/{name}")
//    public TimeSheetDto getTimeSheetByName(@PathVariable String name){
//        return TimeSheetService.getTimeSheetByName(name);
//    }







}