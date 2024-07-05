package com.example.locationapp.controller;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
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
public class LocationController {
    LocationService locationService;
    DepartmentService departmentService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public String getLocations(Model model) {
        List<LocationDto> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "index.html";
    }

    //    @PostMapping("/add")
//    public String addNewLocation(@RequestParam String locName, @RequestParam String department) {
//        LocationDto locationDto = new LocationDto();
//        locationDto.setId(UUID.randomUUID().toString());
//        locationDto.setName(locName);
//        locationDto.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentService.getDepartmentById(UUID.fromString(department))));
//        return "redirect:/";
//    }
    @PostMapping("/add/{locName}/{department}")
    public String addNewLocation(@PathVariable String locName, @PathVariable String department) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(UUID.randomUUID().toString());
        locationDto.setName(locName);
        locationDto.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentService.getDepartmentById(UUID.fromString(department))));
        return "redirect:/";
    }


}
