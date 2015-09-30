package edu.bionic.com;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;


@Named
@Scope("session")
public class MerchantBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Merchant merchant;
	@Inject
	private MerchantService merchantService;

	public MerchantBean(){
	     merchant = new Merchant();           
	}

	public String saveMerchant(){
	      merchantService.save(merchant);
	      return "MerchantList";
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public MerchantService getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	
}
