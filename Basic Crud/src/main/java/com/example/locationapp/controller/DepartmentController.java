package com.example.locationapp.controller;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.service.DepartmentService;
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
    public String addDepartment( @RequestBody DepartmentDto departmentDto) {
        departmentService.createDepartment(departmentDto);
        return "redirect:/departments";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartmentById(UUID.fromString(id));
        return "redirect:/departments";
    }

    @PutMapping("/edit/{id}")
    public String editDepartment(@PathVariable String id, @RequestBody DepartmentDto departmentDto) {
        departmentService.editDepartment(UUID.fromString(id), departmentDto);
        return "redirect:/departments";
    }

}
