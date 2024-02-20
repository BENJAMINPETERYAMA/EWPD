/*
 * RetrieveQuestionaireResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveQuestionaireResponse extends WPDResponse {

	List selectedQuestionaireList;
	
	/**
	 * @return Returns the selectedQuestionaireList.
	 */
	public List getSelectedQuestionaireList() {
		return selectedQuestionaireList;
	}
	/**
	 * @param selectedQuestionaireList The selectedQuestionaireList to set.
	 */
	public void setSelectedQuestionaireList(List selectedQuestionaireList) {
		this.selectedQuestionaireList = selectedQuestionaireList;
	}
}
