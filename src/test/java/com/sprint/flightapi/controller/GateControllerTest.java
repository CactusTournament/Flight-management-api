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

import com.sprint.flightapi.model.Gate;
import com.sprint.flightapi.service.GateService;

class GateControllerTest {
    private MockMvc mockMvc;
    @Mock
    private GateService gateService;
    @InjectMocks
    private GateController gateController;

    public GateControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gateController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<Gate> gates = Arrays.asList(new Gate());
        when(gateService.findAll()).thenReturn(gates);
        mockMvc.perform(get("/gates"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Gate gate = new Gate();
        when(gateService.findById(1L)).thenReturn(gate);
        mockMvc.perform(get("/gates/1"))
                .andExpect(status().isOk());
    }
}
