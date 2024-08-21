package com.example.locationapp.model.mapper;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.entity.Department;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2024-08-21T21:57:47+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
=======
    date = "2024-07-15T11:21:47+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
>>>>>>> 3fa8d26d221cd229aeb630b134e8c699b768a767
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
