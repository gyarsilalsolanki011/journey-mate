package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.exception.TripNotFoundException;
import com.gyarsilalsolanki011.journeymate.repository.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTripsBetweenDates_NoTripsFound_ShouldThrowException() {
        // Arrange
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 10);

        when(tripRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(start, end))
                .thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(TripNotFoundException.class, () ->
                tripService.getTripsBetweenDates("2025-01-01", "2025-01-10")
        );
    }
}

