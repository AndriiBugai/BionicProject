package managedBeans;

import com.sun.org.apache.xpath.internal.SourceTree;
import iservice.ICustomerService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import service.CustomerService;
import entities.Customer;

@Named("acc")
@Scope("session")
public class Accountant implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	ICustomerService cs;
	
	private List<Customer> list;
		
	private String orderID;
	
	private final String OrderIDRequired = "Input the name of an order";
	
	public void checkRole2() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Booking office accountant")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println("User with " + position + " tried to get reach Accountant page");
			context.redirect("signIn.xhtml");
		}
	}
	
	public void submit() {
		list = new ArrayList<>();
		int number = Integer.valueOf(orderID);
		Customer cust = cs.findById(number);
		if(cust != null) {
			list.add(cust);
		}
	
	}
	
	public void submitOrder(String id) {
		int number = Integer.valueOf(id);
		Customer cust = cs.findById(number);
		cs.confirmOrder(cust);
		list = new ArrayList<>();
	}

	public ICustomerService getCs() {
		return cs;
	}

	public void setCs(CustomerService cs) {
		this.cs = cs;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOrderIDRequired() {
		return OrderIDRequired;
	}
	
}
