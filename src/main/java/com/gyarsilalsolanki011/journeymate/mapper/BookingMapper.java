package com.gyarsilalsolanki011.journeymate.mapper;

import com.gyarsilalsolanki011.journeymate.model.dto.BookingDTO;
import com.gyarsilalsolanki011.journeymate.model.entity.Booking;
import com.gyarsilalsolanki011.journeymate.model.entity.Customer;
import com.gyarsilalsolanki011.journeymate.model.entity.Trip;
import com.gyarsilalsolanki011.journeymate.repository.CustomerRepository;
import com.gyarsilalsolanki011.journeymate.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingMapper {
    public static BookingDTO toBookingDto(Booking booking){
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setTripId(booking.getTrip().getTripId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setSeats(booking.getSeats());
       return dto;
    }

    public static Booking toBookingEntity(BookingDTO bookingDTO, Trip trip, Customer customer){
        Booking booking = new Booking();
        booking.setTrip(trip);
        booking.setCustomer(customer);
        booking.setSeats(bookingDTO.getSeats());
        return null;
    }
}
