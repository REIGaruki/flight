package service;

import model.Flight;
import model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter implements FilterByArrival, FilterByDeparture, FilterBySegmentCount {

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
                    long totalGroundTime = 0;
                    for (int i = 1; i < segments.size(); i++) {
                        LocalDateTime arrival = segments.get(i-1).getArrivalDate();
                        LocalDateTime departure = segments.get(i).getDepartureDate();
                        totalGroundTime += Duration.between(arrival, departure).toMinutes();
                    }
                    return totalGroundTime <= groundtime * 60;
                })
                .collect(Collectors.toList());
    }

    public List<Flight> beforeAll(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.isEmpty()) {
                        return false;
                    }
                    List<Segment> sorted = segments.stream()
                            .sorted(Comparator.comparing(Segment::getDepartureDate))
                            .toList();
                    for (int i = 0; i < sorted.size() - 1; i++) {
                        LocalDateTime currentArrival = sorted.get(i).getArrivalDate();
                        LocalDateTime nextDeparture = sorted.get(i + 1).getDepartureDate();
                        if (!nextDeparture.isAfter(currentArrival)) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterByArrival(List<Flight> flights, LocalDateTime arrival) {
        return flights.stream().filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.isEmpty()) {
                        return false;
                    }
                    LocalDateTime finalArrivalDate = segments.get(segments.size() - 1).getArrivalDate();
                    return finalArrivalDate.isEqual(arrival);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterByDeparture(List<Flight> flights, LocalDateTime departure) {
        return flights.stream().filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.isEmpty()) {
                        return false;
                    }
                    LocalDateTime firstDepartureDate = segments.get(0).getDepartureDate();
                    return firstDepartureDate.isEqual(departure);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterBySegmentCount(List<Flight> flights, int segmentCount) {
        return flights.stream().
                filter(flight -> flight.getSegments().size() == segmentCount)
                .collect(Collectors.toList());
    }
}
