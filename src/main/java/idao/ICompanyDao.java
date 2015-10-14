package idao;

import entities.Company;
import entities.Customer;

import java.util.List;

/**
 * Created by strapper on 07.10.15.
 */
public interface ICompanyDao {
    void persist(Company company);
    void update(Company company);
    void remove(int id);
    Company findById(int id);

    List<Company> findCompanies() ;
    void updateImage(int id, byte[] array);
}
