package service;

import idao.IAirportDao;
import iservice.IAirportService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import entities.Airport;

@Named
public class AirportService implements IAirportService  {

	@Inject
	private IAirportDao airportDao;

	@Override
	@Transactional
	public void persist(Airport a) {
		 airportDao.persist(a);
	 }

	@Override
	@Transactional
	public void remove(int id) {
		airportDao.remove(id);
	}

	@Override
	public Airport findById(int id) {
		 return airportDao.findById(id);
	 }

	@Override
	public List<Airport> findAirports() {
		return airportDao.findAirports();
	}

	@Override
	public List<Airport> findAirports(String city) {
		return airportDao.findAirports(city);
	}


}
