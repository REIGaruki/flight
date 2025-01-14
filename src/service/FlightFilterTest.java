package service;

import model.Flight;
import model.Segment;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FlightFilterTest {

    public static void testRemoveExpired() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.minusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment2));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = filter.removeExpired(flights);

        System.out.println("testRemoveExpired");
        System.out.println(result.size() == 1 && flight2.equals(result.get(0)));
    }

    public static void testRemoveBeforeDate() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment2));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = FlightFilter.removeBeforeDate(flights, now.plusHours(2));

        System.out.println("testRemoveBeforeDate");
        System.out.println(result.size() == 1 && flight2.equals(result.get(0)));
    }

    public static void testRemoveArrivalBeforeDepartment() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        Segment segment3 = new Segment(now.plusHours(4), now.plusHours(3));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment3));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = filter.removeArrivalBeforeDepartment(flights);

        System.out.println("testRemoveArrivalBeforeDepartment");
        System.out.println(result.size() == 1 && flight1.equals(result.get(0)));
    }

    public static void testRemoveMoreThanGroundtimeOnGround() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusMinutes(181), now.plusHours(5));
        Segment segment3 = new Segment(now.plusHours(7), now.plusHours(8));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2, segment3));
        Flight flight2 = new Flight(Arrays.asList(segment1, segment2));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = filter.removeMoreThanGroundtimeOnGround(flights, 2);

        System.out.println("testRemoveMoreThanGroundtimeOnGround");
        System.out.println(result.size() == 1 && flight2.equals(result.get(0)));
    }

    public static void testBeforeAll() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        Segment segment3 = new Segment(now.plusHours(4), now.plusHours(3));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment3));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = filter.beforeAll(flights);

        System.out.println("testBeforeAll");
        System.out.println(result.size() == 1 && flight1.equals(result.get(0)));
    }

    public static void testFilterByArrival() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment1));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = filter.filterByArrival(flights, now.plusHours(5));

        System.out.println("testFilterByArrival");
        System.out.println(result.size() == 1 && flight1.equals(result.get(0)));
    }

    public static void testFilterByDeparture() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment2));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = filter.filterByDeparture(flights, now.plusHours(1));

        System.out.println("testFilterByDeparture");
        System.out.println(result.size() == 1 && flight1.equals(result.get(0)));
    }

    public static void testFilterBySegmentCount() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment1));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> result = filter.filterBySegmentCount(flights, 1);

        System.out.println("testFilterBySegmentCount");
        System.out.println(result.size() == 1 && flight2.equals(result.get(0)));
    }
}