/*
 * Created on Jun 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteQuestionnaireRequest extends WPDRequest{
	
	private List questionnaireIds;
	
	private int questionnaireId;
	
	private int adminoptionId;
	
	private String adminName;
	
	private int version;
	
	private boolean isRootQuestion = false;
	

	/**
	 * @return Returns the adminoptionId.
	 */
	public int getAdminoptionId() {
		return adminoptionId;
	}
	/**
	 * @param adminoptionId The adminoptionId to set.
	 */
	public void setAdminoptionId(int adminoptionId) {
		this.adminoptionId = adminoptionId;
	}
	/**
	 * @return Returns the questionnaireId.
	 */
	public int getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * @param questionnaireId The questionnaireId to set.
	 */
	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * @return Returns the questionnaireIds.
	 */
	public List getQuestionnaireIds() {
		return questionnaireIds;
	}
	/**
	 * @param questionnaireIds The questionnaireIds to set.
	 */
	public void setQuestionnaireIds(List questionnaireIds) {
		this.questionnaireIds = questionnaireIds;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

    /**
     * @return Returns the adminName.
     */
    public String getAdminName() {
        return adminName;
    }
    /**
     * @param adminName The adminName to set.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
	 * @return Returns the isRootQuestion.
	 */
	public boolean isRootQuestion() {
		return isRootQuestion;
	}
	/**
	 * @param isRootQuestion The isRootQuestion to set.
	 */
	public void setRootQuestion(boolean isRootQuestion) {
		this.isRootQuestion = isRootQuestion;
	}
}
