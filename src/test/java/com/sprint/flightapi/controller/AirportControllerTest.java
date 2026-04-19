package com.sprint.flightapi.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sprint.flightapi.model.Airport;
import com.sprint.flightapi.service.AirportService;

class AirportControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AirportService airportService;
    @InjectMocks
    private AirportController airportController;

    public AirportControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<Airport> airports = Arrays.asList(new Airport());
        when(airportService.findAll()).thenReturn(airports);
        mockMvc.perform(get("/airports"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Airport airport = new Airport();
        when(airportService.findById(1L)).thenReturn(airport);
        mockMvc.perform(get("/airports/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateAirport() throws Exception {
        Airport airport = new Airport();
        when(airportService.save(airport)).thenReturn(airport);
        mockMvc.perform(post("/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAirport() throws Exception {
        Airport airport = new Airport();
        when(airportService.update(1L, airport)).thenReturn(airport);
        mockMvc.perform(put("/airports/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAirport() throws Exception {
        mockMvc.perform(delete("/airports/1"))
                .andExpect(status().isOk());
    }
}
