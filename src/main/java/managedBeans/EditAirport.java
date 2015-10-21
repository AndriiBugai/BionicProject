package managedBeans;

import entities.Airport;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by strapper on 14.10.15.
 */

@Named(value = "editAirport")
@Scope("session")
public class EditAirport {

    @ManagedProperty(value="#{airport}")
    private Airport airport;

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public void checkRole() throws IOException {
        HttpSession session = Util.getSession();
        String position = (String) session.getAttribute("position");
        if(position == null || !position.equals("Booking office administrator")) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            System.out.println("User with " + position + " tried to get reach Admin page (edit Airport)");

            context.redirect("signIn.xhtml");
        }
    }

}
