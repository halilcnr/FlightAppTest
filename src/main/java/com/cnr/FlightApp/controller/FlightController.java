package com.cnr.FlightApp.controller;

import com.cnr.FlightApp.Flight;
import com.cnr.FlightApp.FlightService;
import com.cnr.FlightApp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Flight> getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        if (!flightRepository.existsById(id)) {
            throw new IllegalArgumentException("Flight not found with Code: " + id);
        }
        flight.setId(id);
        return flightRepository.save(flight);
    }
    @Autowired
    private FlightService flightService;
    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam("departureAirport") String departureAirport,
            @RequestParam("arrivalAirport") String arrivalAirport,
            @RequestParam("departureDate") LocalDateTime departureDate,
            @RequestParam(value = "arrivalDate", required = false) LocalDateTime arrivalDate
    ) {
        if (arrivalDate == null) {
            return flightService.searchOneWayFlights(departureAirport, arrivalAirport, departureDate, arrivalDate);
        } else {
            return flightService.searchTwoWayFlights(departureAirport, arrivalAirport, departureDate, arrivalDate);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightRepository.deleteById(id);
    }
}

