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

import com.sprint.flightapi.model.Airline;
import com.sprint.flightapi.repository.AirlineRepository;

class AirlineServiceTest {
    @Mock
    private AirlineRepository airlineRepository;
    @InjectMocks
    private AirlineService airlineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Airline> airlines = Arrays.asList(new Airline());
        when(airlineRepository.findAll()).thenReturn(airlines);
        assertEquals(airlines, airlineService.findAll());
    }

    @Test
    void testFindById() {
        Airline airline = new Airline();
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        assertEquals(airline, airlineService.findById(1L));
    }
}
