/*
 * Created on Jun 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteQuestionnaireResponse extends WPDResponse{
	
	private int questionnaireId;

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
}
