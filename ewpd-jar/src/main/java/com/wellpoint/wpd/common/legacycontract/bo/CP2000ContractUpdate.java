/*
 * CP2000ContractUpdate.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class CP2000ContractUpdate extends CP2000Contract {
	private String contractStatusForSC;
	public CP2000ContractUpdate(LegacyContract contract){
		super(contract);
	}
	public CP2000ContractUpdate(){
		super();
	}
	public CP2000ContractUpdate(CP2000ContractUpdate contract){
		super(contract);
	}

	/**
	 * @return Returns the contractStatusForSC.
	 */
	public String getContractStatusForSC() {
		return contractStatusForSC;
	}
	/**
	 * @param contractStatusForSC The contractStatusForSC to set.
	 */
	public void setContractStatusForSC(String contractStatusForSC) {
		this.contractStatusForSC = contractStatusForSC;
	}
}
