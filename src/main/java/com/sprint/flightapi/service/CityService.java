
package com.sprint.flightapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.model.City;
import com.sprint.flightapi.model.Passenger;
import com.sprint.flightapi.repository.CityRepository;
import com.sprint.flightapi.repository.PassengerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final PassengerRepository passengerRepository;

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

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        City city = findById(id);
        // Delete all airports in this city
        if (city.getAirports() != null) {
            for (var airport : city.getAirports()) {
                // Remove airport from all arrivals
                if (airport.getArrivals() != null) {
                    for (var flight : airport.getArrivals()) {
                        flight.setDestinationAirport(null);
                    }
                }
                // Remove airport from all departures
                if (airport.getDepartures() != null) {
                    for (var flight : airport.getDepartures()) {
                        flight.setOriginAirport(null);
                    }
                }
                // Remove this airport from all aircraft
                if (airport.getAircraft() != null) {
                    for (var aircraft : airport.getAircraft()) {
                        if (aircraft.getAirports() != null) {
                            aircraft.getAirports().remove(airport);
                        }
                    }
                }
                airport.setCity(null);
            }
        }
        // Delete all passengers in this city
        List<Passenger> passengers = city.getPassengers();
        if (passengers != null && !passengers.isEmpty()) {
            for (var passenger : passengers) {
                passenger.setCity(null);
            }
        }
        cityRepository.deleteById(id);
    }

    public CascadeDeletePreview cascadeDeletePreview(Long id) {
        City city = findById(id);
        List<Airport> airports = city.getAirports();
        List<Passenger> passengers = city.getPassengers();
        return new CascadeDeletePreview(airports, passengers);
    }

    public static class CascadeDeletePreview {
        public final List<Airport> airports;
        public final List<Passenger> passengers;
        public CascadeDeletePreview(List<Airport> airports, List<Passenger> passengers) {
            this.airports = airports;
            this.passengers = passengers;
        }
    }

    public List<Airport> getAirports(Long cityId) {
        return findById(cityId).getAirports();
    }
}