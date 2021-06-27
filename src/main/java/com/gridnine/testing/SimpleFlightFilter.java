package com.gridnine.testing;

import com.gridnine.testing.models.Flight;

import java.util.List;

public interface SimpleFlightFilter
{
    List<Flight> departureBeforeNow();
    List<Flight>  arrivalBeforeDeparture();
    List<Flight>  totalTimeOnGroundMoreTwoHours();
}
