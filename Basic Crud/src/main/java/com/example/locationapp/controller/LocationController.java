package com.example.locationapp.controller;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.dto.SetDepartmentRequest;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.DepartmentRepository;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
import com.example.locationapp.service.impl.DepartmentServiceImpl;
import com.example.locationapp.service.impl.LocationServiceImpl;
import jakarta.annotation.Nullable;
import javassist.bytecode.LocalVariableAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("locations")
public class LocationController {
    LocationService locationService;
    DepartmentService departmentService;

    public LocationController(LocationService locationService, DepartmentService departmentService) {
        this.departmentService = departmentService;
        this.locationService = locationService;
    }

    @GetMapping("/all")
    public String getLocations(Model model) {
        List<LocationDto> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "index.html";
    }

    @PostMapping("/add")
    public String addNewLocation(@RequestBody LocationDto locationDto) {
        locationService.createLocation(locationDto);
        return "redirect:/locations";
    }

    @PostMapping("/set-department")
    public String setDepartments(@RequestBody SetDepartmentRequest setDepartmentRequest) {
        locationService.updateLocation(LocationMapper.INSTANCE.toDto(setDepartmentRequest.getLocation()), DepartmentMapper.INSTANCE.toDto(setDepartmentRequest.getDepartment()));
        return "redirect:/locations";
    }


}
