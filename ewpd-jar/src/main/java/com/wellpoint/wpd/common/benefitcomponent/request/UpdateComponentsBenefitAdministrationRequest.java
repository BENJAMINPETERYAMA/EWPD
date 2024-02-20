/*
 * UpdateComponentsBenefitAdministrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

import java.util.List;


/**
 * Request for Benfit Component Administration.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class UpdateComponentsBenefitAdministrationRequest extends WPDRequest{

	private List benefitAdministrationVOList;
	
	private String mainObjectIdentifier;
	
	private List domainList;
	   
	private int entityId;
	
	private int versionNumber;
	
	private int adminOptionAssnId;
	
	private List questionnareList;
	
	private List orgQuestionnareList;
	
	private List questionnaireListToAdd;
	
	private List questionnaireListToUpdate;
	
	private List questionnaireListToRemove;
	
	private int adminlevelOptionSysId;
	

    /**
     * @return Returns the versionNumber.
     */
    public int getVersionNumber() {
        return versionNumber;
    }
    /**
     * @param versionNumber The versionNumber to set.
     */
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }
    /**
     * @return Returns the domainList.
     */
    public List getDomainList() {
        return domainList;
    }
    /**
     * @param domainList The domainList to set.
     */
    public void setDomainList(List domainList) {
        this.domainList = domainList;
    }
    /**
     * @return Returns the mainObjectIdentifier.
     */
    public String getMainObjectIdentifier() {
        return mainObjectIdentifier;
    }
    /**
     * @param mainObjectIdentifier The mainObjectIdentifier to set.
     */
    public void setMainObjectIdentifier(String mainObjectIdentifier) {
        this.mainObjectIdentifier = mainObjectIdentifier;
    }
    /**
     * @return Returns the benefitAdministrationVOList.
     */
    public List getBenefitAdministrationVOList() {
        return benefitAdministrationVOList;
    }
    /**
     * @param benefitAdministrationVOList The benefitAdministrationVOList to set.
     */
    public void setBenefitAdministrationVOList(List benefitAdministrationVOList) {
        this.benefitAdministrationVOList = benefitAdministrationVOList;
    }
    /**
     * @return Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * @param entityId The entityId to set.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

    /**
     * Returns the adminOptionAssnId
     * @return int adminOptionAssnId.
     */
    public int getAdminOptionAssnId() {
        return adminOptionAssnId;
    }
    /**
     * Sets the adminOptionAssnId
     * @param adminOptionAssnId.
     */
    public void setAdminOptionAssnId(int adminOptionAssnId) {
        this.adminOptionAssnId = adminOptionAssnId;
    }
	/**
	 * @return Returns the questionnareList.
	 */
	public List getQuestionnareList() {
		return questionnareList;
	}
	/**
	 * @param questionnareList The questionnareList to set.
	 */
	public void setQuestionnareList(List questionnareList) {
		this.questionnareList = questionnareList;
	}
	/**
	 * @return Returns the adminlevelOptionSysId.
	 */
	public int getAdminlevelOptionSysId() {
		return adminlevelOptionSysId;
	}
	/**
	 * @param adminlevelOptionSysId The adminlevelOptionSysId to set.
	 */
	public void setAdminlevelOptionSysId(int adminlevelOptionSysId) {
		this.adminlevelOptionSysId = adminlevelOptionSysId;
	}
	/**
	 * @return Returns the orgQuestionnareList.
	 */
	public List getOrgQuestionnareList() {
		return orgQuestionnareList;
	}
	/**
	 * @param orgQuestionnareList The orgQuestionnareList to set.
	 */
	public void setOrgQuestionnareList(List orgQuestionnareList) {
		this.orgQuestionnareList = orgQuestionnareList;
	}
	/**
	 * @return Returns the questionnaireListToAdd.
	 */
	public List getQuestionnaireListToAdd() {
		return questionnaireListToAdd;
	}
	/**
	 * @param questionnaireListToAdd The questionnaireListToAdd to set.
	 */
	public void setQuestionnaireListToAdd(List questionnaireListToAdd) {
		this.questionnaireListToAdd = questionnaireListToAdd;
	}
	/**
	 * @return Returns the questionnaireListToRemove.
	 */
	public List getQuestionnaireListToRemove() {
		return questionnaireListToRemove;
	}
	/**
	 * @param questionnaireListToRemove The questionnaireListToRemove to set.
	 */
	public void setQuestionnaireListToRemove(List questionnaireListToRemove) {
		this.questionnaireListToRemove = questionnaireListToRemove;
	}
	/**
	 * @return Returns the questionnaireListToUpdate.
	 */
	public List getQuestionnaireListToUpdate() {
		return questionnaireListToUpdate;
	}
	/**
	 * @param questionnaireListToUpdate The questionnaireListToUpdate to set.
	 */
	public void setQuestionnaireListToUpdate(List questionnaireListToUpdate) {
		this.questionnaireListToUpdate = questionnaireListToUpdate;
	}
}
