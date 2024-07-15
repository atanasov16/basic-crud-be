package com.example.locationapp.service;

import com.example.locationapp.repository.DepartmentRepository;
import com.example.locationapp.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @InjectMocks
    DepartmentServiceImpl departmentServiceImpl;

    @Mock
    DepartmentRepository departmentRepository;

}
