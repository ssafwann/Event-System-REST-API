
package com.example.eventsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EventsystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventsystemApplication.class, args);
	}

}
