/*
 * Created on Sep 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U15701
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodOverrideResponse extends WPDResponse{
	private List spsList;

    private List benefitDefinitionsList; //CARS:AM2
    
    private List criteriaList;//CARS:AM2
    
    private List tieredAdminMethodList;//CARS:AM2 
    
    private HashMap spsAdminMethodListMap;//CARS DATAFEDD CHANFE 
    private boolean isTierPOS = false; //CARS:AM2|POS
    private List tierProductFamily =null; //CARS:AM2|POS
//  CARS:AM2:START
    //POS{
    
	
	
    public boolean isTierPOS() {
		return isTierPOS;
	}
	public void setTierPOS(boolean isTierPOS) {
		this.isTierPOS = isTierPOS;
	}
	//POS}
	/**
	 * @return Returns the spsList.
	 */
	public List getSpsList() {
		return spsList;
	}
	/**
	 * @param spsList The spsList to set.
	 */
	public void setSpsList(List spsList) {
		this.spsList = spsList;
	}

	
	
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
	 * @return Returns the lvlLineList.
	 */
	public List getTieredAdminMethodList() {
		return tieredAdminMethodList;
	}
	/**
	 * @param lvlLineList The lvlLineList to set.
	 */
	public void setTieredAdminMethodList(List tieredAdminMethodList) {
		this.tieredAdminMethodList = tieredAdminMethodList;
	}
	//CARS:AM2:END
	/**
	 * @return Returns the spsAdminMethodListMap.
	 */
	public HashMap getSpsAdminMethodListMap() {
		return spsAdminMethodListMap;
	}
	/**
	 * @param spsAdminMethodListMap The spsAdminMethodListMap to set.
	 */
	public void setSpsAdminMethodListMap(HashMap spsAdminMethodListMap) {
		this.spsAdminMethodListMap = spsAdminMethodListMap;
	}
	/**
	 * @return Returns the tierProductFamily.
	 */
	public List getTierProductFamily() {
		return tierProductFamily;
	}
	/**
	 * @param tierProductFamily The tierProductFamily to set.
	 */
	public void setTierProductFamily(List tierProductFamily) {
		this.tierProductFamily = tierProductFamily;
	}
}
