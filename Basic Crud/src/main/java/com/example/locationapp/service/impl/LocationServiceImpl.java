package com.example.locationapp.service.impl;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.LocationRepository;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
    LocationRepository locationRepository;
    DepartmentService departmentService;

    @Override
    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll().stream().map(LocationMapper.INSTANCE::toDto).toList();
    }

    @Override
    public LocationDto getLocationById(UUID id) {
        return LocationMapper.INSTANCE.toDto(locationRepository.findById(id).orElse(null));
    }


    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        locationRepository.save(LocationMapper.INSTANCE.toEntity(locationDto));
        return locationDto;
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto, DepartmentDto departmentDto) {
        Location location = LocationMapper.INSTANCE.toEntity(locationDto);
        location.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentDto));
        departmentService.addLocationToDepartment(departmentDto, locationDto);
        return null;
    }

    @Override
    public void deleteLocationDto(UUID id) {

    }
}
