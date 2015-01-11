package dao;

import idao.IFlightDao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Airport;
import entities.Flight;

@Repository
public class FlightDao implements IFlightDao {

    @PersistenceContext
    private EntityManager em;
	
	@Override
	public void persist(Flight flight) {
		// TODO Auto-generated method stub
		if (flight != null){
		
			em.persist(flight);
		
		}
	}
	
	@Override
	public void update(Flight flight) {
		// TODO Auto-generated method stub
		if (flight != null){
		
			em.merge(flight);
		
		}
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		Flight entity = em.find(Flight.class, id); 
	
		em.remove(entity);
	
	}

	@Override
	public Flight findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Flight.class, id);
	}

	@Override
	public List<Flight> getFlights(Airport departurePlace, Airport arrivalPlace, LocalDate ld) {
		// TODO Auto-generated method stub
			LocalDate ldend = ld.plusDays(1);
			
	    	Timestamp start = Timestamp.valueOf(ld.atStartOfDay());
	    	Timestamp end = Timestamp.valueOf(ldend.atStartOfDay());
	    	
			TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f where (f.destAirport = :dest) and (f.depAirport = :dep) and (f.departureTime between :start and :end)  ", Flight.class);
			query.setParameter("dest", arrivalPlace);  // 
			query.setParameter("dep", departurePlace);
			query.setParameter("start", start);
			query.setParameter("end", end);
		
			List<Flight> list = query.getResultList(); 

			return list;
	}
	

	
//	@Override
	public List<Flight> getFlights(String departurePlace, String arrivalPlace, LocalDate ld) {
		// TODO Auto-generated method stub
			LocalDate ldend = ld.plusDays(1);
			
	    	Timestamp start = Timestamp.valueOf(ld.atStartOfDay());
	    	Timestamp end = Timestamp.valueOf(ldend.atStartOfDay());
	    	System.out.println(departurePlace);
	    	System.out.println(arrivalPlace);
	    	System.out.println(start);
	    	System.out.println(end);
	    	
			TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f where (f.destAirport.city = :dest) and (f.depAirport.city = :dep) and (f.departureTime between :start and :end)  ", Flight.class);
			query.setParameter("dest", arrivalPlace);  // 
			query.setParameter("dep", departurePlace);
			query.setParameter("start", start);
			query.setParameter("end", end);
			List<Flight> list = query.getResultList(); 

			System.out.println("!?!?!??!!?");
			System.out.println(list.size());
			System.out.println("?!?!?!?!?!?!?!");
			for(Flight s: list) {
				System.out.println("?!?!?" + s.getStringForPrint());
			}
			return list;
	}

	
//	@Override
	public List<Flight> getFlights(String departurePlace, String arrivalPlace, LocalDate ld1, LocalDate ld2 ) {
		// TODO Auto-generated method stub
			LocalDate ldend = ld2.plusDays(1);
			
	    	Timestamp start = Timestamp.valueOf(ld1.atStartOfDay());
	    	Timestamp end = Timestamp.valueOf(ldend.atStartOfDay());
	    	System.out.println(departurePlace);
	    	System.out.println(arrivalPlace);
	    	System.out.println(start);
	    	System.out.println(end);
	    	
			TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f where (f.destAirport.city = :dest) and (f.depAirport.city = :dep) and (f.departureTime between :start and :end)  ", Flight.class);
			query.setParameter("dest", arrivalPlace);  // 
			query.setParameter("dep", departurePlace);
			query.setParameter("start", start);
			query.setParameter("end", end);
			List<Flight> list = query.getResultList(); 

			System.out.println("!?!?!??!!?");
			System.out.println(list.size());
			System.out.println("?!?!?!?!?!?!?!");
			for(Flight s: list) {
				System.out.println("?!?!?" + s.getStringForPrint());
			}
			return list;
	}	
	
	@Override
	public List<Flight> getFlights(Airport departurePlace, Airport arrivalPlace, LocalDate ld1, LocalDate ld2) {
		// TODO Auto-generated method stub
		LocalDate ldend = ld2.plusDays(1);
		
    	Timestamp start = Timestamp.valueOf(ld1.atStartOfDay());
    	Timestamp end = Timestamp.valueOf(ldend.atStartOfDay());
    	
		TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f where (f.destAirport = :dest) and (f.depAirport = :dep) and (f.departureTime between :start and :end)  ", Flight.class);
		query.setParameter("dest", arrivalPlace);  // 
		query.setParameter("dep", departurePlace);
		query.setParameter("start", start);
		query.setParameter("end", end);
		List<Flight> list = query.getResultList(); 
		return list;
	}

	@Override
	public int getAvailableEconomyTickets(int flightId) {
		// TODO Auto-generated method stub
		Flight f = em.find(Flight.class, flightId);
		TypedQuery<Long> query = em.createQuery("SELECT count(t) FROM Ticket t where t.flight = :flight and t.seatClass = 'economy class' ", Long.class);
		query.setParameter("flight", f);  
		long number =  query.getSingleResult();
		return  f.getEconomyClass() - (int) number;
	}

	@Override
	public int getAvailableFirstTickets(int flightId) {
		// TODO Auto-generated method stub
		Flight f = em.find(Flight.class, flightId);
		TypedQuery<Long> query = em.createQuery("SELECT count(t) FROM Ticket t where t.flight = :flight and t.seatClass = 'first class' ", Long.class);
		query.setParameter("flight", f);  
		long number =  query.getSingleResult();
		return  f.getFirstClass() - (int) number;
	}
	
	@Override
    public List<Flight> getAllFlightList(){
    	List<Flight> list = null;
    	TypedQuery<Flight> query =  em.createQuery("SELECT m FROM Flight m", Flight.class);
    	list = query.getResultList();
    	System.out.println("selectflight" + list.size());
    	for(Flight f : list) {
    		System.out.println(f.getStringForPrint());
    	}
    	return list;
    }

}
