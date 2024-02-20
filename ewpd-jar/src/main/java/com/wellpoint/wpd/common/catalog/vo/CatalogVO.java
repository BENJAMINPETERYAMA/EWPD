package com.wellpoint.wpd.common.catalog.vo;

import java.util.List;



public class CatalogVO {
	private String catalogName = "";

	private String description = "";

	private String catalogDatatype = "";

	private int catalogSize;

	private int catalogId;
	
	private int catalogParentid;
	
	private List lobList = null;

	private List businessEntityList = null;

	private List businessGroupList = null;
	
	private List marketBusinessUnitList = null;

	private List businessDomains;

	private String parentCatalog;
	 
	private String catalogDatatypeOld="";
	
	private String descriptionOld="";
	    
	private int catalogSizeOld;
	
	private List associatedItems;
	
	private String primaryCode;
	
	
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
	 * @return Returns the catalogParentid.
	 */
	public int getCatalogParentid() {
		return catalogParentid;
	}
	/**
	 * @param catalogParentid The catalogParentid to set.
	 */
	public void setCatalogParentid(int catalogParentid) {
		this.catalogParentid = catalogParentid;
	}
	/**
	 * @return Returns the descriptionOld.
	 */
	public String getDescriptionOld() {
		return descriptionOld;
	}
	/**
	 * @param descriptionOld The descriptionOld to set.
	 */
	public void setDescriptionOld(String descriptionOld) {
		this.descriptionOld = descriptionOld;
	}
	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogDatatype() {
		return catalogDatatype;
	}

	public void setCatalogDatatype(String catalogDatatype) {
		this.catalogDatatype = catalogDatatype;
	}

	public int getCatalogSize() {
		return catalogSize;
	}

	public void setCatalogSize(int catalogSize) {
		this.catalogSize = catalogSize;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public List getBusinessEntityList() {
		return businessEntityList;
	}

	/**
	 * Sets the businessEntityList
	 * 
	 * @param businessEntityList.
	 */
	public void setBusinessEntityList(List businessEntityList) {
		this.businessEntityList = businessEntityList;
	}

	/**
	 * Returns the businessGroupList
	 * 
	 * @return List businessGroupList.
	 */
	public List getBusinessGroupList() {
		return businessGroupList;
	}

	/**
	 * Sets the businessGroupList
	 * 
	 * @param businessGroupList.
	 */
	public void setBusinessGroupList(List businessGroupList) {
		this.businessGroupList = businessGroupList;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * 
	 * @param description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the lobList
	 * 
	 * @return List lobList.
	 */
	public List getLobList() {
		return lobList;
	}

	/**
	 * Sets the lobList
	 * 
	 * @param lobList.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}

	public List getBusinessDomains() {
		return businessDomains;
	}

	/**
	 * @param businessDomains
	 *            The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}

	
	/**
	 * @return Returns the catalogDatatypeOld.
	 */
	public String getCatalogDatatypeOld() {
		return catalogDatatypeOld;
	}
	/**
	 * @param catalogDatatypeOld The catalogDatatypeOld to set.
	 */
	public void setCatalogDatatypeOld(String catalogDatatypeOld) {
		this.catalogDatatypeOld = catalogDatatypeOld;
	}
	/**
	 * @return Returns the catalogSizeOld.
	 */
	public int getCatalogSizeOld() {
		return catalogSizeOld;
	}
	/**
	 * @param catalogSizeOld The catalogSizeOld to set.
	 */
	public void setCatalogSizeOld(int catalogSizeOld) {
		this.catalogSizeOld = catalogSizeOld;
	}
	
    /**
     * @return Returns the associatedItems.
     * @return List associatedItems
     */
    public List getAssociatedItems() {
        return associatedItems;
    }
    /**
     * Sets the associatedItems
     * @param associatedItems
     */
    public void setAssociatedItems(List associatedItems) {
        this.associatedItems = associatedItems;
    }
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}