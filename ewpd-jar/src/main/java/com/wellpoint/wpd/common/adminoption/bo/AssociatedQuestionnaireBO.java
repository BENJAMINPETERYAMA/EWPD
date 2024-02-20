/*
 * AssociatedQuestionnaireBO.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.bo;

import java.util.List;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;

/**
 * Business Object for the associated questionnaire  for an Admin Option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AssociatedQuestionnaireBO extends Questionnaire{
    
     private int adminOptionId;
	
	 private int answerId;
	 
	 private String answerDesc;
	 
	 private int questionId;
	 
	 private String questionDesc;
	 
	 private String referenceid;
	 
	 private String referenceDesc;
	 
	 private int sequence;
	 
	 private List questionnaireList; 
	 
	 private String levelIndicator;
	 
	 private DomainDetail domainDetail ;
	 
	 private String beString;
	 
	 private String lobString;
	 
	 private String bgString;
	 
	 private String buString;
	 
	 private int adminLevelOptionSysId;
	 
	 private int benefitAdminId;
	 
	 private int productId;
	 
	 private int contractSysId;
	 
	 private boolean root = false;
	 
	 private boolean parentRequiredChecked = false;
	 
	/**
	 * @return Returns the parentRequired.
	 */
	public String getParentRequired() {
		return parentRequired;
	}
	/**
	 * @param parentRequired The parentRequired to set.
	 */
	public void setParentRequired(String parentRequired) {
		this.parentRequired = parentRequired;
	}
	 private String parentRequired;
	 
	 private String functionalDomainDesc;
	 

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
	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}
	/**
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

    /**
     * @return Returns the sequence.
     */
    public int getSequence() {
        return sequence;
    }
    /**
     * @param sequence The sequence to set.
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
	/**
	 * @return Returns the questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}
	/**
	 * @param questionDesc The questionDesc to set.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	/**
	 * @return Returns the questionId.
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}
	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
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
    /**
     * @return Returns the levelIndicator.
     */
    public String getLevelIndicator() {
        return levelIndicator;
    }
    /**
     * @param levelIndicator The levelIndicator to set.
     */
    public void setLevelIndicator(String levelIndicator) {
        this.levelIndicator = levelIndicator;
    }
    /**
     * @return Returns the referenceid.
     */
    public String getReferenceid() {
        return referenceid;
    }
    /**
     * @param referenceid The referenceid to set.
     */
    public void setReferenceid(String referenceid) {
        this.referenceid = referenceid;
    }
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
     * @return Returns the beString.
     */
    public String getBeString() {
        return beString;
    }
    /**
     * @param beString The beString to set.
     */
    public void setBeString(String beString) {
        this.beString = beString;
    }
    /**
     * @return Returns the bgString.
     */
    public String getBgString() {
        return bgString;
    }
    /**
     * @param bgString The bgString to set.
     */
    public void setBgString(String bgString) {
        this.bgString = bgString;
    }
    /**
     * @return Returns the lobString.
     */
    public String getLobString() {
        return lobString;
    }
    /**
     * @param lobString The lobString to set.
     */
    public void setLobString(String lobString) {
        this.lobString = lobString;
    }
	/**
	 * @return Returns the adminLevelOptionSysId.
	 */
	public int getAdminLevelOptionSysId() {
		return adminLevelOptionSysId;
	}
	/**
	 * @param adminLevelOptionSysId The adminLevelOptionSysId to set.
	 */
	public void setAdminLevelOptionSysId(int adminLevelOptionSysId) {
		this.adminLevelOptionSysId = adminLevelOptionSysId;
	}
	/**
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
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
	 * @return Returns the root.
	 */
	public boolean isRoot() {
		return root;
	}
	/**
	 * @param root The root to set.
	 */
	public void setRoot(boolean root) {
		this.root = root;
	}
	
	/**
	 * @return Returns the functionalDomainDesc.
	 */
	public String getFunctionalDomainDesc() {
		return functionalDomainDesc;
	}
	/**
	 * @param functionalDomainDesc The functionalDomainDesc to set.
	 */
	public void setFunctionalDomainDesc(String functionalDomainDesc) {
		this.functionalDomainDesc = functionalDomainDesc;
	}

	/**
	 * @return Returns the parentRequiredChecked.
	 */
	public boolean isParentRequiredChecked() {
		return parentRequiredChecked;
	}
	/**
	 * @param parentRequiredChecked The parentRequiredChecked to set.
	 */
	public void setParentRequiredChecked(boolean parentRequiredChecked) {
		this.parentRequiredChecked = parentRequiredChecked;
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
	 * @return Returns the buString.
	 */
	public String getBuString() {
		return buString;
	}
	/**
	 * @param buString The buString to set.
	 */
	public void setBuString(String buString) {
		this.buString = buString;
	}
}
