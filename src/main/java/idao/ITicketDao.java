package idao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;




import entities.ReportDate;
import entities.ReportPlace;
import entities.Ticket;

public interface ITicketDao {
   
    void persist(Ticket ticket);
    void remove(int id);
//  void update(Ticket ticket);    
    Ticket findById(int id);
	
	

    
    /**
     * 
     * @return list of all booked tickets 
     */
    List<Ticket> getBookedList();
        
    /**
     * 
     * @param bookingNumber a unique field if customer
     * @return list of tickets booked by only one person
     */
    List<Ticket> getBookedList(String bookingNumber);
      
    /**
     * 
     * @param ld1
     * @param ld2
     * @return get the number of sold tickets during ld1 and ld2
     */
    long getSoldCount(LocalDate ld1, LocalDate ld2);
	List<ReportPlace> getReportByPlace(LocalDate ld1, LocalDate ld2);
	List<ReportDate> getReportByDate(LocalDate ld1, LocalDate ld2);
    

    
}
