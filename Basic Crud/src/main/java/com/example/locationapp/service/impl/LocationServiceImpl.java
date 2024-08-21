package com.example.locationapp.service.impl;

import com.example.locationapp.Exceptions.LocationNotFoundException;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.LocationRepository;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final DepartmentService departmentService;

    @Override
    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll().stream().map(LocationMapper.INSTANCE::toDto).toList();
    }

    @Override
    public LocationDto getLocationById(UUID id) {
        return locationRepository.findById(id)
                .map(LocationMapper.INSTANCE::toDto)
                .orElseThrow(LocationNotFoundException::new);
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        Department department = new Department();
        Location location = LocationMapper.INSTANCE.toEntity(locationDto);
        if(locationDto.getDepartment()!=null){
            department = DepartmentMapper.INSTANCE.toEntity(departmentService.findByName(locationDto.getDepartment().getName()));
            location.setDepartment(department);
        }
        locationRepository.save(location);
        return locationDto;
    }

    @Override
    public LocationDto editLocation(UUID id, LocationDto locationDto) {
        Location location = locationRepository.findById(id).orElseThrow(LocationNotFoundException::new);
        Department department = DepartmentMapper.INSTANCE.toEntity(
                departmentService.findByName(locationDto.getDepartment().getName()));
        location.setName(locationDto.getName());
        location.setDepartment(department);
        locationRepository.save(location);
        return locationDto;
    }

    @Override
    public LocationDto deleteLocationDto(UUID id) {
        Location location = locationRepository.findById(id).orElseThrow(LocationNotFoundException::new);
        locationRepository.delete(location);
        return LocationMapper.INSTANCE.toDto(location);
    }
}


