package managedBeans;

import entities.Company;
import iservice.ICompanyService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by strapper on 13.10.15.
 */
@Named(value = "findCompany")
@Scope("session")
public class FindCompanies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    ICompanyService companyService;

    @Inject
    EditCompany editCompany;

    @ManagedProperty(value="#{companyList}")
    private List<Company> companyList;

    public void downloadCompanies() {
        companyList = companyService.findCompanies();
    }

    public byte[] getImage(int studentId) {
        return companyService.findById(studentId).getImage();
    }


    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public String delete(int id) throws IOException {
        companyService.remove(id);
        downloadCompanies();
        addMessage("System Error", "Please try again later.");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        return "findCompany";
    }

    public String edit(int id) {
        Company copmany = companyService.findById(id);
        editCompany.setCompany(copmany);
        return "editCompany";
    }

    public String addNewCompany() {
        return "addCompany";
    }


    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String studentId = context.getExternalContext().getRequestParameterMap().get("companyId");
            Company student = companyService.findById(Integer.valueOf(studentId));
            return new DefaultStreamedContent(new ByteArrayInputStream(student.getImage()));
        }
    }

    public void checkRole() throws IOException  {
        HttpSession session = Util.getSession();
        String position = (String) session.getAttribute("position");
        if(position == null || !position.equals("Booking office administrator")) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            System.out.println("User with " + position + " tried to get reach Admin page (find Companies)");

            context.redirect("signIn.xhtml");
        }
    }
}
