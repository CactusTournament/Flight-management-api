package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Airline;
import com.sprint.flightapi.repository.AirlineRepository;

@Service
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    public Airline findById(Long id) {
        return airlineRepository.findById(id)
            .orElseThrow(() -> new com.sprint.flightapi.exception.NotFoundException("Airline not found"));
    }

    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline update(Long id, Airline airline) {
        Airline existing = findById(id);
        airline.setId(id);
        return airlineRepository.save(airline);
    }

    public void delete(Long id) {
        airlineRepository.deleteById(id);
    }
}
