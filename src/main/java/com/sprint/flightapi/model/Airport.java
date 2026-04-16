package com.sprint.flightapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @ManyToMany(mappedBy = "airports")
    @JsonIgnore
    private List<Aircraft> aircraft;

    @OneToMany(mappedBy = "airport")
    @JsonIgnore
    private List<Gate> gates;

    @OneToMany(mappedBy = "originAirport")
    @JsonIgnore
    private List<Flight> departingFlights;

    @OneToMany(mappedBy = "destinationAirport")
    @JsonIgnore
    private List<Flight> arrivingFlights;
}