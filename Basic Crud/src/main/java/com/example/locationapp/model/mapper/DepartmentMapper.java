package com.example.locationapp.model.mapper;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    @Mapping(source = "uuid", target = "id", qualifiedByName = "uuidToString")
    DepartmentDto toDto(Department department);

    @Mapping(source = "id", target = "uuid", qualifiedByName = "stringToUUID")
    Department toEntity(DepartmentDto departmentDto);

    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }
    @Named("stringToUUID")
    static UUID stringToUUID(String id) {
        return id != null ? UUID.fromString(id) : null;
    }
}
