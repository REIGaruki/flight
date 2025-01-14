package service;

import model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FilterByDeparture {
    List<Flight> filterByDeparture(List<Flight> flights, LocalDateTime departure);
}
