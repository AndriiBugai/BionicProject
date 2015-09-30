package entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
//	private int scheduleId;
//	private int customerId;
	private String surname;
	private String name;
	private Date birthdate;
	private String gender;
	private String passport;
	private String seatClass;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "scheduleId")
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	
	public Ticket() {
		
	}
	
	public String getStringForPrint() {
		String txt = "id = " + id;
		txt += "; scheduleId: " + flight.getId();
	//	txt += "; customerId: " + customer.getSurname();
		txt += "; surname: " + surname;
		txt += "; name: " + name;
		txt += "; birthdate: " + birthdate;
		txt += "; gender: " + gender;
		txt += "; seatClass: " + seatClass;
		txt += "; price: " + price;

		return txt;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}



}
