package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.exception.NotFoundException;
import com.sprint.flightapi.model.Flight;
import com.sprint.flightapi.model.Passenger;
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

    public List<Flight> findArrivalsByAirport(Long airportId) {
        return flightRepository.findByDestinationAirportId(airportId);
    }

    public List<Flight> findDeparturesByAirport(Long airportId) {
        return flightRepository.findByOriginAirportId(airportId);
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

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        Flight flight = findById(id);
        if (flight.getPassengers() != null) {
            flight.getPassengers().clear();
            flightRepository.save(flight);
        }
        flightRepository.deleteById(id);
    }

    public CascadeDeletePreview cascadeDeletePreview(Long id) {
        Flight flight = findById(id);
        List<Passenger> passengers = flight.getPassengers() != null ? flight.getPassengers() : List.of();
        return new CascadeDeletePreview(passengers);
    }

    public static class CascadeDeletePreview {
        public final List<Passenger> passengers;
        public CascadeDeletePreview(List<Passenger> passengers) {
            this.passengers = passengers;
        }
    }
}

