package edu.bionic.com;

import java.util.List;

public interface MerchantDao {

	public Merchant findById(int id);
	public void save(Merchant m);
	public List<Merchant> getAllMerchantList();
	
}
