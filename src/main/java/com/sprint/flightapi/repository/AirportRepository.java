package com.sprint.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}