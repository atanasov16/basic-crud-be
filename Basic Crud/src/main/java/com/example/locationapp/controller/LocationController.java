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
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
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
    public LocationDto deleteLocation(@PathVariable String id) {
        return locationService.deleteLocationDto(UUID.fromString(id));
    }
}
