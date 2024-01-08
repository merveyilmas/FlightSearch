package com.FlightSearch.FlightSearchApi.bussines.concretes;

import com.FlightSearch.FlightSearchApi.bussines.abstracts.AirportsService;
import com.FlightSearch.FlightSearchApi.entities.concretes.Airports;
import com.FlightSearch.FlightSearchApi.repositories.abstracts.AirportsRepository;
import com.FlightSearch.FlightSearchApi.utilities.results.DataResult;
import com.FlightSearch.FlightSearchApi.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportsManager implements AirportsService {

    private final AirportsRepository airportsRepository;

    @Override
    public DataResult<Airports> getAirport(String city) {

        Airports airports = this.airportsRepository.findByCity(city);

        if(airports == null){
            return new DataResult<>(airports, false, "Airport not found!");
        }else {
            return new DataResult<>(airports, true, "Airport listed.");
        }

    }

    @Override
    public Result deleteAirport(String city) {
        Airports airport = this.airportsRepository.findByCity(city);

        if(airport == null){
            return new Result(false, "Airport not found!");
        }else {
            this.airportsRepository.deleteById(airport.getId());
            return new Result(true, "Airport deleted.");
        }
    }

    @Override
    public Result saveNewAirport(int id, String city) {

        Airports airport = new Airports();
        airport.setCity(city);
        airport.setId(id);

        this.airportsRepository.save(airport);

        return new Result(true, "Airport saved.");
    }

    @Override
    public Result updateAirport(String oldCity, String newCity) {

        Airports airport = this.airportsRepository.findByCity(oldCity);

        if(airport == null){

            return new Result(false, "Airport not founded!");

        } else {
            Airports updateAirport = new Airports();
            updateAirport.setCity(newCity);
            updateAirport.setId(airport.getId());

            this.airportsRepository.save(updateAirport);

            return new Result(true, "Airport updated.");
        }

    }

    @Override
    public DataResult<List<Airports>> getAllAirports() {
        return new DataResult<>(this.airportsRepository.findAll(), true, "Listed all airports.");
    }
}
