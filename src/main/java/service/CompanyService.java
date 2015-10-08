package service;

import entities.Company;
import idao.ICompanyDao;
import iservice.ICompanyService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
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

    public List<Company> findCompanies(String name) {
        return companyDao.findCompanies(name);
    }

    public Company findById(int id) {
        return companyDao.findById(id);
    }
}
