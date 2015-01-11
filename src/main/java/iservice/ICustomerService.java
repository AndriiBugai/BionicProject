package iservice;


import entities.Customer;

public interface ICustomerService  {
	
	public Customer findById(int id);
	public void confirmOrder(Customer cust) ;
	public void update(Customer cust) ;
	public void persist(Customer c);
	public void convertBookedIntoFree();
}
