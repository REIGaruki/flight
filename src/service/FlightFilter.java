package service;

import model.Flight;
import model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {

    static List<Flight> removeExpired(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(currentTime)))
                .collect(Collectors.toList());
    }

    static List<Flight> removeBeforeDate(List<Flight> flights, LocalDateTime earliestDeparture) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(earliestDeparture)))
                .collect(Collectors.toList());
    }

    static List<Flight> removeArrivalBeforeDepartment(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    static List<Flight> removeMoreThanGroundtimeOnGround(List<Flight> flights, long groundtime) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() <= 1) {
                        return true;
                    }
                    Segment[] sorted = segments.stream()
                            .sorted(Comparator.comparing(Segment::getDepartureDate))
                            .toArray(Segment[]::new);
                    long totalGroundTime = 0;
                    for (int i = 1; i < sorted.length; i++) {
                        LocalDateTime arrival = sorted[i-1].getArrivalDate();
                        LocalDateTime departure = sorted[i].getDepartureDate();
                        totalGroundTime += Duration.between(arrival, departure).toMinutes();
                    }
                    return totalGroundTime <= groundtime * 60;
                })
                .collect(Collectors.toList());
    }

}
