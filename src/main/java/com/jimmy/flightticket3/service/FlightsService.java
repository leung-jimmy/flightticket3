package com.jimmy.flightticket3.service;

import com.jimmy.flightticket3.api.FlightsApi;
import com.jimmy.flightticket3.entity.FlightEntity;
import com.jimmy.flightticket3.model.Flight;
import com.jimmy.flightticket3.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightsService {

    @Autowired
    private FlightsRepository flightsRepository;

    public List<Flight> getFlightsByParams(String flightNumber, String origin, String destination, LocalDate departureDate, LocalDate arrivalDate){
//        OffsetDateTime offsetDateTimeDeparture = departureDate.atStartOfDay().atOffset(ZoneOffset.ofHours(8));
//        OffsetDateTime offsetDateTimeArrival = arrivalDate.atStartOfDay().atOffset(ZoneOffset.ofHours(8));
//        System.out.println(flightNumber);
//        var flightEntityList = flightsRepository.findFlightsByParameters(flightNumber, origin, destination, offsetDateTimeDeparture, offsetDateTimeArrival);
        var flightEntityList = flightsRepository.findFlightsByFlightNumber(flightNumber);
        List<Flight> flightList = new ArrayList<>();
        for (FlightEntity flightEntity : flightEntityList){
            Flight tempFlight = new Flight();
            tempFlight.setFlightNumber(flightEntity.getFlightNumber());
            tempFlight.setDepartureTime(flightEntity.getDepartureDateTime());
            tempFlight.setArrivalTime(flightEntity.getArrivalDateTime());
            tempFlight.setOrigin(flightEntity.getOrigin());
            tempFlight.setDestination(flightEntity.getDestination());
            tempFlight.setPrice(flightEntity.getPrice());
            flightList.add(tempFlight);
        }
        return flightList;
    }
}
