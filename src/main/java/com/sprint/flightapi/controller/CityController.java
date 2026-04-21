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
import com.sprint.flightapi.model.City;
import com.sprint.flightapi.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/{id}/cascade-delete-preview")
    public CityService.CascadeDeletePreview cascadeDeletePreview(@PathVariable Long id) {
        return cityService.cascadeDeletePreview(id);
    }

    // Compatibility endpoint for frontend expecting /cities/{id}/cascade-preview
    @GetMapping("/{id}/cascade-preview")
    public CityService.CascadeDeletePreview cascadePreviewAlias(@PathVariable Long id) {
        return cityService.cascadeDeletePreview(id);
    }

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    public City create(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id, @RequestBody City city) {
        return cityService.update(id, city);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }

    // SPECIAL ENDPOINT #1 — Airports in a city
    @GetMapping("/{id}/airports")
    public List<Airport> getAirports(@PathVariable Long id) {
        return cityService.getAirports(id);
    }
}