package com.gyarsilalsolanki011.journeymate.mapper;

import com.gyarsilalsolanki011.journeymate.model.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.model.entity.Trip;
import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;

public class TripMapper {
    public static TripDTO toDto(Trip trip) {
        TripDTO dto = new TripDTO();
        dto.setDestination(trip.getDestination());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        dto.setPrice(trip.getPrice());
        dto.setTripStatus(trip.getTripStatus().toString());
        return dto;
    }

    public static Trip toEntity(TripDTO dto) {
        Trip trip = new Trip();
        trip.setDestination(dto.getDestination());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setPrice(dto.getPrice());
        trip.setTripStatus(TripStatusParser.fromString(dto.getTripStatus()));
        return trip;
    }
}

