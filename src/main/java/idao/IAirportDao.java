package idao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Airport;
import entities.Flight;
import entities.Ticket;

public interface IAirportDao {
	
    void persist(Airport airport);
    void remove(int id);
//    void update(Airport airport);    
    Airport findById(int id);
	List<Airport> findAirports();
	List<Airport> findAirports(String city);
           

}
