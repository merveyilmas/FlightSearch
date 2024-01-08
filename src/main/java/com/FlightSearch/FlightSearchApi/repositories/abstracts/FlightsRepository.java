package com.FlightSearch.FlightSearchApi.repositories.abstracts;

import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Integer> {

    Flights findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(
            String departureAirport,
            String arrivalAirport,
            LocalDateTime departureDateTime,
            LocalDateTime returnDateTime
    );

    //@Query(value = "delete from flights where id = :id", nativeQuery = true)
    //int deleteHwProfileBySoftCode(@Param("softCode") String softCode);
}
