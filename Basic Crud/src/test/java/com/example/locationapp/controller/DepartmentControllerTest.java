package com.example.locationapp.controller;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.model.mapper.DepartmentMapper;
import com.example.locationapp.service.DepartmentService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DepartmentControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddDepartment() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentService.createDepartment(departmentDto)).thenReturn(departmentDto);
        mockMvc.perform(post("/departments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"HR\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteDepartment() throws Exception {
        String id = UUID.randomUUID().toString();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(id);
        departmentDto.setName("Test Department");
        when(departmentService.deleteDepartmentById(UUID.fromString(id))).thenReturn(departmentDto);
        mockMvc.perform(delete("/departments/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentDto.getId()))
                .andExpect(jsonPath("$.name").value(departmentDto.getName()));
    }

    @Test
    void testEditDepartment() throws Exception {
        String id = UUID.randomUUID().toString();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(id);
        departmentDto.setName("Edited Department");
        String departmentString = objectMapper.writeValueAsString(departmentDto);
        when(departmentService.editDepartment(UUID.fromString(id), departmentDto)).thenReturn(departmentDto);

        mockMvc.perform(put("/departments/edit/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentDto.getId()))
                .andExpect(jsonPath("$.name").value(departmentDto.getName()));
    }
}
