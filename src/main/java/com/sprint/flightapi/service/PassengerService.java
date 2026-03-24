package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Aircraft;
import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.model.Passenger;
import com.sprint.flightapi.repository.PassengerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Passenger findById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
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

    public void delete(Long id) {
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
}