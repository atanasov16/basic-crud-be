package com.example.locationapp.controller;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
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

}
