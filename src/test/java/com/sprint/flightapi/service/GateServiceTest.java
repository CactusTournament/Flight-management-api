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

import com.sprint.flightapi.model.Gate;
import com.sprint.flightapi.repository.GateRepository;

class GateServiceTest {
    @Mock
    private GateRepository gateRepository;
    @InjectMocks
    private GateService gateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Gate> gates = Arrays.asList(new Gate());
        when(gateRepository.findAll()).thenReturn(gates);
        assertEquals(gates, gateService.findAll());
    }

    @Test
    void testFindById() {
        Gate gate = new Gate();
        when(gateRepository.findById(1L)).thenReturn(Optional.of(gate));
        assertEquals(gate, gateService.findById(1L));
    }
}
