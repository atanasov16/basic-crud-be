package com.example.locationapp.repository;

import com.example.locationapp.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

}
