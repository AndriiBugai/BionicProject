package managedBeans;

import iservice.ICustomerService;
import iservice.IFlightService;
import iservice.ITicketService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.FlightService;
import entities.Customer;
import entities.Flight;
import entities.Ticket;

@Named("flightC")
@Scope("session")
public class FindFlightCustomer1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IFlightService fService;
	@Inject
	private ITicketService tService;
	@Inject
	private ICustomerService cService;
	
	private Flight flight;
	
	
	// id of a flight
	@ManagedProperty(value="#{number}")
	int number=0;
	
// addTicket
	// current ticket
	@ManagedProperty(value="#{ticket}")
	Ticket ticket;
	private int ticketToEdit = -1;
	
	@ManagedProperty(value="#{birthdate}")
	private Date birthdate;
	
	
// findFlightCustomer
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

	
// ticketCart	
	@ManagedProperty(value="#{cart}")
	private List<Ticket> cart;
	private double totalSum;
	
// bookTickets
	private String name;
	private String surname;
	private String email;
	
	private final String depatureCityRequired = "Input the departure city";
	private final String arrivalCityRequired = "Input the arrival city";
	private final String departureDateRequired = "Input the departure date";
	private final String nameRequired = "Input the first name";
	private final String surnameRequired = "Input the last name";
	private final String genderRequired = "Input the departure date";
	private final String birthdateRequired = "Input the birthdate";
	private final String emailRequired = "Input your email";
	private final String passportRequired = "Input your passport number";
	private final String seatsError = "No avalilable tickets of the chosen class for this flight";
	private String error;
	
	FindFlightCustomer1() {
		ticket= new Ticket();
		cart = new ArrayList<Ticket>();
	}
	

	
	// findFlightCustomer -> addTicket
	public String addTicketToCart(String id) {
		 number = Integer.valueOf(id);
		 for(Flight f : list) {
			 if(f.getId() == number) {
				 flight = f;
			 }
		 }	 
		 ticket= new Ticket();
		 birthdate = null;
		 error ="";
		 return "addTicket";
	}
	
	// addTicket -> ticketCart
	public String confirmTicket() {
		boolean availableSeatsError = false;
		
		ticket.setFlight(flight);
		ticket.setBirthdate(new java.sql.Date(birthdate.getTime()));	
		if(ticket.getSeatClass().equals("economy class")) {
			if(flight.getAvailableEconomy() < 1) availableSeatsError=true ;
			ticket.setPrice(flight.getEconomyPrice());
		} else  {
			ticket.setPrice(flight.getFirstPrice());
			if(flight.getAvailableFirst() < 1) availableSeatsError=true ;
		}
		
		if(availableSeatsError) {
			error = seatsError;
			return "addTicket";
			
		}
		
		
		if(ticketToEdit == -1) {
			cart.add(ticket);
		} else {
			cart.set(ticketToEdit, ticket);
		}
		
		ticket = new Ticket();
		birthdate = null;
		totalSum = 0;
		error ="";
		
		for(Ticket t: cart) {
			totalSum += t.getPrice();
			System.out.println(t.getStringForPrint());
		}
		
		ticketToEdit = -1;

		
		return "ticketCart";

	}
	
	
	
	//ticketCart
	public void deleteFromCart(String row) {
		System.out.println(row);
		int index = Integer.valueOf(row);
		cart.remove(index);
	}
	//ticketCart
	public String editTicket(String row) {
		System.out.println(row);
		int index = Integer.valueOf(row);
		ticket = cart.get(index);
		ticketToEdit = index;
	//	cart.remove(index);
		birthdate = ticket.getBirthdate();
		return "addTicket";
	}
	
	public String bookTickets(){
			
		// forming information about customer
		Customer c = new Customer();
		c.setName(name);
		c.setSurname(surname);
		c.setEmail(email);
		
		// time now
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp bookingDate = new java.sql.Timestamp(now.getTime());
		c.setBookingDate(bookingDate);

		c.setSettlementAccount("1234565554"); // bank account
		//c.setTicketList(cart);
		
		System.out.println(c.getStringForPrint());
		
		cService.persist(c);
		
		for(Ticket t: cart) {
			t.setCustomer(c);
			System.out.println(t.getStringForPrint());
			
		}
		
		tService.persist(cart);
		
		ticket= new Ticket();
		cart = new ArrayList<Ticket>();
		totalSum = 0;
		return "findFlightCustomer";
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
	public String getDepatureCityRequired() {
		return depatureCityRequired;
	}
	public String getArrivalCityRequired() {
		return arrivalCityRequired;
	}
	public String getDepartureDateRequired() {
		return departureDateRequired;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Ticket> getCart() {
		return cart;
	}

	public void setCart(List<Ticket> cart) {
		this.cart = cart;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getNameRequired() {
		return nameRequired;
	}

	public String getSurnameRequired() {
		return surnameRequired;
	}

	public String getGenderRequired() {
		return genderRequired;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthdateRequired() {
		return birthdateRequired;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailRequired() {
		return emailRequired;
	}

	public String getPassportRequired() {
		return passportRequired;
	}

	public void setfService(IFlightService fService) {
		this.fService = fService;
	}



	public ITicketService gettService() {
		return tService;
	}



	public void settService(ITicketService tService) {
		this.tService = tService;
	}



	public ICustomerService getcService() {
		return cService;
	}



	public void setcService(ICustomerService cService) {
		this.cService = cService;
	}



	public int getTicketToEdit() {
		return ticketToEdit;
	}



	public void setTicketToEdit(int ticketToEdit) {
		this.ticketToEdit = ticketToEdit;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public String getSeatsError() {
		return seatsError;
	}
	
	
}
