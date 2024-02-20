/*
 * Created on Sep 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodBO extends BusinessObject{
    
    private int spsId;
    private String spsName;
    private String reference;
    private int adminMethodNumber;
    private String adminMethodDesc;
    private String entityType;
    private int entitySysId;
    private int benefitCompSysId;
    private int prodSysId;
    private int dateSgmntId;
    private int adminMethodSysId;
    private int administrationId;
    private String adminMethod;
    private int BenSysId;
    private int referenceId;
    private int validationId;
    private String processType;
    private String changeType;
    private List affectedBenComps;
    private List affectedBenCompNames;
    private int productId;
    private int contractId;

    
    /**
     * Returns the affectedBenCompNames
     * @return List affectedBenCompNames.
     */

    public List getAffectedBenCompNames() {
        return affectedBenCompNames;
    }
    /**
     * Sets the affectedBenCompNames
     * @param affectedBenCompNames.
     */

    public void setAffectedBenCompNames(List affectedBenCompNames) {
        this.affectedBenCompNames = affectedBenCompNames;
    }
    private List affectedBenefits;
    private boolean questionProduct;
    private String benCompName;
    
    /**
     * Returns the changeType
     * @return String changeType.
     */

    public String getChangeType() {
        return changeType;
    }
    /**
     * Sets the changeType
     * @param changeType.
     */

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
    /**
     * Returns the processType
     * @return String processType.
     */

    public String getProcessType() {
        return processType;
    }
    /**
     * Sets the processType
     * @param processType.
     */

    public void setProcessType(String processType) {
        this.processType = processType;
    }
    /**
     * @return validationId
     * 
     * Returns the validationId.
     */
    public int getValidationId() {
        return validationId;
    }
    /**
     * @param validationId
     * 
     * Sets the validationId.
     */
    public void setValidationId(int validationId) {
        this.validationId = validationId;
    }
    /**
     * Returns the referenceId
     * @return int referenceId.
     */
    public int getReferenceId() {
        return referenceId;
    }
    /**
     * Sets the referenceId
     * @param referenceId.
     */
    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }
  

    /**
     * Returns the administrationId
     * @return int administrationId.
     */

    public int getAdministrationId() {
        return administrationId;
    }
    /**
     * Sets the administrationId
     * @param administrationId.
     */

    public void setAdministrationId(int administrationId) {
        this.administrationId = administrationId;
    }
    /**
     * Returns the adminMethodDesc
     * @return String adminMethodDesc.
     */

    public String getAdminMethodDesc() {
        return adminMethodDesc;
    }
    /**
     * Sets the adminMethodDesc
     * @param adminMethodDesc.
     */

    public void setAdminMethodDesc(String adminMethodDesc) {
        this.adminMethodDesc = adminMethodDesc;
    }
    /**
     * Returns the adminMethodNumber
     * @return int adminMethodNumber.
     */

    public int getAdminMethodNumber() {
        return adminMethodNumber;
    }
    /**
     * Sets the adminMethodNumber
     * @param adminMethodNumber.
     */

    public void setAdminMethodNumber(int adminMethodNumber) {
        this.adminMethodNumber = adminMethodNumber;
    }
    /**
     * Returns the adminMethodSysId
     * @return int adminMethodSysId.
     */

    public int getAdminMethodSysId() {
        return adminMethodSysId;
    }
    /**
     * Sets the adminMethodSysId
     * @param adminMethodSysId.
     */

    public void setAdminMethodSysId(int adminMethodSysId) {
        this.adminMethodSysId = adminMethodSysId;
    }
    /**
     * Returns the benefitCompSysId
     * @return int benefitCompSysId.
     */

    public int getBenefitCompSysId() {
        return benefitCompSysId;
    }
    /**
     * Sets the benefitCompSysId
     * @param benefitCompSysId.
     */

    public void setBenefitCompSysId(int benefitCompSysId) {
        this.benefitCompSysId = benefitCompSysId;
    }
     /**
     * Returns the entitySysId
     * @return int entitySysId.
     */

    public int getEntitySysId() {
        return entitySysId;
    }
    /**
     * Sets the entitySysId
     * @param entitySysId.
     */

    public void setEntitySysId(int entitySysId) {
        this.entitySysId = entitySysId;
    }
    /**
     * Returns the entityType
     * @return String entityType.
     */

    public String getEntityType() {
        return entityType;
    }
    /**
     * Sets the entityType
     * @param entityType.
     */

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
     /**
     * Returns the reference
     * @return String reference.
     */

    public String getReference() {
        return reference;
    }
    /**
     * Sets the reference
     * @param reference.
     */

    public void setReference(String reference) {
        this.reference = reference;
    }
    /**
     * Returns the spsId
     * @return int spsId.
     */

    public int getSpsId() {
        return spsId;
    }
    /**
     * Sets the spsId
     * @param spsId.
     */

    public void setSpsId(int spsId) {
        this.spsId = spsId;
    }
    /**
     * Returns the spsName
     * @return String spsName.
     */

    public String getSpsName() {
        return spsName;
    }
    /**
     * Sets the spsName
     * @param spsName.
     */

    public void setSpsName(String spsName) {
        this.spsName = spsName;
    }
    /**
     * Returns the adminMethod
     * @return String adminMethod.
     */

    public String getAdminMethod() {
        return adminMethod;
    }
    /**
     * Sets the adminMethod
     * @param adminMethod.
     */

    public void setAdminMethod(String adminMethod) {
        this.adminMethod = adminMethod;
    }
    /**
     * Returns the benSysId
     * @return int benSysId.
     */

    public int getBenSysId() {
        return BenSysId;
    }
    /**
     * Sets the benSysId
     * @param benSysId.
     */

    public void setBenSysId(int benSysId) {
        BenSysId = benSysId;
    }
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
     * Returns the questionProduct
     * @return boolean questionProduct.
     */

    public boolean isQuestionProduct() {
        return questionProduct;
    }
    /**
     * Sets the questionProduct
     * @param questionProduct.
     */

    public void setQuestionProduct(boolean questionProduct) {
        this.questionProduct = questionProduct;
    }
    /**
     * Returns the benCompName
     * @return String benCompName.
     */

    public String getBenCompName() {
        return benCompName;
    }
    /**
     * Sets the benCompName
     * @param benCompName.
     */

    public void setBenCompName(String benCompName) {
        this.benCompName = benCompName;
    }
    /**
     * Returns the affectedBenComps
     * @return List affectedBenComps.
     */

    public List getAffectedBenComps() {
        return affectedBenComps;
    }
    /**
     * Sets the affectedBenComps
     * @param affectedBenComps.
     */

    public void setAffectedBenComps(List affectedBenComps) {
        this.affectedBenComps = affectedBenComps;
    }
    /**
     * Returns the affectedBenefits
     * @return List affectedBenefits.
     */

    public List getAffectedBenefits() {
        return affectedBenefits;
    }
    /**
     * Sets the affectedBenefits
     * @param affectedBenefits.
     */

    public void setAffectedBenefits(List affectedBenefits) {
        this.affectedBenefits = affectedBenefits;
    }
	/**
	 * @return Returns the prodSysId.
	 */
	public int getProdSysId() {
		return prodSysId;
	}
	/**
	 * @param prodSysId The prodSysId to set.
	 */
	public void setProdSysId(int prodSysId) {
		this.prodSysId = prodSysId;
	}
	/**
	 * @return Returns the dateSgmntId.
	 */
	public int getDateSgmntId() {
		return dateSgmntId;
	}
	/**
	 * @param dateSgmntId The dateSgmntId to set.
	 */
	public void setDateSgmntId(int dateSgmntId) {
		this.dateSgmntId = dateSgmntId;
	}
    /**
     * Returns the contractId
     * @return int contractId.
     */

    public int getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
    /**
     * Returns the productId
     * @return int productId.
     */

    public int getProductId() {
        return productId;
    }
    /**
     * Sets the productId
     * @param productId.
     */

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
