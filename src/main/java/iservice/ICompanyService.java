package iservice;

import entities.Company;
import org.primefaces.model.StreamedContent;

import java.io.IOException;
import java.util.List;

/**
 * Created by strapper on 07.10.15.
 */
public interface ICompanyService {
    void persist(Company company);
    void update(Company company);
    void remove(int id);
    Company findById(int id);

    List<Company> findCompanies() ;
    StreamedContent getImage() throws IOException;
    public void updateImage(int id, byte[] array);
}
