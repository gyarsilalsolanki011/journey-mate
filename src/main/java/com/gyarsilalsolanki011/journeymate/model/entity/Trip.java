package com.gyarsilalsolanki011.journeymate.model.entity;

import com.gyarsilalsolanki011.journeymate.model.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", nullable = false)
    private Integer tripId;

    @Column(nullable = false, length = 100)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_status", nullable = false, length = 20)
    private TripStatus tripStatus;
}
