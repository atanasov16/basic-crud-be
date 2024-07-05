package com.example.locationapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "locations")
public class Location {
    @GeneratedValue
    @Id
    private UUID uuid;

}
