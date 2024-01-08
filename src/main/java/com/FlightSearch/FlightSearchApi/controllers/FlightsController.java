package com.FlightSearch.FlightSearchApi.controllers;

import com.FlightSearch.FlightSearchApi.bussines.abstracts.AirportsService;
import com.FlightSearch.FlightSearchApi.bussines.abstracts.FlightsService;
import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import com.FlightSearch.FlightSearchApi.utilities.results.DataResult;
import com.FlightSearch.FlightSearchApi.utilities.results.Result;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/FlightsController")
@AllArgsConstructor
public class FlightsController {

    private FlightsService flightsService;

    @GetMapping("/getFlight")
    public DataResult<Flights> getFlight(@RequestParam String departureAirport, @RequestParam String arrivalAirport, @RequestParam LocalDateTime departureDateTime, @RequestParam @Nullable LocalDateTime returnDateTime) {
    //public DataResult<Flights> getFlight( String departureAirport,  String arrivalAirport,  LocalDateTime departureDateTime,  LocalDateTime returnDateTime) {

      return this.flightsService.getFlight(departureAirport, arrivalAirport, departureDateTime, returnDateTime);
    }

    @PostMapping("/deleteFlight")
    public Result deleteFlight(@RequestParam String departureAirport, @RequestParam String arrivalAirport, @RequestParam LocalDateTime departureDateTime, @RequestParam LocalDateTime returnDateTime) {

        return this.flightsService.deleteFlight(departureAirport, arrivalAirport, departureDateTime, returnDateTime);
    }

    @PostMapping("/saveNewFlight")
    public Result saveNewFlight(@RequestParam String departureAirport, @RequestParam String arrivalAirport, @RequestParam LocalDateTime departureDateTime, @RequestParam LocalDateTime returnDateTime, int price) {

       return this.flightsService.saveNewFlight(departureAirport, arrivalAirport, departureDateTime, returnDateTime, price);
    }

    @PostMapping("/updateFlight")
    public Result updateFlight(@RequestParam String departureAirport, @RequestParam String arrivalAirport, @RequestParam LocalDateTime departureDateTime, @RequestParam LocalDateTime returnDateTime) {
        return null;
    }

    @GetMapping("/getAllFligths")
    public DataResult<List<Flights>> getAllFligths() {
        return this.flightsService.getAllFligths();
    }
}
