package com.example.locationapp.model.mapper;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.entity.Department;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-08T13:38:44+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto toDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId( DepartmentMapper.uuidToString( department.getUuid() ) );
        departmentDto.setName( department.getName() );

        return departmentDto;
    }

    @Override
    public Department toEntity(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setUuid( DepartmentMapper.stringToUUID( departmentDto.getId() ) );
        department.setName( departmentDto.getName() );

        return department;
    }
}
