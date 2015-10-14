package service;


import entities.Company;
import idao.ICompanyDao;
import iservice.ICompanyService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by strapper on 08.10.15.
 */
@Named
public class CompanyService implements ICompanyService {

    @Inject
    private ICompanyDao companyDao;

    @Override
    @Transactional
    public void persist(Company company) {
        companyDao.persist(company);
    }

    @Override
    @Transactional
    public void update(Company company) { companyDao.update(company);}

    @Override
    @Transactional
    public void remove(int id) {
        companyDao.remove(id);
    }

    @Override
    public Company findById(int id) {
        return companyDao.findById(id);
    }



    @Override
    public List<Company> findCompanies() {
        List<Company> list = companyDao.findCompanies();
        for(int i = 0; i < list.size(); i++) {
            byte[] image = list.get(i).getImage();
            StreamedContent img = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/png");

            list.get(i).setOutputImage(img);
        }
        return list;
    }



    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String studentId = context.getExternalContext().getRequestParameterMap().get("studentId");
            Company student = findById(Integer.valueOf(studentId));
            return new DefaultStreamedContent(new ByteArrayInputStream(student.getImage()));
        }
    }

    @Transactional
    public void updateImage(int id, byte[] array) {
        companyDao.updateImage(id, array);
    }




}
