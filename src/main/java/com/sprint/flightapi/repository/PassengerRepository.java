package com.sprint.flightapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	Optional<Passenger> findByUsername(String username);
	Optional<Passenger> findByEmail(String email);
}