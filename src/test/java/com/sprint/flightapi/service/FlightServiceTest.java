package com.sprint.flightapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.sprint.flightapi.model.Flight;
import com.sprint.flightapi.repository.FlightRepository;

class FlightServiceTest {
    @Mock
    private FlightRepository flightRepository;
    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Flight> flights = Arrays.asList(new Flight());
        when(flightRepository.findAll()).thenReturn(flights);
        assertEquals(flights, flightService.findAll());
    }

    @Test
    void testFindById() {
        Flight flight = new Flight();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        assertEquals(flight, flightService.findById(1L));
    }
}
