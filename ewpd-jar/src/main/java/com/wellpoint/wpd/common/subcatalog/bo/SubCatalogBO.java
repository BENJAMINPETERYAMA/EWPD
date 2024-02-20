package com.wellpoint.wpd.common.subcatalog.bo;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.framework.util.StringUtil;

public class SubCatalogBO implements Comparable{
	
	private boolean termCheckBx;
	
    private List businessDomains; 
    
    private String subCatalogName;
    
    private int subCatalogId;
    
    private String parentCatalog;
    
    private String primaryCode;
    
    private int parentCatalogId;
    
    private int subCatalogSysId;
    
    private String description;
    
    private String createdUser;
    
    private Date createdTimestamp;
    
    private String lastUpdatedUser;
    
    private Date lastUpdatedTimestamp;
    
    private String parentCatalogName;
    
    private String lineOfBusiness;
    
    private String  businessEntity;
    
    private String businessGroup;
    
    private String marketBusinessUnit;
    
    private String searchValue;
    
    private List parentCatalogList;
	
    /**
     * @return primaryCode
     * 
     * Returns the primaryCode.
     */
    public String getPrimaryCode() {
        return primaryCode;
    }
    /**
     * @param primaryCode
     * 
     * Sets the primaryCode.
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }
    /**
     * @return subCatalogId
     * 
     * Returns the subCatalogId.
     */
    public int getSubCatalogId() {
        return subCatalogId;
    }
    /**
     * @param subCatalogId
     * 
     * Sets the subCatalogId.
     */
    public void setSubCatalogId(int subCatalogId) {
        this.subCatalogId = subCatalogId;
    }
    /**
     * @return parentCatalogId
     * 
     * Returns the parentCatalogId.
     */
    public int getParentCatalogId() {
        return parentCatalogId;
    }
    /**
     * @param parentCatalogId
     * 
     * Sets the parentCatalogId.
     */
    public void setParentCatalogId(int parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
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
     * @return Returns the subCatalogSysId.
     * @return int subCatalogSysId
     */
    public int getSubCatalogSysId() {
        return subCatalogSysId;
    }
    /**
     * Sets the subCatalogSysId
     * @param subCatalogSysId
     */
    public void setSubCatalogSysId(int subCatalogSysId) {
        this.subCatalogSysId = subCatalogSysId;
    }
    /**
     * @return businessEntity
     * Returns the businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }
    /**
     * @param businessEntity
     * Sets the businessEntity.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * @return businessGroup
     * Returns the businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }
    /**
     * @param businessGroup
     * Sets the businessGroup.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * @return lineOfBusiness
     * Returns the lineOfBusiness.
     */
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }
    /**
     * @param lineOfBusiness
     * Sets the lineOfBusiness.
     */
    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
    /**
     * @return parentCatalogName
     * Returns the parentCatalogName.
     */
    public String getParentCatalogName() {
        return parentCatalogName;
    }
    /**
     * @param parentCatalogName
     * Sets the parentCatalogName.
     */
    public void setParentCatalogName(String parentCatalogName) {
        this.parentCatalogName = parentCatalogName;
    }
	/**
	 * @return Returns the searchValue.
	 */
	public String getSearchValue() {
		return searchValue;
	}
	/**
	 * @param searchValue The searchValue to set.
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (!(obj instanceof SubCatalogBO))
			throw new ClassCastException("SubCatalog object Expected.");

		SubCatalogBO catalog = (SubCatalogBO) obj;
		if (StringUtil.isInteger(this.primaryCode)
				&& StringUtil.isInteger(catalog.getPrimaryCode())) {
			
			int val1 = Integer.parseInt(this.primaryCode);
			int val2 = Integer.parseInt(catalog.getPrimaryCode());
			return (val1 - val2);
			
		} else if (!StringUtil.isInteger(this.primaryCode)
				&& StringUtil.isInteger(catalog.getPrimaryCode())) {
			
			return 1;
			
		} else if (StringUtil.isInteger(this.primaryCode)
				&& !StringUtil.isInteger(catalog.getPrimaryCode())) {
			
			return -1;
			
		} else if (!StringUtil.isInteger(this.primaryCode)
				&& !StringUtil.isInteger(catalog.getPrimaryCode())) {
			
			int val = this.primaryCode.compareTo(catalog.getPrimaryCode());
			return val;
		}

		return 0;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
	
	public boolean isTermCheckBx() {
		return termCheckBx;
	}


	public void setTermCheckBx(boolean termCheckBx) {
		this.termCheckBx = termCheckBx;
	}
}