package iservice;

import java.util.List;

import entities.Ticket;

public interface ITicketService {
	public void persist( List<Ticket> cart);
}
