package com.gyarsilalsolanki011.journeymate.controller;

import com.gyarsilalsolanki011.journeymate.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.dto.TripSummaryDTO;
import com.gyarsilalsolanki011.journeymate.service.TripService;
import com.gyarsilalsolanki011.journeymate.validation.validator.ValidTripStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@Validated
public class TripController {
    private final TripService tripService;
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<String> createTrip(@Valid @RequestBody TripDTO tripDto) {
        return ResponseEntity.ok().body(tripService.createTrip(tripDto));
    }

    @GetMapping
    public ResponseEntity<Page<TripDTO>> getAllTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        return ResponseEntity.ok().body(tripService.getAllTrips(page, size, sort));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDTO> getTrip(@NotNull @PathVariable Integer tripId) {
        return ResponseEntity.ok().body(tripService.getTripById(tripId));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<TripDTO> updateTrip(
            @NotNull @PathVariable Integer tripId,
            @Valid @RequestBody TripDTO tripDto
    ) {
        return ResponseEntity.ok().body(tripService.updateTrip(tripId, tripDto));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<String> deleteTrip(@NotNull @PathVariable Integer tripId) {
        return ResponseEntity.ok().body(tripService.deleteTrip(tripId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TripDTO>> searchTrips(@NotBlank @RequestParam String destination) {
        return ResponseEntity.ok().body(tripService.searchTripsByDestination(destination));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TripDTO>> getTripsByStatus(@NotBlank @ValidTripStatus @RequestParam String tripStatus) {
        return ResponseEntity.ok().body(tripService.getTripsByStatus(tripStatus));
    }

    @GetMapping("/daterange")
    public ResponseEntity<List<TripDTO>> getTripsBetweenDates(
            @NotBlank @RequestParam String startDate,
            @NotBlank @RequestParam String endDate
    ) {
        return ResponseEntity.ok().body(tripService.getTripsBetweenDates(startDate, endDate));
    }

    @GetMapping("/summary")
    public ResponseEntity<TripSummaryDTO> getTripSummary() {
        return ResponseEntity.ok().body(tripService.getTripSummary());
    }

}
