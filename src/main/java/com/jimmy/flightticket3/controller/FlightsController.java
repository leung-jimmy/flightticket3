package com.jimmy.flightticket3.controller;

import com.jimmy.flightticket3.api.FlightsApi;
import com.jimmy.flightticket3.model.Flight;
import com.jimmy.flightticket3.service.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightsController implements FlightsApi {

    @Autowired
    private FlightsService flightsService;
/*
        @Override
        public ResponseEntity<List<Flight>> _flightsGet(
                @RequestParam(value = "flightNumber", required = false) @Nullable String flightNumber,
                @RequestParam(value = "origin", required = false) @Nullable String origin,
                @RequestParam(value = "destination", required = false) @Nullable String destination,
                @RequestParam(value = "departureDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Nullable LocalDate departureDate,
                @RequestParam(value = "arrivalDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Nullable LocalDate arrivalDate
        ) {
*/

    @Override
    public ResponseEntity<List<Flight>> flightsGet(String flightNumber, String origin, String destination, LocalDate departureDate, LocalDate arrivalDate) {

        System.out.println("Flights GET endpoint called.");
        var flightList = flightsService.getFlightsByParams(flightNumber, origin, destination, departureDate, arrivalDate);
        return ResponseEntity.ok(flightList);
    }

}
