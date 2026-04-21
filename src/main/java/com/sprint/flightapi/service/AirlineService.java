package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Airline;
import com.sprint.flightapi.model.Aircraft;
import com.sprint.flightapi.model.Flight;
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

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        Airline airline = findById(id);
        // Remove airline from all aircraft
        if (airline.getAircraft() != null) {
            for (var aircraft : airline.getAircraft()) {
                aircraft.setAirline(null);
                // Remove airline from all flights of this aircraft
                if (aircraft.getFlights() != null) {
                    for (var flight : aircraft.getFlights()) {
                        if (flight.getAirline() != null && flight.getAirline().equals(airline)) {
                            flight.setAirline(null);
                        }
                    }
                }
            }
        }
        // Remove airline from all flights
        if (airline.getFlights() != null) {
            for (var flight : airline.getFlights()) {
                flight.setAirline(null);
            }
        }
        airlineRepository.deleteById(id);
    }

    public CascadeDeletePreview cascadeDeletePreview(Long id) {
        Airline airline = findById(id);
        List<Aircraft> aircraft = airline.getAircraft() != null ? airline.getAircraft() : List.of();
        List<Flight> flights = airline.getFlights() != null ? airline.getFlights() : List.of();
        return new CascadeDeletePreview(aircraft, flights);
    }

    public static class CascadeDeletePreview {
        public final List<Aircraft> aircraft;
        public final List<Flight> flights;
        public CascadeDeletePreview(List<Aircraft> aircraft, List<Flight> flights) {
            this.aircraft = aircraft;
            this.flights = flights;
        }
    }
}

