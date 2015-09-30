package service;

import idao.IAirportDao;
import iservice.IAirportService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dao.AirportDao;
import entities.Airport;

@Named
public class AirportService implements IAirportService  {

	 @Inject
	private IAirportDao ad;
	
	 public List<Airport> getList(){
		 return ad.getList();
	 }
	 
	 public List<Airport> getList(String city){
		 return ad.getList(city);
	 }
	 
	 @Transactional
	 public void persist(Airport a) {
		 ad.persist(a);
	 }
	 
	 public Airport findById(int id) {
		 return ad.findById(id);
	 }
}
