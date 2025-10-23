package com.gyarsilalsolanki011.journeymate.service.Repository;

import com.gyarsilalsolanki011.journeymate.model.dto.BookingDTO;

import java.security.Principal;

public interface BookingService {
    String tripBooking(BookingDTO bookingDTO, Principal principal);
    String cancelBooking(Integer bookingId, Principal principal);
    BookingDTO getBooking(Integer bookingId, Principal principal);
}
