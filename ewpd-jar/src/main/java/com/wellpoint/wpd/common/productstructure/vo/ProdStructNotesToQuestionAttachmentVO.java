/*
 * Created on Jun 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.vo;

import java.util.List;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProdStructNotesToQuestionAttachmentVO {

	
	private int questionId;
	private String noteId;
	private int primaryId;
	private int secondaryId;
	private int benefitCompId;
	private int bnftDefId;
	private String noteOverrideStatus;
	private int noteVersionNumber;
	private String primaryEntityType;
	private String secondaryEntityType;
	private String action;
	private String primaryEntityName;
	private List businessDomainList;
	private int prodStructVersionNumber;
	
	
	public int getBenefitCompId() {
		return benefitCompId;
	}
	public void setBenefitCompId(int benefitCompId) {
		this.benefitCompId = benefitCompId;
	}
	public int getBnftDefId() {
		return bnftDefId;
	}
	public void setBnftDefId(int bnftDefId) {
		this.bnftDefId = bnftDefId;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getNoteOverrideStatus() {
		return noteOverrideStatus;
	}
	public void setNoteOverrideStatus(String noteOverrideStatus) {
		this.noteOverrideStatus = noteOverrideStatus;
	}
	public int getNoteVersionNumber() {
		return noteVersionNumber;
	}
	public void setNoteVersionNumber(int noteVersionNumber) {
		this.noteVersionNumber = noteVersionNumber;
	}
	public String getPrimaryEntityType() {
		return primaryEntityType;
	}
	public void setPrimaryEntityType(String primaryEntityType) {
		this.primaryEntityType = primaryEntityType;
	}
	public int getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getSecondaryEntityType() {
		return secondaryEntityType;
	}
	public void setSecondaryEntityType(String secondaryEntityType) {
		this.secondaryEntityType = secondaryEntityType;
	}
	public int getSecondaryId() {
		return secondaryId;
	}
	public void setSecondaryId(int secondaryId) {
		this.secondaryId = secondaryId;
	}
    /**
     * @return Returns the primaryEntityName.
     */
    public String getPrimaryEntityName() {
        return primaryEntityName;
    }
    /**
     * @param primaryEntityName The primaryEntityName to set.
     */
    public void setPrimaryEntityName(String primaryEntityName) {
        this.primaryEntityName = primaryEntityName;
    }
    /**
     * @return Returns the businessDomainList.
     */
    public List getBusinessDomainList() {
        return businessDomainList;
    }
    /**
     * @param businessDomainList The businessDomainList to set.
     */
    public void setBusinessDomainList(List businessDomainList) {
        this.businessDomainList = businessDomainList;
    }
    /**
     * @return Returns the prodStructVersionNumber.
     */
    public int getProdStructVersionNumber() {
        return prodStructVersionNumber;
    }
    /**
     * @param prodStructVersionNumber The prodStructVersionNumber to set.
     */
    public void setProdStructVersionNumber(int prodStructVersionNumber) {
        this.prodStructVersionNumber = prodStructVersionNumber;
    }
   
    /**
     * @return Returns the action.
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action The action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }
}
