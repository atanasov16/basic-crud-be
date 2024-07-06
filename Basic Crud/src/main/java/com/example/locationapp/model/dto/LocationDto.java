package com.example.locationapp.model.dto;

import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private String id;
    private String name;
    private DepartmentDto departmentDto;
}
