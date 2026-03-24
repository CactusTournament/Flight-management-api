package com.sprint.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}