package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.exception.TripNotFoundException;
import com.gyarsilalsolanki011.journeymate.exception.TripServiceException;
import com.gyarsilalsolanki011.journeymate.mapper.TripMapper;
import com.gyarsilalsolanki011.journeymate.model.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.model.dto.TripSummaryDTO;
import com.gyarsilalsolanki011.journeymate.model.entity.Trip;
import com.gyarsilalsolanki011.journeymate.model.enums.TripStatus;
import com.gyarsilalsolanki011.journeymate.repository.TripRepository;
import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidDate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public String createTrip(TripDTO tripDto) {

        Trip trip = TripMapper.toEntity(tripDto);

        tripRepository.save(trip);
        return "Trip created successfully";
    }


    public Page<TripDTO> getAllTrips(int page, int size, String[] sort) {
        if (sort.length < 2) {
            throw new TripServiceException("Sort parameter must include field and direction");
        }

        Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));

        return tripRepository.findAll(pageable).map(TripMapper::toDto);
    }

    public TripDTO getTripById(int id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + id));
        return TripMapper.toDto(trip);
    }

    @Transactional
    public TripDTO updateTrip(Integer tripId, TripDTO tripDto) {
        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + tripId));

        existingTrip.setDestination(tripDto.getDestination());
        existingTrip.setStartDate(LocalDate.parse(tripDto.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        existingTrip.setEndDate(LocalDate.parse(tripDto.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE));
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

    public List<TripDTO> searchTripsByDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new TripServiceException("Destination parameter must not be null or empty");
        }
        List<Trip> trips = tripRepository.findByDestinationContainingIgnoreCase(destination);
        if (trips.isEmpty()) {
            throw new TripNotFoundException("No trips found for destination: " + destination);
        }
        return trips.stream().map(TripMapper::toDto).toList();
    }

    public List<TripDTO> getTripsByStatus(String status) {
        TripStatus tripStatus = TripStatusParser.fromString(status);
        List<Trip> trips = tripRepository.findByTripStatus(tripStatus);

        if (trips.isEmpty()) {
            throw new TripNotFoundException("No trips found with status: " + status);
        }
        return trips.stream().map(TripMapper::toDto).toList();
    }

    public List<TripDTO> getTripsBetweenDates(@ValidDate String startDate, @ValidDate String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        if (start.isAfter(end)) throw new TripServiceException("Start date must not be after end date");

        List<Trip> trips = tripRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(start, end);
        if (trips.isEmpty()) {
            throw new TripNotFoundException("No trips found between " + startDate + " and " + endDate);
        }

        return trips.stream().map(TripMapper::toDto).toList();
    }

    public TripSummaryDTO getTripSummary() {
        return new TripSummaryDTO(
                tripRepository.count(),
                Optional.ofNullable(tripRepository.findMinPrice()).orElse(0.0),
                Optional.ofNullable(tripRepository.findMaxPrice()).orElse(0.0),
                Optional.ofNullable(tripRepository.findAveragePrice()).orElse(0.0)
        );
    }
}

