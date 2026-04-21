
package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprint.flightapi.dto.SignupRequest;
import com.sprint.flightapi.exception.NotFoundException;
import com.sprint.flightapi.model.Aircraft;
import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.model.Passenger;
import com.sprint.flightapi.repository.PassengerRepository;
import com.sprint.flightapi.repository.AircraftRepository;
import com.sprint.flightapi.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;
    @Autowired
    private com.sprint.flightapi.repository.CityRepository cityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Passenger signup(SignupRequest request) {
        // Validate required fields
        if (request.getUsername() == null || request.getUsername().isBlank())
            throw new IllegalArgumentException("Username is required");
        if (request.getPassword() == null || request.getPassword().isBlank())
            throw new IllegalArgumentException("Password is required");
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new IllegalArgumentException("Passwords do not match");
        if (passengerRepository.findByUsername(request.getUsername()).isPresent())
            throw new IllegalArgumentException("Username already exists");
        if (request.getEmail() != null && passengerRepository.findByEmail(request.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists");

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setUsername(request.getUsername());
        passenger.setEmail(request.getEmail());
        passenger.setPhoneNumber(request.getPhoneNumber());
        passenger.setRole("USER");
        // Set country if provided
        passenger.setCountry(request.getCountry());
        // Always hash the password
        passenger.setPassword(passwordEncoder.encode(request.getPassword()));
        // Set city if cityId is provided
        if (request.getCityId() != null) {
            var city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found with id: " + request.getCityId()));
            passenger.setCity(city);
        }
        return passengerRepository.save(passenger);
    }

    public Passenger findById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Passenger not found with id: " + id));
    }

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger update(Long id, Passenger updated) {
        Passenger existing = findById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setCity(updated.getCity());
        return passengerRepository.save(existing);
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        Passenger passenger = findById(id);
        // Remove passenger from all aircraft's passengers lists (owning side of aircraft_passenger)
        if (passenger.getAircraft() != null) {
            for (var aircraft : passenger.getAircraft()) {
                if (aircraft.getPassengers() != null) {
                    aircraft.getPassengers().remove(passenger);
                    aircraftRepository.save(aircraft);
                }
            }
        }

        // Remove passenger from all flights' passenger lists and update flights
        if (passenger.getFlights() != null) {
            for (var flight : passenger.getFlights()) {
                if (flight.getPassengers() != null) {
                    flight.getPassengers().remove(passenger);
                    flightRepository.save(flight);
                }
            }
        }

        // Save the passenger to update join tables
        passengerRepository.save(passenger);
        passengerRepository.deleteById(id);
    }

    public List<Aircraft> getAircraft(Long passengerId) {
        return findById(passengerId).getAircraft();
    }

    public List<Airport> getAirports(Long passengerId) {
        return findById(passengerId)
                .getAircraft()
                .stream()
                .flatMap(a -> a.getAirports().stream())
                .distinct()
                .toList();
    }

    public CascadeDeletePreview cascadeDeletePreview(Long id) {
        Passenger passenger = findById(id);
        List<Aircraft> aircraft = passenger.getAircraft() != null ? passenger.getAircraft() : List.of();
        List<Airport> airports = getAirports(id);
        return new CascadeDeletePreview(aircraft, airports);
    }

    public static class CascadeDeletePreview {
        public final List<Aircraft> aircraft;
        public final List<Airport> airports;
        public CascadeDeletePreview(List<Aircraft> aircraft, List<Airport> airports) {
            this.aircraft = aircraft;
            this.airports = airports;
        }
    }
}