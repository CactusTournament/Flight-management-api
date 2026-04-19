package com.sprint.flightapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.flightapi.model.Flight;
import com.sprint.flightapi.service.FlightService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;


    @GetMapping
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    // Arrivals for a given airport
    @GetMapping("/arrivals/{airportId}")
    public List<Flight> getArrivals(@PathVariable Long airportId) {
        return flightService.findArrivalsByAirport(airportId);
    }

    // Departures for a given airport
    @GetMapping("/departures/{airportId}")
    public List<Flight> getDepartures(@PathVariable Long airportId) {
        return flightService.findDeparturesByAirport(airportId);
    }

    @GetMapping("/{id}")
    public Flight findById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @PostMapping
    public Flight create(@RequestBody Flight flight) {
        return flightService.save(flight);
    }

    @PutMapping("/{id}")
    public Flight update(@PathVariable Long id, @RequestBody Flight flight) {
        return flightService.update(id, flight);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        flightService.delete(id);
    }
}
