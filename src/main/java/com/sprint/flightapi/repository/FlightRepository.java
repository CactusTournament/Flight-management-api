
package com.sprint.flightapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	// Find all flights arriving at a given airport
	List<Flight> findByDestinationAirportId(Long airportId);

	// Find all flights departing from a given airport
	List<Flight> findByOriginAirportId(Long airportId);
}
