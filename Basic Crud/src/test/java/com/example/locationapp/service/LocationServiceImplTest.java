package com.example.locationapp.service;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.entity.Location;
import com.example.locationapp.model.mapper.LocationMapper;
import com.example.locationapp.repository.LocationRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

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
    void createLocationTest(){
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
}
