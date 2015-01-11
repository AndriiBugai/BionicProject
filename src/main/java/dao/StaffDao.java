package dao;

import idao.IStaffDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Staff;

@Repository
public class StaffDao implements IStaffDao  {

    @PersistenceContext
    private EntityManager em;
	

	
	@Override
	public void persist(Staff staff) {
		// TODO Auto-generated method stub
		if (staff != null){
		
			em.persist(staff);
	
		}
	}
	
	
	public void update(Staff staff) {
		// TODO Auto-generated method stub
		if (staff != null){
		
			em.merge(staff);
		
		}
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		Staff entity = em.find(Staff.class, id); 
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}

	@Override
	public Staff findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Staff.class, id);
	}

	@Override
	public void blockAccout(int id) {
		// TODO Auto-generated method stub
		Staff staff = em.find(Staff.class, id);
			em.getTransaction().begin();
			staff.setEnabled("Disabled");	
			em.getTransaction().commit();	
	}
	
	public List<Staff> getList() {
		// TODO Auto-generated method stub
			
			TypedQuery<Staff> query = em.createQuery("SELECT c FROM Staff c",Staff.class);
			List<Staff> results = query.getResultList();
			return results;
	}
	
	public String getPassword(String login) {
		TypedQuery<String> query = em.createQuery("SELECT c.password FROM Staff c where c.login = :text ",String.class);
		query.setParameter("text", login);
		String password;

		List<String> list =  query.getResultList();
		if(list.size() == 0 ) {
			password = "";
		} else {
			password = list.get(0);
		}

		return password;
	}
	
	public Staff getUser(String login) {
		TypedQuery<Staff> query = em.createQuery("SELECT c FROM Staff c where c.login = :text ",Staff.class);
		query.setParameter("text", login);
		
		Staff s = query.getSingleResult();

		return s;
	}
	
	
	
}
