package com.example.eventsystem.controller;

import com.example.eventsystem.model.Event;
import com.example.eventsystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping(path = "eventsystem/events")
public class EventController {

    private final EventService eventService; // beam

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEventList() {
        return eventService.getEventList();
    }

    @GetMapping(path = "{eventId}")
    public Event getSingleEvent(@PathVariable("eventId") Long eventId) {return eventService.getSingleEvent(eventId);}

    @PostMapping
    public void registerNewEvent(@RequestBody Event event) {
        eventService.addNewEvent(event);
    }

    @DeleteMapping (path = "{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
    }

    @PutMapping(path = "{eventId}")
    public void updateEvent (
            @PathVariable("eventId") Long eventId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.TIME) LocalTime time,
            @RequestParam(required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String capacity,
            @RequestParam(required = false) Integer price ) {
        eventService.updateEvent(eventId,name, time,date, capacity, price);

    }


}
