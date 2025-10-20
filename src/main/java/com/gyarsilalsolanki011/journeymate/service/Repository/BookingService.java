package com.gyarsilalsolanki011.journeymate.service.Repository;

import com.gyarsilalsolanki011.journeymate.model.dto.BookingDTO;

public interface BookingService {
    String tripBooking(BookingDTO bookingDTO);
    String cancelBooking(Integer bookingId);
    BookingDTO getBooking(Integer bookingId);
}
