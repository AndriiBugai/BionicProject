package service;

import idao.IFlightDao;
import iservice.IFlightService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import entities.Flight;

@Named
public class FlightService implements IFlightService {

	 @Inject
	private IFlightDao fd;
	
	public List<Flight> findFlightList(String depatureCity, String arrivalCity, Date depatureDate ) {
		Instant instant = Instant.ofEpochMilli(depatureDate.getTime());
		LocalDate ld1 = LocalDateTime.ofInstant(instant, 	ZoneId.systemDefault()).toLocalDate();

		List<Flight> list = fd.getFlights(depatureCity, arrivalCity, ld1);
		return list;		
	}
	
	
	public List<Flight> findFlightList(String depatureCity, String arrivalCity, Date depatureDate, Date depatureDate2) {
		Instant instant = Instant.ofEpochMilli(depatureDate.getTime());
		LocalDate ld1 = LocalDateTime.ofInstant(instant, 	ZoneId.systemDefault()).toLocalDate();
		
		Instant instant2 = Instant.ofEpochMilli(depatureDate2.getTime());
		LocalDate ld2 = LocalDateTime.ofInstant(instant2, 	ZoneId.systemDefault()).toLocalDate();

		List<Flight> list = fd.getFlights(depatureCity, arrivalCity, ld1, ld2);
		return list;		
	}
	
	public int getAvailableEconomyTickets(int id) {
		return fd.getAvailableEconomyTickets(id);
	}
	
	public int getAvailableFirstTickets(int id) {
		return fd.getAvailableFirstTickets(id);
	}
	
	public Flight findById(int id) {
		return fd.findById(id);
	}
	
	@Transactional
	public void addFlight(Flight f) {
		if(f.getId() == 0) {
			fd.persist(f);
		} else {
			fd.update(f);
		}
		
	}
	
	
	public List<Flight> getAllFlightList() {
		// TODO Auto-generated method stub
		return fd.getAllFlightList();
	
	}
	
	@Transactional
	public void removeFlight(int id) {
		fd.remove(id);
	}
	
}
