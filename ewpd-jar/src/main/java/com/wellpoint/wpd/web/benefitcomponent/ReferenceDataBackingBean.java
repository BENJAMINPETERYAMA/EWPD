/*
 * ReferenceDataBackingBean.java
 * Created on Feb 20, 2007
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.web.benefitcomponent;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


/**
 * Backing bean for all popup
 */
public class ReferenceDataBackingBean extends WPDBackingBean {
        
       
    private List administrationLevelList;
    
    private List benefitLevelList;
    
    private List adminOptionsList;

    /**
     * Constructor
     *  
     */
    public ReferenceDataBackingBean() {

    }


  /**
   * Returns the adminstrationLevelList
   * @return List administrationLevelList
   */
    public List getAdministrationLevelList() {
    	List adminstrationLevelList = new ArrayList(2);
    	adminstrationLevelList.add(new SelectItem("0","Benefit Level"));
    	adminstrationLevelList.add(new SelectItem("1","Benefit"));
    	return adminstrationLevelList;
    }
    
    /**
     * Sets adminstrationLevelList
     * @param adminstrationLevelList 
     */
    public void setAdminstrationLevelList(List adminstrationLevelList) {
    	this.administrationLevelList = adminstrationLevelList;
    }
    
	/**
	 * @return Returns the benefitLevelList.
	 */
	public List getBenefitLevelList() {
		List benefitLevelList = new ArrayList(4);
		benefitLevelList.add("Individual Copay");
		benefitLevelList.add("Wavier Copay");
		benefitLevelList.add("Wavier Deductable");
		benefitLevelList.add("Family Payment Percent");
		return benefitLevelList;
	}
	/**
	 * @param benefitLevelList The benefitLevelList to set.
	 */
	public void setBenefitLevelList(List benefitLevelList) {
		this.benefitLevelList = benefitLevelList;
	}
	/**
	 * @return Returns the adminOptionsList.
	 */
	public List getAdminOptionsList() {
		List adminOptionsList = new ArrayList(2);
		adminOptionsList.add(0,"Admin1");
		adminOptionsList.add(1,"Admin2");
		
		return adminOptionsList;
	}
	/**
	 * @param adminOptionsList The adminOptionsList to set.
	 */
	public void setAdminOptionsList(List adminOptionsList) {
		this.adminOptionsList = adminOptionsList;
	}
}