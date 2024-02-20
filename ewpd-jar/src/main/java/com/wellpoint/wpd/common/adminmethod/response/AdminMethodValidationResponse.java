/*
 * Created on Mar 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodValidationResponse  extends WPDResponse{
	
	private List resultList = new ArrayList();
	
    private List benefitDefinitionsList; //CARS:AM1
	   
	private List criteriaList;//CARS:AM1
	   
	private List tieredAdminMethodList;//CARS:AM1
	
//	CARS:AM1:START
	/**
	 * @return Returns the benefitDefinitionsList.
	 */
	public List getBenefitDefinitionsList() {
		return benefitDefinitionsList;
	}
	/**
	 * @param benefitDefinitionsList The benefitDefinitionsList to set.
	 */
	public void setBenefitDefinitionsList(List benefitDefinitionsList) {
		this.benefitDefinitionsList = benefitDefinitionsList;
	}
	/**
	 * @return Returns the criteriaList.
	 */
	public List getCriteriaList() {
		return criteriaList;
	}
	/**
	 * @param criteriaList The criteriaList to set.
	 */
	public void setCriteriaList(List criteriaList) {
		this.criteriaList = criteriaList;
	}
	/**
	 * @return Returns the tieredAdminMethodList.
	 */
	public List getTieredAdminMethodList() {
		return tieredAdminMethodList;
	}
	/**
	 * @param tieredAdminMethodList The tieredAdminMethodList to set.
	 */
	public void setTieredAdminMethodList(List tieredAdminMethodList) {
		this.tieredAdminMethodList = tieredAdminMethodList;
	}
//	CARS:AM1:END
	/**
	 * @return Returns the resultList.
	 */
	public List getResultList() {
		return resultList;
	}
	/**
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}
