package com.example.locationapp.service;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.DepartmentRepository;
import com.example.locationapp.service.impl.DepartmentServiceImpl;
import org.hamcrest.core.AnyOf;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @InjectMocks
    DepartmentServiceImpl departmentServiceImpl;

    @Mock
    DepartmentRepository departmentRepository;

    @Test
    void getAllDepartmentsTest() {
        List<Department> departments = Arrays.asList(
                new Department(UUID.randomUUID(), "Dept1"),
                new Department(UUID.randomUUID(), "Dept2")
        );

        when(departmentRepository.findAll()).thenReturn(departments);
        List<DepartmentDto> departmentDtos = departmentServiceImpl.getAllDepartments();

        assertEquals(departments.size(), departmentDtos.size());

        for (int i=0; i<departments.size(); i++) {
            DepartmentDto departmentDto = departmentDtos.get(i);
            Department department = departments.get(i);
            assertEquals(departmentDto.getName(), department.getName());
            assertEquals(departmentDto.getId(), department.getUuid().toString());
        }
    }

    @Test
    void getDepartmentByIdTest() {
        UUID uuid = UUID.randomUUID();
        Department department = new Department(uuid, "Dept1");
        when(departmentRepository.findById(uuid)).thenReturn(Optional.of(department));

        DepartmentDto departmentDto = departmentServiceImpl.getDepartmentById(uuid);
        assertEquals(departmentDto.getName(), department.getName());
        assertEquals(departmentDto.getId(), department.getUuid().toString());

        verify(departmentRepository, times(1)).findById(uuid);

    }

    @Test
    void getDepartmentByNameTest() {
        String name = "TestName";
        UUID uuid = UUID.randomUUID();
        Department department = new Department(uuid, name);
        when(departmentRepository.findByName(name)).thenReturn(department);

        DepartmentDto departmentDto = departmentServiceImpl.findByName(name);
        assertEquals(departmentDto.getName(), department.getName());
        assertEquals(departmentDto.getId(), department.getUuid().toString());
        verify(departmentRepository, times(1)).findByName(name);

    }

    @Test
    void saveDepartmentTest() {
        UUID uuid = UUID.randomUUID();
        Department department = new Department();
        department.setUuid(uuid);

        when(departmentRepository.save(department)).thenReturn(department);

        DepartmentDto departmentDto = DepartmentMapper.INSTANCE.toDto(department);
        DepartmentDto savedDpt = departmentServiceImpl.createDepartment(departmentDto);

        verify(departmentRepository).save(department);
        assertNotNull(savedDpt);
        assertEquals(department.getUuid(), UUID.fromString(savedDpt.getId()));
    }

    @Test
    void editDepartmentTest() {
        UUID uuid = UUID.randomUUID();
        Department department = new Department(uuid, "Name1");

        when(departmentRepository.findById(uuid)).thenReturn(Optional.of(department));

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(uuid.toString());
        departmentDto.setName("New name");

        departmentServiceImpl.editDepartment(uuid, departmentDto);

        verify(departmentRepository, times(1)).findById(uuid);

        assertEquals(departmentDto.getName(), "New name");
        assertEquals(departmentDto.getId(), uuid.toString());
    }

    @Test
    void deleteDepartmentTest() {
        UUID uuid = UUID.randomUUID();
        Department department = new Department(uuid, "Name1");
        when(departmentRepository.findById(uuid)).thenReturn(Optional.of(department));

        DepartmentDto deletedDeptDto = departmentServiceImpl.deleteDepartmentById(uuid);
        verify(departmentRepository, times(1)).findById(uuid);
        verify(departmentRepository, times(1)).delete(department);
        assertEquals(deletedDeptDto.getName(), department.getName());
        assertEquals(deletedDeptDto.getId(), uuid.toString());
    }

}
