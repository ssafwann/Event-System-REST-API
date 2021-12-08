/*
    The class which is used to do the query requests involving events
*/
package com.example.eventsystem.repository;

import com.example.eventsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query
    Optional<Event> findEventByName(String name);

    @Query
    Optional<Event> findEventById(Long id);
}
