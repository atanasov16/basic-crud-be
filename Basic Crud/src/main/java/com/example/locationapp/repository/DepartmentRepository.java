package com.example.locationapp.repository;

import com.example.locationapp.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Department findByName(String name);
}
