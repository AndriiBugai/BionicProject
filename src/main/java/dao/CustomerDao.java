package dao;

import idao.ICustomerDao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Customer;
import entities.Ticket;

@Repository
public class CustomerDao implements ICustomerDao {

    @PersistenceContext
    private EntityManager em;
	

	
	@Override
	public void persist(Customer customer) {
		// TODO Auto-generated method stub
		if (customer != null){
			
			em.persist(customer);
			
		}
	}
	

	public void update(Customer customer) {
		// TODO Auto-generated method stub
		if (customer != null){
		
			em.merge(customer);
		
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

	@Override
	public Double getTotalPrice(int customerId) {
		// TODO Auto-generated method stub
		Customer entity = em.find(Customer.class, customerId); 
		TypedQuery<Double> query = em.createQuery("SELECT sum(t.price) FROM Ticket t where t.customer = :entity ", Double.class);
		query.setParameter("entity", entity);
		Double price = 0.0;
		if(query.getSingleResult() != null) {
			price = query.getSingleResult();
		}
		return price;
	}
	
	@Override
	public double getSoldPrice(LocalDate ld1, LocalDate ld2) {
		// TODO Auto-generated method stub
		Timestamp ts1 = Timestamp.valueOf(ld1.atStartOfDay());
		ld2 = ld2.plusDays(1);
		Timestamp ts2 = Timestamp.valueOf(ld2.atStartOfDay());
		
		TypedQuery<Double> query = em.createQuery("SELECT sum(t.price) FROM Ticket t where (t.customer.paymentDate > :ld1) and (t.customer.paymentDate < :ld2) ", Double.class);
		query.setParameter("ld1", ts1);
		query.setParameter("ld2", ts2);
		Double price = 0.0;
		if(query.getSingleResult() != null) {
			price = query.getSingleResult();
		}
		return price;
	}
	
	@Override
	public void convertBookedIntoSold(int customerId, LocalDateTime ldt) {
		// TODO Auto-generated method stub
		Customer customer = em.find(Customer.class, 1);
   
	    customer.setBookingDate(Timestamp.valueOf(ldt));
	    
	}
	
	@Override
	public void convertBookedIntoFree() {
		// TODO Auto-generated method stub
	
		LocalDateTime now = LocalDateTime.now();
		now.minusDays(3);
		Timestamp ts = Timestamp.valueOf(now);
		
		//todayDate.
		TypedQuery<Ticket> query = em.createQuery("delete from Ticket t where t.customer.bookingDate < :ts and t.customer.paymentDate is null ", Ticket.class);
		query.setParameter("ts", ts);
		TypedQuery<Customer> query2 = em.createQuery("delete from Customer c where c.bookingDate < :ts and c.paymentDate is null ", Customer.class);
		query2.setParameter("ts", ts);
		
		query.executeUpdate();
		query2.executeUpdate();

	}
	
}
