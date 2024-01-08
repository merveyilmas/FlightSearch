package com.FlightSearch.FlightSearchApi.bussines.concretes;

import com.FlightSearch.FlightSearchApi.bussines.abstracts.FlightsService;
import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import com.FlightSearch.FlightSearchApi.repositories.abstracts.FlightsRepository;
import com.FlightSearch.FlightSearchApi.utilities.results.DataResult;
import com.FlightSearch.FlightSearchApi.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightsManager implements FlightsService {

    private final FlightsRepository flightsRepository;

    @Override
    public DataResult<Flights> getFlight(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {

        Flights flights = this.flightsRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(departureAirport, arrivalAirport, departureDateTime, returnDateTime);

        //Flights flights = this.flightsRepository.getFlight(departureAirport, arrivalAirport, departureDateTime, returnDateTime);
        System.out.println("flights");
        System.out.println(flights);

        if(flights == null){
            return new DataResult<>(flights, false, "Flight not found!");
        }else {
            return new DataResult<>(flights, true, "Flight listed.");
        }

    }

    @Override
    public Result deleteFlight(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {

        Flights flights = this.flightsRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(departureAirport, arrivalAirport, departureDateTime, returnDateTime);

        if(flights == null){
            return new Result(false, "Flight not found!");
        }else {
            this.flightsRepository.deleteById(flights.getId());
            return new Result(true, "Flight deleted.");
        }
    }

    @Override
    public Result saveNewFlight(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime, int price) {

        Flights flights = new Flights();
        flights.setDepartureAirport(departureAirport);
        flights.setArrivalAirport(arrivalAirport);
        flights.setDepartureDateTime(departureDateTime);
        flights.setReturnDateTime(returnDateTime);
        flights.setPrice(price);

        this.flightsRepository.save(flights);
        return new Result(true, "Flight saved.");
    }

    @Override
    public Result updateFlight(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        return null;
    }

    @Override
    public DataResult<List<Flights>> getAllFligths() {
        return new DataResult<>(this.flightsRepository.findAll(), true, "Listed all flights.");
    }
}
