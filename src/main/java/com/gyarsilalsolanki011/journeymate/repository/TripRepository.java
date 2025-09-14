package com.gyarsilalsolanki011.journeymate.repository;

import com.gyarsilalsolanki011.journeymate.model.entity.Trip;
import com.gyarsilalsolanki011.journeymate.model.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByDestinationContainingIgnoreCase(String destination);
    List<Trip> findByTripStatus(TripStatus status);
    List<Trip> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate start, LocalDate end);
    @Query("SELECT MIN(t.price) FROM Trip t")
    Double findMinPrice();
    @Query("SELECT MAX(t.price) FROM Trip t")
    Double findMaxPrice();
    @Query("SELECT AVG(t.price) FROM Trip t")
    Double findAveragePrice();

}
