package managedBeans;

import entities.Airport;
import entities.Company;
import iservice.IAirportService;
import iservice.ICompanyService;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by strapper on 14.10.15.
 */

@Named(value = "findAirport")
@Scope("session")
public class FindAirport {

    private static final long serialVersionUID = 1L;

    @Inject
    IAirportService airportService;

    @Inject
    EditAirport editAirport;

    @ManagedProperty(value="#{airportList}")
    private List<Airport> airportList;

    public void downloadAirports() {
        airportList = airportService.findAirports();
    }

    public String hello() {
        return "error";

    }

    public String edit(int id) {
        Airport airport = airportService.findById(id);
        editAirport.setAirport(airport);
        return "editAirport";
    }

    public String delete(int id) throws IOException {
        System.out.println("hello");
        airportService.remove(id);
        downloadAirports();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        return "findAirport";
    }

    public String addNewAirport() {
        return "createAirport";
    }
    public List<Airport> getAirportList() {
        return airportList;
    }
    public void setAirportList(List<Airport> airportList) {
        this.airportList = airportList;
    }


    public void checkRole() throws IOException  {
        HttpSession session = Util.getSession();
        String position = (String) session.getAttribute("position");
        if(position == null || !position.equals("Booking office administrator")) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            System.out.println("User with " + position + " tried to get reach Admin page (find Airport)");

            context.redirect("signIn.xhtml");
        }
    }
}
