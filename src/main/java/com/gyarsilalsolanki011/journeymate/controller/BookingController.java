package com.gyarsilalsolanki011.journeymate.controller;

import com.gyarsilalsolanki011.journeymate.model.dto.BookingDTO;
import com.gyarsilalsolanki011.journeymate.service.Repository.BookingService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> tripBooking(
            @RequestBody BookingDTO bookingDTO,
            Principal principal
    ) {
        return ResponseEntity.ok(bookingService.tripBooking(bookingDTO));
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(
            @NotNull @PathVariable Integer bookingId,
            Principal principal
    ) {
       return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(
            @NotNull @PathVariable Integer bookingId,
            Principal principal
    ) {
        return ResponseEntity.ok(bookingService.getBooking(bookingId));
    }
}
