import com.gridnine.testing.FlightFilter;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightFilterTest extends TestCase
{
    public List<Flight> flightsWithPast;
    public List<Flight> flightsWithArrivalBeforeDeparture;
    public List<Flight> flightsWithMoreThanTwoHoursOnGround;
    public FlightFilter filter;

    @Override
    protected void setUp() {
        flightsWithPast = FlightFilterTest.createTestFlightsWithPastDeparture();
        flightsWithArrivalBeforeDeparture = FlightFilterTest.createTestFlightsWithArrivalBeforeDeparture();
        flightsWithMoreThanTwoHoursOnGround = FlightFilterTest.createTestFlightsWithMoreThanTwoHoursOnGround();
    }

    public void testExcludeDepartureBeforeNow(){
        filter = new FlightFilter(flightsWithPast);
        List<Flight> actual = filter.departureBeforeNow();
        List<Flight> expected = FlightFilterTest.getNormalFlights();
        assertEquals(expected.toString(), actual.toString());
    }

    public void testExcludeArrivalBeforeDeparture(){
        filter = new FlightFilter(flightsWithArrivalBeforeDeparture);
        List<Flight> actual = filter.arrivalBeforeDeparture();
        List<Flight> expected = FlightFilterTest.getNormalFlights();
        assertEquals(expected.toString(), actual.toString());
    }

    public void testExcludeTotalTimeOnGroundMoreTwoHours(){
        filter = new FlightFilter(flightsWithMoreThanTwoHoursOnGround);
        List<Flight> actual = filter.totalTimeOnGroundMoreTwoHours();
        List<Flight> expected = FlightFilterTest.getNormalFlights();
        assertEquals(expected.toString(), actual.toString());
    }



    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }

    private static List<Flight> createTestFlightsWithPastDeparture(){
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                //A flight departing in the past
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow)
                );
    }

    private static List<Flight> createTestFlightsWithArrivalBeforeDeparture(){
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                //A flight that departs before it arrives
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6))
        );
    }

    private static List<Flight> createTestFlightsWithMoreThanTwoHoursOnGround(){
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                //A flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6))
        );
    }

    private static List<Flight> getNormalFlights(){
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5))
        );
    }
}
