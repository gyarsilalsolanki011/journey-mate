package com.gyarsilalsolanki011.journeymate.dto;

import com.gyarsilalsolanki011.journeymate.enums.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private String destination;
    private String startDate;
    private String endDate;
    private Double price;
    private String tripStatus;
}
