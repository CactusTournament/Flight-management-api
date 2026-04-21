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

import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.service.AirportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/{id}/cascade-delete-preview")
    public AirportService.CascadeDeletePreview cascadeDeletePreview(@PathVariable Long id) {
        return airportService.cascadeDeletePreview(id);
    }

    // Compatibility endpoint for frontend expecting /airports/{id}/cascade-preview
    @GetMapping("/{id}/cascade-preview")
    public AirportService.CascadeDeletePreview cascadePreviewAlias(@PathVariable Long id) {
        return airportService.cascadeDeletePreview(id);
    }

    @GetMapping
    public List<Airport> findAll() {
        return airportService.findAll();
    }

    @GetMapping("/{id}")
    public Airport findById(@PathVariable Long id) {
        return airportService.findById(id);
    }

    @PostMapping
    public Airport create(@RequestBody Airport airport) {
        return airportService.save(airport);
    }

    @PutMapping("/{id}")
    public Airport update(@PathVariable Long id, @RequestBody Airport airport) {
        return airportService.update(id, airport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airportService.delete(id);
    }
}