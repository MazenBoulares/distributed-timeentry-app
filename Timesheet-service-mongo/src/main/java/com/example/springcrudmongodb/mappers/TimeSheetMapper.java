package com.example.springcrudmongodb.mappers;

import com.example.springcrudmongodb.dto.TimeSheetDto;
import com.example.springcrudmongodb.entities.TimeSheet;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link TimeSheet} entities and {@link TimeSheetDto} DTOs.
 * <p>
 * This interface uses the <strong>MapStruct</strong> library to automatically generate
 * mapping implementations at compile time. The {@code @Mapper} annotation indicates that
 * this interface is a MapStruct mapper.
 * <p>
 * The {@code componentModel = "spring"} attribute specifies that the generated mapper
 * implementation should be a Spring bean, allowing it to be injected and managed by the
 * Spring framework.
 * <p>
 * MapStruct eliminates the need for manual mapping code by generating efficient and
 * type-safe mapping implementations.
 *
 * @see <a href="https://mapstruct.org/">MapStruct Documentation</a>
 */
@Mapper(componentModel = "spring")
public interface TimeSheetMapper {

    /**
     * Converts a {@link TimeSheetDto} object to a {@link TimeSheet} entity.
     *
     * @param TimeSheetDto the {@link TimeSheetDto} object to be mapped
     * @return the corresponding {@link TimeSheet} entity
     */
    TimeSheet mapToEntity(TimeSheetDto TimeSheetDto);

    /**
     * Converts a {@link TimeSheet} entity to a {@link TimeSheetDto} object.
     *
     * @param TimeSheet the {@link TimeSheet} entity to be mapped
     * @return the corresponding {@link TimeSheetDto} object
     */
    TimeSheetDto mapToDto(TimeSheet TimeSheet);
}