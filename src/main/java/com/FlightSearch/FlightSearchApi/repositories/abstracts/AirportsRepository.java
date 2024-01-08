package com.FlightSearch.FlightSearchApi.repositories.abstracts;

import com.FlightSearch.FlightSearchApi.entities.concretes.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportsRepository extends JpaRepository<Airports, Integer> {

    Airports findByCity(String city);

}
