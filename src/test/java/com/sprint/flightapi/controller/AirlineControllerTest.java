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

import com.sprint.flightapi.model.Airline;
import com.sprint.flightapi.service.AirlineService;

class AirlineControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AirlineService airlineService;
    @InjectMocks
    private AirlineController airlineController;

    public AirlineControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airlineController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<Airline> airlines = Arrays.asList(new Airline());
        when(airlineService.findAll()).thenReturn(airlines);
        mockMvc.perform(get("/airlines"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Airline airline = new Airline();
        when(airlineService.findById(1L)).thenReturn(airline);
        mockMvc.perform(get("/airlines/1"))
                .andExpect(status().isOk());
    }
}
