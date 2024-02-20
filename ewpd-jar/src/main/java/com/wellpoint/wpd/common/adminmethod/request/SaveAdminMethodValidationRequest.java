/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminMethodValidationRequest   extends WPDRequest{
	
	private int entitySysId;
	   
	   private int benefitComSysId;
	   private int benefitComSqncNumber;
	   private int benefitAdminSysId;
	   
	   private String entityType;
	   private int benefitSysId;
	   private int benefitSqncNumber;
	   private int spsId;
	   private int adminMethodId;
	   
	   
	   private String effectiveDate;
	   
	   private String expiryDate;
	
	
	  private List adminMethodValidationList;
	  
	  /*START AM1 CARS */
	  private List tieredAdminMethodList;  
	  /*END AM1 CARS */ 
	  private String benefitComponentName;
	  private int contractSysId;
	  private int productId;
	  
	/*START AM1 CARS */
	/**
	 * @return Returns the tierdAdminMethodList.
	 */
	public List getTieredAdminMethodList() {
		return tieredAdminMethodList;
	}
	/**
	 * @param tierdAdminMethodList The tierdAdminMethodList to set.
	 */
	public void setTieredAdminMethodList(List tieredAdminMethodList) {
		this.tieredAdminMethodList = tieredAdminMethodList;
	}
	/*END AM1 CARS */
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * @return Returns the adminMethodId.
	 */
	public int getAdminMethodId() {
		return adminMethodId;
	}
	/**
	 * @param adminMethodId The adminMethodId to set.
	 */
	public void setAdminMethodId(int adminMethodId) {
		this.adminMethodId = adminMethodId;
	}
	/**
	 * @return Returns the adminMethodValidationList.
	 */
	public List getAdminMethodValidationList() {
		return adminMethodValidationList;
	}
	/**
	 * @param adminMethodValidationList The adminMethodValidationList to set.
	 */
	public void setAdminMethodValidationList(List adminMethodValidationList) {
		this.adminMethodValidationList = adminMethodValidationList;
	}
	/**
	 * @return Returns the benefitAdminSysId.
	 */
	public int getBenefitAdminSysId() {
		return benefitAdminSysId;
	}
	/**
	 * @param benefitAdminSysId The benefitAdminSysId to set.
	 */
	public void setBenefitAdminSysId(int benefitAdminSysId) {
		this.benefitAdminSysId = benefitAdminSysId;
	}
	/**
	 * @return Returns the benefitComSysId.
	 */
	public int getBenefitComSysId() {
		return benefitComSysId;
	}
	/**
	 * @param benefitComSysId The benefitComSysId to set.
	 */
	public void setBenefitComSysId(int benefitComSysId) {
		this.benefitComSysId = benefitComSysId;
	}
	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return Returns the spsId.
	 */
	public int getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(int spsId) {
		this.spsId = spsId;
	}
	public void validate()throws ValidationException{
		
	}

	/**
	 * @return Returns the benefitComSqncNumber.
	 */
	public int getBenefitComSqncNumber() {
		return benefitComSqncNumber;
	}
	/**
	 * @param benefitComSqncNumber The benefitComSqncNumber to set.
	 */
	public void setBenefitComSqncNumber(int benefitComSqncNumber) {
		this.benefitComSqncNumber = benefitComSqncNumber;
	}
	/**
	 * @return Returns the benefitSqncNumber.
	 */
	public int getBenefitSqncNumber() {
		return benefitSqncNumber;
	}
	/**
	 * @param benefitSqncNumber The benefitSqncNumber to set.
	 */
	public void setBenefitSqncNumber(int benefitSqncNumber) {
		this.benefitSqncNumber = benefitSqncNumber;
	}
}
