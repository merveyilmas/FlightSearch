package com.FlightSearch.FlightSearchApi.bussines.abstracts;

import com.FlightSearch.FlightSearchApi.entities.concretes.Airports;
import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import com.FlightSearch.FlightSearchApi.utilities.results.DataResult;
import com.FlightSearch.FlightSearchApi.utilities.results.Result;

import java.time.LocalDateTime;
import java.util.List;

public interface AirportsService {

    DataResult<Airports> getAirport(String city);

    Result deleteAirport(String city);

    Result saveNewAirport(String city);

    Result updateAirport(String oldCity, String newCity);

    DataResult<List<Airports>> getAllAirports();
}
