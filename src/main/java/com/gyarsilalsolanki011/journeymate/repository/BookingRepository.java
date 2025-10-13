package com.gyarsilalsolanki011.journeymate.repository;

import com.gyarsilalsolanki011.journeymate.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
