package com.gyarsilalsolanki011.journeymate.mapper;

import com.gyarsilalsolanki011.journeymate.dto.TripDto;
import com.gyarsilalsolanki011.journeymate.entity.Trip;

public class TripMapper {

    public static TripDto toDto(Trip trip) {
        TripDto dto = new TripDto();
        dto.setDestination(trip.getDestination());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        dto.setPrice(trip.getPrice());
        dto.setStatus(trip.getStatus());
        return dto;
    }

    public static Trip toEntity(TripDto dto) {
        Trip trip = new Trip();
        trip.setDestination(dto.getDestination());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setPrice(dto.getPrice());
        trip.setStatus(dto.getStatus());
        return trip;
    }

}
