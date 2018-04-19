package org.movies.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoviesReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesReservationApplication.class, args);
	}
}
