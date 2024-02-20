/*
 * LimitedTo.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LimitedTo {
	
	public static final String VARIABLE_ALL = "ALL";
	
	private List lineOfBusiness = new ArrayList();
	
	private List businessEntity = new ArrayList();
	
	private List businessGroup = new ArrayList();
    /*START CARS*/
    //Market Business Unit
	private List marketBusinessUnit = new ArrayList();
    /*START CARS*/
	private List combinedList = null;

	/**
	 * @return Returns the businessEntity.
	 */
	public List getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(List businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the businessGroup.
	 */
	public List getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(List businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the lineOfBusiness.
	 */
	public List getLineOfBusiness() {
		return lineOfBusiness;
	}
	/**
	 * @param lineOfBusiness The lineOfBusiness to set.
	 */
	public void setLineOfBusiness(List lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	
	public List getPermutedListForLineOfBusiness(){		
		return lineOfBusiness;
	}
	
	public List getPermutedListForBusinessEntity(){	
		return businessEntity;
		
	}
	public List getPermutedListForBusinessGroup(){	
		return businessGroup;
		
	}
    /*START CARS*/
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
	/**
	 * @return
	 */
	public List getPermutedListForMarketBusinessUnit(){	
		return marketBusinessUnit;
		
	}
    /*END CARS*/
}
