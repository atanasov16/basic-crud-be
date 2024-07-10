package com.example.locationapp.controller;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.service.DepartmentService;
import com.example.locationapp.service.LocationService;
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

    @Mock
    private LocationService locationService;

    @Mock
    private DepartmentService departmentService;

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
        doNothing().when(locationService).createLocation(any(LocationDto.class));

        mockMvc.perform(post("/locations/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New York\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:/locations"));
        verify(locationService, times(1)).createLocation(any(LocationDto.class));
        verifyNoMoreInteractions(locationService);
    }

    @Test
    void testDeleteLocation() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(locationService).deleteLocationDto(id);

        mockMvc.perform(delete("/locations/delete/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:/locations"));
        verify(locationService, times(1)).deleteLocationDto(id);
        verifyNoMoreInteractions(locationService);
    }

    @Test
    void testEditLocation() throws Exception {
        UUID id = UUID.randomUUID();
        LocationDto locationDto = new LocationDto();
        doNothing().when(locationService).editLocation(any(UUID.class), any(LocationDto.class));

        mockMvc.perform(put("/locations/edit/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Edited second location\", \"department\": {\"name\": \"Department3\"}}"))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:/locations"));
        verify(locationService, times(1)).editLocation(any(UUID.class), any(LocationDto.class));
        verifyNoMoreInteractions(locationService);
    }

}