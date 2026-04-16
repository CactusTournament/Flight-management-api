package com.sprint.flightapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.flightapi.model.Gate;
import com.sprint.flightapi.service.GateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gates")
@RequiredArgsConstructor
public class GateController {
    private final GateService gateService;

    @GetMapping
    public List<Gate> findAll() {
        return gateService.findAll();
    }

    @GetMapping("/{id}")
    public Gate findById(@PathVariable Long id) {
        return gateService.findById(id);
    }

    @PostMapping
    public Gate create(@RequestBody Gate gate) {
        return gateService.save(gate);
    }

    @PutMapping("/{id}")
    public Gate update(@PathVariable Long id, @RequestBody Gate gate) {
        return gateService.update(id, gate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gateService.delete(id);
    }
}
