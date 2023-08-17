package com.cnr.FlightApp.repository;

import com.cnr.FlightApp.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    //List<Airport> findDepartureAirportByCode(String departureAirport);
    //List<Airport> findArrivalAirportByCode(String arrivalAirport);
}
