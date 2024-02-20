/*
 * Created on Dec 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U16012
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodAnswerOverrideBO extends BusinessObject {

	private int questionNumber;

	private int answerId;

	private String entityType;

	private int entitySysId;

	private int benefitCompSysId;

	private int adminId;

	private int defId;

	private String spsName;

	/**
	 * @return Returns the adminId.
	 */
	public int getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId
	 *            The adminId to set.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return Returns the defId.
	 */
	public int getDefId() {
		return defId;
	}

	/**
	 * @param defId
	 *            The defId to set.
	 */
	public void setDefId(int defId) {
		this.defId = defId;
	}

	/**
	 * @return Returns the benefitCompSysId.
	 */
	public int getBenefitCompSysId() {
		return benefitCompSysId;
	}

	/**
	 * @param benefitCompSysId
	 *            The benefitCompSysId to set.
	 */
	public void setBenefitCompSysId(int benefitCompSysId) {
		this.benefitCompSysId = benefitCompSysId;
	}

	/**
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}

	/**
	 * @param entitySysId
	 *            The entitySysId to set.
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
	 * @param entityType
	 *            The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId
	 *            The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return Returns the questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * @param questionNumber
	 *            The questionNumber to set.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the spsName.
	 */
	public String getSpsName() {
		return spsName;
	}

	/**
	 * @param spsName
	 *            The spsName to set.
	 */
	public void setSpsName(String spsName) {
		this.spsName = spsName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof AdminMethodAnswerOverrideBO) {
			AdminMethodAnswerOverrideBO newValue = (AdminMethodAnswerOverrideBO) obj;
			if (this.questionNumber == newValue.getQuestionNumber())
				return true;
		}
		return false;
	}
}