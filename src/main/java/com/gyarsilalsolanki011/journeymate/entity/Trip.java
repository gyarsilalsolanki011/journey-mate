package com.gyarsilalsolanki011.journeymate.entity;

import com.gyarsilalsolanki011.journeymate.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.enums.TripStatus;
import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

import static com.gyarsilalsolanki011.journeymate.mapper.TripMapper.FORMATTER;

@Data
@Entity
@Table(name = "trips")
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Positive(message = "Price must be positive")
    private Double price;

    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    public Trip(TripDTO tripDto) {
        this.destination = tripDto.getDestination();
        this.startDate = tripDto.getStartDate();
        this.endDate = tripDto.getEndDate();
        this.price = tripDto.getPrice();
        this.tripStatus = TripStatusParser.fromString(tripDto.getTripStatus());
    }
}
