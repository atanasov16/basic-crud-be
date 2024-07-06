package com.example.locationapp.model.mapper;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(source = "uuid", target = "id")
    LocationDto toDto(Location location);

    @Mapping(source="id", target = "uuid")
    Location toEntity(LocationDto locationDto);
}
