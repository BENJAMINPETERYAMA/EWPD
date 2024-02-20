/*
 * RetrieveBaseContractRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBaseContractRequest extends ContractRequest{
	
	public static final int BASECONTRACTS_FOR_ETAB = 1;
	public static final int BASECONTRACTS_FOR_MIGRATION = 2;
		
	 	List lineOfBusiness;
	    List businessEntity;
	    List businessGroup;
		/*START CARS*/
	    List marketBusinessUnit;
		/*END CARS*/
	    private int action;
	    private String contractId;
	    private int productParentSysId;
	 
		/**
		 * @return Returns the productParentSysId.
		 */
		public int getProductParentSysId() {
			return productParentSysId;
		}
		/**
		 * @param productParentSysId The productParentSysId to set.
		 */
		public void setProductParentSysId(int productParentSysId) {
			this.productParentSysId = productParentSysId;
		}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
		/**
		 * Returns the businessEntity
		 * @return List businessEntity.
		 */
		public List getBusinessEntity() {
			return businessEntity;
		}
		/**
		 * Sets the businessEntity
		 * @param businessEntity.
		 */
		public void setBusinessEntity(List businessEntity) {
			this.businessEntity = businessEntity;
		}
		/**
		 * Returns the businessGroup
		 * @return List businessGroup.
		 */
		public List getBusinessGroup() {
			return businessGroup;
		}
		/**
		 * Sets the businessGroup
		 * @param businessGroup.
		 */
		public void setBusinessGroup(List businessGroup) {
			this.businessGroup = businessGroup;
		}
		/**
		 * Returns the lineOfBusiness
		 * @return List lineOfBusiness.
		 */
		public List getLineOfBusiness() {
			return lineOfBusiness;
		}
		/**
		 * Sets the lineOfBusiness
		 * @param lineOfBusiness.
		 */
		public void setLineOfBusiness(List lineOfBusiness) {
			this.lineOfBusiness = lineOfBusiness;
		}
		
		/**
		 * @return Returns the action.
		 */
		public int getAction() {
			return action;
		}
		/**
		 * @param action The action to set.
		 */
		public void setAction(int action) {
			this.action = action;
		}
		
		/**
		 * @return Returns the contractId.
		 */
		public String getContractId() {
			return contractId;
		}
		/**
		 * @param contractId The contractId to set.
		 */
		public void setContractId(String contractId) {
			this.contractId = contractId;
		}
		/**
		 * @return Returns the marketBusinessUnit.
		 */
		public List getMarketBusinessUnit() {
			return marketBusinessUnit;
		}
		/**
		 * @param marketBusinessUnit The marketBusinessUnit to set.
		 */
		public void setMarketBusinessUnit(List marketBusinessUnit) {
			this.marketBusinessUnit = marketBusinessUnit;
		}
}
