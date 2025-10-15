package com.gyarsilalsolanki011.journeymate.mapper;

import com.gyarsilalsolanki011.journeymate.model.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.model.entity.Trip;
import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TripMapper {
    public static TripDTO toDto(Trip trip) {
        TripDTO dto = new TripDTO();
        dto.setTripId(trip.getTripId());
        dto.setDestination(trip.getDestination());
        dto.setStartDate(trip.getStartDate().toString());
        dto.setEndDate(trip.getEndDate().toString());
        dto.setPrice(trip.getPrice());
        dto.setTripStatus(trip.getTripStatus().toString());
        return dto;
    }

    public static Trip toEntity(TripDTO dto) {
        Trip trip = new Trip();
        trip.setDestination(dto.getDestination());
        trip.setStartDate(LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        trip.setEndDate(LocalDate.parse(dto.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        trip.setPrice(dto.getPrice());
        trip.setTripStatus(TripStatusParser.fromString(dto.getTripStatus()));
        return trip;
    }
}

