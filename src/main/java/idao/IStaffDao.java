package idao;

import java.util.List;

import entities.Staff;

public interface IStaffDao {
    void persist(Staff staff);
    void remove(int id);
//  void update(Staff staff);    
    Staff findById(int id);
    void blockAccout(int id);
	List<Staff> getList();
	void update(Staff staff);
	String getPassword(String login);
	Staff getUser(String login);
}
