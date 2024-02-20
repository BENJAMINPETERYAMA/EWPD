/*
 * RetrieveBenefitGeneralInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitGeneralInfoRequest  extends ContractRequest{
    
    public static final int GET_BENEFIT_DEFINITION = 1;
	
	public static final int VIEW_BENEFIT_DEFINITION = 2; 
	
	public static final int RULE_BENEFIT_DEFINITION = 3; 
	
	private int action;
    private int standardBenefitKey;
    private int benefitComponentId;
    private ContractVO contractVO;
    private int dateSegmentId;
    
    
    
    /**
     * Returns the contractVO
     * @return ContractVO contractVO.
     */
    public ContractVO getContractVO() {
        return contractVO;
    }
    /**
     * Sets the contractVO
     * @param contractVO.
     */
    public void setContractVO(ContractVO contractVO) {
        this.contractVO = contractVO;
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
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * Returns the standardBenefitKey
     * @return int standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * Sets the standardBenefitKey
     * @param standardBenefitKey.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
}
