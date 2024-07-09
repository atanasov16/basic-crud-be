package com.example.locationapp.controller;

import com.example.locationapp.model.dto.DepartmentDto;
import com.example.locationapp.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        doNothing().when(departmentService).createDepartment(any(DepartmentDto.class));

        mockMvc.perform(post("/departments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"HR\", \"description\": \"Human Resources\"}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(departmentService).deleteDepartmentById(id);

        mockMvc.perform(delete("/departments/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"));
    }

    @Test
    void testEditDepartment() throws Exception {
        UUID id = UUID.randomUUID();
        DepartmentDto departmentDto = new DepartmentDto();
        doNothing().when(departmentService).editDepartment(any(UUID.class), any(DepartmentDto.class));

        mockMvc.perform(put("/departments/edit/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Edited second department\"}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"));
    }
}
