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
    List<Company> findCompanies() ;
    Company findById(int id);
    void remove(int id);
    StreamedContent getImage() throws IOException;
}
