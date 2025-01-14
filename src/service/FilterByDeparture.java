package service;

import model.Flight;

import java.util.List;

public interface FilterByDeparture {
    List<Flight> filterByDeparture(List<Flight> flights);
}
