package managedBeans;

import entities.Airport;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
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
}
