package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.exception.EntityNotFoundException;
import com.gyarsilalsolanki011.journeymate.exception.InternalServiceException;
import com.gyarsilalsolanki011.journeymate.mapper.BookingMapper;
import com.gyarsilalsolanki011.journeymate.model.dto.BookingDTO;
import com.gyarsilalsolanki011.journeymate.model.entity.Booking;
import com.gyarsilalsolanki011.journeymate.model.entity.Customer;
import com.gyarsilalsolanki011.journeymate.model.entity.Trip;
import com.gyarsilalsolanki011.journeymate.repository.BookingRepository;
import com.gyarsilalsolanki011.journeymate.repository.CustomerRepository;
import com.gyarsilalsolanki011.journeymate.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class BookingService implements com.gyarsilalsolanki011.journeymate.service.Repository.BookingService {
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final TripRepository tripRepository;

    public BookingService(CustomerRepository customerRepository, BookingRepository bookingRepository, TripRepository tripRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
    }


    @Override
    public String tripBooking(BookingDTO bookingDTO, Principal principal) {
        String email = principal.getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer Not Found with email: " + email));

        Trip trip = tripRepository.findById(bookingDTO.getTripId())
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + bookingDTO.getTripId()));

        Booking booking = BookingMapper.toBookingEntity(bookingDTO, trip, customer);
        try {
            assert booking != null;
            bookingRepository.save(booking);
        } catch (Exception e){
            throw new InternalServiceException("Booking Service error while saving the data");
        }
        return "Trip booked successfully";
    }

    @Override
    public String cancelBooking(Integer bookingId, Principal principal) {
        String email = principal.getName();
        customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer Not Found with email: " + email));

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("No booking found with id: " + bookingId));

        try {
            bookingRepository.delete(booking);
        } catch (Exception e){
            throw new InternalServiceException("Service Error while deleting the Booking");
        }

        return "Booking Cancelled successfully!";
    }

    @Override
    public BookingDTO getBooking(Integer bookingId, Principal principal) {
        String email = principal.getName();
        customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer Not Found with email: " + email));

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with Id: " + bookingId));

        return BookingMapper.toBookingDto(booking);
    }
}
