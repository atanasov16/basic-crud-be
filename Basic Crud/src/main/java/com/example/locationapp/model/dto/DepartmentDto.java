package com.example.locationapp.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private String id;
    private String name;

}
