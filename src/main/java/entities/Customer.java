package entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String surname;
	private String name;
	private String email;
	private String settlementAccount;
	private Timestamp paymentDate;
	private Timestamp bookingDate;
	private Date paymentDateNoTime;
		
	@OneToMany(mappedBy="customer")
	List<Ticket> ticketList;
	
	public Customer(){
		
	}
	
	public String getStringForPrint() {
		String txt = "id = " + id;
		txt += "; surname: " + surname;
		txt += "; name: " + name;
		txt += "; email: " + email;
		txt += "; settlementAccount: " + settlementAccount;
	//	txt += "; bookingNumber: " + bookingNumber;
		txt += "; paymentDate: " + paymentDate;
		txt += "; bookingDate: " + bookingDate;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Timestamp getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	public String getSettlementAccount() {
		return settlementAccount;
	}

	public void setSettlementAccount(String settlementAccount) {
		this.settlementAccount = settlementAccount;
	}


	public Date getPaymentDateNoTime() {
		return paymentDateNoTime;
	}

	public void setPaymentDateNoTime(Date paymentDateNoTime) {
		this.paymentDateNoTime = paymentDateNoTime;
	}
	
}
