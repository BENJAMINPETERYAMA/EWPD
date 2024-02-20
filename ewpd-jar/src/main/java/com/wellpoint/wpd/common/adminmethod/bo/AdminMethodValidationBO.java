/*
 * Created on Feb 29, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodValidationBO  extends BusinessObject {
	
	   private int entitySysId;
	   private String productName;
	   private int benefitComSysId;
	   private String benefitComName;
	   private int benefitAdminSysId;
	   private String benefitAdmiDesc;
	   private String entityType;
	   private int benefitSysId;
	   private String benefitName;
	   private int contractId;
	   private int productId;
	   private int spsId;
	   private int adminMethodId;
	   private String spsValidFlag;
	   private String procsRunningFlag = "F";
	   private String procsRepeatFlag;	   
	   private String effectiveDate;	   
	   private String expiryDate;	   
	   private String spsName;	   
	   private String adminMethodDesc;	   
	   private String adminMethod;  
	   private String  prcssRunningFlag = "F";  
	   private String entityTypeForValidation;	   
	   private List adminmethodValidationList;
	   private int tierSysId; //CARS:AM2	   
	   private int benefitTierSysId;//CARS:AM1
	   private String productFamName;
	   private boolean posProductFamily ;
	   
	   
	   /* (non-Javadoc)
	     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	     */
	    public boolean equals(BusinessObject object) {
	        // TODO Auto-generated method stub
	        return false;
	    }
	    /* (non-Javadoc)
	     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	     */
	    public int hashCode() {
	        // TODO Auto-generated method stub
	        return 0;
	    }
	    /* (non-Javadoc)
	     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	     */
	    public String toString() {
	        // TODO Auto-generated method stub
	        return null;
	    }

	/**
	 * @return Returns the adminmethodValidationList.
	 */
	public List getAdminmethodValidationList() {
		return adminmethodValidationList;
	}
	/**
	 * @param adminmethodValidationList The adminmethodValidationList to set.
	 */
	public void setAdminmethodValidationList(List adminmethodValidationList) {
		this.adminmethodValidationList = adminmethodValidationList;
	}
	/**
	 * @return Returns the entityTypeForValidation.
	 */
	public String getEntityTypeForValidation() {
		return entityTypeForValidation;
	}
	/**
	 * @param entityTypeForValidation The entityTypeForValidation to set.
	 */
	public void setEntityTypeForValidation(String entityTypeForValidation) {
		this.entityTypeForValidation = entityTypeForValidation;
	}
	/**
	 * @return Returns the prcssRunningFlag.
	 */
	public String getPrcssRunningFlag() {
		return prcssRunningFlag;
	}
	/**
	 * @param prcssRunningFlag The prcssRunningFlag to set.
	 */
	public void setPrcssRunningFlag(String prcssRunningFlag) {
		this.prcssRunningFlag = prcssRunningFlag;
	}
	/**
	 * @return Returns the adminMethod.
	 */
	public String getAdminMethod() {
		return adminMethod;
	}
	/**
	 * @param adminMethod The adminMethod to set.
	 */
	public void setAdminMethod(String adminMethod) {
		this.adminMethod = adminMethod;
	}
	/**
	 * @return Returns the adminMethodDesc.
	 */
	public String getAdminMethodDesc() {
		return adminMethodDesc;
	}
	/**
	 * @param adminMethodDesc The adminMethodDesc to set.
	 */
	public void setAdminMethodDesc(String adminMethodDesc) {
		this.adminMethodDesc = adminMethodDesc;
	}
	/**
	 * @return Returns the spsName.
	 */
	public String getSpsName() {
		return spsName;
	}
	/**
	 * @param spsName The spsName to set.
	 */
	public void setSpsName(String spsName) {
		this.spsName = spsName;
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
	 * @return Returns the benefitAdmiDesc.
	 */
	public String getBenefitAdmiDesc() {
		return benefitAdmiDesc;
	}
	/**
	 * @param benefitAdmiDesc The benefitAdmiDesc to set.
	 */
	public void setBenefitAdmiDesc(String benefitAdmiDesc) {
		this.benefitAdmiDesc = benefitAdmiDesc;
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
	 * @return Returns the benefitComName.
	 */
	public String getBenefitComName() {
		return benefitComName;
	}
	/**
	 * @param benefitComName The benefitComName to set.
	 */
	public void setBenefitComName(String benefitComName) {
		this.benefitComName = benefitComName;
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
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
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
	 * @return Returns the procsRepeatFlag.
	 */
	public String getProcsRepeatFlag() {
		return procsRepeatFlag;
	}
	/**
	 * @param procsRepeatFlag The procsRepeatFlag to set.
	 */
	public void setProcsRepeatFlag(String procsRepeatFlag) {
		this.procsRepeatFlag = procsRepeatFlag;
	}
	/**
	 * @return Returns the procsRunningFlag.
	 */
	public String getProcsRunningFlag() {
		return procsRunningFlag;
	}
	/**
	 * @param procsRunningFlag The procsRunningFlag to set.
	 */
	public void setProcsRunningFlag(String procsRunningFlag) {
		this.procsRunningFlag = procsRunningFlag;
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
	/**
	 * @return Returns the spsValidFlag.
	 */
	public String getSpsValidFlag() {
		return spsValidFlag;
	}
	/**
	 * @param spsValidFlag The spsValidFlag to set.
	 */
	public void setSpsValidFlag(String spsValidFlag) {
		this.spsValidFlag = spsValidFlag;
	}
	/**
	 * @return Returns the contractId.
	 */
	public int getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	
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
	 * @return Returns the productFamName.
	 */
	public String getProductFamName() {
		return productFamName;
	}
	/**
	 * @param productFamName The productFamName to set.
	 */
	public void setProductFamName(String productFamName) {
		this.productFamName = productFamName;
	}
	/**
	 * @return Returns the posProductFamily.
	 */
	public boolean isPosProductFamily() {
		return posProductFamily;
	}
	/**
	 * @param posProductFamily The posProductFamily to set.
	 */
	public void setPosProductFamily(boolean posProductFamily) {
		this.posProductFamily = posProductFamily;
	}
	//CARS:AM2:START
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	
	/**
	 * @return Returns the benefitTierSysId.
	 */
	public int getBenefitTierSysId() {
		return benefitTierSysId;
	}
	/**
	 * @param benefitTierSysId The benefitTierSysId to set.
	 */
	public void setBenefitTierSysId(int benefitTierSysId) {
		this.benefitTierSysId = benefitTierSysId;
	}
	   //CARS:AM2:END
}
