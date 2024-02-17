package com.doremi.booking;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
		LOGGER.info("Booking Doremi is now running...");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
