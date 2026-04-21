package com.sprint.flightapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String airlineName;
    private Integer numberOfPassengers;

    @ManyToMany(mappedBy = "aircraft")
    @JsonIgnore
    private List<Passenger> passengers;

    @ManyToMany
    @JoinTable(
            name = "aircraft_airport",
            joinColumns = @JoinColumn(name = "aircraft_id"),
            inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    @JsonIgnore
        private List<Airport> airports;

        @ManyToOne
        @JoinColumn(name = "airline_id")
        private Airline airline;

        @OneToMany(mappedBy = "aircraft")
        @com.fasterxml.jackson.annotation.JsonIgnore
        private List<Flight> flights;
}