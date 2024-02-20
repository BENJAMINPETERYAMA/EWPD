package com.wellpoint.wpd.common.catalog.bo;

import java.util.Date;
import java.util.List;

public class SrdaCatalogBO {

	    private String catalogName = "";
	    private String catalogDatatypeOld="";
	    private int catalogSizeOld;
	    private int catalogId;
	    private int catalogParentid;
	    //Id used only for insertion purpose
	    private String catalogParentID = null;
	    private String parentCatalog;
	    private String description = "";
	    private String catalogDatatype="";
	    private int catalogSize;
	    private List businessDomains; 
	    private String createdUser;
	    private Date createdTimestamp;
	    private String lastUpdatedUser;
	    private Date lastUpdatedTimestamp;
	    
	    private List associatedItems;
	    
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
	    public String getCatalogName() {
	        return catalogName;
	    }
	    
	    public void setCatalogName(String catalogName) {
	        this.catalogName = catalogName;
	    }
	    
	    public int getCatalogId() {
	        return catalogId;
	    }
	    
	    public void setCatalogId(int catalogId) {
	        this.catalogId = catalogId;
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
	    
	   
	    public String getDescription() {
	        return description;
	    }
	    /**
	     * Sets the description
	     * @param description.
	     */
	    public void setDescription(String description) {
	        this.description = description;
	    }
	    /**
	     * Returns the lobList
	     * @return List lobList.
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
		
		public Date getCreatedTimestamp() {
	        return createdTimestamp;
	    }
	    /**
	     * @return Returns the createdUser.
	     */
	    public String getCreatedUser() {
	        return createdUser;
	    }
	    /**
	     * @return Returns the lastUpdatedTimestamp.
	     */
	    public Date getLastUpdatedTimestamp() {
	        return lastUpdatedTimestamp;
	    }
	    /**
	     * @return Returns the lastUpdatedUser.
	     */
	    public String getLastUpdatedUser() {
	        return lastUpdatedUser;
	    }
	    
	    public void setCreatedTimestamp(Date createdTimestamp) {
	        this.createdTimestamp = createdTimestamp;
	    }
	    /**
	     * @param createdUser The createdUser to set.
	     */
	    public void setCreatedUser(String createdUser) {
	        this.createdUser = createdUser;
	    }
	    /**
	     * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	     */
	    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
	        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	    }
	    /**
	     * @param lastUpdatedUser The lastUpdatedUser to set.
	     */
	    public void setLastUpdatedUser(String lastUpdatedUser) {
	        this.lastUpdatedUser = lastUpdatedUser;
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
	     * @return Returns the catalogParentID.
	     * @return String catalogParentID
	     */
	    public String getCatalogParentID() {
	        return catalogParentID;
	    }
	    /**
	     * Sets the catalogParentID
	     * @param catalogParentID
	     */
	    public void setCatalogParentID(String catalogParentID) {
	        this.catalogParentID = catalogParentID;
	    }
	    
	    /**
	     * @return Returns the catalogParentid.
	     * @return int catalogParentid
	     */
	    public int getCatalogParentid() {
	        return catalogParentid;
	    }
	    /**
	     * Sets the catalogParentid
	     * @param catalogParentid
	     */
	    public void setCatalogParentid(int catalogParentid) {
	        this.catalogParentid = catalogParentid;
	    }
	}
