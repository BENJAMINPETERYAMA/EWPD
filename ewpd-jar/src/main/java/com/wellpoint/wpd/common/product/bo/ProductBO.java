/*
 * ProductBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBO extends BusinessObject {

	private int productKey;

	private String productName;

	private String productDesc;

	private String productStructureKey;

	private Date effectiveDate;

	private Date expiryDate;

	private String productFamilyId;

	//new field

	private String productType;

	private String mandateType;

	private String mandateDesc;

	private String stateId;

	private String stateDesc;

	private String stateCode;
	
	private String hiddenProductType;

	/*
	 * BusinessObject Base class Attributes.  
	 * private int version;
	 * private String status;
	 * private String createdUser;
	 * private Date createdTimestamp;
	 * private String lastUpdatedUser;
	 * private Date lastUpdatedTimestamp;
	 */
	private List adminList;
	private List componentList;
	private List denialAndExclusionList;

	private ProductStructureBO productStructure;

	private int parentProductKey;

	private String productStructureName;

	private String productFamilyDesc;

	private List businessDomains;
	
	private int action;
	
	//added for showing product structure version in product info page
	private int productStructureVersion;

	private List marketBusinessUnitList;	

	public ProductBO() {

	}

	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		if (object instanceof ProductBO) {
			ProductBO product = (ProductBO) object;
			if (this.productKey == product.productKey)
				return true;
		}
		return false;

	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("productKey = ").append(this.getProductKey());
		buffer.append(", productName = ").append(productName);
		buffer.append(", businessDomains = ").append(this.getBusinessDomains());
		buffer.append(", productDesc = ").append(productDesc);
		buffer.append(", productStructureKey = ").append(productStructureKey);
		buffer.append(", productStructureVersion = ").append(productStructureVersion);
		buffer.append(", effectiveDate = ").append(effectiveDate);
		buffer.append(", expiryDate = ").append(expiryDate);
		buffer.append(", productFamilyId = ").append(productFamilyId);
		buffer.append(", version = ").append(this.getVersion());
		buffer.append(", status = ").append(this.getStatus());
		buffer.append(", componentList = ").append(componentList);
		buffer.append(", denialAndExclusionList = ").append(denialAndExclusionList);
		buffer.append(", productStructure = ").append(productStructure);
		buffer.append(", createdUser = ").append(this.getCreatedUser());
		buffer.append(", createdTimestamp = ").append(
				this.getCreatedTimestamp());
		buffer.append(", lastUpdatedUser = ").append(this.getLastUpdatedUser());
		buffer.append(", lastUpdatedTimestamp = ").append(
				this.getLastUpdatedTimestamp());
		return buffer.toString();
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
	 * Returns the productDesc
	 * @return String productDesc.
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * Sets the productDesc
	 * @param productDesc.
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * Returns the productFamilyId
	 * @return String productFamilyId.
	 */
	public String getProductFamilyId() {
		return productFamilyId;
	}

	/**
	 * Sets the productFamilyId
	 * @param productFamilyId.
	 */
	public void setProductFamilyId(String productFamilyId) {
		this.productFamilyId = productFamilyId;
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
	 * Returns the componentList
	 * @return List componentList.
	 */
	public List getComponentList() {
		return componentList;
	}

	/**
	 * Sets the componentList
	 * @param componentList.
	 */
	public void setComponentList(List componentList) {
		this.componentList = componentList;
	}

	/**
	 * Returns the denialAndExclusionList
	 * @return List denialAndExclusionList.
	 */
	public List getDenialAndExclusionList() {
		return denialAndExclusionList;
	}
	/**
	 * Sets the denialAndExclusionList
	 * @param denialAndExclusionList.
	 */
	public void setDenialAndExclusionList(List denialAndExclusionList) {
		this.denialAndExclusionList = denialAndExclusionList;
	}
	/**
	 * Returns the productStructure
	 * @return ProductStructureBO productStructure.
	 */
	public ProductStructureBO getProductStructure() {
		return productStructure;
	}

	/**
	 * Sets the productStructure
	 * @param productStructure.
	 */
	public void setProductStructure(ProductStructureBO productStructure) {
		this.productStructure = productStructure;
	}

	/**
	 * Returns the parentProductKey
	 * @return int parentProductKey.
	 */
	public int getParentProductKey() {
		return parentProductKey;
	}

	/**
	 * Sets the parentProductKey
	 * @param parentProductKey.
	 */
	public void setParentProductKey(int parentProductKey) {
		this.parentProductKey = parentProductKey;
	}

	/**
	 * Returns the productStructureName
	 * @return String productStructureName.
	 */
	public String getProductStructureName() {
		return productStructureName;
	}

	/**
	 * Sets the productStructureName
	 * @param productStructureName.
	 */
	public void setProductStructureName(String productStructureName) {
		this.productStructureName = productStructureName;
	}

	/**
	 * Returns the productFamilyDesc
	 * @return String productFamilyDesc.
	 */
	public String getProductFamilyDesc() {
		return productFamilyDesc;
	}

	/**
	 * Sets the productFamilyDesc
	 * @param productFamilyDesc.
	 */
	public void setProductFamilyDesc(String productFamilyDesc) {
		this.productFamilyDesc = productFamilyDesc;
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
	 * @param domains.
	 */
	public void setBusinessDomains(List domains) {
		this.businessDomains = domains;
	}

	/**
	 * @return Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}

	/**
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
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
	 * @return Returns the stateCode.
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode The stateCode to set.
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return Returns the mandateDesc.
	 */
	public String getMandateDesc() {
		return mandateDesc;
	}

	/**
	 * @param mandateDesc The mandateDesc to set.
	 */
	public void setMandateDesc(String mandateDesc) {
		this.mandateDesc = mandateDesc;
	}

	/**
	 * @return Returns the stateDesc.
	 */
	public String getStateDesc() {
		return stateDesc;
	}

	/**
	 * @param stateDesc The stateDesc to set.
	 */
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	/**
	 * @return Returns the stateId.
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId The stateId to set.
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return Returns the adminList.
	 */
	public List getAdminList() {
		return adminList;
	}
	/**
	 * @param adminList The adminList to set.
	 */
	public void setAdminList(List adminList) {
		this.adminList = adminList;
	}
	/**
	 * @return Returns the hiddenProductType.
	 */
	public String getHiddenProductType() {
		return hiddenProductType;
	}
	/**
	 * @param hiddenProductType The hiddenProductType to set.
	 */
	public void setHiddenProductType(String hiddenProductType) {
		this.hiddenProductType = hiddenProductType;
	}
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the productStructureKey.
	 */
	public String getProductStructureKey() {
		return productStructureKey;
	}
	/**
	 * @param productStructureKey The productStructureKey to set.
	 */
	public void setProductStructureKey(String productStructureKey) {
		this.productStructureKey = productStructureKey;
	}
	/**
	 * @return Returns the productStructureVersion.
	 */
	public int getProductStructureVersion() {
		return productStructureVersion;
	}
	/**
	 * @param productStructureVersion The productStructureVersion to set.
	 */
	public void setProductStructureVersion(int productStructureVersion) {
		this.productStructureVersion = productStructureVersion;
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