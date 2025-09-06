package com.gyarsilalsolanki011.journeymate.mapper;

import com.gyarsilalsolanki011.journeymate.dto.TripDto;
import com.gyarsilalsolanki011.journeymate.entity.Trip;
import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TripMapper {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static TripDto toDto(Trip trip) {
        TripDto dto = new TripDto();
        dto.setDestination(trip.getDestination());
        dto.setStartDate(trip.getStartDate().format(FORMATTER));
        dto.setEndDate(trip.getEndDate().format(FORMATTER));
        dto.setPrice(trip.getPrice());
        dto.setTripStatus(trip.getTripStatus().toString());
        return dto;
    }

    public static Trip toEntity(TripDto dto) {
        Trip trip = new Trip();
        trip.setDestination(dto.getDestination());
        trip.setStartDate(LocalDate.parse(dto.getStartDate(), FORMATTER));
        trip.setEndDate(LocalDate.parse(dto.getEndDate(), FORMATTER));
        trip.setPrice(dto.getPrice());
        trip.setTripStatus(TripStatusParser.fromString(dto.getTripStatus()));
        return trip;
    }
}

