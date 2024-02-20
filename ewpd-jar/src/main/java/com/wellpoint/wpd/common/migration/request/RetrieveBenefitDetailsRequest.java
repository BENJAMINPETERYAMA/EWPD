/*
 * Created on Apr 7, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author u13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveBenefitDetailsRequest extends MigrationContractRequest {
	
	private int benefitComponentId;
	private String migratedDateSegmentId;
	private String action;
	private String contractSysId;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}



	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the migratedDateSegmentId.
	 */
	public String getMigratedDateSegmentId() {
		return migratedDateSegmentId;
	}
	/**
	 * @param migratedDateSegmentId The migratedDateSegmentId to set.
	 */
	public void setMigratedDateSegmentId(String migratedDateSegmentId) {
		this.migratedDateSegmentId = migratedDateSegmentId;
	}
	/**
	 * @return Returns the contractSysId.
	 */
	public String getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(String contractSysId) {
		this.contractSysId = contractSysId;
	}
}
