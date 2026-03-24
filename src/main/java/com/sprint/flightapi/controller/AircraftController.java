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
import com.sprint.flightapi.service.AircraftService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/aircraft")
@RequiredArgsConstructor
public class AircraftController {

    private final AircraftService aircraftService;

    @GetMapping
    public List<Aircraft> findAll() {
        return aircraftService.findAll();
    }

    @GetMapping("/{id}")
    public Aircraft findById(@PathVariable Long id) {
        return aircraftService.findById(id);
    }

    @PostMapping
    public Aircraft create(@RequestBody Aircraft aircraft) {
        return aircraftService.save(aircraft);
    }

    @PutMapping("/{id}")
    public Aircraft update(@PathVariable Long id, @RequestBody Aircraft aircraft) {
        return aircraftService.update(id, aircraft);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        aircraftService.delete(id);
    }

    // SPECIAL ENDPOINT #3 — Airports used by aircraft
    @GetMapping("/{id}/airports")
    public List<Airport> getAirports(@PathVariable Long id) {
        return aircraftService.getAirports(id);
    }
}