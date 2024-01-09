package com.FlightSearch.FlightSearchApi.controllers;

import com.FlightSearch.FlightSearchApi.bussines.abstracts.AirportsService;
import com.FlightSearch.FlightSearchApi.entities.concretes.Airports;
import com.FlightSearch.FlightSearchApi.utilities.results.DataResult;
import com.FlightSearch.FlightSearchApi.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/AirportsController")
@AllArgsConstructor
public class AirportsController {

    private AirportsService airportsService;

    @GetMapping("/getAirport")
    public DataResult<Airports> getAirport(@RequestParam String city) {

       return this.airportsService.getAirport(city);
    }

    @PostMapping("/deleteAirport")
    public Result deleteAirport(@RequestParam String city) {
        return this.airportsService.deleteAirport(city);
    }

    @PostMapping("/saveNewAirport")
    public Result saveNewAirport(@RequestParam String city) {

      return this.airportsService.saveNewAirport(city);
    }

    @PostMapping("/updateAirport")
    public Result updateAirport(@RequestParam String oldCity, @RequestParam String newCity) {

       return this.airportsService.updateAirport(oldCity, newCity);
    }


    @GetMapping("/getAllAirports")
    public DataResult<List<Airports>> getAllAirports() {
        return this.airportsService.getAllAirports();
    }
}
