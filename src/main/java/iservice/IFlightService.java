package iservice;


import java.util.Date;
import java.util.List;

import entities.Flight;

public interface IFlightService {

	public List<Flight> findFlightList(String depatureCity, String arrivalCity, Date depatureDate );
	
	public List<Flight> findFlightList(String depatureCity, String arrivalCity, Date depatureDate, Date depatureDate2);
	
	public Flight findById(int id) ;
	
	public void addFlight(Flight f) ;
	
	public void removeFlight(int id) ;
	
	public int getAvailableEconomyTickets(int id);
	
	public int getAvailableFirstTickets(int id);
	
	public List<Flight> getAllFlightList();
}
