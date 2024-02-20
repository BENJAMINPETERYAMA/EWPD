/*
 * ProductSearchResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import com.wellpoint.wpd.common.bo.LocateResult;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductSearchResult extends LocateResult {

	private String  productName;
	
	private Date effectiveDate;
	private Date expiryDate;
	
	private int version;
	
	private int productKey;
	
	private List businessDomains;
	
    private List lineOfBusinessList;
    
    private List businessEntityList;
    
    private List businessGroupList;

	
	private int parentKey;
	
	private String productType;
	
	private String productDescription;
	
	private String lineOfBusiness;
    
    private List marketBusinessUnitList;
	 /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("productName = ").append(productName);
        buffer.append(", effectiveDate = ").append(effectiveDate);
        buffer.append(", expiryDate = ").append(expiryDate);
        buffer.append(", version = ").append(version);
        buffer.append(", productKey = ").append(productKey);
        buffer.append(", businessDomains = ").append(businessDomains);
        buffer.append(", lineOfBusinessList = ").append(lineOfBusinessList);
        buffer.append(", businessEntityList = ").append(businessEntityList);
        buffer.append(", businessGroupList = ").append(businessGroupList);
        buffer.append(", marketBusinessUnitList = ").append(marketBusinessUnitList);
        return buffer.toString();
    }
	
	
	/**
	 * 
	 * @return
	 */
	public String getDeleteMessage() {
        String deleteMessage = "Are you sure you want to delete? "+":"+"0";
        return deleteMessage;
	}	
	/**
	 * 
	 * @return
	 */
    public boolean isValidForApproval() {
        return this.getState().isValidForApproval();
    }

    public boolean isValidForCheckin() {
        return this.getState().isValidForCheckIn();
    }

    public boolean isValidForCheckOut() {
        return this.getState().isValidForCheckOut();
    }

    public boolean isValidForDeletion() {
        //return this.getState().isValidForDelete();
        //TODO
        return this.isEditableByUser() && "BUILDING".equals(this.getStatus());
    }

    public boolean isValidForPuplish() {
        return this.getState().isValidForPublish();
    }

    public boolean isValidForTesting() {
        return this.getState().isValidForTest();
    }

    public boolean isValidForTransfer() {
        return this.getState().isValidForTransfer();
    }

    public boolean isEditableByUser() {
        return this.getState().isEditableByUser();
    }

    public boolean isLocked() {
        return this.getState().isLocked();
    }

	
	
    /**
     * Returns the businessEntityList
     * @return List businessEntityList.
     */
    public List getBusinessEntityList() {
        return businessEntityList;
    }
    /**
     * Sets the businessEntityList
     * @param businessEntityList.
     */
    public void setBusinessEntityList(List businessEntityList) {
        this.businessEntityList = businessEntityList;
    }
    /**
     * Returns the businessGroupList
     * @return List businessGroupList.
     */
    public List getBusinessGroupList() {
        return businessGroupList;
    }
    /**
     * Sets the businessGroupList
     * @param businessGroupList.
     */
    public void setBusinessGroupList(List businessGroupList) {
        this.businessGroupList = businessGroupList;
    }
    /**
     * Returns the effectiveDate
     * @return Date effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Sets the effectiveDate
     * @param effectiveDate.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Returns the expiryDate
     * @return Date expiryDate.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
    /**
     * Sets the expiryDate
     * @param expiryDate.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    /**
     * Returns the lineOfBusinessList
     * @return List lineOfBusinessList.
     */
    public List getLineOfBusinessList() {
        return lineOfBusinessList;
    }
    /**
     * Sets the lineOfBusinessList
     * @param lineOfBusinessList.
     */
    public void setLineOfBusinessList(List lineOfBusinessList) {
        this.lineOfBusinessList = lineOfBusinessList;
    }
    /**
     * Returns the parentKey
     * @return int parentKey.
     */
    public int getParentKey() {
        return parentKey;
    }
    /**
     * Sets the parentKey
     * @param parentKey.
     */
    public void setParentKey(int parentKey) {
        this.parentKey = parentKey;
    }
    /**
     * Returns the productKey
     * @return int productKey.
     */
    public int getProductKey() {
        return productKey;
    }
    /**
     * Sets the productKey
     * @param productKey.
     */
    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }
    /**
     * Returns the productName
     * @return String productName.
     */
    public String getProductName() {
        return productName;
    }
    /**
     * Sets the productName
     * @param productName.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * Returns the version
     * @return int version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * Sets the version
     * @param version.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * Returns the businessDomains
     * @return List businessDomains.
     */
    public List getBusinessDomains() {
        return businessDomains;
    }
    /**
     * Sets the businessDomains
     * @param businessDomains.
     */
    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }
	/**
	 * @return Returns the productType.
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return Returns the productDescription.
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param productDescription The productDescription to set.
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * @return Returns the lineOfBusiness.
	 */
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	/**
	 * @param lineOfBusiness The lineOfBusiness to set.
	 */
	public void setLineOfBusiness(String lineOfBusiness) {
		int flag = 0;
		String stringVal=null;
        StringBuffer stringBuffer = new StringBuffer();
        if (null != lineOfBusiness) {
            StringTokenizer stringTokenizer = new StringTokenizer(
            		lineOfBusiness, "~");
            while (stringTokenizer.hasMoreElements()) {
            	stringTokenizer.nextToken();
            	if(stringTokenizer.hasMoreElements()){
                stringVal = stringTokenizer.nextToken();
            	}
                if (flag != 0) {
                    stringBuffer.append(", ");
                } else {
                    flag = 1;
                }
                stringBuffer.append(stringVal);
            }
        }
        String lobDesc = stringBuffer.toString();
		this.lineOfBusiness = lobDesc;
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
