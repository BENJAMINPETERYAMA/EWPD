/*
 * Created on Oct 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.Response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QuestionAnswerLookupResponse extends WPDResponse {
	private List QuesAnswerList;
	private boolean recordsGrtThanMaxSize; 

	
	/**
	 * @return Returns the quesAnswerList.
	 */
	public List getQuesAnswerList() {
		return QuesAnswerList;
	}
	/**
	 * @param quesAnswerList The quesAnswerList to set.
	 */
	public void setQuesAnswerList(List quesAnswerList) {
		QuesAnswerList = quesAnswerList;
	}
	/**
	 * @return Returns the recordsGrtThanMaxSize.
	 */
	public boolean isRecordsGrtThanMaxSize() {
		return recordsGrtThanMaxSize;
	}
	/**
	 * @param recordsGrtThanMaxSize The recordsGrtThanMaxSize to set.
	 */
	public void setRecordsGrtThanMaxSize(boolean recordsGrtThanMaxSize) {
		this.recordsGrtThanMaxSize = recordsGrtThanMaxSize;
	}
	private String sortOrder;
	/**
	 * @return Returns the sortOrder.
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder The sortOrder to set.
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}

