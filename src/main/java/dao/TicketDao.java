package dao;

import idao.ITicketDao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.ReportDate;
import entities.ReportPlace;
import entities.Ticket;

@Repository
public class TicketDao implements ITicketDao {

    @PersistenceContext
    private EntityManager em;
	

	
	@Override
	public void persist(Ticket ticket) {
		// TODO Auto-generated method stub
		if (ticket != null){
			
			em.persist(ticket);
			
		}
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		Ticket entity = em.find(Ticket.class, id); 
		
		em.remove(entity);
		
	}

	@Override
	public Ticket findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Ticket.class, id);
	}



	@Override
	public List<Ticket> getBookedList() {
		// TODO Auto-generated method stub
		TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t where (t.customer.paymentDate is null) ", Ticket.class);
		List<Ticket> list = query.getResultList(); 
		return list;
	}



	@Override
	public List<Ticket> getBookedList(String bookingNumber) {
		// TODO Auto-generated method stub
		TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t where (t.customer.bookingNumber = :bN) ", Ticket.class);
		query.setParameter("bN", bookingNumber);
		List<Ticket> list = query.getResultList(); 
		return list;
	}

	@Override
	public long getSoldCount(LocalDate ld1, LocalDate ld2) {
		// TODO Auto-generated method stub
		Timestamp ts1 = Timestamp.valueOf(ld1.atStartOfDay());
		ld2 = ld2.plusDays(1);
		Timestamp ts2 = Timestamp.valueOf(ld2.atStartOfDay());
		
		TypedQuery<Long> query = em.createQuery("SELECT count(t.price) FROM Ticket t where (t.customer.paymentDate > :ld1) and (t.customer.paymentDate < :ld2) ", Long.class);
		query.setParameter("ld1", ts1);
		query.setParameter("ld2", ts2);
		long number = 0;
		if(query.getSingleResult() != null) {
			number = query.getSingleResult();
		}
		return number;
	}


	
	public List<ReportPlace> getReportByPlace(LocalDate ld1, LocalDate ld2) {
		Timestamp ts1 = Timestamp.valueOf(ld1.atStartOfDay());
		ld2 = ld2.plusDays(1);
		Timestamp ts2 = Timestamp.valueOf(ld2.atStartOfDay());
		TypedQuery<ReportPlace> query = em.createQuery("select new entities.ReportPlace(a.name, count(t.price), sum(t.price)) from Ticket t, Airport a where t.flight.destAirport.id = a.id and (t.customer.paymentDate > :ld1) and (t.customer.paymentDate < :ld2) group by a.name ", ReportPlace.class );
		query.setParameter("ld1", ts1);
		query.setParameter("ld2", ts2);
		List<ReportPlace> list = query.getResultList(); 
		return list;
	}
	
	public List<ReportDate> getReportByDate(LocalDate ld1, LocalDate ld2) {
		Timestamp ts1 = Timestamp.valueOf(ld1.atStartOfDay());
		ld2 = ld2.plusDays(1);
		Timestamp ts2 = Timestamp.valueOf(ld2.atStartOfDay());
		TypedQuery<ReportDate> query = em.createQuery("select new entities.ReportDate(c.paymentDateNoTime, count(t.price), sum(t.price)) from Ticket t, Customer c where t.customer.id = c.id and (t.customer.paymentDate > :ld1) and (t.customer.paymentDate < :ld2) group by c.paymentDateNoTime ", ReportDate.class );
		query.setParameter("ld1", ts1);
		query.setParameter("ld2", ts2);
		List<ReportDate> list = query.getResultList(); 
		return list;
	}

}
