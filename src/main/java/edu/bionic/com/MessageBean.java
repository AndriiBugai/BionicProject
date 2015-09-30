package edu.bionic.com;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
@Named("msgs")
@Scope("session")
public class MessageBean {
	private final String nameRequired = "Merchant name field could not be empty";
	private final String bankNameRequired = "Bank name field could not be empty";
	private final String swiftRequired = "Bank swift code field could not be empty";
	private final String accountRequired = "Account field could not be empty";
	private final String chargeRequired = "Charge percent field could not be empty";
	private final String chargeRange = "Charge percent field is not between the expected values of 0.1 and 15";
	private final String minSumRequired = "Minimum sum field could not be empty";
	private final String minSumRange = "Minimum sum field is not between the expected values of 0.10 and 10 000.0";

	     public MessageBean(){   }

		public String getNameRequired() {
			return nameRequired;
		}

		public String getBankNameRequired() {
			return bankNameRequired;
		}

		public String getChargeRange() {
			return chargeRange;
		}

		public String getSwiftRequired() {
			return swiftRequired;
		}

		public String getAccountRequired() {
			return accountRequired;
		}

		public String getChargeRequired() {
			return chargeRequired;
		}

		public String getMinSumRequired() {
			return minSumRequired;
		}

		public String getMinSumRange() {
			return minSumRange;
		}

	    
	}
