package com.example.locationapp.model.dto;

import com.example.locationapp.model.entity.Department;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private String id;
    private String name;
    private Department department;
}
