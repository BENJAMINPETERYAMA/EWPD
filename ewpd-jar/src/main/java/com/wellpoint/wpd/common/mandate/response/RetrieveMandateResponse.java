/*
 * RetrieveMandateResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.mandate.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;

import java.util.List;


/**
 * Response for retrieving Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveMandateResponse extends WPDResponse {

    private MandateBO mandateBO;

    private MandateVO mandateVO;
    
    private List viewAllList;



	/**
	 * @return Returns the viewAllList.
	 */
	public List getViewAllList() {
		return viewAllList;
	}
	/**
	 * @param viewAllList The viewAllList to set.
	 */
	public void setViewAllList(List viewAllList) {
		this.viewAllList = viewAllList;
	}
    /**
     * Returns the mandateBO
     * 
     * @return MandateBO mandateBO.
     */
    public MandateBO getMandateBO() {
        return mandateBO;
    }


    /**
     * Sets the mandateBO
     * 
     * @param mandateBO.
     */
    public void setMandateBO(MandateBO mandateBO) {
        this.mandateBO = mandateBO;
    }


    /**
     * Returns the mandateVO
     * 
     * @return MandateVO mandateVO.
     */
    public MandateVO getMandateVO() {
        return mandateVO;
    }


    /**
     * Sets the mandateVO
     * 
     * @param mandateVO.
     */
    public void setMandateVO(MandateVO mandateVO) {
        this.mandateVO = mandateVO;
    }
}
