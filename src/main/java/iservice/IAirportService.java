package iservice;



import java.util.List;

import entities.Airport;

public interface IAirportService {
	
	 public List<Airport> getList(); 
	 public List<Airport> getList(String city); 
	 public void persist(Airport a); 
	 public Airport findById(int id);
}
