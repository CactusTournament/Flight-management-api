package com.sprint.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.flightapi.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
}