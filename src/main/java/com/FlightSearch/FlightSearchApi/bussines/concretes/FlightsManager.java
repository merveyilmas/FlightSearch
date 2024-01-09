package com.FlightSearch.FlightSearchApi.bussines.concretes;

import com.FlightSearch.FlightSearchApi.bussines.abstracts.FlightsService;
import com.FlightSearch.FlightSearchApi.entities.concretes.Airports;
import com.FlightSearch.FlightSearchApi.entities.concretes.Flights;
import com.FlightSearch.FlightSearchApi.repositories.abstracts.AirportsRepository;
import com.FlightSearch.FlightSearchApi.repositories.abstracts.FlightsRepository;
import com.FlightSearch.FlightSearchApi.utilities.results.DataResult;
import com.FlightSearch.FlightSearchApi.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightsManager implements FlightsService {

    private final FlightsRepository flightsRepository;
    private final AirportsRepository airportsRepository;

    private final String MOCK_API_URL = "http://localhost:8080/MockDataController/getMockData";

    @Override
    public DataResult<List<Flights>> getFlight(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {

        List<Flights> flightsInfo = new ArrayList<>();

        Airports departure = new Airports();
        Airports arrival = new Airports();

        departure = this.airportsRepository.findByCity(departureAirport);
        arrival = this.airportsRepository.findByCity(arrivalAirport);

        Flights flights = this.flightsRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(departure, arrival, departureDateTime, returnDateTime);


        if (flights == null) {
            flightsInfo.add(flights);
            return new DataResult<>(flightsInfo, false, "Flight not found!");
        } else {

            flightsInfo.add(flights);

            if (returnDateTime == null) {
                return new DataResult<>(flightsInfo, true, "One way flight listed.");
            } else {
                Flights returnFlights = new Flights();
                returnFlights.setId(flights.getId());
                returnFlights.setDepartureAirport(flights.getArrivalAirport());
                returnFlights.setArrivalAirport(flights.getDepartureAirport());
                returnFlights.setDepartureDateTime(flights.getReturnDateTime());
                returnFlights.setReturnDateTime(null);
                returnFlights.setPrice(flights.getPrice());

                flightsInfo.add(returnFlights);

                return new DataResult<>(flightsInfo, true, "Two way flight listed.");
            }


        }

    }

    @Override
    public Result deleteFlight(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {

        Airports departure = this.airportsRepository.findByCity(departureAirport);
        Airports arrival = this.airportsRepository.findByCity(arrivalAirport);

        Flights flights = this.flightsRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(departure, arrival, departureDateTime, returnDateTime);

        if (flights == null) {
            return new Result(false, "Flight not found!");
        } else {
            this.flightsRepository.deleteById(flights.getId());
            return new Result(true, "Flight deleted.");
        }
    }

    @Override
    public Result saveNewFlight(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime, int price) {

        System.out.println("departureAirport");
        System.out.println(departureAirport);
        System.out.println("arrivalAirport");
        System.out.println(arrivalAirport);

        Airports departure = this.airportsRepository.findByCity(departureAirport);
        Airports arrival = this.airportsRepository.findByCity(arrivalAirport);

        System.out.println("departure");
        System.out.println(departure);
        System.out.println("arrival");
        System.out.println(arrival);

        Flights flights = new Flights();
        flights.setDepartureAirport(departure);
        flights.setArrivalAirport(arrival);
        flights.setDepartureDateTime(departureDateTime);
        flights.setReturnDateTime(returnDateTime);
        flights.setPrice(price);

        this.flightsRepository.save(flights);
        return new Result(true, "Flight saved.");
    }

    @Override
    public Result updateFlight(int id, String updatedDepartureAirport, String updatedArrivalAirport, LocalDateTime updatedDepartureDateTime, LocalDateTime updatedReturnDateTime, int updatedPrice) {

        Airports updatedDeparture = this.airportsRepository.findByCity(updatedDepartureAirport);
        Airports updatedArrival = this.airportsRepository.findByCity(updatedArrivalAirport);

        Flights flight = this.flightsRepository.findById(id);
        flight.setDepartureAirport(updatedDeparture);
        flight.setArrivalAirport(updatedArrival);
        flight.setDepartureDateTime(updatedDepartureDateTime);
        flight.setReturnDateTime(updatedReturnDateTime);
        flight.setPrice(updatedPrice);

        this.flightsRepository.save(flight);

        return new Result(true, "Flight updated.");
    }


    @Override
    public DataResult<List<Flights>> getAllFligths() {
        return new DataResult<>(this.flightsRepository.findAll(), true, "Listed all flights.");
    }

    //@Scheduled(cron = "0 55 11 * * *")
    @Scheduled(cron = "0 0 0 * * *") // Her gün 00:00:00'de çalışacak şekilde ayarlanmıştır
    @Override
    public void fetchMockDataAndSave() {

        // get data from Mock API
        RestTemplate restTemplate = new RestTemplate();
        Flights mockFlight = restTemplate.getForObject(MOCK_API_URL, Flights.class);

        System.out.println("mockFlight");
        System.out.println(mockFlight);

        // save mock data to db
        if (mockFlight != null) {
            this.flightsRepository.save(mockFlight);
        }

    }
}
