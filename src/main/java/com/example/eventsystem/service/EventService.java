package com.example.eventsystem.service;


import com.example.eventsystem.model.Event;
import com.example.eventsystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEventList() {
        return eventRepository.findAll(); // this will return a list to us
    }

    public Event getSingleEvent(Long eventId) {
        return eventRepository.findEventById(eventId).orElseThrow();
    }



    public void addNewEvent(Event event) {
        Optional<Event> eventOptional = eventRepository.findEventByName(event.getName()); // ctrl alt v
        if (eventOptional.isPresent()) {
            throw new IllegalStateException("The event name is already taken");
        }
        eventRepository.save(event);
        System.out.println(event);
    }

    public void deleteEvent(Long eventId) {
        boolean exists = eventRepository.existsById(eventId);
        if (!exists) {
            throw new IllegalStateException(
                    "event with id " + eventId + " does not exist");
        }
        eventRepository.deleteById(eventId);
    }

    @Transactional
    public void updateEvent(Long eventId, String name, LocalTime time, LocalDate date, String capacity, Integer price) {
        // checks if event id exists
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalStateException(
                "Event with id " + eventId + " does not exists. "));

        // update name
        if (name != null && name.length() > 5 && !Objects.equals(event.getName(), name)) {
            Optional<Event> eventOptional = eventRepository.findEventByName(name);
            if (eventOptional.isPresent()) {
                throw new IllegalStateException("The event name has already been taken.");
            }
            event.setName(name);
        }

        // update time
        if (time != null && !Objects.equals(event.getTime(), time)) {
            event.setTime(time);
        }

        // update date
        if (date != null && !Objects.equals(event.getDate(), date)) {
            event.setDate(date);
        }

        // update capacity
        if (capacity != null && capacity.length() > 2 && !Objects.equals(event.getCapacity(), capacity)) {
            event.setCapacity(capacity);
        }

        //update price
        if (price != null  && !Objects.equals(event.getPrice(), price)) {
            event.setPrice(price);
        }
    }

    }



