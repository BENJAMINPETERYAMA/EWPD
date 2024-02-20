/*
 * RetrieveAllPossibleAnswerRequest
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveAllPossibleAnswerRequest extends ContractRequest {
    
	//	14 jan 2011 - Code optimization for - get possible answers
    private int adminOptSysId;
    private int benefitComponentId;
    private int benefitAminSysId;
    private int benefitId;
    private int dateSegmentSysId;
    private Domain domain;
    private int contractParentSysId;
    
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

    /**
     * Returns the adminOptSysId
     * @return int adminOptSysId.
     */
    public int getAdminOptSysId() {
        return adminOptSysId;
    }
    /**
     * Sets the adminOptSysId
     * @param adminOptSysId.
     */
    public void setAdminOptSysId(int adminOptSysId) {
        this.adminOptSysId = adminOptSysId;
    }

	public int getBenefitAminSysId() {
		return benefitAminSysId;
	}

	public void setBenefitAminSysId(int benefitAminSysId) {
		this.benefitAminSysId = benefitAminSysId;
	}

	public int getBenefitComponentId() {
		return benefitComponentId;
	}

	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}

	public int getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}

	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}

	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public int getContractParentSysId() {
		return contractParentSysId;
	}

	public void setContractParentSysId(int contractParentSysId) {
		this.contractParentSysId = contractParentSysId;
	}
    
    
    
}
