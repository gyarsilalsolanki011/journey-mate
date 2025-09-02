package com.gyarsilalsolanki011.journeymate.dto;

import com.gyarsilalsolanki011.journeymate.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private Status status;
}
