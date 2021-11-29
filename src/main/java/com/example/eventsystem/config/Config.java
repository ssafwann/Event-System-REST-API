package com.example.eventsystem.config;

import com.example.eventsystem.model.Booking;
import com.example.eventsystem.model.Event;
import com.example.eventsystem.model.User;
import com.example.eventsystem.repository.BookingRepository;
import com.example.eventsystem.repository.EventRepository;
import com.example.eventsystem.repository.UserRepository;
import com.example.eventsystem.service.EventService;
import com.example.eventsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;


@Configuration
public class Config {
    @Bean
    @Order(0)
    CommandLineRunner loadUsers(UserRepository repository) {
        return args-> {
            User antoinette = new User(
                    "Antoinette",
                    "J Brown",
                    "jacynthe1995@hotmail.com",
                    "Su3pheeGh",
                    LocalDate.of(1984, Month.NOVEMBER, 19)
            );

            User richard = new User(
                    "Richard",
                    "S Wilson",
                    "dina.corker3@yahoo.com",
                    "IuRoa5iki",
                    LocalDate.of(1975, Month.AUGUST, 5)
            );

            User christopher  = new User(
                    "Christopher",
                    "C Gonzalez",
                    "lora.pfeffe8@yahoo.com",
                    "iozeSoo0",
                    LocalDate.of(1962, Month.OCTOBER, 7)
            );

            User tyler = new User(
                    "Tyler ",
                    "R Lane",
                    "skye1997@gmail.com",
                    "wac5shiDai",
                    LocalDate.of(2001, Month.JULY, 5)
            );

            User hattie = new User(
                    "Hattie",
                    "J Gordon",
                    "carmine1999@gmail.com",
                    "xieruR8M",
                    LocalDate.of(2001, Month.MARCH, 2)
            );

            // this will store the data into the database
            repository.saveAll(
                    List.of(antoinette,richard, christopher, tyler, hattie)
            );
        };
    }

    @Bean
    @Order(0)
    CommandLineRunner loadEvents(EventRepository repository) {
        return args -> {
            Event event1 = new Event(
                    "Sharing on Bachelor Degree Experience",
                    LocalDate.of(2021, Month.NOVEMBER, 05),
                    LocalTime.of(14,30),
                    "60",
                    30
            );

            Event event2 = new Event(
                    "Resume Writing Workshop",
                    LocalDate.of(2021, Month.DECEMBER, 05),
                    LocalTime.of(18,00),
                    "20",
                    0
            );

            Event event3 = new Event(
                    "Learn HTML workshop",
                    LocalDate.of(2021, Month.OCTOBER, 15),
                    LocalTime.of(14,30),
                    "30",
                    50
            );

            Event event4 = new Event(
                    "Sharing from alumni",
                    LocalDate.of(2021, Month.NOVEMBER, 20),
                    LocalTime.of(10,00),
                    "40",
                    0
            );

            Event event5 = new Event(
                    "IELTS training",
                    LocalDate.of(2021, Month.DECEMBER, 10),
                    LocalTime.of(16,30),
                    "40",
                    120
            );

            Event event6 = new Event(
                    "Sharing from chancellor",
                    LocalDate.of(2021, Month.NOVEMBER, 25),
                    LocalTime.of(10,00),
                    "80",
                    0
            );


            repository.saveAll(
                    List.of(event1, event2, event3, event4, event5, event6)
            );
        };
    }

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @Bean
    @Order(3)
    CommandLineRunner loadBookings(BookingRepository repository) {
        return args -> {
            Booking book1 = new Booking(
                    eventService.getSingleEvent(1L),
                    userService.getSingleUser(2L),
                    2
            );

            Booking book2 = new Booking(
                    eventService.getSingleEvent(2L),
                    userService.getSingleUser(5L),
                    1
            );
            Booking book3 = new Booking(
                    eventService.getSingleEvent(2L),
                    userService.getSingleUser(1L),
                    1
            );
            Booking book4 = new Booking(
                    eventService.getSingleEvent(1L),
                    userService.getSingleUser(4L),
                    2
            );
            Booking book5 = new Booking(
                    eventService.getSingleEvent(4L),
                    userService.getSingleUser(2L),
                    1
            );
            repository.saveAll(
                    List.of(book1,book2,book3,book4,book5)
            );

        };
    }
}
