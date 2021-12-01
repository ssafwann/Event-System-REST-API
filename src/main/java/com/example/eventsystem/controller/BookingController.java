package com.example.eventsystem.controller;

import com.example.eventsystem.model.Booking;
import com.example.eventsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "eventsystem/bookings")
public class BookingController {

    private final BookingService bookingService; //

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookingService.getBookings();
    }

    @GetMapping(path = "{bookingId}")
    public Booking getSingleBooking(@PathVariable("bookingId") Long bookingId) {
        return bookingService.getSingleBooking(bookingId);}

    @GetMapping(path = "/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserBookings(userId);}

    @GetMapping(path = "/event/{eventId}")
    public List<Booking> getEventBookings(@PathVariable("eventId") Long eventId) {
        return bookingService.getEventBookings(eventId);}

    @PostMapping
    public Booking addNewBooking(@RequestBody Booking booking) {
        bookingService.addNewBooking(booking);
        return getSingleBooking(booking.getId());
    }

    @DeleteMapping (path = "{bookingId}")
    public void deleteBooking(@PathVariable("bookingId") Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }

    @PutMapping(path = "{bookingId}")
    public Booking updateBooking (
            @PathVariable("bookingId") Long bookingId,
            @RequestParam(required = false) Integer tickets ) {
        bookingService.updateBooking(bookingId, tickets);
        return getSingleBooking(bookingId);

    }



}
