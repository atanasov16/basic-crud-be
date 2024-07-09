package com.example.locationapp.controller;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.dto.SetDepartmentRequest;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/locations")
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
    public String addNewLocation(@RequestBody LocationDto locationDto) {
        locationService.createLocation(locationDto);
        return "redirect:/locations";
    }


    @PutMapping("/edit/{id}")
    public String editLocation(@PathVariable String id, @RequestBody LocationDto locationDto) {
        locationService.editLocation(UUID.fromString(id), locationDto);
        return "redirect:/locations";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLocation(@PathVariable String id) {
        locationService.deleteLocationDto(UUID.fromString(id));
        return "redirect:/locations";
    }
}
