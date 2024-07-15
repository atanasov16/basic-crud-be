package com.example.locationapp.service;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.LocationRepository;
import com.example.locationapp.service.impl.DepartmentServiceImpl;
import com.example.locationapp.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private DepartmentServiceImpl departmentService;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    void testGetAllLocations() {
        List<Location> locations = Arrays.asList(
                new Location(UUID.randomUUID(), "Location1", new Department()),
                new Location(UUID.randomUUID(), "Location2", new Department(UUID.randomUUID(), "Department on location"))
        );
        when(locationRepository.findAll()).thenReturn(locations);
        List<LocationDto> locationDtos = locationService.getAllLocations();

        assertEquals(locations.size(), locationDtos.size());

        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            LocationDto locationDto = locationDtos.get(i);
            assertEquals(location.getUuid(), UUID.fromString(locationDto.getId()));
            assertEquals(location.getName(), locationDto.getName());
        }
    }

    @Test
    void testGetLocationById() {
        UUID locId = UUID.randomUUID();
        Location location = new Location(locId, "Location1", new Department());
        when(locationRepository.findById(locId)).thenReturn(Optional.of(location));

        LocationDto locationDto = locationService.getLocationById(locId);

        assertEquals(locId, UUID.fromString(locationDto.getId()));
        assertEquals(location.getName(), locationDto.getName());

        verify(locationRepository, times(1)).findById(locId);
    }

    @Test
    void createLocationTest() {
        UUID uuid = UUID.randomUUID();
        Location location = new Location();
        location.setUuid(uuid);

        when(locationRepository.save(location)).thenReturn(location);

        LocationDto locationDto = LocationMapper.INSTANCE.toDto(location);
        LocationDto savedLoc = locationService.createLocation(locationDto);

        verify(locationRepository).save(location);
        assertNotNull(savedLoc);
        assertEquals(location.getUuid(), UUID.fromString(savedLoc.getId()));
    }

    @Test
    void editLocationTest() {
        UUID uuid = UUID.randomUUID();
        Department department = new Department(UUID.randomUUID(), "Department1");
        Location location = new Location(uuid, "OldLocationName", department);

        LocationDto locationDto = new LocationDto();
        locationDto.setId(uuid.toString());
        locationDto.setName("locationDtoName");
        locationDto.setDepartment(department);
        Location updatedLocation = new Location(uuid, locationDto.getName(), department);

        when(locationRepository.findById(uuid)).thenReturn(Optional.of(location));

        when(locationRepository.save(any(Location.class))).thenReturn(updatedLocation);
        when(departmentService.findByName(anyString())).thenReturn(DepartmentMapper.INSTANCE.toDto(department));

        LocationDto updatedLocationDto = locationService.editLocation(uuid, locationDto);

        verify(locationRepository).findById(uuid);
        verify(locationRepository).save(any(Location.class));

        assertAll("Verify edited location",
                () -> assertNotNull(updatedLocationDto),
                () -> assertEquals(locationDto.getName(), updatedLocationDto.getName()),
                () -> assertEquals(locationDto.getId(), updatedLocationDto.getId()),
                () -> assertEquals(locationDto.getDepartment().getName(), updatedLocationDto.getDepartment().getName())
        );
    }

    @Test
    void deleteLocationTest() {
        UUID uuid = UUID.randomUUID();
        Department department = new Department(UUID.randomUUID(), "Department1");
        Location location = new Location(uuid, "Location1", department);
        LocationDto locationDto = LocationMapper.INSTANCE.toDto(location);

        when(locationRepository.findById(uuid)).thenReturn(Optional.of(location));

        LocationDto deletedLocationDto = locationService.deleteLocationDto(uuid);

        verify(locationRepository).findById(uuid);
        verify(locationRepository).delete(location);

        assertAll("Verify deleted location",
                () -> assertNotNull(deletedLocationDto),
                () -> assertEquals(locationDto.getName(), deletedLocationDto.getName()),
                () -> assertEquals(locationDto.getId(), deletedLocationDto.getId()),
                () -> assertEquals(locationDto.getDepartment().getName(), deletedLocationDto.getDepartment().getName())
        );
    }
}
