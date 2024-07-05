package com.example.locationapp.service.impl;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.DepartmentRepository;
import com.example.locationapp.repository.LocationRepository;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
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
    public LocationDto createLocation(String id, String department) {
        locationRepository.save(new Location(UUID.fromString(id)));
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id);
        locationDto.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentService.getDepartmentById(UUID.fromString(department))));
        return locationDto;

    }

    @Override
    public LocationDto updateLocation(UUID id, LocationDto locationDto) {
        Location location = locationRepository.findById(id).orElse(null);

        return null;
    }

    @Override
    public void deleteLocationDto(UUID id) {

    }
}
