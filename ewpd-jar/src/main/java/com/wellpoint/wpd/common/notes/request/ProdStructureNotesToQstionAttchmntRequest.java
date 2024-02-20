/*
 * Created on June 01, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProdStructureNotesToQstionAttchmntRequest extends WPDRequest {

	private int questionId ;
	
	private String primaryEntityID ;
	
	private String primaryType ;
	
	private int benefitComponentId ;
	
	private String  benefitDenId ;
	
	private String secondaryId;
	
	private String searchString;
	private String requestType;
	
	private int searchAction;
	
	
	
	private int benefitAdminId;
	
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	
	
    
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the benefitDenId.
	 */
	public String getBenefitDenId() {
		return benefitDenId;
	}
	/**
	 * @param benefitDenId The benefitDenId to set.
	 */
	public void setBenefitDenId(String benefitDenId) {
		this.benefitDenId = benefitDenId;
	}
	/**
	 * @return Returns the primaryEntityID.
	 */
	public String getPrimaryEntityID() {
		return primaryEntityID;
	}
	/**
	 * @param primaryEntityID The primaryEntityID to set.
	 */
	public void setPrimaryEntityID(String primaryEntityID) {
		this.primaryEntityID = primaryEntityID;
	}
	/**
	 * @return Returns the primaryType.
	 */
	public String getPrimaryType() {
		return primaryType;
	}
	/**
	 * @param primaryType The primaryType to set.
	 */
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
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
	 * @return Returns the secondaryId.
	 */
	public String getSecondaryId() {
		return secondaryId;
	}
	/**
	 * @param secondaryId The secondaryId to set.
	 */
	public void setSecondaryId(String secondaryId) {
		this.secondaryId = secondaryId;
	}
	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	/**
	 * @return Returns the searchAction.
	 */
	public int getSearchAction() {
		return searchAction;
	}
	/**
	 * @param searchAction The searchAction to set.
	 */
	public void setSearchAction(int searchAction) {
		this.searchAction = searchAction;
	}
    /**
     * @return Returns the requestType.
     */
    public String getRequestType() {
        return requestType;
    }
    /**
     * @param requestType The requestType to set.
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
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
}
