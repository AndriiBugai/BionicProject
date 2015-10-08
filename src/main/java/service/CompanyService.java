package service;

import entities.Company;
import idao.ICompanyDao;
import iservice.ICompanyService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

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
}
