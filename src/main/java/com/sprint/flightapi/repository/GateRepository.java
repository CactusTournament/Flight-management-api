package com.sprint.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.Gate;

public interface GateRepository extends JpaRepository<Gate, Long> {
}
