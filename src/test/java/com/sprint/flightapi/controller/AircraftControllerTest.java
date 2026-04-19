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

import com.sprint.flightapi.model.Aircraft;
import com.sprint.flightapi.service.AircraftService;

class AircraftControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AircraftService aircraftService;
    @InjectMocks
    private AircraftController aircraftController;

    public AircraftControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(aircraftController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<Aircraft> aircraft = Arrays.asList(new Aircraft());
        when(aircraftService.findAll()).thenReturn(aircraft);
        mockMvc.perform(get("/aircraft"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Aircraft aircraft = new Aircraft();
        when(aircraftService.findById(1L)).thenReturn(aircraft);
        mockMvc.perform(get("/aircraft/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateAircraft() throws Exception {
        Aircraft aircraft = new Aircraft();
        when(aircraftService.save(aircraft)).thenReturn(aircraft);
        mockMvc.perform(post("/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAircraft() throws Exception {
        Aircraft aircraft = new Aircraft();
        when(aircraftService.update(1L, aircraft)).thenReturn(aircraft);
        mockMvc.perform(put("/aircraft/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAircraft() throws Exception {
        mockMvc.perform(delete("/aircraft/1"))
                .andExpect(status().isOk());
    }
}
