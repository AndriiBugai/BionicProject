package iservice;

import entities.Company;

import java.util.List;

/**
 * Created by strapper on 07.10.15.
 */
public interface ICompanyService {
    void persist(Company company);
    List<Company> findCompanies(String name) ;
    Company findById(int id);
}
