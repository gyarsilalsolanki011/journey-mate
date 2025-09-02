package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.dto.TripDto;
import com.gyarsilalsolanki011.journeymate.entity.Trip;
import com.gyarsilalsolanki011.journeymate.entity.TripSummary;
import com.gyarsilalsolanki011.journeymate.enums.TripStatus;
import com.gyarsilalsolanki011.journeymate.exception.TripNotFoundException;
import com.gyarsilalsolanki011.journeymate.exception.TripServiceException;
import com.gyarsilalsolanki011.journeymate.mapper.TripMapper;
import com.gyarsilalsolanki011.journeymate.repository.TripRepository;
import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public String createTrip(TripDto tripDto) {
        try {
            Trip trip = TripMapper.toEntity(tripDto);
            try {
                if (!trip.isEndDateAfterStartDate()) {
                    throw new TripServiceException("End date must be after start date");
                }
            } catch (Exception e) {
                throw new TripServiceException("Invalid date format", e);
            }
            tripRepository.save(trip);
            return "Trip created successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error creating trip", e);
        }
    }

    public Page<TripDto> getAllTrips(int page, int size, String[] sort) {
        try {
            Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
            return tripRepository.findAll(pageable).map(TripMapper::toDto);
        } catch (Exception e) {
            throw new TripNotFoundException("Error fetching trips", e);
        }
    }

    public TripDto getTripById(int id) {
        try {
            Trip trip = tripRepository.findById(id)
                    .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + id));
            return TripMapper.toDto(trip);
        } catch (Exception e) {
            throw new TripNotFoundException("Error fetching trip by id", e);
        }
    }

    public TripDto updateTrip(Integer tripId, TripDto tripDto) {
        try {
            Trip existingTrip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + tripId));
            existingTrip.setDestination(tripDto.getDestination());
            existingTrip.setStartDate(tripDto.getStartDate());
            if (existingTrip.isEndDateAfterStartDate()){
                existingTrip.setEndDate(tripDto.getEndDate());
            } else {
                throw new TripServiceException("End date must be after start date");
            }
            existingTrip.setEndDate(tripDto.getEndDate());
            existingTrip.setPrice(tripDto.getPrice());
            existingTrip.setTripStatus(tripDto.getTripStatus());
            Trip updatedTrip = tripRepository.save(existingTrip);
            return TripMapper.toDto(updatedTrip);
        } catch (Exception e) {
            throw new RuntimeException("Error updating trip", e);
        }
    }

    public String deleteTrip(Integer tripId) {
        try {
            Trip existingTrip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));
            tripRepository.delete(existingTrip);
            return "Trip deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error deleting trip", e);
        }
    }

    public List<TripDto> searchTripsByDestination(String destination) {
        try {
            List<Trip> trip = tripRepository.findByDestinationContainingIgnoreCase(destination);
            return trip.stream().map(TripMapper::toDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error searching trips by destination", e);
        }
    }

    public List<TripDto> getTripsByStatus(String status) {
        try {
            TripStatus tripStatus = TripStatusParser.fromString(status);
            List<Trip> trips = tripRepository.findAll().stream()
                    .filter(trip -> trip.getTripStatus().name().equalsIgnoreCase(tripStatus.name()))
                    .toList();
            return trips.stream().map(TripMapper::toDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching trips by status", e);
        }
    }

    public List<TripDto> getTripsBetweenDates(String startDate, String endDate) {
        try {
            List<Trip> trips = tripRepository.findAll().stream()
                    .filter(trip -> !trip.getStartDate().isBefore(java.time.LocalDate.parse(startDate)) &&
                            !trip.getEndDate().isAfter(java.time.LocalDate.parse(endDate)))
                    .toList();
            return trips.stream().map(TripMapper::toDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching trips between dates", e);
        }
    }

    public TripSummary getTripSummary() {
        try {
            long totalTrips = tripRepository.count();
            double minPrice = tripRepository.findAll().stream()
                    .mapToDouble(Trip::getPrice)
                    .min()
                    .orElse(0.0);
            double maxPrice = tripRepository.findAll().stream()
                    .mapToDouble(Trip::getPrice)
                    .max()
                    .orElse(0.0);
            double averagePrice = tripRepository.findAll().stream()
                    .mapToDouble(Trip::getPrice)
                    .average()
                    .orElse(0.0);
            return new TripSummary(totalTrips, minPrice, maxPrice, averagePrice);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching trip summary", e);
        }
    }
}
