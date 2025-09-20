package com.gyarsilalsolanki011.journeymate.model.dto;

import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidDate;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidTripDates;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidTripStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@ValidTripDates
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {
    @NotBlank(message = "Destination is required")
    @Size(max = 100, message = "Destination can have at most 100 characters")
    private String destination;

    @ValidDate
    @NotBlank(message = "Start date must not null")
    private String startDate;

    @ValidDate
    @NotNull(message = "End date must not null")
    private String endDate;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @ValidTripStatus
    @NotBlank(message = "Trip status is required")
    private String tripStatus;
}
