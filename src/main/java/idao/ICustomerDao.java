package idao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import entities.Customer;


public interface ICustomerDao {
    void persist(Customer customer);
    void remove(int id);
 //   void update(Customer customer);    
    Customer findById(int id);
    
    /**
     * 
     * @param customerId
     * @return total price of tickets
     */
    Double getTotalPrice(int customerId);
    
    /**
     * converts booked tickets of particular customer into sold and specifies time when it is payed
     * @param customerId
     */
    void convertBookedIntoSold(int customerId, LocalDateTime ldt);
    
    /**
     * 
     * @param ld1
     * @param ld2
     * @return get the price of sold tickets during ld1 and ld2
     */
    double getSoldPrice(LocalDate ld1, LocalDate ld2);
    
	/**
     * Convert all booked tickets more than three days ago in a free status.
     */
    void convertBookedIntoFree();
	void update(Customer cust);
}
