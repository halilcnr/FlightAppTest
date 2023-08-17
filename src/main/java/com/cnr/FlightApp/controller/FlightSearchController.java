package com.cnr.FlightApp.controller;

import com.cnr.FlightApp.Flight;
import com.cnr.FlightApp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightSearchController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/searchFlights")
    public List<Flight> searchFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime arrivalDateTime
    ) {
        List<Flight> flights = new ArrayList<>();

        if (arrivalDateTime == null) {
            // One-way flight
            flights.addAll(flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeGreaterThanEqual(
                    departureAirport, arrivalAirport, departureDateTime));
        } else {
            // Round-trip flight
            flights.addAll(flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetweenAndArrivalDateTimeBetween(
                    departureAirport, arrivalAirport, departureDateTime.minusHours(12), departureDateTime.plusHours(12),
                    arrivalDateTime.minusHours(12), arrivalDateTime.plusHours(12)));
        }

        return flights;
    }
}
