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
    public void createLocation(LocationDto locationDto) {
        Location location = new Location();
        Department department = DepartmentMapper.INSTANCE.toEntity(departmentService.findByName(locationDto.getDepartment().getName()));
        if (location != null) {
            location.setName(locationDto.getName());
            location.setDepartment(department);
            locationRepository.save(location);
        } else throw new LocationNotFoundException();
    }

    @Override
    public void editLocation(UUID id, LocationDto locationDto) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location != null) {
            location.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentService.getDepartmentByName(locationDto.getDepartment().getName())));
            location.setName(locationDto.getName());
            locationRepository.save(location);
        } else throw new LocationNotFoundException();
    }

    @Override
    public void deleteLocationDto(UUID id) {
        Location loc = locationRepository.findById(id).orElse(null);
        if (loc != null) {
            locationRepository.delete(loc);
        } else {
            throw new LocationNotFoundException();
        }
    }
}

    //    public boolean updateLocation(SetDepartmentRequest setDepartmentRequest){
//        Location location = locationRepository.findById(LocationMapper.INSTANCE.toEntity(setDepartmentRequest.getLocation()).getUuid()).orElse(null);
//        Department department = DepartmentMapper.INSTANCE.toEntity(departmentService.getDepartmentById(DepartmentMapper.INSTANCE.toEntity(setDepartmentRequest.getDepartment()).getUuid()));
//        if (location != null && department != null) {
//            location.setDepartment(department);
//            setDepartmentRequest.getLocation().setDepartmentDto(setDepartmentRequest.getDepartment());
//            locationRepository.save(location);
//            return true;
//        }
//        return false;

//    }
    //    @Override
//    public LocationDto setNewLocation(SetDepartmentRequest setDepartmentRequest) {
//        if(updateLocation(setDepartmentRequest)){
//            return setDepartmentRequest.getLocation();
//        } else throw new InvalidArgumentsException();
//    }

