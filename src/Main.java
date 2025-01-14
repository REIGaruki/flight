import model.Flight;
import model.FlightFilterQuery;
import service.FlightQueryService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<FlightFilterQuery> queries = new ArrayList<>();
        FlightFilterQuery query = new FlightFilterQuery();
        queries.add(query);
        System.out.println("before: " + flights);
        System.out.println("after :" + FlightQueryService.filterFlights(flights, queries));
    }
}