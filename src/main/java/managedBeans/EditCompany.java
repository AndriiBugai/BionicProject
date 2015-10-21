package managedBeans;

import entities.Company;
import iservice.ICompanyService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by strapper on 14.10.15.
 */

@Named(value = "editCompany")
@Scope("session")
public class EditCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    ICompanyService companyService;

    @ManagedProperty(value="#{company}")
    Company company;
    @ManagedProperty(value="#{image}")
    private byte[] image;

    private final String nameRequired = "Input a name";
    private final String descriptionRequired = "Input a description";
    private final String imamgeRequired = "Input an file";

    public void update() {
        System.out.println("commit");
        company.setImage(image);
        companyService.updateImage(company.getId(), image);
     //   companyService.update(company);
 //       companyService.persist(company);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Changes are saved!"));
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        UploadedFile file = event.getFile();
        image = file.getContents();
        System.out.println("hello");
    }

    public Company getCompany() {
        return company;
    }

    public String backToList() {
        return "findCompanies";
    }

    public void setCompany(Company company) {
        this.company = company;
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
            System.out.println("User with " + position + " tried to get reach Admin page (edit Company)");

            context.redirect("signIn.xhtml");
        }
    }
}
