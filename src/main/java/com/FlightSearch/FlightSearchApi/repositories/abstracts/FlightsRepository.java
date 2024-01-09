package com.FlightSearch.FlightSearchApi.repositories.abstracts;

import com.FlightSearch.FlightSearchApi.entities.concretes.Airports;
import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Integer> {

    Flights findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(
            Airports departureAirport,
            Airports arrivalAirport,
            LocalDateTime departureDateTime,
            LocalDateTime returnDateTime
    );

    Flights findById(int id);

    //@Query(value = "delete from flight where id = :id", nativeQuery = true)
    //int deleteFlight(@Param("flight") String flight);
}
