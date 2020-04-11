package com.melcrys.pnsMilage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.melcrys.pnsMilage.common.Greeting;
import com.melcrys.pnsMilage.common.Location;
import com.melcrys.pnsMilage.common.Milage;
import com.melcrys.pnsMilage.common.Trip;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class PnsMilageApplication {
	Logger logger = Logger.getAnonymousLogger();

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	public static void main(String[] args) {
		SpringApplication.run(PnsMilageApplication.class, args);
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping(value = "/pns")
	public Object triggerMilageReport (@RequestParam(value = "trip", defaultValue = "null") String entity) {
		

		//Setup
		Trip trip1 = new Trip(new Date(), Location.HONEA_PATH,"Resource Team");
		Trip trip2 = new Trip(new Date(),Location.WREN,"Resource Team");
		Trip trip3 = new Trip(new Date(),Location.CENTERVILLE,"Resource Team");
		Trip trip4 = new Trip(new Date(),Location.LAKESIDE,"Resource Team");
		Trip trip5 = new Trip(new Date(),Location.HARTWELL,"Resource Team");
		Trip trip6 = new Trip(new Date(),Location.CLEMSON,"Resource Team");
		List<Trip> trips = new ArrayList<>();
		trips.add(trip1);
		trips.add(trip2);
		trips.add(trip3);
		trips.add(trip4);
		trips.add(trip5);
		trips.add(trip6);
		Milage milage = new Milage("Melvin","Cureton","12345",trips);
		return milage;
	}

	@GetMapping(value = "/pnsl")
	public List<Field> getLocations () throws JsonProcessingException {
		logger.info("Returning list of locations.");
		return Location.getLocations();
	}
}
