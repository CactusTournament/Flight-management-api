package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.exception.NotFoundException;
import com.sprint.flightapi.model.Gate;
import com.sprint.flightapi.model.Flight;
import com.sprint.flightapi.repository.GateRepository;

@Service
public class GateService {
    private final GateRepository gateRepository;

    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public List<Gate> findAll() {
        return gateRepository.findAll();
    }

    public Gate findById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gate not found with id: " + id));
    }

    public Gate save(Gate gate) {
        return gateRepository.save(gate);
    }

    public Gate update(Long id, Gate gate) {
        if (!gateRepository.existsById(id)) {
            throw new NotFoundException("Gate not found with id: " + id);
        }
        gate.setId(id);
        return gateRepository.save(gate);
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        Gate gate = findById(id);
        if (gate.getFlights() != null) {
            for (var flight : gate.getFlights()) {
                flight.setGate(null);
            }
        }
        gateRepository.deleteById(id);
    }

    public CascadeDeletePreview cascadeDeletePreview(Long id) {
        Gate gate = findById(id);
        List<Flight> flights = gate.getFlights() != null ? gate.getFlights() : List.of();
        return new CascadeDeletePreview(flights);
    }

    public static class CascadeDeletePreview {
        public final List<Flight> flights;
        public CascadeDeletePreview(List<Flight> flights) {
            this.flights = flights;
        }
    }
}

