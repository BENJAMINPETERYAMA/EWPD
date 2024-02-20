/*
 * AdminOptionBO.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.bo;

import java.util.List;
import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * Business Object of Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionBO extends BusinessObject {

    private int adminOptionId = -1;

    private String adminName;

    private String termId;

    private String qualifierId;

    private String referenceId;

    private String termDesc;

    private String qualifierDesc;

    private String referenceDesc;

    private List associatedQuestionsList;

    private String stateValue;
    
    private int adminOptionParentSysId;
    
    /**
     * Returns the adminOptionId
     * 
     * @return int adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }


    /**
     * Sets the adminOptionId
     * 
     * @param adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }


    /**
     * Returns the adminName
     * 
     * @return String adminName.
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * Sets the adminName
     * 
     * @param adminName.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * Returns the qualifierId
     * 
     * @return String qualifierId.
     */
    public String getQualifierId() {
        return qualifierId;
    }


    /**
     * Sets the qualifierId
     * 
     * @param qualifierId.
     */
    public void setQualifierId(String qualifierId) {
        this.qualifierId = qualifierId;
    }


    /**
     * Returns the referenceId
     * 
     * @return String referenceId.
     */
    public String getReferenceId() {
        return referenceId;
    }


    /**
     * Sets the referenceId
     * 
     * @param referenceId.
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }


    /**
     * Returns the termId
     * 
     * @return String termId.
     */
    public String getTermId() {
        return termId;
    }


    /**
     * Sets the termId
     * 
     * @param termId.
     */
    public void setTermId(String termId) {
        this.termId = termId;
    }


    /**
     * Returns the qualifierDesc
     * 
     * @return String qualifierDesc.
     */
    public String getQualifierDesc() {
        return qualifierDesc;
    }


    /**
     * Sets the qualifierDesc
     * 
     * @param qualifierDesc.
     */
    public void setQualifierDesc(String qualifierDesc) {
        this.qualifierDesc = qualifierDesc;
    }


    /**
     * Returns the referenceDesc
     * 
     * @return String referenceDesc.
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }


    /**
     * Sets the referenceDesc
     * 
     * @param referenceDesc.
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }


    /**
     * Returns the termDesc
     * 
     * @return String termDesc.
     */
    public String getTermDesc() {
        return termDesc;
    }


    /**
     * Sets the termDesc
     * 
     * @param termDesc.
     */
    public void setTermDesc(String termDesc) {
        this.termDesc = termDesc;
    }


    /**
     * Returns the associatedQuestionsList.
     * 
     * @return associatedQuestionsList
     */
    public List getAssociatedQuestionsList() {
        return associatedQuestionsList;
    }


    /**
     * Sets the associatedQuestionsList.
     * 
     * @param associatedQuestionsList
     */
    public void setAssociatedQuestionsList(List associatedQuestionsList) {
        this.associatedQuestionsList = associatedQuestionsList;
    }


    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param object
     * @return boolean
     */
    public boolean equals(BusinessObject object) {
        return true;
    }


    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * @return int
     */
    public int hashCode() {
        return 1;
    }


    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("adminOptionId = ").append(this.adminOptionId);
        buffer.append(", adminName = ").append(this.adminName);
        buffer.append(", termId = ").append(this.termId);
        buffer.append(", qualifierId = ").append(this.qualifierId);
        buffer.append(", referenceId = ").append(this.referenceId);
        return buffer.toString();
    }


    /**
     * Returns the stateValue.
     * 
     * @return stateValue
     */
    public String getStateValue() {
        return stateValue;
    }


    /**
     * The stateValue to set.
     * 
     * @param stateValue
     */
    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

	/**
	 * @return Returns the adminOptionParentSysId.
	 */
	public int getAdminOptionParentSysId() {
		return adminOptionParentSysId;
	}
	/**
	 * @param adminOptionParentSysId The adminOptionParentSysId to set.
	 */
	public void setAdminOptionParentSysId(int adminOptionParentSysId) {
		this.adminOptionParentSysId = adminOptionParentSysId;
	}
}