package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.model.Flight;
import com.sprint.flightapi.model.Gate;
import com.sprint.flightapi.repository.AirportRepository;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


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

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        Airport airport = findById(id);
        // Remove airport from all arrivals
        if (airport.getArrivals() != null) {
            for (var flight : airport.getArrivals()) {
                flight.setDestinationAirport(null);
            }
        }
        // Remove airport from all departures
        if (airport.getDepartures() != null) {
            for (var flight : airport.getDepartures()) {
                flight.setOriginAirport(null);
            }
        }
        // Remove airport from all gates
        if (airport.getGates() != null) {
            for (var gate : airport.getGates()) {
                gate.setAirport(null);
            }
        }
        // Remove this airport from all aircraft
        if (airport.getAircraft() != null) {
            for (var aircraft : airport.getAircraft()) {
                if (aircraft.getAirports() != null) {
                    aircraft.getAirports().remove(airport);
                }
            }
        }
        airportRepository.deleteById(id);
    }

    public CascadeDeletePreview cascadeDeletePreview(Long id) {
        Airport airport = findById(id);
        List<Flight> arrivals = airport.getArrivals() != null ? airport.getArrivals() : List.of();
        List<Flight> departures = airport.getDepartures() != null ? airport.getDepartures() : List.of();
        List<Flight> flights = new java.util.ArrayList<>();
        flights.addAll(arrivals);
        flights.addAll(departures);
        List<Gate> gates = airport.getGates() != null ? airport.getGates() : List.of();
        return new CascadeDeletePreview(flights, gates);
    }

    public static class CascadeDeletePreview {
        public final List<Flight> flights;
        public final List<Gate> gates;
        public CascadeDeletePreview(List<Flight> flights, List<Gate> gates) {
            this.flights = flights;
            this.gates = gates;
        }
    }
}
