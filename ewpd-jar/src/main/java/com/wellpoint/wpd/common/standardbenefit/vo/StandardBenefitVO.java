/*
 * StandardBenefitVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.vo;

import com.wellpoint.wpd.common.bo.State;

import java.util.List;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitVO {
    private int standardBenefitKey;
    private int standardBenefitParentKey;    
    private String benefitIdentifier = "";
    private String description = "";
       
    private List lobList = null;
    private List businessEntityList = null;
    private List businessGroupList = null;
    private List marketBusinessUnitList = null;
    private List termList = null;
    private List qualifierList = null;
    private List PVAList = null;
    private List dataTypeList = null;
   
    
    private State state;
    private String status= null;
    private int version;
    private boolean checkIn;
    private boolean versionFlag = false;
    private List businessDomains; 
    private boolean updateFromDone;
    
    private List attachedNotes;
    private List versionList;
    
    private String benefitType = "";
    private String mandateType = "";
    
    private List ruleTypeList = null;
    private String stateId;
    private String stateDesc;
    private String mandateDesc;
    //added 28thNov
    private String benefitDefinitionKey;
    
    private String benefitCategory;
    private DomainDetail domainDetail = null;
    private String lob;

    	
    	/**
    	 * @return Returns the domainDetail.
    	 */
    	public DomainDetail getDomainDetail() {
    		return domainDetail;
    	}
    	/**
    	 * @param domainDetail The domainDetail to set.
    	 */
    	public void setDomainDetail(DomainDetail domainDetail) {
    		this.domainDetail = domainDetail;
    	}
    	
    	
    	/**
    	 * @return Returns the lob.
    	 */
    	public String getLob() {
    		return lob;
    	}
    	/**
    	 * @param lob The lob to set.
    	 */
    	public void setLob(String lob) {
    		this.lob = lob;
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
	 * @return Returns the benefitType.
	 */
	public String getBenefitType() {
		return benefitType;
	}
	/**
	 * @param benefitType The benefitType to set.
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
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
	 * @return Returns the ruleTypeList.
	 */
	public List getRuleTypeList() {
		return ruleTypeList;
	}
	/**
	 * @param ruleTypeList The ruleTypeList to set.
	 */
	public void setRuleTypeList(List ruleTypeList) {
		this.ruleTypeList = ruleTypeList;
	}
	/**
	 * @return Returns the stateTyprList.
	 */
	/*public List getStateTyprList() {
		return stateTyprList;
	}*/
	/**
	 * @param stateTyprList The stateTyprList to set.
	 */
	/*public void setStateTyprList(List stateTyprList) {
		this.stateTyprList = stateTyprList;
	}*/
    /**
     * Returns the checkIn
     * @return boolean checkIn.
     */

    public boolean isCheckIn() {
        return checkIn;
    }
    /**
     * Sets the checkIn
     * @param checkIn.
     */

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

	/**
	 * @return Returns the state.
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(State state) {
		this.state = state;
	}
    /**
     * Returns the status
     * @return String status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status
     * @param status.
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * @param standardBenefitKey The standardBenefitKey to set.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
    /**
     * Returns the benefitIdentifier
     * @return String benefitIdentifier.
     */
    public String getBenefitIdentifier() {
        return benefitIdentifier;
    }
    /**
     * Sets the benefitIdentifier
     * @param benefitIdentifier.
     */
    public void setBenefitIdentifier(String benefitIdentifier) {
        this.benefitIdentifier = benefitIdentifier;
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
     * Returns the dataTypeList
     * @return List dataTypeList.
     */
    public List getDataTypeList() {
        return dataTypeList;
    }
    /**
     * Sets the dataTypeList
     * @param dataTypeList.
     */
    public void setDataTypeList(List dataTypeList) {
        this.dataTypeList = dataTypeList;
    }
    /**
     * Returns the description
     * @return String description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns the lobList
     * @return List lobList.
     */
    public List getLobList() {
        return lobList;
    }
    /**
     * Sets the lobList
     * @param lobList.
     */
    public void setLobList(List lobList) {
        this.lobList = lobList;
    }
    /**
     * Returns the pVAList
     * @return List pVAList.
     */
    public List getPVAList() {
        return PVAList;
    }
    /**
     * Sets the pVAList
     * @param pVAList.
     */
    public void setPVAList(List list) {
        PVAList = list;
    }
    /**
     * Returns the qualifierList
     * @return List qualifierList.
     */
    public List getQualifierList() {
        return qualifierList;
    }
    /**
     * Sets the qualifierList
     * @param qualifierList.
     */
    public void setQualifierList(List qualifierList) {
        this.qualifierList = qualifierList;
    }
    /**
     * Returns the termList
     * @return List termList.
     */
    public List getTermList() {
        return termList;
    }
    /**
     * Sets the termList
     * @param termList.
     */
    public void setTermList(List termList) {
        this.termList = termList;
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
	 * @return Returns the versionFlag.
	 */
	public boolean isVersionFlag() {
		return versionFlag;
	}
	/**
	 * @param versionFlag The versionFlag to set.
	 */
	public void setVersionFlag(boolean versionFlag) {
		this.versionFlag = versionFlag;
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
	 * @return Returns the updateFromDone.
	 */
	public boolean isUpdateFromDone() {
		return updateFromDone;
	}
	/**
	 * @param updateFromDone The updateFromDone to set.
	 */
	public void setUpdateFromDone(boolean updateFromDone) {
		this.updateFromDone = updateFromDone;
	}
	/**
	 * @return Returns the attachedNotes.
	 */
	public List getAttachedNotes() {
		return attachedNotes;
	}
	/**
	 * @param attachedNotes The attachedNotes to set.
	 */
	public void setAttachedNotes(List attachedNotes) {
		this.attachedNotes = attachedNotes;
	}
	/**
	 * @return Returns the versionList.
	 */
	public List getVersionList() {
		return versionList;
	}
	/**
	 * @param versionList The versionList to set.
	 */
	public void setVersionList(List versionList) {
		this.versionList = versionList;
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
