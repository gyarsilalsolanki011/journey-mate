package com.gyarsilalsolanki011.journeymate.service.Repository;

import com.gyarsilalsolanki011.journeymate.model.dto.BookingDTO;

public interface BookingService {
    String bookTrip(BookingDTO bookingDTO);
    String cancelBooking(Integer bookingId);
}
