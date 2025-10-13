package com.gyarsilalsolanki011.journeymate.service.Repository;

import com.gyarsilalsolanki011.journeymate.model.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.model.dto.TripSummaryDTO;
import org.springframework.data.domain.Page;
import java.util.List;


public interface TripService {
    String createTrip(TripDTO tripDto);
    Page<TripDTO> getAllTrips(int page, int size, String[] sort);
    TripDTO getTripById(int id);
    TripDTO updateTrip(Integer tripId, TripDTO tripDto);
    String deleteTrip(Integer tripId);
    List<TripDTO> searchTripsByDestination(String destination);
    List<TripDTO> getTripsByStatus(String status);
    List<TripDTO> getTripsBetweenDates(String startDate, String endDate);
    TripSummaryDTO getTripSummary();
}
