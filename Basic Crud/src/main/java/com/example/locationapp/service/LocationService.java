package com.example.locationapp.service;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.dto.SetDepartmentRequest;

import java.util.List;
import java.util.UUID;
public interface LocationService {
    List<LocationDto> getAllLocations();
    LocationDto getLocationById(UUID id);
    LocationDto createLocation(SetDepartmentRequest setDepartmentRequest);
    LocationDto updateLocation(SetDepartmentRequest setDepartmentRequest);
    void deleteLocationDto(LocationDto locationDto);

    void editLocation(LocationDto locationDto, String id);
}
