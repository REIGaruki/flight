package service;

import model.Flight;

import java.util.List;

public interface FilterBySegmentCount {
    List<Flight> filterBySegmentCount(List<Flight> flights);
}
