package iservice;



import java.util.List;

import entities.Airport;
import entities.Company;

public interface IAirportService {
	
	void persist(Airport a);
	void remove(int id);
	Airport findById(int id);
	List<Airport> findAirports();
	List<Airport> findAirports(String city);


}
