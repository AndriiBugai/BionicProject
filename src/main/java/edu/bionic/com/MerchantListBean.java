package edu.bionic.com;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
@Named
@Scope("request")
public class MerchantListBean {
	private List<Merchant> merchants = null;
	@Inject
	private MerchantService merchantService;
	public MerchantListBean(){   }
	public List<Merchant> getMerchants() { return merchants; }
	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants; }
	public void refreshList(){
		merchants = merchantService.getAllMerchantList();
	}}
