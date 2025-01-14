package service;

import model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FilterByArrival {
    List<Flight> filterByArrival(List<Flight> flights, LocalDateTime arrival);
}
