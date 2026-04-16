package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.model.City;
import com.sprint.flightapi.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Long id) {
        return cityRepository.findById(id)
            .orElseThrow(() -> new com.sprint.flightapi.exception.NotFoundException("City not found"));
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City update(Long id, City updated) {
        City existing = findById(id);
        existing.setName(updated.getName());
        existing.setState(updated.getState());
        existing.setPopulation(updated.getPopulation());
        return cityRepository.save(existing);
    }

    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    public List<Airport> getAirports(Long cityId) {
        return findById(cityId).getAirports();
    }
}