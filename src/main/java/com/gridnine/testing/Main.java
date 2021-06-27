package com.gridnine.testing;

import com.gridnine.testing.models.Flight;

import java.util.List;

public class Main
{
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Total list of flights:");
        flights.forEach(System.out::println);
        System.out.println("*************************************************");
        System.out.println();
        System.out.println();

        // create flight filter
        FlightFilter filter = new FlightFilter(flights);

        // remove only flights which departure before now
        List<Flight> firstExclusion = filter.departureBeforeNow();
        System.out.println("Flights with departure in the past excluded:");
        firstExclusion.forEach(System.out::println);
        System.out.println();
        System.out.println();

        // remove only flights which arrive before departure
        List<Flight> secondExclusion = filter.arrivalBeforeDeparture();
        System.out.println("Flights with arrival before departure excluded:");
        secondExclusion.forEach(System.out::println);
        System.out.println();
        System.out.println();

        // remove flights which total time on ground more than two hours
        List<Flight> thirdExclusion = filter.totalTimeOnGroundMoreTwoHours();
        System.out.println("Flights with total time on the ground more than 2 hours excluded:");
        thirdExclusion.forEach(System.out::println);
        System.out.println();
        System.out.println();

    }
}
