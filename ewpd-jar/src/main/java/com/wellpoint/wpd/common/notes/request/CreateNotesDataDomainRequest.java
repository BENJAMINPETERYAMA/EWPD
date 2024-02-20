/*
 * Created on May 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateNotesDataDomainRequest extends WPDRequest{
	
	private String noteId;
	
	private String noteName;
	
	private List referenceList;
	
	private List productList;
	
	private List benefitList;
	
	private List benefitComponentList;
	
	private List termList;

	private List qualifierList;
	
	private int version = -1;
	
	private String state;
	
	private List questionList;
	
	/**
	 * @return Returns the qualifierList.
	 */
	public List getQualifierList() {
		return qualifierList;
	}
	/**
	 * @param qualifierList The qualifierList to set.
	 */
	public void setQualifierList(List qualifierList) {
		this.qualifierList = qualifierList;
	}
	/**
	 * @return Returns the termList.
	 */
	public List getTermList() {
		return termList;
	}
	/**
	 * @param termList The termList to set.
	 */
	public void setTermList(List termList) {
		this.termList = termList;
	}
	private boolean createFlag;

	/**
	 * @return Returns the benefitComponentList.
	 */
	public List getBenefitComponentList() {
		return benefitComponentList;
	}
	/**
	 * @param benefitComponentList The benefitComponentList to set.
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}
	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	/**
	 * @return Returns the productList.
	 */
	public List getProductList() {
		return productList;
	}
	/**
	 * @param productList The productList to set.
	 */
	public void setProductList(List productList) {
		this.productList = productList;
	}
	/**
	 * @return Returns the referenceList.
	 */
	public List getReferenceList() {
		return referenceList;
	}
	/**
	 * @param referenceList The referenceList to set.
	 */
	public void setReferenceList(List referenceList) {
		this.referenceList = referenceList;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	/**
	 * @return Returns the createFlag.
	 */
	public boolean isCreateFlag() {
		return createFlag;
	}
	/**
	 * @param createFlag The createFlag to set.
	 */
	public void setCreateFlag(boolean createFlag) {
		this.createFlag = createFlag;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}
	/**
	 * @param noteName The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
    /**
     * @return Returns the questionList.
     */
    public List getQuestionList() {
        return questionList;
    }
    /**
     * @param questionList The questionList to set.
     */
    public void setQuestionList(List questionList) {
        this.questionList = questionList;
    }
}
