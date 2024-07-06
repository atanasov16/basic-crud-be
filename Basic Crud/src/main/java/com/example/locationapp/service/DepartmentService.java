package com.example.locationapp.service;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentById(UUID id);
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto editDepartment(UUID id, DepartmentDto departmentDto);
    void deleteDepartmentById(UUID id);
    DepartmentDto getDepartmentByName(String departmentName);
    void addLocationToDepartment(DepartmentDto departmentDto, LocationDto locationDto);
}
