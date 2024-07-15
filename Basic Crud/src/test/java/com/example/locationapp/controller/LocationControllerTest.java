package com.example.locationapp.controller;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class LocationControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }

    @Test
    void testGetLocations() throws Exception {
        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk());
        verify(locationService, times(1)).getAllLocations();
        verifyNoMoreInteractions(locationService);
    }

    @Test
    void testAddNewLocation() throws Exception {
        LocationDto locationDto = new LocationDto();
        when(locationService.createLocation(locationDto)).thenReturn(locationDto);
        String obj = objectMapper.writeValueAsString(locationDto);
        mockMvc.perform(post("/locations/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New York\"}")
                        .content(obj))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(locationDto.getId()));
        verify(locationService, times(1)).createLocation(any(LocationDto.class));
        verifyNoMoreInteractions(locationService);
    }

    @Test
    void testAddNewLocationWithDepartment() throws Exception {
        LocationDto locationDto = new LocationDto();
        when(locationService.createLocation(locationDto)).thenReturn(locationDto);


        mockMvc.perform(post("/locations/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New York\"}"))
                .andExpect(status().isOk());
        verify(locationService, times(1)).createLocation(any(LocationDto.class));
        verifyNoMoreInteractions(locationService);
    }

    @Test
    void testDeleteLocation() throws Exception {
        UUID id = UUID.randomUUID();
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id.toString());
        when(locationService.deleteLocationDto(id)).thenReturn(locationDto);

        mockMvc.perform(delete("/locations/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(locationDto.getId()));
        verify(locationService, times(1)).deleteLocationDto(id);
        verifyNoMoreInteractions(locationService);
    }

    @Test
    void testEditLocation() throws Exception {
        UUID id = UUID.randomUUID();
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id.toString());
        String locationString = objectMapper.writeValueAsString(locationDto);
        when(locationService.editLocation(id, locationDto)).thenReturn(locationDto);
        mockMvc.perform(put("/locations/edit/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(locationString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(locationDto.getId()))
                .andExpect(jsonPath("$.name").value(locationDto.getName()));
        verify(locationService, times(1)).editLocation(any(UUID.class), any(LocationDto.class));
        verifyNoMoreInteractions(locationService);
    }

}
