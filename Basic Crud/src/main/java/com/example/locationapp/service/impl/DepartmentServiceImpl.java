package com.example.locationapp.service.impl;

import com.example.locationapp.Exceptions.DepartmentNotFoundException;
import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.repository.DepartmentRepository;
import com.example.locationapp.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return departmentRepository.findById(id)
                .map(DepartmentMapper.INSTANCE::toDto)
                .orElseThrow(DepartmentNotFoundException::new);

    }

    @Override
    public DepartmentDto findByName(String name) {
        return DepartmentMapper.INSTANCE.toDto(departmentRepository.findByName(name));
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        departmentRepository.save(DepartmentMapper.INSTANCE.toEntity(departmentDto));
        return departmentDto;
    }

    @Override
    public DepartmentDto editDepartment(UUID id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return departmentDto;
    }

    @Override
    public DepartmentDto deleteDepartmentById(UUID id) {
        Department department = departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);
        departmentRepository.delete(department);
        return DepartmentMapper.INSTANCE.toDto(department);
    }
}
