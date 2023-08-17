package com.cnr.FlightApp.repository;

import com.cnr.FlightApp.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
            String departureAirport, String arrivalAirport, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetweenAndArrivalDateTimeBetween(
            String departureAirport, String arrivalAirport,
            LocalDateTime startDepartureDateTime, LocalDateTime endDepartureDateTime,
            LocalDateTime startArrivalDateTime, LocalDateTime endArrivalDateTime);

    List<Flight> findByDepartureAirportAndArrivalAirport(
            String departureAirport, String arrivalAirport);

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeGreaterThanEqual(
            String departureAirport, String arrivalAirport, LocalDateTime departureDateTime);

    List<Flight> findByDepartureAirportAndArrivalAirportAndArrivalDateTimeGreaterThanEqual(
            String departureAirport, String arrivalAirport, LocalDateTime arrivalDateTime);

    // Add other custom query methods if needed
}
