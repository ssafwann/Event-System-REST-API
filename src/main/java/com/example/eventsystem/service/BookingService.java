package com.example.eventsystem.service;

import com.example.eventsystem.model.Booking;;
import com.example.eventsystem.repository.BookingRepository;
import com.example.eventsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BookingService {

   private final BookingRepository bookingRepository;

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private UserService userService;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public List<Booking> getBookingList() {
        return bookingRepository.findAll();
    }

    public Booking getSingleBooking(Long bookingId) {
        return bookingRepository.findBookingById(bookingId).orElseThrow();
    }

    /*
    public List<Booking> getAllBookingsByUser(Long userId) {
        List <Booking> bookings = getBookingList(); // give us all bookings
        List <Booking> userBookings = null;
        List <User> allUsers = userService.getUser();

        for (int i = 0 ; i < bookings.size(); i++) {
            if (bookings.get(i).getUserBooked().getId() == allUsers.get(i).getId()) {
                userBookings.add(bookings.get(i));
               // userBookings.add(bookings.get(i));
            }
        }
        return userBookings;
    }
    */

    public void addNewBooking(Booking booking) {
        Optional<Booking> bookingOptional = bookingRepository.findBookingById(booking.getId());
        if (bookingOptional.isPresent()) {
            throw new IllegalStateException("The booking ID is already taken");
        }
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        boolean exists = bookingRepository.existsById(bookingId);
        if (!exists) {
            throw new IllegalStateException(
                    "booking with id " + bookingId + " does not exist");
        }
        bookingRepository.deleteById(bookingId);
    }

    @Transactional
    public void updateBooking(Long bookingId, Integer tickets) {
        // checks if booking id exists
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalStateException(
                "Booking with id " + bookingId + " does not exists. "));

        // update tickets and re-calculates amount
        if (tickets != null  && !Objects.equals(booking.getTickets(), tickets)) {
            booking.setTickets(tickets);
            booking.setAmount(booking.getAmount());
        }
        else {
            throw new IllegalStateException(
                    "The update was unsuccessful, try entering another amount" );
        }
    }
}
