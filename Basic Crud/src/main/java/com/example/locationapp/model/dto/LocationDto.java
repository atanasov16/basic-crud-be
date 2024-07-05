package com.example.locationapp.model.dto;

import com.example.locationapp.model.entity.Department;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private String id;
    private String name;
    private Department department;
}
