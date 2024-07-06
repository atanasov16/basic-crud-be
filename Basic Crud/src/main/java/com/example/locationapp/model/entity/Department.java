package com.example.locationapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "departments")
public class Department {
    @GeneratedValue
    @Id
    private UUID uuid;
    private String name;
    @OneToMany
    private List<Location> locations;
}
