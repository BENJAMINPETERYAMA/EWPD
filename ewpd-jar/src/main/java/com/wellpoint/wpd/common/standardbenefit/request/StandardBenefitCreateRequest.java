/*
 * StandardBenefitCreateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;

//import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitCreateRequest extends WPDRequest {

    private StandardBenefitVO standardBenefitVO;
    private int action;
    public static final int CREATE_STANDARD_BENEFIT = 1;

    public static final int UPDATE_STANDARD_BENEFIT = 2;
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    
    /**
     * @return Returns the standardBenefitVO.
     */
    public StandardBenefitVO getStandardBenefitVO() {
        return standardBenefitVO;
    }
    /**
     * @param standardBenefitVO The standardBenefitVO to set.
     */
    public void setStandardBenefitVO(StandardBenefitVO standardBenefitVO) {
        this.standardBenefitVO = standardBenefitVO;
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
}
