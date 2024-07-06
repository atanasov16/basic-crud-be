package com.example.locationapp.service;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;

import java.util.List;
import java.util.UUID;
public interface LocationService {
    List<LocationDto> getAllLocations();
    LocationDto getLocationById(UUID id);
    LocationDto createLocation(LocationDto locationDto);
    LocationDto updateLocation(LocationDto locationDto, DepartmentDto departmentDto);
    void deleteLocationDto(UUID id);
}
