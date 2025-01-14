package service;

import model.Flight;
import model.FlightFilterQuery;

import java.util.List;

public interface FilterBySegmentCount {
    List<Flight> filterBySegmentCount(List<Flight> flights, int segmentCount);
}
