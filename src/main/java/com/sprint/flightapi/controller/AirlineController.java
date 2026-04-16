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

import com.sprint.flightapi.model.Airline;
import com.sprint.flightapi.service.AirlineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping
    public List<Airline> findAll() {
        return airlineService.findAll();
    }

    @GetMapping("/{id}")
    public Airline findById(@PathVariable Long id) {
        return airlineService.findById(id);
    }

    @PostMapping
    public Airline create(@RequestBody Airline airline) {
        return airlineService.save(airline);
    }

    @PutMapping("/{id}")
    public Airline update(@PathVariable Long id, @RequestBody Airline airline) {
        return airlineService.update(id, airline);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airlineService.delete(id);
    }
}
