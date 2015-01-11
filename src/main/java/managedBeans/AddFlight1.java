package managedBeans;

import iservice.IAirportService;
import iservice.IFlightService;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import service.AirportService;
import service.FlightService;
import entities.Airport;
import entities.Flight;

@Named("addflight1")
@Scope("session")
public class AddFlight1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IAirportService as;
	
	@Inject
	private IFlightService fs;

	@ManagedProperty(value="#{flight}")
	private Flight flight;
	@ManagedProperty(value="#{depDate}")
	private Date depDate;
	@ManagedProperty(value="#{arrDate}")
	private Date arrDate;
	
	@ManagedProperty(value="#{acity}")
	private String aCity;
	@ManagedProperty(value="#{dcity}")
	private String dCity;
	
	@ManagedProperty(value="#{alist}")
	private List<Airport> alist;
	
	@ManagedProperty(value="#{dlist}")
	private List<Airport> dlist;
	
	@ManagedProperty(value="#{depName")
	private String depName;
	@ManagedProperty(value="#{arrName")
	private String arrName;
	@ManagedProperty(value="#{airportError")
	private String airportError;
	
	
	private final String acityRequired = "Input the arrival city";
	private final String dcityRequired = "Input the departure city";
	private final String departureTimeRequired = "Input the departure time";
	private final String arrivalTimeRequired = "Input the arrival time";
	private final String firstClassRequired = "Input the number of first class tickets";
	private final String firstPriceRequired = "Input the price of a first class ticket";
	private final String economyClassRequired = "Input the number economy class tickets ";
	private final String economyPriceRequired = "Input the price for an economy class ticket";
	private final String flightNumberRequired = "Input the flight number";
	private final String airportReqired = "Choose departure or arrival airport";
	

	public void checkRole() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Booking office administrator")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
			context.redirect("signIn.xhtml");
		}
	}
	
	public String submit() {
//		if( flight.getDepAirport() != null && flight.getDestAirport() != null ) {
//			flight.setArrivalTime(new Timestamp(arrDate.getTime()));
//			flight.setDepartureTime(new Timestamp(depDate.getTime()));
//			airportError="";
//			return "AddFlight1";
//		} else {
//			airportError = airportReqired;
//			return "AddFlight1";
//		}
		
		FacesContext context = FacesContext.getCurrentInstance();
        

        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
		
		flight.setArrivalTime(new Timestamp(arrDate.getTime()));
		flight.setDepartureTime(new Timestamp(depDate.getTime()));
		fs.addFlight(flight);
		flight = new Flight();
		alist = null;
		dlist = null;
		depName = "";
		arrName = "";
		depDate = null;
		arrDate = null;
		return "flightConfirmation";
	}
	
	public AddFlight1() {
		flight= new Flight();
	}
	
	public String findAirports() {
		dlist = as.getList(dCity);
		alist = as.getList(aCity);
		return "addFlight1";
	}

	public String  chooseDeparture(String id){
		System.out.println(id);
		Airport a = as.findById( Integer.valueOf(id));
		flight.setDepAirport(a) ;
		return "addFlight1";
	}
	
	public String chooseArrival(String id){
		System.out.println(id);
		Airport a = as.findById( Integer.valueOf(id));
		flight.setDestAirport(a);
		return "addFlight1";
	}
	
	public String getAcity() {
		return aCity;
	}
	public void setAcity(String acity) {
		this.aCity = acity;
	}
	public String getDcity() {
		return dCity;
	}
	public void setDcity(String dcity) {
		this.dCity = dcity;
	}
	public String getAcityRequired() {
		return acityRequired;
	}
	public String getDcityRequired() {
		return dcityRequired;
	}
	public String getaCity() {
		return aCity;
	}
	public void setaCity(String aCity) {
		this.aCity = aCity;
	}
	public String getdCity() {
		return dCity;
	}
	public void setdCity(String dCity) {
		this.dCity = dCity;
	}
	public List<Airport> getaList() {
		return alist;
	}
	public void setaList(List<Airport> aList) {
		this.alist = aList;
	}
	public List<Airport> getdList() {
		return dlist;
	}
	public void setdList(List<Airport> dList) {
		this.dlist = dList;
	}

	public List<Airport> getAlist() {
		return alist;
	}

	public void setAlist(List<Airport> alist) {
		this.alist = alist;
	}

	public List<Airport> getDlist() {
		return dlist;
	}

	public void setDlist(List<Airport> dlist) {
		this.dlist = dlist;
	}

	public IAirportService getAs() {
		return as;
	}

	public void setAs(AirportService as) {
		this.as = as;
	}



	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getArrName() {
		return arrName;
	}

	public void setArrName(String arrName) {
		this.arrName = arrName;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getDepartureTimeRequired() {
		return departureTimeRequired;
	}

	public String getArrivalTimeRequired() {
		return arrivalTimeRequired;
	}

	public String getFirstClassRequired() {
		return firstClassRequired;
	}

	public String getFirstPriceRequired() {
		return firstPriceRequired;
	}

	public String getEconomyClassRequired() {
		return economyClassRequired;
	}

	public String getEconomyPriceRequired() {
		return economyPriceRequired;
	}

	public String getFlightNumberRequired() {
		return flightNumberRequired;
	}

	public String getAirportError() {
		return airportError;
	}

	public void setAirportError(String airportError) {
		this.airportError = airportError;
	}

	public String getAirportReqired() {
		return airportReqired;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getArrDate() {
		return arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	public IFlightService getFs() {
		return fs;
	}

	public void setFs(FlightService fs) {
		this.fs = fs;
	}


	
	
	
}
