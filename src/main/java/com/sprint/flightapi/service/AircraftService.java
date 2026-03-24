package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Aircraft;
import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.repository.AircraftRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }

    public Aircraft findById(Long id) {
        return aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));
    }

    public Aircraft save(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft update(Long id, Aircraft updated) {
        Aircraft existing = findById(id);
        existing.setType(updated.getType());
        existing.setAirlineName(updated.getAirlineName());
        existing.setNumberOfPassengers(updated.getNumberOfPassengers());
        return aircraftRepository.save(existing);
    }

    public void delete(Long id) {
        aircraftRepository.deleteById(id);
    }

    public List<Airport> getAirports(Long aircraftId) {
        return findById(aircraftId).getAirports();
    }
}