package com.gyarsilalsolanki011.journeymate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripSummaryDTO {
    private long totalTrips;
    private double minPrice;
    private double maxPrice;
    private double avgPrice;
}
