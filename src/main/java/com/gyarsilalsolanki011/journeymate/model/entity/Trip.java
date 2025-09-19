package com.gyarsilalsolanki011.journeymate.model.entity;

import com.gyarsilalsolanki011.journeymate.model.enums.TripStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "trips")
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Integer tripId;

    @NotBlank(message = "Destination is required")
    @Column(nullable = false, length = 100)
    private String destination;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Trip status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "trip_status", nullable = false, length = 20)
    private TripStatus tripStatus;
}
