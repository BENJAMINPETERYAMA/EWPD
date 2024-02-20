/*
 * ContractNonAdjBenefitMandateGenaralInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitMandateRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitMandateResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractNonAdjBenefitMandateBackingBean extends
		ContractBackingBean {
	
	private List locateResultsList;
	private String optionalIdentifier;
	private Date effectiveDate;
	private Date expiryDate;
	private String mandate;
	private boolean locateResultsReturned;
	
	public String loadContractBenefitMandate(){
		return "contractBenefitMandate";
	}
	

	/**
	 * Returns the locateResultsList
	 * @return List locateResultsList.
	 */
	public List getLocateResultsList() {
		if(!locateResultsReturned){
			RetrieveContractBenefitMandateRequest retrieveContractBenefitMandateRequest =(RetrieveContractBenefitMandateRequest)
			this.getServiceRequest(ServiceManager.RETRIEVE_CONTRACTBENEFITMANDATEREQUEST);
	    	retrieveContractBenefitMandateRequest.setBenefitMasterSystemId(3821); //getProductSessionObject().getBenefitId());
	    	RetrieveContractBenefitMandateResponse retrieveContractBenefitMandateResponse = 
			    (RetrieveContractBenefitMandateResponse)executeService(retrieveContractBenefitMandateRequest); 
	    	if(retrieveContractBenefitMandateResponse !=null){
	    		
	    		this.locateResultsList = retrieveContractBenefitMandateResponse.getNonAdjMandateList();
	    		if(locateResultsList.size()==0){
	    			this.locateResultsList = null;
	    		}
	    	//if(locateResults.getLocateResults()!= null && locateResults.getLocateResults().size()!=0 ){
		    	locateResultsReturned = true;
	    	//}
	    	}

		}

		return locateResultsList;
	}
	/**
	 * Sets the locateResultsList
	 * @param locateResultsList.
	 */
	public void setLocateResultsList(List locateResultsList) {
		this.locateResultsList = locateResultsList;
	}
	
	/**
	 * Returns the effectiveDate
	 * @return Date effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * Returns the expiryDate
	 * @return Date expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * Returns the mandate
	 * @return String mandate.
	 */
	public String getMandate() {
		return mandate;
	}
	/**
	 * Sets the mandate
	 * @param mandate.
	 */
	public void setMandate(String mandate) {
		this.mandate = mandate;
	}
	/**
	 * Returns the optionalIdentifier
	 * @return String optionalIdentifier.
	 */
	public String getOptionalIdentifier() {
		return optionalIdentifier;
	}
	/**
	 * Sets the optionalIdentifier
	 * @param optionalIdentifier.
	 */
	public void setOptionalIdentifier(String optionalIdentifier) {
		this.optionalIdentifier = optionalIdentifier;
	}
}
