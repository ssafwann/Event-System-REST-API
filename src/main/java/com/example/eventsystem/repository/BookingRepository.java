package com.example.eventsystem.repository;

import com.example.eventsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface BookingRepository extends JpaRepository<Booking, Long > {

    @Query()
    Optional<Booking> findBookingById(Long id);

    @Query(value = "SELECT * from bookings, user where bookings.user_id = :id", nativeQuery = true)
    List<Booking> findByUserid(Long id);

    @Query(value = "SELECT * from bookings, user where bookings.event_id = :id", nativeQuery = true)
    List<Booking> findByEventid(Long id);

}
