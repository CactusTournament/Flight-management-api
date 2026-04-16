package com.sprint.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
