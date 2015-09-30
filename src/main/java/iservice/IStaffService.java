package iservice;

import java.util.List;


import entities.Staff;

public interface IStaffService {

	public List<Staff> findStaff();
	public void persist(Staff staff);
	public void update(Staff staff);	
	public String getPassword(String login);	
	public Staff getUser(String login);
	
}
