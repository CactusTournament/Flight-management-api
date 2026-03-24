package com.sprint.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}