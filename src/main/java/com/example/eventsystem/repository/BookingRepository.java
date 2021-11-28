package com.example.eventsystem.repository;

import com.example.eventsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// TODO: need to implement way to get all bookings made by a certain user
// TODO: need to implement way to get all the bookings for a single event

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long > {

    @Query
    Optional<Booking> findBookingById(Long id);



}
