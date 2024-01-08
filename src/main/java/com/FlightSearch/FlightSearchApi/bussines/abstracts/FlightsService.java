package com.FlightSearch.FlightSearchApi.bussines.abstracts;

import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import com.FlightSearch.FlightSearchApi.utilities.results.DataResult;
import com.FlightSearch.FlightSearchApi.utilities.results.Result;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightsService {

    DataResult<Flights> getFlight(
            String departureAirport,
            String arrivalAirport,
            LocalDateTime departureDateTime,
            LocalDateTime returnDateTime
    );

    Result deleteFlight(String departureAirport,
                        String arrivalAirport,
                        LocalDateTime departureDateTime,
                        LocalDateTime returnDateTime);

    Result saveNewFlight(String departureAirport,
                        String arrivalAirport,
                        LocalDateTime departureDateTime,
                        LocalDateTime returnDateTime,
                        int price);

    Result updateFlight(String departureAirport,
                        String arrivalAirport,
                        LocalDateTime departureDateTime,
                        LocalDateTime returnDateTime);

    DataResult<List<Flights>> getAllFligths();
}
