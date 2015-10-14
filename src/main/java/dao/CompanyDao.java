package dao;

import entities.Company;
import entities.Customer;
import entities.Flight;
import idao.ICompanyDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
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
            System.out.println(company.getImage().length);
            em.persist(company);

        }
    }

    @Override
    public void update(Company company) {
        // TODO Auto-generated method stub
        if (company != null){
            System.out.println(company.getImage().length) ;
            em.merge(company);


        }
    }

    @Override
    public void remove(int id) {
        // TODO Auto-generated method stub
        Company entity = em.find(Company.class, id);
        em.remove(entity);
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

    public void updateImage(int id, byte[] array) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Company> updateCriteria = cb.createCriteriaUpdate(Company.class);
        Root<Company> root = updateCriteria.from(Company.class);
// update dateOfBirth property
        updateCriteria.set(root.get("image"), array);
// set where clause
        updateCriteria.where(cb.equal(root.get("id"), id));
// update
        int affected = em.createQuery(updateCriteria).executeUpdate();
        System.out.println("Affected row: " + affected);
    }






}
