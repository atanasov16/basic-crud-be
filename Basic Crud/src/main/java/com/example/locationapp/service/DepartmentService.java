package com.example.locationapp.service;

import com.example.locationapp.model.dto.DepartmentDto;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(UUID id);

    DepartmentDto findByName(String name);

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto editDepartment(UUID id, DepartmentDto departmentDto);

    DepartmentDto deleteDepartmentById(UUID id);
}
