package dao;

import entities.Company;
import entities.Customer;
import entities.Flight;
import idao.ICompanyDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by strapper on 07.10.15.
 */
@Repository
public class CompanyDao implements ICompanyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Company company) {
        if (company != null){
            em.persist(company);
        }
    }

    @Override
    public void remove(int id) {
        // TODO Auto-generated method stub
        Company entity = em.find(Company.class, id);
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Company findById(int id) {
        // TODO Auto-generated method stub
        return em.find(Company.class, id);
    }
    @Override
    public List<Company> findCompanies() {
        TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c ", Company.class);
    //    TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c where (c.name LIKE: param) ", Company.class);
    //    query.setParameter("param", name);  //
        List<Company> list = query.getResultList();
        return list;
    }





}
