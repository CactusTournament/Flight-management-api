package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Aircraft;
import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.model.Passenger;
import com.sprint.flightapi.repository.AircraftRepository;

@Service
public class AircraftService {
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }

    public Aircraft findById(Long id) {
        return aircraftRepository.findById(id)
            .orElseThrow(() -> new com.sprint.flightapi.exception.NotFoundException("Aircraft not found"));
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

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        Aircraft aircraft = findById(id);
        if (aircraft.getPassengers() != null) {
            aircraft.getPassengers().clear();
            aircraftRepository.save(aircraft);
        }
        aircraftRepository.deleteById(id);
    }

    public List<Airport> getAirports(Long aircraftId) {
        return findById(aircraftId).getAirports();
    }

    public CascadeDeletePreview cascadeDeletePreview(Long id) {
        Aircraft aircraft = findById(id);
        List<Airport> airports = aircraft.getAirports() != null ? aircraft.getAirports() : List.of();
        List<Passenger> passengers = aircraft.getPassengers() != null ? aircraft.getPassengers() : List.of();
        return new CascadeDeletePreview(airports, passengers);
    }

    public static class CascadeDeletePreview {
        public final List<Airport> airports;
        public final List<Passenger> passengers;
        public CascadeDeletePreview(List<Airport> airports, List<Passenger> passengers) {
            this.airports = airports;
            this.passengers = passengers;
        }
    }
}

