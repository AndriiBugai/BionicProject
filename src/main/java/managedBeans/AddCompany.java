package managedBeans;


import entities.Company;
import iservice.ICompanyService;
import org.apache.commons.io.IOUtils;
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
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by strapper on 07.10.15.
 */

@Named(value = "addCompany")
@Scope("session")
public class AddCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    ICompanyService companyService;

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        file = event.getFile();
        image = file.getContents();
//        image = new DefaultStreamedContent(new ByteArrayInputStream(arr), "image/png");
    }

    @ManagedProperty(value="#{name}")
    private String name;
    @ManagedProperty(value="#{description}")
    private String description;
    @ManagedProperty(value="#{file}")
    private UploadedFile file;
    @ManagedProperty(value="#{image}")
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private final String nameRequired = "Input a name";
    private final String descriptionRequired = "Input a description";
    private final String imamgeRequired = "Input an file";



    public void destroyWorld() {
        submit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "The new company has been successfully added"));
    }


    public String backToList() {
        return "findCompanies";
    }

    public void submit() {
        if(file == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Upload a logo for the company");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return ;
        }
        Company company = new Company();
        company.setName(name);
        company.setDescription(description);
        company.setImage(image);
        companyService.persist(company);
    }

    public String getNameRequired() {
        return nameRequired;
    }
    public String getDescriptionRequired() {
        return descriptionRequired;
    }
    public String getImamgeRequired() {
        return imamgeRequired;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public UploadedFile getFile() {
        return file;
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
