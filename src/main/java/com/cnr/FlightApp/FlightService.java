package com.cnr.FlightApp;

import com.cnr.FlightApp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ExternalAPIService externalAPIService;
    @Scheduled(cron = "0 0 * * *") // Run every hour
    public void updateFlightInformationFromExternalAPI() {
        String departureAirport = "SAW"; // Example departure airport code
        String arrivalAirport = "BCN";   // Example arrival airport code
        String departureDateTime = "2023-08-16T12:00"; // Example departure date and time in ISO 8601 format
        String arrivalDateTime = "2023-08-17T15:00";   // Example arrival date and time in ISO 8601 format

        List<Flight> newFlights = externalAPIService.fetchFlightInformation(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime);
        flightRepository.saveAll(newFlights);
    }



    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public List<Flight> searchOneWayFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        // Implement the logic to search for one-way flights based on criteria
        // Example: return flightRepository.findByDepartureAndArrivalAndDepartureDate(departure, arrival, departureDate);
        return null;
    }

    public List<Flight> searchTwoWayFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        // Implement the logic to search for two-way flights based on criteria
        // Example: return flightRepository.findByDepartureAndArrivalAndDepartureDateAndArrivalDate(departure, arrival, departureDate, arrivalDate);
        return null;
    }




}
