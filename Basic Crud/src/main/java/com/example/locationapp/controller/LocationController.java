package com.example.locationapp.controller;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.dto.SetDepartmentRequest;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {
    LocationService locationService;
    DepartmentService departmentService;

    public LocationController(LocationService locationService, DepartmentService departmentService) {
        this.departmentService = departmentService;
        this.locationService = locationService;
    }

    @GetMapping()
    public List<LocationDto> getLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/add")
    public String addNewLocation(@RequestBody SetDepartmentRequest setDepartmentRequest) {
        locationService.createLocation(setDepartmentRequest);
        return "redirect:/locations";
    }

    @PostMapping("/set-department")
    public String setDepartments(@RequestBody SetDepartmentRequest setDepartmentRequest) {
        locationService.updateLocation(setDepartmentRequest);
        return "redirect:/locations";
    }

    @PostMapping("/edit-location/{id}")
    public String editLocation(@PathVariable String id, @RequestBody LocationDto locationDto) {
        locationService.editLocation(locationDto, id);
        return "redirect:/locations";
    }

    @DeleteMapping("/delete")
    public String deleteLocation(@RequestBody LocationDto locationDto) {
        locationService.deleteLocationDto(locationDto);
        return "redirect:/locations";
    }
}
