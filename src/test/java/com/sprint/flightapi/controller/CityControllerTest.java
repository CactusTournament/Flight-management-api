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

import com.sprint.flightapi.model.City;
import com.sprint.flightapi.service.CityService;

class CityControllerTest {
    private MockMvc mockMvc;
    @Mock
    private CityService cityService;
    @InjectMocks
    private CityController cityController;

    public CityControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<City> cities = Arrays.asList(new City());
        when(cityService.findAll()).thenReturn(cities);
        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        City city = new City();
        when(cityService.findById(1L)).thenReturn(city);
        mockMvc.perform(get("/cities/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCity() throws Exception {
        City city = new City();
        when(cityService.save(city)).thenReturn(city);
        mockMvc.perform(post("/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCity() throws Exception {
        City city = new City();
        when(cityService.update(1L, city)).thenReturn(city);
        mockMvc.perform(put("/cities/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCity() throws Exception {
        mockMvc.perform(delete("/cities/1"))
                .andExpect(status().isOk());
    }
}
