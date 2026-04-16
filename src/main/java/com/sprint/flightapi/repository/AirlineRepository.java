package com.sprint.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
