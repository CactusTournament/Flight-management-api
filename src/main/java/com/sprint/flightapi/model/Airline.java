package com.sprint.flightapi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "airline")
    private List<Aircraft> aircraft;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Aircraft> getAircraft() { return aircraft; }
    public void setAircraft(List<Aircraft> aircraft) { this.aircraft = aircraft; }
    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
