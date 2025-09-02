package com.gyarsilalsolanki011.journeymate.controller;

import com.gyarsilalsolanki011.journeymate.dto.TripDto;
import com.gyarsilalsolanki011.journeymate.entity.TripSummary;
import com.gyarsilalsolanki011.journeymate.service.TripService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/api/trips")
public class TripController {
    private final TripService tripService;
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<String> createTrip(@RequestBody TripDto tripDto) {
        return ResponseEntity.ok().body(tripService.createTrip(tripDto));
    }

    @GetMapping
    public ResponseEntity<Page<TripDto>> getAllTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        return ResponseEntity.ok().body(tripService.getAllTrips(page, size, sort));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Integer tripId) {
        return ResponseEntity.ok().body(tripService.getTripById(tripId));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Integer tripId, @RequestBody TripDto tripDto) {
        return ResponseEntity.ok().body(tripService.updateTrip(tripId, tripDto));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<String> deleteTrip(@PathVariable Integer tripId) {
        return ResponseEntity.ok().body(tripService.deleteTrip(tripId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TripDto>> searchTrips(@RequestParam String destination) {
        return ResponseEntity.ok().body(tripService.searchTripsByDestination(destination));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TripDto>> getTripsByStatus(@RequestParam String status) {
        return ResponseEntity.ok().body(tripService.getTripsByStatus(status));
    }

    @GetMapping("/daterange")
    public ResponseEntity<List<TripDto>> getTripsBetweenDates(@RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok().body(tripService.getTripsBetweenDates(startDate, endDate));
    }

    @GetMapping("/summary")
    public ResponseEntity<TripSummary> getTripSummary() {
        return ResponseEntity.ok().body(tripService.getTripSummary());
    }

}
