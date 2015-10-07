package idao;

import entities.Company;

/**
 * Created by strapper on 07.10.15.
 */
public interface ICompanyDao {
    void persist(Company company);
    void remove(int id);
}
