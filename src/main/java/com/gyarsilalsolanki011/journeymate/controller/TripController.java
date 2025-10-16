package com.gyarsilalsolanki011.journeymate.controller;

import com.gyarsilalsolanki011.journeymate.model.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.model.dto.TripSummary;
import com.gyarsilalsolanki011.journeymate.service.Repository.TripService;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidDate;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidTripStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/trip")
public class TripController {
    private final TripService tripService;
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<String> createTrip(
            @Valid @RequestBody TripDTO tripDto,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.createTrip(tripDto));
    }

    @GetMapping
    public ResponseEntity<Page<TripDTO>> getAllTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.getAllTrips(page, size, sort));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDTO> getTrip(
            @NotNull @PathVariable Integer tripId,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.getTripById(tripId));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<TripDTO> updateTrip(
            @NotNull @PathVariable Integer tripId,
            @Valid @RequestBody TripDTO tripDto,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.updateTrip(tripId, tripDto));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<String> deleteTrip(
            @NotNull @PathVariable Integer tripId,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.deleteTrip(tripId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TripDTO>> searchTrips(
            @NotBlank @RequestParam String destination,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.searchTripsByDestination(destination));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TripDTO>> getTripsByStatus(
            @NotBlank @ValidTripStatus @RequestParam String tripStatus,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.getTripsByStatus(tripStatus));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<TripDTO>> getTripsBetweenDates(
            @ValidDate @NotBlank @RequestParam String startDate,
            @ValidDate @NotBlank @RequestParam String endDate,
            Principal principal
    ) {
        return ResponseEntity.ok().body(tripService.getTripsBetweenDates(startDate, endDate));
    }

    @GetMapping("/summary")
    public ResponseEntity<TripSummary> getTripSummary(Principal principal) {
        return ResponseEntity.ok().body(tripService.getTripSummary());
    }

}
