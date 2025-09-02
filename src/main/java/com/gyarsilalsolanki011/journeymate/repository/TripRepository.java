package com.gyarsilalsolanki011.journeymate.repository;

import com.gyarsilalsolanki011.journeymate.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByDestinationContainingIgnoreCase(String destination);
}
