package com.cnr.FlightApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${timetable-lookup.p.rapidapi.com}")
    private String externalApiUrl;

    @Value("${e13c1230d2mshb697a141ceaf6bap1cae60jsnb67760e86f8c}")
    private String apiKey;

    public List fetchFlightInformation(String departureAirport, String arrivalAirport, String departureDateTime, String arrivalDateTime) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "timetable-lookup.p.rapidapi.com");

        String requestUrl = buildRequestUrl(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime);

        return restTemplate.getForObject(requestUrl, List.class);
    }

    private String buildRequestUrl(String departureAirport, String arrivalAirport, String departureDateTime, String arrivalDateTime) {
        return externalApiUrl + "/" + departureAirport + "/" + arrivalAirport + "/" + departureDateTime + "/" + arrivalDateTime + "/";
    }
}