package managedBeans;

import iservice.IFlightService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import service.FlightService;
import entities.Flight;

@Named("flight1")
@Scope("session")
public class FindFlight1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IFlightService fService;
	
	@Inject
	private AddFlight1 addFlight1;
	
	private Flight flight;
	
	@ManagedProperty(value="#{depatureCity}")
	private String depatureCity;
	@ManagedProperty(value="#{arrivalCity}")
	private String arrivalCity;
	@ManagedProperty(value="#{depatureCity}")
	private Date depatureDate;
	@ManagedProperty(value="#{departureDate2}")
	private Date departureDate2;
	
	@ManagedProperty(value="#{list}")
	private List<Flight> list;
	
	private final String depatureCityRequired = "Input the departure city";
	private final String arrivalCityRequired = "Input the arrival city";
	private final String departureDateRequired = "Input the departure date";
	
	public void checkRole() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Booking office administrator")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
			context.redirect("signIn.xhtml");
		}
	}
	
	public String delete(String id) {
		int number = Integer.valueOf(id);
		fService.removeFlight(number);
		System.out.println(id);
		findFlightList();
		return "findFlightAdmin";
	}
	
	public String edit(String id) {
		int number = Integer.valueOf(id);
		System.out.println(number);

		flight = fService.findById(number);
		addFlight1.setFlight(flight);
		addFlight1.setDepDate(flight.getDepartureTime());
		addFlight1.setArrDate(flight.getArrivalTime());
		return "addFlight";
	}
	
	public String findFlightList() {
		if(departureDate2 == null ) {
			list = fService.findFlightList(depatureCity, arrivalCity, depatureDate);
		} else {
			list = fService.findFlightList(depatureCity, arrivalCity, depatureDate, departureDate2 );
		}
		
		for(Flight f : list) {
			f.setAvailableEconomy(fService.getAvailableEconomyTickets(f.getId()));
			f.setAvailableFirst(fService.getAvailableFirstTickets(f.getId()));
		}	
		return "findFlight";
	}
	
	public String getDepatureCity() {
		return depatureCity;
	}
	public void setDepatureCity(String depatureCity) {
		this.depatureCity = depatureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDepatureCityRequired() {
		return depatureCityRequired;
	}
	public String getArrivalCityRequired() {
		return arrivalCityRequired;
	}

	public String getDepartureDateRequired() {
		return departureDateRequired;
	}
	public Date getDepatureDate() {
		return depatureDate;
	}
	public void setDepatureDate(Date depatureDate) {
		this.depatureDate = depatureDate;
	}
	public Date getDepartureDate2() {
		return departureDate2;
	}
	public void setDepartureDate2(Date departureDate2) {
		this.departureDate2 = departureDate2;
	}
	public List<Flight> getList() {
		return list;
	}
	public void setList(List<Flight> list) {
		this.list = list;
	}

	public IFlightService getfService() {
		return fService;
	}

	public void setfService(FlightService fService) {
		this.fService = fService;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public AddFlight1 getAddFlight1() {
		return addFlight1;
	}

	public void setAddFlight1(AddFlight1 addFlight1) {
		this.addFlight1 = addFlight1;
	}
	
}
