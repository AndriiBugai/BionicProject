package entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Flight  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Timestamp departureTime;
	private Timestamp arrivalTime;
//	private int departureAirport;
//	private int destinationAirport;
	private int firstClass;
	private int economyClass;
	private double firstPrice;
	private double economyPrice;
	private String flightNumber;
	@ManyToOne
	@JoinColumn(name = "destinationAirport")
	private Airport destAirport;
	
	@ManyToOne
	@JoinColumn(name = "departureAirport")
	private Airport depAirport;
	
	@Transient 
	private int availableEconomy;
	@Transient 
	private int availableFirst;
	
	public Flight() {
	}
	
	public String getStringForPrint() {
		String txt = "id = " + id;
		txt += "; departureTime: " + departureTime;
		txt += "; arrivalTime: " + arrivalTime;
		txt += "; departureAirport: " + depAirport.getName();
		txt += "; destinationAirport: " + destAirport.getName();
		txt += "; FirstClass: " + firstClass;
		txt += "; EconomyClass: " + economyClass;
		txt += "; firstPrice: " + firstPrice;
		txt += "; economyPrice: " + economyPrice;
		txt += "; flightNumber: " + flightNumber;
		return txt;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getFirstClass() {
		return firstClass;
	}
	public void setFirstClass(int firstClass) {
		this.firstClass = firstClass;
	}
	public int getEconomyClass() {
		return economyClass;
	}
	public void setEconomyClass(int economyClass) {
		this.economyClass = economyClass;
	}
	public double getFirstPrice() {
		return firstPrice;
	}
	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}
	public double getEconomyPrice() {
		return economyPrice;
	}
	public void setEconomyPrice(double economyPrice) {
		this.economyPrice = economyPrice;
	}
	public Airport getDestAirport() {
		return destAirport;
	}
	public void setDestAirport(Airport destAirport) {
		this.destAirport = destAirport;
	}
	public Airport getDepAirport() {
		return depAirport;
	}
	public void setDepAirport(Airport depAirport) {
		this.depAirport = depAirport;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getAvailableEconomy() {
		return availableEconomy;
	}

	public void setAvailableEconomy(int availableEconomy) {
		this.availableEconomy = availableEconomy;
	}

	public int getAvailableFirst() {
		return availableFirst;
	}

	public void setAvailableFirst(int availableFirst) {
		this.availableFirst = availableFirst;
	}
	
}
