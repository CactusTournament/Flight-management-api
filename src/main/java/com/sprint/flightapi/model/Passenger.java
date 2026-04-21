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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String username;
    private String email;
    private String password;
    private String role; // e.g., USER or ADMIN

    private String country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

        @ManyToMany
        @JoinTable(
            name = "aircraft_passenger",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "aircraft_id")
        )
        @JsonIgnore
        private List<Aircraft> aircraft;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @ManyToMany(mappedBy = "passengers")
    private List<Flight> flights;
}