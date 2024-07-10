package com.example.locationapp.controller;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.service.DepartmentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping
    public List<DepartmentDto> getAll() {
        return departmentService.getAllDepartments();
    }
    @PostMapping("/add")
    public DepartmentDto addDepartment( @RequestBody DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto);
    }

    @DeleteMapping("/delete/{id}")
    public DepartmentDto deleteDepartment(@PathVariable String id) {
        return departmentService.deleteDepartmentById(UUID.fromString(id));
    }

    @PutMapping("/edit/{id}")
    public DepartmentDto editDepartment(@PathVariable String id,  @RequestBody DepartmentDto departmentDto) {
        return departmentService.editDepartment(UUID.fromString(id), departmentDto);
    }

}
