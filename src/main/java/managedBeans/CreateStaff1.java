package managedBeans;

import iservice.IStaffService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import service.StaffService;
import entities.Staff;

@Named("createStaff")
@Scope("request")
public class CreateStaff1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IStaffService ss;
	
	private List<Staff> list;
	
	private Staff user;
	
	private final String nameRequired = "Input the first name";
	private final String surnameRequired = "Input the last name";
	private final String passportRequired = "Input the passport ";
	private final String emailRequired = "Input the e-mail ";
	private final String phoneRequired = "Input the phone ";
	private final String loginRequired = "Input the login ";
	private final String passwordRequired = "Input the password ";
	private final String passwordRequired2 = "Repeat the password here";
	
	public CreateStaff1() {
		newUser();
	}
	
	public void checkRole() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Security officer")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println("User with " + position + " tried to get reach Security Officer page (createStaff)");
			context.redirect("signIn.xhtml");
		}
	}
	
	public void newUser() {
		user = new Staff();
		user.setPosition("Booking office administrator");
		user.setEnabled("Enabled"); 
	}
	
	public String submition() {
	
	     if (user.getId() == 0) {
	    		ss.persist(user);
	    	     } else {
	    		ss.update(user);
	    	     }
	     user = new Staff();
		return "findStaff";
	}

	public IStaffService getSs() {
		return ss;
	}

	public void setSs(StaffService ss) {
		this.ss = ss;
	}

	public List<Staff> getList() {
		return list;
	}

	public void setList(List<Staff> list) {
		this.list = list;
	}

	public Staff getUser() {
		return user;
	}

	public void setUser(Staff user) {
		this.user = user;
	}

	public String getNameRequired() {
		return nameRequired;
	}

	public String getSurnameRequired() {
		return surnameRequired;
	}

	public String getPassportRequired() {
		return passportRequired;
	}

	public String getEmailRequired() {
		return emailRequired;
	}

	public String getPhoneRequired() {
		return phoneRequired;
	}

	public String getLoginRequired() {
		return loginRequired;
	}

	public String getPasswordRequired() {
		return passwordRequired;
	}

	public String getPasswordRequired2() {
		return passwordRequired2;
	}
	
}
