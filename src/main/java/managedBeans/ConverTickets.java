package managedBeans;

import iservice.ICustomerService;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;


@Named("convertTickets")
@Scope("session")
public class ConverTickets {
	
	@Inject
	private ICustomerService cService;

	public String submit() {
		
		cService.convertBookedIntoFree();
		return "convertTickets";
	}
	
	public void checkRole() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Booking office administrator")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println("User with " + position + " tried to get reach Admin page (convertTickets)");
			context.redirect("signIn.xhtml");
		}
	}
	
}
