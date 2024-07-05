package com.example.locationapp.controller;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PostMapping("/add")
    public String addDepartment( @RequestBody DepartmentDto departmentDto) {
        departmentService.createDepartment(departmentDto);
        return "redirect:/departments";
    }

}
