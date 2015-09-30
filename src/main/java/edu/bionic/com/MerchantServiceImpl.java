package edu.bionic.com;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
@Named
public class MerchantServiceImpl implements MerchantService {
    @Inject
    private MerchantDao merchantDao;
    @Transactional
    public void save(Merchant m){
	    merchantDao.save(m); }
	@Override
	public Merchant findById(int id) {
		return merchantDao.findById(id);
	
	}
    
	public List<Merchant> getAllMerchantList(){
	    return merchantDao.getAllMerchantList();
	}

}
