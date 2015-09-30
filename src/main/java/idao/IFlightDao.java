package idao;

import java.time.LocalDate;
import java.util.List;

import entities.Airport;
import entities.Flight;

public interface IFlightDao {
    void persist(Flight flight);
    void remove(int id);
//    void update(Flight flight);    
    Flight findById(int id);
    
    
    /**
     * 
     * @param departurePlace
     * @param arrivalPlace
     * @param ld
     * @return list of schedule 
     */
    List<Flight> getFlights(Airport departurePlace, Airport arrivalPlace, LocalDate ld ) ;
    
    /**
     * 
     * @param departurePlace
     * @param arrivalPlace
     * @param ld1
     * @param ld2
     * @return list of schedule 
     */
    List<Flight> getFlights(Airport departurePlace, Airport arrivalPlace, LocalDate ld1, LocalDate ld2 ); 
    
    /**
     * 
     * @param flightId - id of a flight
     * @return number of available tickets
     */
    int getAvailableEconomyTickets(int flightId);
    int getAvailableFirstTickets(int flightId);
	List<Flight> getFlights(String depatureCity, String arrivalCity,
			LocalDate ld1);
	List<Flight> getFlights(String depatureCity, String arrivalCity,
			LocalDate ld1, LocalDate ld2);
	
	void update(Flight flight);
	List<Flight> getAllFlightList();
    
}
