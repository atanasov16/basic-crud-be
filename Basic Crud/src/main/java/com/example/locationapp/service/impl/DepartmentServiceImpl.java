package com.example.locationapp.service.impl;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.DepartmentRepository;
import com.example.locationapp.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentMapper.INSTANCE::toDto).toList();
    }

    @Override
    public DepartmentDto getDepartmentById(UUID id) {
        return DepartmentMapper.INSTANCE.toDto(departmentRepository.findById(id).orElse(null));
    }

    @Override
    public DepartmentDto getDepartmentByName(String name) {
        return DepartmentMapper.INSTANCE.toDto(departmentRepository.findByName(name));
    }

    @Override
    public void addLocationToDepartment(DepartmentDto departmentDto, LocationDto locationDto) {
        Department department = departmentRepository.findById(UUID.fromString(departmentDto.getId())).orElse(null);
        if (department != null) {
            List<Location> locations = department.getLocations();
            locations.add(LocationMapper.INSTANCE.toEntity(locationDto));
            department.setLocations(locations);
            departmentRepository.save(department);
        }
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        departmentRepository.save(DepartmentMapper.INSTANCE.toEntity(departmentDto));
        return departmentDto;
    }

    @Override
    public DepartmentDto editDepartment(UUID id, DepartmentDto departmentDto) {
        return null;
    }

    @Override
    public void deleteDepartmentById(UUID id) {

    }
}
