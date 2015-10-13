package managedBeans;

import entities.Company;
import iservice.ICompanyService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
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

    @ManagedProperty(value="#{companyList}")
    private List<Company> companyList;

    public void downloadCompanies() {
        companyList = companyService.findCompanies();
    }

    public byte[] getImage(int studentId) {
        return companyService.findById(studentId).getImage();
    }



    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
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
}
