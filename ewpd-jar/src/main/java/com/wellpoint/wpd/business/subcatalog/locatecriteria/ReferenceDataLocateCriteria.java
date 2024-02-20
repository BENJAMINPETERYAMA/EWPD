package com.wellpoint.wpd.business.subcatalog.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

public class ReferenceDataLocateCriteria extends LocateCriteria {
	
	private String parentCatalog="";
	
	private String entityType = null;
	
	private String entityName = null;
	
	private int entityId;
	
	private int action;
	
	private List lobList;
	
	private List beList;
		
	private List bgList;
	
	private List parentCatalogList;
	
	private String SearchValueForPopUp;
	
	private String termValue;
	
	private String pvaValue;
	
	private String catalogNameForPvaValue;
	
	private String sortOrder; 
	
	private String headerRuleId;
	
	private String searchString;
	
    /**
     * @return beList
     * Returns the beList.
     */
    public List getBeList() {
        return beList;
    }
    /**
     * @param beList
     * Sets the beList.
     */
    public void setBeList(List beList) {
        this.beList = beList;
    }
    /**
     * @return bgList
     * Returns the bgList.
     */
    public List getBgList() {
        return bgList;
    }
    /**
     * @param bgList
     * Sets the bgList.
     */
    public void setBgList(List bgList) {
        this.bgList = bgList;
    }
    /**
     * @return lobList
     * Returns the lobList.
     */
    public List getLobList() {
        return lobList;
    }
    /**
     * @param lobList
     * Sets the lobList.
     */
    public void setLobList(List lobList) {
        this.lobList = lobList;
    }
	/**
	 * @return Returns the parentCatalog.
	 */
	public String getParentCatalog() {
		return parentCatalog;
	}
	/**
	 * @param parentCatalog The parentCatalog to set.
	 */
	public void setParentCatalog(String parentCatalog) {
		this.parentCatalog = parentCatalog;
	}
	
    /**
     * @return Returns the entityId.
     * @return int entityId
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * Sets the entityId
     * @param entityId
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * @return Returns the entityName.
     * @return String entityName
     */
    public String getEntityName() {
        return entityName;
    }
    /**
     * Sets the entityName
     * @param entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    /**
     * @return Returns the entityType.
     * @return String entityType
     */
    public String getEntityType() {
        return entityType;
    }
    /**
     * Sets the entityType
     * @param entityType
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    
    /**
     * @return Returns the action.
     * @return int action
     */
    public int getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param action
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
     * @return parentCatalogList
     * 
     * Returns the parentCatalogList.
     */
    public List getParentCatalogList() {
        return parentCatalogList;
    }
    /**
     * @param parentCatalogList
     * 
     * Sets the parentCatalogList.
     */
    public void setParentCatalogList(List parentCatalogList) {
        this.parentCatalogList = parentCatalogList;
    }
	/**
	 * @return Returns the searchValueForPopUp.
	 */
	public String getSearchValueForPopUp() {
		return SearchValueForPopUp;
	}
	/**
	 * @param searchValueForPopUp The searchValueForPopUp to set.
	 */
	public void setSearchValueForPopUp(String searchValueForPopUp) {
		SearchValueForPopUp = searchValueForPopUp;
	}
	/**
	 * @return Returns the pvaValue.
	 */
	public String getPvaValue() {
		return pvaValue;
	}
	/**
	 * @param pvaValue The pvaValue to set.
	 */
	public void setPvaValue(String pvaValue) {
		this.pvaValue = pvaValue;
	}
	/**
	 * @return Returns the termValue.
	 */
	public String getTermValue() {
		return termValue;
	}
	/**
	 * @param termValue The termValue to set.
	 */
	public void setTermValue(String termValue) {
		this.termValue = termValue;
	}
	/**
	 * @return Returns the catalogNameForPvaValue.
	 */
	public String getCatalogNameForPvaValue() {
		return catalogNameForPvaValue;
	}
	/**
	 * @param catalogNameForPvaValue The catalogNameForPvaValue to set.
	 */
	public void setCatalogNameForPvaValue(String catalogNameForPvaValue) {
		this.catalogNameForPvaValue = catalogNameForPvaValue;
	}

	/**
	 * @return Returns the sortOrder.
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder The sortOrder to set.
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	/**
	 * @param headerRuleId The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}