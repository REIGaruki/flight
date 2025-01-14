package service;

import model.Flight;

import java.util.List;

public interface FilterByArrival {
    List<Flight> filterByArrival(List<Flight> flights);
}
