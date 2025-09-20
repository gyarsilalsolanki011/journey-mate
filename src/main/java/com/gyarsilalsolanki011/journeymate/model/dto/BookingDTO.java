package com.gyarsilalsolanki011.journeymate.model.dto;

import com.gyarsilalsolanki011.journeymate.model.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer bookingId;

    private Trip trip;

    private Customer customer;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @NotNull(message = "Seats count is required")
    @Positive(message = "Seats must be greater than 0")
    private Integer seats;
}
