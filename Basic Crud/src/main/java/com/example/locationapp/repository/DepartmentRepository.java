package com.example.locationapp.repository;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
