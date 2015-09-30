package edu.bionic.com;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class MerchantDaoImpl implements MerchantDao {
	@PersistenceContext
	private EntityManager em;

	public void save(Merchant m) {
		em.persist(m);
	}

	public Merchant findById(int id) {
		Merchant m = null;
		m = em.find(Merchant.class, id);
		return m;
	}
	
	public List<Merchant> getAllMerchantList(){
	    List<Merchant> list = null;
	    TypedQuery<Merchant> query =  	em.createQuery("SELECT m FROM Merchant m", 	Merchant.class);
	    list = query.getResultList();
	    return list;
	}

}
