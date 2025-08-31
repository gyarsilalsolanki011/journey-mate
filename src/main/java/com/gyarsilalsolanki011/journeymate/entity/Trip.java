package com.gyarsilalsolanki011.journeymate.entity;

import com.gyarsilalsolanki011.journeymate.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "trips-db")
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
    private Status status;

    // ✅ Custom validation
    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateAfterStartDate() {
        if (startDate == null || endDate == null) return true; // handled by @NotNull already
        return endDate.isAfter(startDate);
    }
}
