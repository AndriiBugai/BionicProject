package managedBeans;

import iservice.IStaffService;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import service.StaffService;
import entities.Staff;

@Named("signin1")
@Scope("request")
public class SignIn1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject 
	IStaffService ss;
	

	
	
	
	@ManagedProperty(value="#{login}")
	private String login;
	@ManagedProperty(value="#{password}")
	private String password;
	private final String incorrect = "Incorrect login or password";
	private final String loginRequired = "Input your login";
	private final String passwordRequired = "Input your password";
	private Staff user;
	
	private final String adminHome = "addFlight"; 
	private final String securityHome = "findStaff";
	private final String analystHome = "report";
	private final String accountantHome = "accountant";
	private final String customerHome = "findFlightCustomer";
	

	
	public String submit() {
		String passDB = ss.getPassword(login);
		String passPr = Staff.convertPassword(password);
		
		if(passDB.equals(passPr)) {
			System.out.println("ok");
			user = ss.getUser(login);
			
			HttpSession session = Util.getSession();
            session.setAttribute("position", user.getPosition());
			
            
			switch(user.getPosition()) {
			case "Booking office administrator": return adminHome;
			case "Booking office accountant": return accountantHome;
			case "Business analyst" : return analystHome;
			case "Security officer" : return securityHome;
			default : return customerHome;
			}
			
		}
		else {
			System.out.println("not ok");
			return "signIn";
		}
	}
	
	public String signOut() {
		HttpSession session = Util.getSession();
        session.setAttribute("position", "");
		return "signIn";
	}
	


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIncorrect() {
		return incorrect;
	}

	public String getLoginRequired() {
		return loginRequired;
	}

	public String getPasswordRequired() {
		return passwordRequired;
	}


	public IStaffService getSs() {
		return ss;
	}


	public void setSs(StaffService ss) {
		this.ss = ss;
	}


	public Staff getUser() {
		return user;
	}


	public void setUser(Staff user) {
		this.user = user;
	}
		
}
