package com.example.locationapp.service;

import com.example.locationapp.model.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentById(UUID id);
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto editDepartment(UUID id, DepartmentDto departmentDto);
    void deleteDepartmentById(UUID id);
}
