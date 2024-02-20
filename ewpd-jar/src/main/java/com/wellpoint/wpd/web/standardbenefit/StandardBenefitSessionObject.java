/*
 * StandardBenefitSessionObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.HashMap;
import java.util.List;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitSessionObject {

	int standardBenefitKey;
	String standardBenefitName;
	int standardBenefitVersionNumber;
	int standardBenefitParentKey;
	String standardBenefitState;
	String standardBenefitStatus;
	String standardBenefitMode;
	String benefitType;
	List businessDomains;
	boolean checkout; 
	boolean copy;
	boolean copyFlag;
	int benefitMandateId;
	String mandateType;
	String noteText;
	String stateCode;
	int sequenceNumber;
	//added 28thNOv
	String benefitDefinitionKey;
	String benefitCategory;
	List questionnaireList;
	List orgQuestionnaireList;
	HashMap allPossibleAnswerMap;
	int adminOptionId;
	
	List adminMethodsList;
	List dateTypeList;
	List benefitLevelForViewVOList;
	boolean dateAvailable;
	BenefitWrapperVO benefitWrapperVO;
	
	/**
	 * @return Returns the benefitWrapperVO.
	 */
	public BenefitWrapperVO getBenefitWrapperVO() {
		return benefitWrapperVO;
	}
	/**
	 * @param benefitWrapperVO The benefitWrapperVO to set.
	 */
	public void setBenefitWrapperVO(BenefitWrapperVO benefitWrapperVO) {
		this.benefitWrapperVO = benefitWrapperVO;
	}
	/**
	 * @return Returns the benefitLevelForViewVOList.
	 */
	public List getBenefitLevelForViewVOList() {
		return benefitLevelForViewVOList;
	}
	/**
	 * @param benefitLevelForViewVOList The benefitLevelForViewVOList to set.
	 */
	public void setBenefitLevelForViewVOList(List benefitLevelForViewVOList) {
		this.benefitLevelForViewVOList = benefitLevelForViewVOList;
	}
	/**
	 * @return Returns the dateAvailable.
	 */
	public boolean isDateAvailable() {
		return dateAvailable;
	}
	/**
	 * @param dateAvailable The dateAvailable to set.
	 */
	public void setDateAvailable(boolean dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	/**
	 * @return the dateTypeList
	 */
	public List getDateTypeList() {
		return dateTypeList;
	}
	/**
	 * @param dateTypeList the dateTypeList to set
	 */
	public void setDateTypeList(List dateTypeList) {
		this.dateTypeList = dateTypeList;
	}
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
	}
    /**
     * @return copyFlag
     * 
     * Returns the copyFlag.
     */
    public boolean isCopyFlag() {
        return copyFlag;
    }
    /**
     * @param copyFlag
     * 
     * Sets the copyFlag.
     */
    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }
    /**
     * Returns the standardBenefitKey
     * @return int standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * Sets the standardBenefitKey
     * @param standardBenefitKey.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
    /**
     * Returns the standardBenefitVersionNumber
     * @return int standardBenefitVersionNumber.
     */
    public int getStandardBenefitVersionNumber() {
        return standardBenefitVersionNumber;
    }
    /**
     * Sets the standardBenefitVersionNumber
     * @param standardBenefitVersionNumber.
     */
    public void setStandardBenefitVersionNumber(int standardBenefitVersionNumber) {
        this.standardBenefitVersionNumber = standardBenefitVersionNumber;
    }
    
    /**
     * Returns the standardBenefitName
     * @return String standardBenefitName.
     */
    public String getStandardBenefitName() {
        return standardBenefitName;
    }
    /**
     * Sets the standardBenefitName
     * @param standardBenefitName.
     */
    public void setStandardBenefitName(String standardBenefitName) {
        this.standardBenefitName = standardBenefitName;
    }
    /**
     * @return Returns the standardBenefitMode.
     */
    public String getStandardBenefitMode() {
        return standardBenefitMode;
    }
    /**
     * @param standardBenefitMode The standardBenefitMode to set.
     */
    public void setStandardBenefitMode(String standardBenefitMode) {
        this.standardBenefitMode = standardBenefitMode;
    }
    
	/**
	 * @return Returns the standardBenefitState.
	 */
	public String getStandardBenefitState() {
		return standardBenefitState;
	}
	/**
	 * @param standardBenefitState The standardBenefitState to set.
	 */
	public void setStandardBenefitState(String standardBenefitState) {
		this.standardBenefitState = standardBenefitState;
	}
	/**
	 * @return Returns the standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}
	/**
	 * @param standardBenefitStatus The standardBenefitStatus to set.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}
	/**
	 * @return Returns the businessDomains.
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
	
	/**
	 * @return Returns the standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * @param standardBenefitParentKey The standardBenefitParentKey to set.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	
	/**
	 * @return Returns the checkout.
	 */
	public boolean isCheckout() {
		return checkout;
	}
	/**
	 * @param checkout The checkout to set.
	 */
	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
	}

	/**
	 * @return Returns the copy.
	 */
	public boolean isCopy() {
		return copy;
	}
	/**
	 * @param copy The copy to set.
	 */
	public void setCopy(boolean copy) {
		this.copy = copy;
	}
    /**
     * @return benefitType
     * 
     * Returns the benefitType.
     */
    public String getBenefitType() {
        return benefitType;
    }
    /**
     * @param benefitType
     * 
     * Sets the benefitType.
     */
    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }
	/**
	 * Returns the benefitMandateId
	 * @return int benefitMandateId.
	 */
	public int getBenefitMandateId() {
		return benefitMandateId;
	}
	/**
	 * Sets the benefitMandateId
	 * @param benefitMandateId.
	 */
	public void setBenefitMandateId(int benefitMandateId) {
		this.benefitMandateId = benefitMandateId;
	}
	/**
	 * Returns the mandateType
	 * @return String mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}
	/**
	 * Sets the mandateType
	 * @param mandateType.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
	/**
	 * Returns the noteText
	 * @return String noteText.
	 */
	public String getNoteText() {
		return noteText;
	}
	/**
	 * Sets the noteText
	 * @param noteText.
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	/**
	 * Returns the stateCode
	 * @return String stateCode.
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * Sets the stateCode
	 * @param stateCode.
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return Returns the benefitDefinitionKey.
	 */
	public String getBenefitDefinitionKey() {
		return benefitDefinitionKey;
	}
	/**
	 * @param benefitDefinitionKey The benefitDefinitionKey to set.
	 */
	public void setBenefitDefinitionKey(String benefitDefinitionKey) {
		this.benefitDefinitionKey = benefitDefinitionKey;
	}
	/**
	 * @return Returns the questionnaireList.
	 */
	public List getQuestionnaireList() {
		return questionnaireList;
	}
	/**
	 * @param questionnaireList The questionnaireList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
	
	public List getAdminMethodsList() {
		return adminMethodsList;
	}
	public void setAdminMethodsList(List adminMethodsList) {
		this.adminMethodsList = adminMethodsList;
	}
	
	
	/**
	 * @return Returns the allPossibleAnswerMap.
	 */
	public HashMap getAllPossibleAnswerMap() {
		return allPossibleAnswerMap;
	}
	/**
	 * @param allPossibleAnswerMap The allPossibleAnswerMap to set.
	 */
	public void setAllPossibleAnswerMap(HashMap allPossibleAnswerMap) {
		this.allPossibleAnswerMap = allPossibleAnswerMap;
	}
	/**
	 * @return Returns the orgQuestionnaireList.
	 */
	public List getOrgQuestionnaireList() {
		return orgQuestionnaireList;
	}
	/**
	 * @param orgQuestionnaireList The orgQuestionnaireList to set.
	 */
	public void setOrgQuestionnaireList(List orgQuestionnaireList) {
		this.orgQuestionnaireList = orgQuestionnaireList;
	}
	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
}
