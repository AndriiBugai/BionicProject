package managedBeans;


import entities.Company;
import iservice.ICompanyService;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by strapper on 07.10.15.
 */

@Named(value = "addCompany")
@Scope("session")
public class AddCompany {

    private static final long serialVersionUID = 1L;

    @Inject
    ICompanyService companyService;

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        image = event.getFile();
        System.out.println("iamge saved");
    }


    @ManagedProperty(value="#{name}")
    private String name;
    @ManagedProperty(value="#{description}")
    private String description;
    @ManagedProperty(value="#{image}")
    private UploadedFile image;

    private final String nameRequired = "Input a name";
    private final String descriptionRequired = "Input a description";
    private final String imamgeRequired = "Input an imamge";

    public String submit() {
        Company company = new Company();
        company.setName(name);
        company.setDescription(description);
        if(image != null) {
            FacesMessage message = new FacesMessage("Succesful", image.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("Succesful" + image.getFileName() + " is uploaded.");

        } else {
            System.out.println("UnSuccesful" );
        }
        try {
            InputStream is = image.getInputstream();
            byte[] bytes = IOUtils.toByteArray(is);
            company.setImage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        companyService.persist(company);
        return "addCompany";
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
    public UploadedFile getImage() {
        return image;
    }
    public void setImage(UploadedFile image) {
        this.image = image;
    }

}
