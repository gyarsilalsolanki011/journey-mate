package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.dto.TripDto;
import com.gyarsilalsolanki011.journeymate.entity.Trip;
import com.gyarsilalsolanki011.journeymate.entity.TripSummary;
import com.gyarsilalsolanki011.journeymate.enums.TripStatus;
import com.gyarsilalsolanki011.journeymate.exception.TripNotFoundException;
import com.gyarsilalsolanki011.journeymate.exception.TripServiceException;
import com.gyarsilalsolanki011.journeymate.mapper.TripMapper;
import com.gyarsilalsolanki011.journeymate.repository.TripRepository;
import com.gyarsilalsolanki011.journeymate.util.TripDateParser;
import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public String createTrip(TripDto tripDto) {
        // if TripDto has String dates
        LocalDate start = TripDateParser.parseDate(tripDto.getStartDate(), "startDate");
        LocalDate end = TripDateParser.parseDate(tripDto.getEndDate(), "endDate");

        if (end.isBefore(start) || end.isEqual(start)) {
            throw new TripServiceException("End date must be after start date");
        }

        Trip trip = TripMapper.toEntity(tripDto);
        trip.setStartDate(start);
        trip.setEndDate(end);

        tripRepository.save(trip);
        return "Trip created successfully";
    }


    public Page<TripDto> getAllTrips(int page, int size, String[] sort) {
        if (sort.length < 2) {
            throw new TripServiceException("Sort parameter must include field and direction");
        }

        Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));

        return tripRepository.findAll(pageable).map(TripMapper::toDto);
    }

    public TripDto getTripById(int id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + id));
        return TripMapper.toDto(trip);
    }

    @Transactional
    public TripDto updateTrip(Integer tripId, TripDto tripDto) {
        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + tripId));

        LocalDate start = TripDateParser.parseDate(tripDto.getStartDate(), "startDate");
        LocalDate end = TripDateParser.parseDate(tripDto.getEndDate(), "endDate");

        if (end.isBefore(start) || end.isEqual(start)) {
            throw new TripServiceException("End date must be after start date");
        }

        existingTrip.setDestination(tripDto.getDestination());
        existingTrip.setStartDate(start);
        existingTrip.setEndDate(end);
        existingTrip.setPrice(tripDto.getPrice());
        existingTrip.setTripStatus(TripStatusParser.fromString(tripDto.getTripStatus()));

        return TripMapper.toDto(tripRepository.save(existingTrip));
    }

    @Transactional
    public String deleteTrip(Integer tripId) {
        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + tripId));

        tripRepository.delete(existingTrip);
        return "Trip deleted successfully";
    }

    public List<TripDto> searchTripsByDestination(String destination) {
        List<Trip> trips = tripRepository.findByDestinationContainingIgnoreCase(destination);
        if (trips.isEmpty()) {
            throw new TripNotFoundException("No trips found for destination: " + destination);
        }
        return trips.stream().map(TripMapper::toDto).toList();
    }

    public List<TripDto> getTripsByStatus(String status) {
        TripStatus tripStatus = TripStatusParser.fromString(status);
        List<Trip> trips = tripRepository.findByTripStatus(tripStatus);

        if (trips.isEmpty()) {
            throw new TripNotFoundException("No trips found with status: " + status);
        }
        return trips.stream().map(TripMapper::toDto).toList();
    }

    public List<TripDto> getTripsBetweenDates(String startDate, String endDate) {
        LocalDate start = TripDateParser.parseDate(startDate, "startDate");
        LocalDate end = TripDateParser.parseDate(endDate, "endDate");

        if (end.isBefore(start) || end.isEqual(start)) {
            throw new TripServiceException("End date must be after start date");
        }

        List<Trip> trips = tripRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(start, end);
        if (trips.isEmpty()) {
            throw new TripNotFoundException("No trips found between " + startDate + " and " + endDate);
        }

        return trips.stream().map(TripMapper::toDto).toList();
    }

    public TripSummary getTripSummary() {
        return new TripSummary(
                tripRepository.count(),
                Optional.ofNullable(tripRepository.findMinPrice()).orElse(0.0),
                Optional.ofNullable(tripRepository.findMaxPrice()).orElse(0.0),
                Optional.ofNullable(tripRepository.findAveragePrice()).orElse(0.0)
        );
    }
}

