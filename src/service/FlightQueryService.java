package service;

import model.Flight;
import model.FlightFilterQuery;

import java.util.List;

public class FlightQueryService {

    // На вход подается список полетов и список запросов
    // Запросы могут содержать (пока что) дату отправления, дату прибытия и количество перелетов
    public static List<Flight> filterFlights(List<Flight> flights, List<FlightFilterQuery> queries) {
        FlightFilter filter = new FlightFilter();
        flights = filter.beforeAll(flights);
        for (FlightFilterQuery query : queries) {
            //Сущность можно расширить дополнительными полями( анпример прибытие после определенного времени
            // или количество сегментов меньше чем число), на каждое нужен будет свой блок if()
            if (query.getArrivalTime() != null) {
                flights = filter.filterByArrival(flights, query.getArrivalTime());
            }
            if (query.getDepartureTime() != null) {
                flights = filter.filterByDeparture(flights, query.getDepartureTime());
            }
            if (query.getSegmentCount() > 0) {
                flights = filter.filterBySegmentCount(flights, query.getSegmentCount());
            }
        }
        return flights;
    }
}
