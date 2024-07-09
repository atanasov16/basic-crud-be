package com.example.locationapp.service;

import com.example.locationapp.model.dto.DepartmentDto;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(UUID id);

    DepartmentDto getDepartmentByName(String departmentName);

    DepartmentDto findByName(String name);

    void createDepartment(DepartmentDto departmentDto);

    void editDepartment(UUID id, DepartmentDto departmentDto);

    void deleteDepartmentById(UUID id);
}
