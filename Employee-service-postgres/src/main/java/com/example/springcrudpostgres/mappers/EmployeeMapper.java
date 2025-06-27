package com.example.springcrudpostgres.mappers;

import com.example.springcrudpostgres.dto.EmployeeDto;
import com.example.springcrudpostgres.entities.Employee;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link Employee} entities and {@link EmployeeDto} DTOs.
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
public interface EmployeeMapper {

    /**
     * Converts a {@link EmployeeDto} object to a {@link Employee} entity.
     *
     * @param EmployeeDto the {@link EmployeeDto} object to be mapped
     * @return the corresponding {@link Employee} entity
     */
    Employee mapToEntity(EmployeeDto EmployeeDto);

    /**
     * Converts a {@link Employee} entity to a {@link EmployeeDto} object.
     *
     * @param Employee the {@link Employee} entity to be mapped
     * @return the corresponding {@link EmployeeDto} object
     */
    EmployeeDto mapToDto(Employee Employee);
}