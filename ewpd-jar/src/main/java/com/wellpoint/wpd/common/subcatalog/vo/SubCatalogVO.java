package com.wellpoint.wpd.common.subcatalog.vo;

import java.util.Date;
import java.util.List;

public class SubCatalogVO {
	
	private List businessDomains;
	
    private String subCatalogName;
    
    private int selectedSubCatalogId;
    
    private String parentCatalog;
    
    private String description;
    
    private String createdUser;
    
    private Date createdTimestamp;
    
    private String lastUpdatedUser;
    
    private Date lastUpdatedTimestamp;
    
	private List catalogIdList;
	
    private String primaryCode;
    
    private String subCatalogId;
    
    private int parentCatalogId;
    
    private String entityType;
    
    private String entityName;
    
    private int entityId;
   
	private List lobList;
	
	private List beList;
		
	private List bgList;
	
	private String searchText;
	
	private String benefitLevelTerm;

	private String benefitLevelPVA;
	
	private boolean searchCriteriaEntered;
	
	private String catalogNameForPva; 
	
	private List parentCatalogList;
	
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
     * @return Returns the parentCatalogId.
     * @return int parentCatalogId
     */
    public int getParentCatalogId() {
        return parentCatalogId;
    }
    /**
     * Sets the parentCatalogId
     * @param parentCatalogId
     */
    public void setParentCatalogId(int parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }
    /**
     * @return Returns the primaryCode.
     * @return String primaryCode
     */
    public String getPrimaryCode() {
        return primaryCode;
    }
    /**
     * Sets the primaryCode
     * @param primaryCode
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }
    /**
     * @return Returns the subCatalogId.
     * @return String subCatalogId
     */
    public String getSubCatalogId() {
        return subCatalogId;
    }
    /**
     * Sets the subCatalogId
     * @param subCatalogId
     */
    public void setSubCatalogId(String subCatalogId) {
        this.subCatalogId = subCatalogId;
    }
	/**
	 * @return Returns the selectedSubCatalogId.
	 */
	public int getSelectedSubCatalogId() {
		return selectedSubCatalogId;
	}
	/**
	 * @param selectedSubCatalogId The selectedSubCatalogId to set.
	 */
	public void setSelectedSubCatalogId(int selectedSubCatalogId) {
		this.selectedSubCatalogId = selectedSubCatalogId;
	}
    /**
     * @return catalogIdList
     * Returns the catalogIdList.
     */
    public List getCatalogIdList() {
        return catalogIdList;
    }
    /**
     * @param catalogIdList
     * Sets the catalogIdList.
     */
    public void setCatalogIdList(List catalogIdList) {
        this.catalogIdList = catalogIdList;
    }
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
   
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	
	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
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
	 * @return Returns the subCatalogName.
	 */
	public String getSubCatalogName() {
		return subCatalogName;
	}
	/**
	 * @param subCatalogName The subCatalogName to set.
	 */
	public void setSubCatalogName(String subCatalogName) {
		this.subCatalogName = subCatalogName;
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
	 * @return Returns the searchText.
	 */
	public String getSearchText() {
		return searchText;
	}
	/**
	 * @param searchText The searchText to set.
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	/**
	 * @return Returns the benefitLevelPVA.
	 */
	public String getBenefitLevelPVA() {
		return benefitLevelPVA;
	}
	/**
	 * @param benefitLevelPVA The benefitLevelPVA to set.
	 */
	public void setBenefitLevelPVA(String benefitLevelPVA) {
		this.benefitLevelPVA = benefitLevelPVA;
	}
	/**
	 * @return Returns the benefitLevelTerm.
	 */
	public String getBenefitLevelTerm() {
		return benefitLevelTerm;
	}
	/**
	 * @param benefitLevelTerm The benefitLevelTerm to set.
	 */
	public void setBenefitLevelTerm(String benefitLevelTerm) {
		this.benefitLevelTerm = benefitLevelTerm;
	}
	/**
	 * @return Returns the searchCriteriaEntered.
	 */
	public boolean isSearchCriteriaEntered() {
		return searchCriteriaEntered;
	}
	/**
	 * @param searchCriteriaEntered The searchCriteriaEntered to set.
	 */
	public void setSearchCriteriaEntered(boolean searchCriteriaEntered) {
		this.searchCriteriaEntered = searchCriteriaEntered;
	}
	/**
	 * @return Returns the catalogNameForPva.
	 */
	public String getCatalogNameForPva() {
		return catalogNameForPva;
	}
	/**
	 * @param catalogNameForPva The catalogNameForPva to set.
	 */
	public void setCatalogNameForPva(String catalogNameForPva) {
		this.catalogNameForPva = catalogNameForPva;
	}
	
	/**
	 * @return Returns the parentCatalogList.
	 */
	public List getParentCatalogList() {
		return parentCatalogList;
	}
	/**
	 * @param parentCatalogList The parentCatalogList to set.
	 */
	public void setParentCatalogList(List parentCatalogList) {
		this.parentCatalogList = parentCatalogList;
	}
}