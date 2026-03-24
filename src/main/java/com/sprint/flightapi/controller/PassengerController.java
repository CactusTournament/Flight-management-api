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

import com.sprint.flightapi.model.Aircraft;
import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.model.Passenger;
import com.sprint.flightapi.service.PassengerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public List<Passenger> findAll() {
        return passengerService.findAll();
    }

    @GetMapping("/{id}")
    public Passenger findById(@PathVariable Long id) {
        return passengerService.findById(id);
    }

    @PostMapping
    public Passenger create(@RequestBody Passenger passenger) {
        return passengerService.save(passenger);
    }

    @PutMapping("/{id}")
    public Passenger update(@PathVariable Long id, @RequestBody Passenger passenger) {
        return passengerService.update(id, passenger);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        passengerService.delete(id);
    }

    // SPECIAL ENDPOINT #2 — Aircraft flown by passenger
    @GetMapping("/{id}/aircraft")
    public List<Aircraft> getAircraft(@PathVariable Long id) {
        return passengerService.getAircraft(id);
    }

    // SPECIAL ENDPOINT #4 — Airports used by passenger
    @GetMapping("/{id}/airports")
    public List<Airport> getAirports(@PathVariable Long id) {
        return passengerService.getAirports(id);
    }
}