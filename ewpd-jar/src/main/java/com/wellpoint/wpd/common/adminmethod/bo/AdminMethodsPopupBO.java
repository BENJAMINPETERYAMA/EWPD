/*
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;

import java.util.List;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodsPopupBO {

    private String termCode;
    
    private String actualAdminMthdDesc;
    
    private String qualifierCode;
    
    private String providerCode;
    
    private int dataTypeId;
    
    private List questionAnswers;
    
    private int adminMethodId;
    
    private int spsId;
    
    private String spsName;
    
    private int benftId;
    
    private int adminId;
    
    private String adminMethodDesc;

    private int adminMethodNumber;
    
    private int entityId;
    private int contractId;
    private String entityType;
    private int count;    
    private int answerId;
    
    private int benefitCompId;
    
    private int prodStructId;
    
    private int prodId;
    
    private int contractDateSgmntId;
    
    private int stdDefId;
    

    private String benefitComponentName;
    private int adminMethodFilterCriteriaSysId;
    
    private String answerList;
    
    private double rank;
    
    private String benefitName;
    
    private String benAdmnDateRange;
    
    private int flagId;
    
    private String referenceId;
    
    private String answerDesc;
    //CARS AM1 START
    private int benefitTierSysId; 

    private int entityTierSysId; 
    
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
	/**
     * @return Returns the entityTierSysId.
     */
    public int getEntityTierSysId() {
        return entityTierSysId;
    }
    /**
     * @param entityTierSysId The entityTierSysId to set.
     */
    public void setEntityTierSysId(int entityTierSysId) {
        this.entityTierSysId = entityTierSysId;
    }
	//CARS AM1 END
	/**
	 * @return Returns the benAdmnDateRange.
	 */
	public String getBenAdmnDateRange() {
		return benAdmnDateRange;
	}
	/**
	 * @param benAdmnDateRange The benAdmnDateRange to set.
	 */
	public void setBenAdmnDateRange(String benAdmnDateRange) {
		this.benAdmnDateRange = benAdmnDateRange;
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
	 * @return Returns the flagId.
	 */
	public int getFlagId() {
		return flagId;
	}
	/**
	 * @param flagId The flagId to set.
	 */
	public void setFlagId(int flagId) {
		this.flagId = flagId;
	}
    

    /**
     * Returns the rank
     * @return int rank.
     */

    public double getRank() {
        return rank;
    }
    /**
     * Sets the rank
     * @param rank.
     */

    public void setRank(double rank) {
        this.rank = rank;
    }
    /**
     * @return answerList
     * 
     * Returns the answerList.
     */
    public String getAnswerList() {
        return answerList;
    }
    /**
     * @param answerList
     * 
     * Sets the answerList.
     */
    public void setAnswerList(String answerList) {
        this.answerList = answerList;
    }
    /**
     * @return adminMethodFilterCriteriaSysId
     * 
     * Returns the adminMethodFilterCriteriaSysId.
     */
    public int getAdminMethodFilterCriteriaSysId() {
        return adminMethodFilterCriteriaSysId;
    }
    /**
     * @param adminMethodFilterCriteriaSysId
     * 
     * Sets the adminMethodFilterCriteriaSysId.
     */
    public void setAdminMethodFilterCriteriaSysId(
            int adminMethodFilterCriteriaSysId) {
        this.adminMethodFilterCriteriaSysId = adminMethodFilterCriteriaSysId;
    }
    /**
     * Returns the benftId
     * @return int benftId.
     */

    public int getBenftId() {
        return benftId;
    }
    /**
     * Sets the benftId
     * @param benftId.
     */

    public void setBenftId(int benftId) {
        this.benftId = benftId;
    }
    /**
     * @return spsId
     * 
     * Returns the spsId.
     */
    public int getSpsId() {
        return spsId;
    }
    /**
     * @param spsId
     * 
     * Sets the spsId.
     */
    public void setSpsId(int spsId) {
        this.spsId = spsId;
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
     * @return Returns the dataTypeId.
     */
    public int getDataTypeId() {
        return dataTypeId;
    }
    /**
     * @param dataTypeId The dataTypeId to set.
     */
    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
    /**
     * @return Returns the providerCode.
     */
    public String getProviderCode() {
        return providerCode;
    }
    /**
     * @param providerCode The providerCode to set.
     */
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
    /**
     * @return Returns the qualifierCode.
     */
    public String getQualifierCode() {
        return qualifierCode;
    }
    /**
     * @param qualifierCode The qualifierCode to set.
     */
    public void setQualifierCode(String qualifierCode) {
        this.qualifierCode = qualifierCode;
    }
    /**
     * @return Returns the questionAnswers.
     */
    public List getQuestionAnswers() {
        return questionAnswers;
    }
    /**
     * @param questionAnswers The questionAnswers to set.
     */
    public void setQuestionAnswers(List questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
    /**
     * @return Returns the termCode.
     */
    public String getTermCode() {
        return termCode;
    }
    /**
     * @param termCode The termCode to set.
     */
    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }
    
    /**
     * @return Returns the adminMethodNumber.
     */
    public int getAdminMethodNumber() {
        return adminMethodNumber;
    }
    /**
     * @param adminMethodNumber The adminMethodNumber to set.
     */
    public void setAdminMethodNumber(int adminMethodNumber) {
        this.adminMethodNumber = adminMethodNumber;
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
     * Returns the entityId
     * @return int entityId.
     */

    public int getEntityId() {
        return entityId;
    }
    /**
     * Sets the entityId
     * @param entityId.
     */

    public void setEntityId(int entityId) {
        this.entityId = entityId;
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
     * Returns the count
     * @return int count.
     */

    public int getCount() {
        return count;
    }
    /**
     * Sets the count
     * @param count.
     */

    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * Returns the answerId
     * @return int answerId.
     */

    public int getAnswerId() {
        return answerId;
    }
    /**
     * Sets the answerId
     * @param answerId.
     */

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
    
    
    /**
     * Returns the benefitCompId
     * @return int benefitCompId.
     */
    public int getBenefitCompId() {
        return benefitCompId;
    }
    /**
     * Sets the benefitCompId
     * @param benefitCompId.
     */
    public void setBenefitCompId(int benefitCompId) {
        this.benefitCompId = benefitCompId;
    }
    /**
     * Returns the contractDateSgmntId
     * @return int contractDateSgmntId.
     */
    public int getContractDateSgmntId() {
        return contractDateSgmntId;
    }
    /**
     * Sets the contractDateSgmntId
     * @param contractDateSgmntId.
     */
    public void setContractDateSgmntId(int contractDateSgmntId) {
        this.contractDateSgmntId = contractDateSgmntId;
    }
    /**
     * Returns the prodId
     * @return int prodId.
     */
    public int getProdId() {
        return prodId;
    }
    /**
     * Sets the prodId
     * @param prodId.
     */
    public void setProdId(int prodId) {
        this.prodId = prodId;
    }
    /**
     * Returns the prodStructId
     * @return int prodStructId.
     */
    public int getProdStructId() {
        return prodStructId;
    }
    /**
     * Sets the prodStructId
     * @param prodStructId.
     */
    public void setProdStructId(int prodStructId) {
        this.prodStructId = prodStructId;
    }
    
    
    /**
     * Returns the stdDefId
     * @return int stdDefId.
     */
    public int getStdDefId() {
        return stdDefId;
    }
    /**
     * Sets the stdDefId
     * @param stdDefId.
     */
    public void setStdDefId(int stdDefId) {
        this.stdDefId = stdDefId;
    }
 
    /**
     * Returns the adminId
     * @return int adminId.
     */

    public int getAdminId() {
        return adminId;
    }
    /**
     * Sets the adminId
     * @param adminId.
     */

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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
	 * @return Returns the actualAdminMthdDesc.
	 */
	public String getActualAdminMthdDesc() {
		return actualAdminMthdDesc;
	}
	/**
	 * @param actualAdminMthdDesc The actualAdminMthdDesc to set.
	 */
	public void setActualAdminMthdDesc(String actualAdminMthdDesc) {
		this.actualAdminMthdDesc = actualAdminMthdDesc;
	}
	/**
	 * @return Returns the referenceId.
	 */
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	/**
	 * @return Returns the answerDesc.
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}
	/**
	 * @param answerDesc The answerDesc to set.
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}    
}
