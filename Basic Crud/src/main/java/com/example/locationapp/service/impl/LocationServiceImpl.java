package com.example.locationapp.service.impl;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.dto.SetDepartmentRequest;
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
    public LocationDto createLocation(SetDepartmentRequest setDepartmentRequest) {
        Location location = LocationMapper.INSTANCE.toEntity(setDepartmentRequest.getLocation());
        Department department = DepartmentMapper.INSTANCE.toEntity(departmentService.getDepartmentById(DepartmentMapper.INSTANCE.toEntity(setDepartmentRequest.getDepartment()).getUuid()));
//        DepartmentDto departmentDto = departmentService.getDepartmentById(DepartmentMapper.INSTANCE.toEntity(setDepartmentRequest.getDepartment()).getUuid());
        location.setDepartment(department);
        locationRepository.save(location);
        return setDepartmentRequest.getLocation();
    }

    @Override
    public LocationDto updateLocation(SetDepartmentRequest setDepartmentRequest) {
        Location location = locationRepository.findById(LocationMapper.INSTANCE.toEntity(setDepartmentRequest.getLocation()).getUuid()).orElse(null);
        if (location != null) {
            Department department = DepartmentMapper.INSTANCE.toEntity(departmentService.getDepartmentById(DepartmentMapper.INSTANCE.toEntity(setDepartmentRequest.getDepartment()).getUuid()));
            location.setDepartment(department);
            setDepartmentRequest.getLocation().setDepartmentDto(setDepartmentRequest.getDepartment());
            locationRepository.save(location);
        }
        return setDepartmentRequest.getLocation();
    }


    @Override
    public void deleteLocationDto(LocationDto locationDto) {
        locationRepository.delete(LocationMapper.INSTANCE.toEntity(locationDto));
    }

    @Override
    public void editLocation(LocationDto newName, String id) {
        Location location = locationRepository.getById(UUID.fromString(id));
        location.setName(newName.getName());
        locationRepository.save(location);
    }
}
