package service;

import idao.ICustomerDao;
import iservice.ICustomerService;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Customer;


@Named
public class CustomerService implements ICustomerService {

	 @Inject
	 private ICustomerDao cd;
	
	public Customer findById(int id) {
		Customer cust = cd.findById(id);
		return cust;
	}
	
	@Transactional
	public void persist(Customer c) {
		cd.persist(c);
	}
	
	@Transactional
	public void convertBookedIntoFree() {
		cd.convertBookedIntoFree();
	}
	
	@Transactional
	public void confirmOrder(Customer cust) {
					
		java.util.Date currentDate= new java.util.Date();
	
		Timestamp paymentDate = new Timestamp(currentDate.getTime());
		java.sql.Date paymentDateNoTime = new java.sql.Date(currentDate.getTime());
			
		cust.setPaymentDate(paymentDate);
		cust.setPaymentDateNoTime(paymentDateNoTime);
		update(cust);
	}
	
	@Transactional
	public void update(Customer cust) {
		cd.update(cust);
	}
	
	
	
}
