package com.FlightSearch.FlightSearchApi.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "flights")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "flights" })
public class Flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "departureAirport_id", nullable = false)
    private Airports departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrivalAirport_id", nullable = false)
    private Airports arrivalAirport;

    @Column(name = "departureDateTime", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "returnDateTime")
    @Nullable
    private LocalDateTime  returnDateTime;

    @Column(name = "price", nullable = false)
    private Integer price;


}
