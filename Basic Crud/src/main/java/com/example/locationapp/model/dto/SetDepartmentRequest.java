package com.example.locationapp.model.dto;

import com.example.locationapp.model.entity.Department;
import com.example.locationapp.model.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetDepartmentRequest {
    private Location location;
    private Department department;

}
