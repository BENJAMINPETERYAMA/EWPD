/*
 * LocateComponentsBenefitAdministrationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * Response for Component Administration Locate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateComponentsBenefitAdministrationResponse extends WPDResponse{

    private List benefitAdministrationList;
    
    private List questionnareList;
    
    /**
     * @return Returns the benefitAdministrationList.
     */
    public List getBenefitAdministrationList() {
        return benefitAdministrationList;
    }
    /**
     * @param benefitAdministrationList The benefitAdministrationList to set.
     */
    public void setBenefitAdministrationList(List benefitAdministrationList) {
        this.benefitAdministrationList = benefitAdministrationList;
    }
	/**
	 * @return Returns the questionnareList.
	 */
	public List getQuestionnareList() {
		return questionnareList;
	}
	/**
	 * @param questionnareList The questionnareList to set.
	 */
	public void setQuestionnareList(List questionnareList) {
		this.questionnareList = questionnareList;
	}
}
