/*
 * BenefitHierarchyCreateRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request to Create Benefit Hierarchy.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyCreateRequest extends WPDRequest{

	private BenefitHierarchyVO benefitHierarchyVO;
	
	private BenefitComponentBO benefitComponentBO;	
	
	private boolean doneFlag;
	
	private boolean checkInFlag;
	
	private boolean notesFlag;
	
	/**
	 * @return Returns the benefitComponentBO.
	 */
	public BenefitComponentBO getBenefitComponentBO() {
		return benefitComponentBO;
	}
	/**
	 * @param benefitComponentBO The benefitComponentBO to set.
	 */
	public void setBenefitComponentBO(BenefitComponentBO benefitComponentBO) {
		this.benefitComponentBO = benefitComponentBO;
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
	 * @return Returns the benefitHierarchyVO.
	 */
	public BenefitHierarchyVO getBenefitHierarchyVO() {
		return benefitHierarchyVO;
	}
	/**
	 * @param benefitHierarchyVO The benefitHierarchyVO to set.
	 */
	public void setBenefitHierarchyVO(BenefitHierarchyVO benefitHierarchyVO) {
		this.benefitHierarchyVO = benefitHierarchyVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * Returns the notesFlag
	 * @return boolean notesFlag.
	 */
	public boolean isNotesFlag() {
		return notesFlag;
	}
	/**
	 * Sets the notesFlag
	 * @param notesFlag.
	 */
	public void setNotesFlag(boolean notesFlag) {
		this.notesFlag = notesFlag;
	}
}
