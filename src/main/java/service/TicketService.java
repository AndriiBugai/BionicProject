package service;

import idao.ITicketDao;
import iservice.ITicketService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import entities.Ticket;

@Named
public class TicketService implements ITicketService {
	@Inject
	ITicketDao td;
	
	@Transactional
	public void persist( List<Ticket> cart) {
		for(Ticket t : cart) {
			td.persist(t);
		}
	}
}
