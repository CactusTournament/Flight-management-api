package com.sprint.flightapi;

import com.sprint.flightapi.model.*;
import com.sprint.flightapi.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class EntityDeleteIntegrationTests {
    @Autowired private CityRepository cityRepository;
    @Autowired private AirportRepository airportRepository;
    @Autowired private PassengerRepository passengerRepository;
    @Autowired private AircraftRepository aircraftRepository;
    @Autowired private AirlineRepository airlineRepository;
    @Autowired private FlightRepository flightRepository;
    @Autowired private GateRepository gateRepository;

    @Test
    void canDeleteCity() {
        City city = City.builder()
                .name("TestCity")
                .state("TS")
                .population(1000)
                .build();
        city = cityRepository.save(city);
        cityRepository.deleteById(city.getId());
        assertThat(cityRepository.findById(city.getId())).isEmpty();
    }

    @Test
    void canDeleteAirport() {
        City city = City.builder()
                .name("TestCity2")
                .state("TS")
                .population(1000)
                .build();
        city = cityRepository.save(city);
        Airport airport = Airport.builder()
                .code("TST")
                .name("TestAirport")
                .city(city)
                .build();
        airport = airportRepository.save(airport);
        airportRepository.deleteById(airport.getId());
        assertThat(airportRepository.findById(airport.getId())).isEmpty();
    }

    @Test
    void canDeletePassenger() {
        City city = City.builder()
                .name("TestCity3")
                .state("TS")
                .population(1000)
                .build();
        city = cityRepository.save(city);
        Passenger passenger = Passenger.builder()
                .firstName("First")
                .lastName("Last")
                .phoneNumber("123")
                .username("user")
                .email("email")
                .password("pass")
                .role("role")
                .city(city)
                .build();
        passenger = passengerRepository.save(passenger);
        passengerRepository.deleteById(passenger.getId());
        assertThat(passengerRepository.findById(passenger.getId())).isEmpty();
    }

    @Test
    void canDeleteAircraft() {
        Airline airline = new Airline();
        airline.setName("TestAirline");
        airline = airlineRepository.save(airline);
        Aircraft aircraft = Aircraft.builder()
                .type("Type")
                .airlineName("TestAirline")
                .numberOfPassengers(100)
                .airline(airline)
                .build();
        aircraft = aircraftRepository.save(aircraft);
        aircraftRepository.deleteById(aircraft.getId());
        assertThat(aircraftRepository.findById(aircraft.getId())).isEmpty();
    }

    @Test
    void canDeleteAirline() {
        Airline airline = new Airline();
        airline.setName("TestAirline2");
        airline = airlineRepository.save(airline);
        airlineRepository.deleteById(airline.getId());
        assertThat(airlineRepository.findById(airline.getId())).isEmpty();
    }

    @Test
    void canDeleteFlight() {
        Airline airline = new Airline();
        airline.setName("TestAirline3");
        airline = airlineRepository.save(airline);
        Aircraft aircraft = Aircraft.builder()
                .type("Type")
                .airlineName("TestAirline3")
                .numberOfPassengers(100)
                .airline(airline)
                .build();
        aircraft = aircraftRepository.save(aircraft);
        City city = City.builder()
                .name("TestCity4")
                .state("TS")
                .population(1000)
                .build();
        city = cityRepository.save(city);
        Airport origin = Airport.builder()
                .code("ORG")
                .name("Origin")
                .city(city)
                .build();
        origin = airportRepository.save(origin);
        Airport dest = Airport.builder()
                .code("DST")
                .name("Dest")
                .city(city)
                .build();
        dest = airportRepository.save(dest);
        Gate gate = Gate.builder()
                .code("G1")
                .airport(origin)
                .build();
        gate = gateRepository.save(gate);
        Flight flight = new Flight();
        flight.setFlightNumber("FN");
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(dest);
        flight.setAircraft(aircraft);
        flight.setAirline(airline);
        flight.setGate(gate);
        flight = flightRepository.save(flight);
        flightRepository.deleteById(flight.getId());
        assertThat(flightRepository.findById(flight.getId())).isEmpty();
    }

    @Test
    void canDeleteGate() {
        City city = City.builder()
                .name("TestCity5")
                .state("TS")
                .population(1000)
                .build();
        city = cityRepository.save(city);
        Airport airport = Airport.builder()
                .code("TST2")
                .name("TestAirport2")
                .city(city)
                .build();
        airport = airportRepository.save(airport);
        Gate gate = Gate.builder()
                .code("G1")
                .airport(airport)
                .build();
        gate = gateRepository.save(gate);
        gateRepository.deleteById(gate.getId());
        assertThat(gateRepository.findById(gate.getId())).isEmpty();
    }
}
