package com.example.locationapp.service;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
public interface LocationService {
    List<LocationDto> getAllLocations();
    LocationDto getLocationById(UUID id);
    LocationDto createLocation(String uuid, String department);
    LocationDto updateLocation(UUID id, LocationDto locationDto);
    void deleteLocationDto(UUID id);
}
