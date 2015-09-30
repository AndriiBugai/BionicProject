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


@Named("staff1")
@Scope("session")
public class Staff1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IStaffService ss;
	
	@Inject
	private CreateStaff1 editStaff;
	
	
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
	
	
	public void checkRole() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Security officer")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
			context.redirect("signIn.xhtml");
		}
	}
	
	public Staff1() {
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
	
	public String edit(String id) {
		int number = Integer.parseInt(id);
		for(Staff s : list) {
			if(s.getId() == number) {
				user =s; 
				break;
			}
		}
		System.out.println(number);
		return "editUser";
	}
	
	public String changePassword(String id) {
		int number = Integer.parseInt(id);
		for(Staff s : list) {
			if(s.getId() == number) {
				user =s; 
				break;
			}
		}
		System.out.println(number);
		return "changePassword";
	}
	
	public void findStaff() {
		list = ss.findStaff();
	}
	
	public void newUser() {
		user = new Staff();
		user.setPosition("Booking office administrator");
		user.setEnabled("Enabled"); 
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

	public CreateStaff1 getEditStaff() {
		return editStaff;
	}

	public void setEditStaff(CreateStaff1 editStaff) {
		this.editStaff = editStaff;
	}
	
	
}
