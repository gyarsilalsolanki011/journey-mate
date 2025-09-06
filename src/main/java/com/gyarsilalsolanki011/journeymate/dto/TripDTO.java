package com.gyarsilalsolanki011.journeymate.dto;

import com.gyarsilalsolanki011.journeymate.validator.ValidTripDates;
import com.gyarsilalsolanki011.journeymate.validator.ValidTripStatus;
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
    @NotBlank
    private String destination;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Positive
    private Double price;

    @ValidTripStatus
    private String tripStatus;
}
