package service;

import idao.IStaffDao;
import iservice.IStaffService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import entities.Staff;

@Named
public class StaffService implements IStaffService {

	@Inject
	IStaffDao sd;
	
	public List<Staff> findStaff() {
	   return sd.getList();
	}
	
	@Transactional
	public void persist(Staff staff) {
		sd.persist(staff);
	}
//	
	@Transactional
	public void update(Staff staff) {
		sd.update(staff);
	}
	
	public String getPassword(String login) {
		String password = sd.getPassword(login);
		return password;
	}
	
	
	public Staff getUser(String login) {
		Staff user = sd.getUser(login);
		return user;
	}
}
