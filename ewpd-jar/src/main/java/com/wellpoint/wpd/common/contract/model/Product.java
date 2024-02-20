/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Product implements Serializable{
	
	private List lineOfBusiness;
	
	private List businessEntity;
	
	private List businessGroup;
	
	private String productName;
	
	private String productFamily;
	
	private String productType;
	
	private String productStructureName;
	
	private int productStructureVersion;
	
	private String productStructureDescription;
	
	private Date effectiveDate;
	
	private Date expiryDate;
	
	private int version;
	
	private List benefitComponents;
	
	
	/**
	 * @return Returns the benefitComponents.
	 */
	public List getBenefitComponents() {
		return benefitComponents;
	}
	/**
	 * @param benefitComponents The benefitComponents to set.
	 */
	public void setBenefitComponents(List benefitComponents) {
		this.benefitComponents = benefitComponents;
	}
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
	 * @return Returns the effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return Returns the productStructureDescription.
	 */
	public String getProductStructureDescription() {
		return productStructureDescription;
	}
	/**
	 * @param productStructureDescription The productStructureDescription to set.
	 */
	public void setProductStructureDescription(
			String productStructureDescription) {
		this.productStructureDescription = productStructureDescription;
	}
	/**
	 * @return Returns the productStructureName.
	 */
	public String getProductStructureName() {
		return productStructureName;
	}
	/**
	 * @param productStructureName The productStructureName to set.
	 */
	public void setProductStructureName(String productStructureName) {
		this.productStructureName = productStructureName;
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
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}
