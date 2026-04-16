package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.exception.NotFoundException;
import com.sprint.flightapi.model.Flight;
import com.sprint.flightapi.repository.FlightRepository;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }


    public Flight findById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found with id: " + id));
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }


    public Flight update(Long id, Flight flight) {
        if (!flightRepository.existsById(id)) {
            throw new NotFoundException("Flight not found with id: " + id);
        }
        flight.setId(id);
        return flightRepository.save(flight);
    }

    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
