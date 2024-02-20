/*
 * LocateMigrationPricingInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationPricingInfoRequest extends MigrationContractRequest{
	
    private String coverage;   
    private String pricing;    
    private String network;
    private int migratedDSSysID;
    private String contractId;
	private int action;
	private String migratedContractSysId;
	private List pricingInformationList;	
    public static final int MIGRATION_SAVE_ADD_PRICING_INFO = 1;
    public static final int MIGRATION_DELETE_PRICING_INFO = 2;
    public static final int MIGRATION_RETRIEVE_PRICING_INFO = 3;
    public static final int MIGRATION_DELETE_ALL_PRICING_INFO = 4;
    public static final int UPDATE_STEP_COMPLETED = 5;
    
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
			
	}
	/**
	 * Returns the coverage
	 * @return String coverage.
	 */
	public String getCoverage() {
		return coverage;
	}
	/**
	 * Sets the coverage
	 * @param coverage.
	 */
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	/**
	 * Returns the network
	 * @return String network.
	 */
	public String getNetwork() {
		return network;
	}
	/**
	 * Sets the network
	 * @param network.
	 */
	public void setNetwork(String network) {
		this.network = network;
	}
	/**
	 * Returns the pricing
	 * @return String pricing.
	 */
	public String getPricing() {
		return pricing;
	}
	/**
	 * Sets the pricing
	 * @param pricing.
	 */
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * Returns the migratedDSSysID
	 * @return int migratedDSSysID.
	 */
	public int getMigratedDSSysID() {
		return migratedDSSysID;
	}
	/**
	 * Sets the migratedDSSysID
	 * @param migratedDSSysID.
	 */
	public void setMigratedDSSysID(int migratedDSSysID) {
		this.migratedDSSysID = migratedDSSysID;
	}
	/**
	 * Returns the pricingInformationList
	 * @return List pricingInformationList.
	 */
	public List getPricingInformationList() {
		return pricingInformationList;
	}
	/**
	 * Sets the pricingInformationList
	 * @param pricingInformationList.
	 */
	public void setPricingInformationList(List pricingInformationList) {
		this.pricingInformationList = pricingInformationList;
	}
	
	/**
	 * Returns the contractId
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the migratedContractSysId
	 * @return String migratedContractSysId.
	 */
	public String getMigratedContractSysId() {
		return migratedContractSysId;
	}
	/**
	 * Sets the migratedContractSysId
	 * @param migratedContractSysId.
	 */
	public void setMigratedContractSysId(String migratedContractSysId) {
		this.migratedContractSysId = migratedContractSysId;
	}
}
