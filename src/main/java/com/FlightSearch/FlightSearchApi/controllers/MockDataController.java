package com.FlightSearch.FlightSearchApi.controllers;

import com.FlightSearch.FlightSearchApi.entities.concretes.Airports;
import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import com.FlightSearch.FlightSearchApi.repositories.abstracts.AirportsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/MockDataController")
@AllArgsConstructor
public class MockDataController {

    private final AirportsRepository airportsRepository;

    @GetMapping("/getMockData")
    public Flights getFlightData() {

        Airports departure = this.airportsRepository.findByCity("IST");
        Airports arrival = this.airportsRepository.findByCity("JFK");

        Flights flights = new Flights();
        flights.setDepartureAirport(departure);
        flights.setArrivalAirport(arrival);
        flights.setDepartureDateTime(LocalDateTime.parse("2024-01-06T12:00:00"));
        flights.setReturnDateTime(LocalDateTime.parse("2024-01-09T15:00:00"));
        flights.setPrice(5000);

        return flights;
    }
}
