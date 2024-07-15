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
    public LocationDto addNewLocation(@RequestBody LocationDto locationDto) {
        return locationService.createLocation(locationDto);
    }


    @PutMapping("/edit/{id}")
    public LocationDto editLocation(@PathVariable String id, @RequestBody LocationDto locationDto) {
        return locationService.editLocation(UUID.fromString(id), locationDto);
    }

    @DeleteMapping("/delete/{id}")
    public LocationDto deleteLocation(@PathVariable String id) {
        return locationService.deleteLocationDto(UUID.fromString(id));
    }
}
