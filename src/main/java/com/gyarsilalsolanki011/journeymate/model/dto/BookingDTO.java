package com.gyarsilalsolanki011.journeymate.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @NotNull(message = "Seats count is required")
    @Positive(message = "Seats must be greater than 0")
    private Integer seats;
}
