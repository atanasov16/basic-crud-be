package com.example.locationapp.model.mapper;

import com.example.locationapp.model.dto.LocationDto;
import com.example.locationapp.model.entity.Location;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-06T10:59:28+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class LocationMapperImpl implements LocationMapper {

    @Override
    public LocationDto toDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDto locationDto = new LocationDto();

        if ( location.getUuid() != null ) {
            locationDto.setId( location.getUuid().toString() );
        }

        return locationDto;
    }

    @Override
    public Location toEntity(LocationDto locationDto) {
        if ( locationDto == null ) {
            return null;
        }

        Location location = new Location();

        if ( locationDto.getId() != null ) {
            location.setUuid( UUID.fromString( locationDto.getId() ) );
        }

        return location;
    }
}
