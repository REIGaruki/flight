package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class FlightFilterQuery {

    private final LocalDateTime DepartureTime;

    private final LocalDateTime ArrivalTime;

    private final int SegmentCount;

    public FlightFilterQuery(LocalDateTime departureTime, LocalDateTime arrivalTime, int segmentCount) {
        DepartureTime = departureTime;
        ArrivalTime = arrivalTime;
        SegmentCount = segmentCount;
    }

    public LocalDateTime getDepartureTime() {
        return DepartureTime;
    }

    public LocalDateTime getArrivalTime() {
        return ArrivalTime;
    }

    public int getSegmentCount() {
        return SegmentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightFilterQuery that = (FlightFilterQuery) o;
        return SegmentCount == that.SegmentCount && Objects.equals(DepartureTime, that.DepartureTime) && Objects.equals(ArrivalTime, that.ArrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DepartureTime, ArrivalTime, SegmentCount);
    }
}
