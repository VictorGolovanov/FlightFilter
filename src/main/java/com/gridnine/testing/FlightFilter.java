package com.gridnine.testing;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilter implements SimpleFlightFilter {

    private final List<Flight> flights;

    // insert list of all flights to filter
    public FlightFilter(List<Flight> flights){
        this.flights = new ArrayList<>(flights);
    }

    private List<Flight> getFlights() {
        return flights;
    }

    @Override
    public List<Flight> departureBeforeNow() {
        List<Flight> filteredFlights = new ArrayList<>(getFlights());
        filteredFlights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
        );
        return filteredFlights;
    }

    @Override
    public List<Flight>  arrivalBeforeDeparture() {
        List<Flight> filteredFlights = new ArrayList<>(getFlights());
        filteredFlights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))
        );
        return filteredFlights;
    }

    @Override
    public List<Flight>  totalTimeOnGroundMoreTwoHours() {
        List<Flight> filteredFlights = new ArrayList<>(getFlights());
        filteredFlights.removeIf(flight -> {

            List<Segment> segments = flight.getSegments();
            Duration duration = Duration.ZERO;

            for (int i = 1; i < segments.size(); i++) {
                LocalDateTime currentDeparture = segments.get(i).getDepartureDate();
                LocalDateTime lastArrival = segments.get(i - 1).getArrivalDate();
                duration = duration.plus(Duration.between(currentDeparture, lastArrival).abs());
            }
            return duration.toHours() >= 2;
        });
        return filteredFlights;
    }
}
