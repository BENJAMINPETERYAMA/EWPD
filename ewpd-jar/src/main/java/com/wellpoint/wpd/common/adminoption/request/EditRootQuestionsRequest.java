/*
 * EditRootQuestionsRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminoption.request;

import java.util.List;

import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EditRootQuestionsRequest extends WPDRequest{
	
	private List rootQuestionsList;
	
	private AdminOptionBO adminOptionBO;
	
	private String questionnaireToDelete;
	
	private boolean deleteFlag = false;
	/**
	 * @return Returns the adminOptionBO.
	 */
	public AdminOptionBO getAdminOptionBO() {
		return adminOptionBO;
	}
	/**
	 * @param adminOptionBO The adminOptionBO to set.
	 */
	public void setAdminOptionBO(AdminOptionBO adminOptionBO) {
		this.adminOptionBO = adminOptionBO;
	}
	 /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }
	/**
	 * @return Returns the rootQuestionsList.
	 */
	public List getRootQuestionsList() {
		return rootQuestionsList;
	}
	/**
	 * @param rootQuestionsList The rootQuestionsList to set.
	 */
	public void setRootQuestionsList(List rootQuestionsList) {
		this.rootQuestionsList = rootQuestionsList;
	}
	/**
	 * @return Returns the questionnaireToDelete.
	 */
	public String getQuestionnaireToDelete() {
		return questionnaireToDelete;
	}
	/**
	 * @param questionnaireToDelete The questionnaireToDelete to set.
	 */
	public void setQuestionnaireToDelete(String questionnaireToDelete) {
		this.questionnaireToDelete = questionnaireToDelete;
	}
	
	/**
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
