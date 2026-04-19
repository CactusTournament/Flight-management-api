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

import com.sprint.flightapi.model.Passenger;
import com.sprint.flightapi.service.PassengerService;

class PassengerControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PassengerService passengerService;
    @InjectMocks
    private PassengerController passengerController;

    public PassengerControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<Passenger> passengers = Arrays.asList(new Passenger());
        when(passengerService.findAll()).thenReturn(passengers);
        mockMvc.perform(get("/passengers"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Passenger passenger = new Passenger();
        when(passengerService.findById(1L)).thenReturn(passenger);
        mockMvc.perform(get("/passengers/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreatePassenger() throws Exception {
        Passenger passenger = new Passenger();
        when(passengerService.save(passenger)).thenReturn(passenger);
        mockMvc.perform(post("/passengers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePassenger() throws Exception {
        Passenger passenger = new Passenger();
        when(passengerService.update(1L, passenger)).thenReturn(passenger);
        mockMvc.perform(put("/passengers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePassenger() throws Exception {
        mockMvc.perform(delete("/passengers/1"))
                .andExpect(status().isOk());
    }
}
