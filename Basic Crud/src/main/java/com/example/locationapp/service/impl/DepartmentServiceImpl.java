package com.example.locationapp.service.impl;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.mapper.DepartmentMapper;
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
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.INSTANCE.toEntity(departmentDto);
        departmentRepository.save(department);
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
