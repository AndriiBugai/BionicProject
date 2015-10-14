package dao;

import idao.IAirportDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Airport;

@Repository
public class AirportDao implements IAirportDao {
    @PersistenceContext
    private EntityManager em;
	

	
	@Override
	public void persist(Airport airport) {
		// TODO Auto-generated method stub
		if (airport != null){
			em.persist(airport);
		}
	}
	
	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		Airport entity = em.find(Airport.class, id);

			em.remove(entity);
		
	}

	@Override
	public Airport findById(int id) {
		// TODO Auto-generated method stub
			return em.find(Airport.class, id);

	}
	
	public List<Airport> findAirports() {
		// TODO Auto-generated method stub
		 List<Airport> list = null;
		 TypedQuery<Airport> query =  	em.createQuery("SELECT a FROM Airport a", 	Airport.class);
		 list = query.getResultList();
		 return list;
	}
	
	public List<Airport> findAirports(String city) {
		// TODO Auto-generated method stub
		 List<Airport> list = null;
		 TypedQuery<Airport> query =  	em.createQuery("SELECT a FROM Airport a where a.city = :city", 	Airport.class);
		 query.setParameter("city", city);
		 list = query.getResultList();
		 for(Airport a : list) {
			 System.out.println(a.getStringForPrint());
		 }
		 return list;
	}
	
	public void finalize() {
		em.close();
	}


}
