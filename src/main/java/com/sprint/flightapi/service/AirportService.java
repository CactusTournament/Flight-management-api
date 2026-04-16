package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.repository.AirportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    public Airport findById(Long id) {
        return airportRepository.findById(id)
            .orElseThrow(() -> new com.sprint.flightapi.exception.NotFoundException("Airport not found"));
    }

    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport update(Long id, Airport updated) {
        Airport existing = findById(id);
        existing.setName(updated.getName());
        existing.setCode(updated.getCode());
        existing.setCity(updated.getCity());
        return airportRepository.save(existing);
    }

    public void delete(Long id) {
        airportRepository.deleteById(id);
    }
}