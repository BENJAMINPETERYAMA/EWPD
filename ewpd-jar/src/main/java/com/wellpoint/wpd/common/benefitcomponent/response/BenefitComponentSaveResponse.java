/*
 * BenefitComponentSaveResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Response for Benefit Component Save
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentSaveResponse extends WPDResponse {
	
	private BenefitComponentBO benefitComponentBO;
	
	private boolean successFlagForSave;
	
	private boolean successFlag;	
	
	private DomainDetail domainDetail;
	
	/**
	 * Constructor
	 *
	 */
	public BenefitComponentSaveResponse(){
		benefitComponentBO = new BenefitComponentBO();
	}

	
	/**
	 * Returns the benefitComponentBO
	 * @return BenefitComponentBO benefitComponentBO.
	 */
	public BenefitComponentBO getBenefitComponentBO() {
		return benefitComponentBO;
	}
	/**
	 * Sets the benefitComponentBO
	 * @param benefitComponentBO.
	 */
	public void setBenefitComponentBO(BenefitComponentBO benefitComponentBO) {
		this.benefitComponentBO = benefitComponentBO;
	}
	/**
	 * Returns the successFlag
	 * @return boolean successFlag.
	 */
	public boolean isSuccessFlag() {
		return successFlag;
	}
	/**
	 * Sets the successFlag
	 * @param successFlag.
	 */
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	
	/**
	 * @return Returns the domainDetail.
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}
	/**
	 * @param domainDetail The domainDetail to set.
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}
	/**
	 * @return Returns the successFlagForSave.
	 */
	public boolean isSuccessFlagForSave(){
		return successFlagForSave;
	}
	/**
	 * @param successFlagForSave The successFlagForSave to set.
	 */
	public void setSuccessFlagForSave(boolean successFlagForSave){
		this.successFlagForSave = successFlagForSave;
	}
}
