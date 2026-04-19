
package com.sprint.flightapi.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sprint.flightapi.model.Flight;
import com.sprint.flightapi.service.FlightService;

class FlightControllerTest {
    private MockMvc mockMvc;
    @Mock
    private FlightService flightService;
    @InjectMocks
    private FlightController flightController;

    public FlightControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<Flight> flights = Arrays.asList(new Flight());
        when(flightService.findAll()).thenReturn(flights);
        mockMvc.perform(get("/flights"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Flight flight = new Flight();
        when(flightService.findById(1L)).thenReturn(flight);
        mockMvc.perform(get("/flights/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetArrivals() throws Exception {
        List<Flight> flights = Arrays.asList(new Flight());
        when(flightService.findArrivalsByAirport(3L)).thenReturn(flights);
        mockMvc.perform(get("/flights/arrivals/3"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDepartures() throws Exception {
        List<Flight> flights = Arrays.asList(new Flight());
        when(flightService.findDeparturesByAirport(1L)).thenReturn(flights);
        mockMvc.perform(get("/flights/departures/1"))
                .andExpect(status().isOk());
    }
}
