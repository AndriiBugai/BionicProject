package dao;

import entities.Company;
import entities.Customer;
import idao.ICompanyDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        Customer entity = em.find(Customer.class, id);
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Customer findById(int id) {
        // TODO Auto-generated method stub
        return em.find(Customer.class, id);
    }


}
