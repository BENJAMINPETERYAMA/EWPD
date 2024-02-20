/*
 * BenefitComponentCreateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request for Save Benefit Component
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentSaveRequest extends WPDRequest{
	
	private BenefitComponentVO benefitComponentVO;
	private BenefitComponentVO oldBenefitComponentVO;
	private int action;
	private int benefitHierarchyId;
	private boolean doneFlag = false;
	private boolean checkInFlag = false;
	private boolean dateChanged = false;
	public static final int CREATE_BENEFIT_COMPONENT = 1;
    public static final int UPDATE_BENEFIT_COMPONENT = 2;
    
    // to check if the Component type has changed while editing
    private boolean deleteBenefit = false;
    private boolean domainChange = false;
	
    /**
     * Constructor
     *
     */
	public BenefitComponentSaveRequest(){
		benefitComponentVO = new BenefitComponentVO();
	}
	
	/**
	 * Returns the dateChanged
	 * @return boolean dateChanged.
	 */
	public boolean isDateChanged() {
		return dateChanged;
	}
	/**
	 * Sets the dateChanged
	 * @param dateChanged.
	 */
	public void setDateChanged(boolean dateChanged) {
		this.dateChanged = dateChanged;
	}
	/**
	 * @return Returns the checkInFlag.
	 */
	public boolean isCheckInFlag() {
		return checkInFlag;
	}
	/**
	 * @param checkInFlag The checkInFlag to set.
	 */
	public void setCheckInFlag(boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}
	/**
	 * @return Returns the doneFlag.
	 */
	public boolean isDoneFlag() {
		return doneFlag;
	}
	/**
	 * @param doneFlag The doneFlag to set.
	 */
	public void setDoneFlag(boolean doneFlag) {
		this.doneFlag = doneFlag;
	}
    
	
	/**
	 * Returns the benefitComponentVO
	 * @return BenefitComponentVO benefitComponentVO.
	 */
	public BenefitComponentVO getBenefitComponentVO() {
		return benefitComponentVO;
	}
	/**
	 * Sets the benefitComponentVO
	 * @param benefitComponentVO.
	 */
	public void setBenefitComponentVO(BenefitComponentVO benefitComponentVO) {
		this.benefitComponentVO = benefitComponentVO;
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return Returns the oldBenefitComponentVO.
	 */
	public BenefitComponentVO getOldBenefitComponentVO() {
		return oldBenefitComponentVO;
	}
	/**
	 * @param oldBenefitComponentVO The oldBenefitComponentVO to set.
	 */
	public void setOldBenefitComponentVO(
			BenefitComponentVO oldBenefitComponentVO) {
		this.oldBenefitComponentVO = oldBenefitComponentVO;
	}
	
	
	/**
	 * Returns the benefitHierarchyId
	 * @return int benefitHierarchyId.
	 */
	public int getBenefitHierarchyId() {
		return benefitHierarchyId;
	}
	/**
	 * Sets the benefitHierarchyId
	 * @param benefitHierarchyId.
	 */
	public void setBenefitHierarchyId(int benefitHierarchyId) {
		this.benefitHierarchyId = benefitHierarchyId;
	}
	/**
	 * @return Returns the deleteBenefit.
	 */
	public boolean isDeleteBenefit() {
		return deleteBenefit;
	}
	/**
	 * @param deleteBenefit The deleteBenefit to set.
	 */
	public void setDeleteBenefit(boolean deleteBenefit) {
		this.deleteBenefit = deleteBenefit;
	}
	/**
	 * @return Returns the domainChange.
	 */
	public boolean isDomainChange() {
		return domainChange;
	}
	/**
	 * @param domainChange The domainChange to set.
	 */
	public void setDomainChange(boolean domainChange) {
		this.domainChange = domainChange;
	}
}
