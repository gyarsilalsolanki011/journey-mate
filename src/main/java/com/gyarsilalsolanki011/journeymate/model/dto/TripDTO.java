package com.gyarsilalsolanki011.journeymate.model.dto;

import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidTripDates;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidTripStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidTripDates
public class TripDTO {
    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Start date must not null")
    private LocalDate startDate;

    @NotNull(message = "End date must not null")
    private LocalDate endDate;

    @Positive(message = "Price must be positive")
    private Double price;

    @ValidTripStatus
    @NotBlank(message = "Trip status is required")
    private String tripStatus;
}
