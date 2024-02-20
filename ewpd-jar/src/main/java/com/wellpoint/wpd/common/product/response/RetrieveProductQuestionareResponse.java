/*
 * Created on Jun 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveProductQuestionareResponse extends WPDResponse{
	
	private List questionareList;

	/**
	 * @return Returns the questionareList.
	 */
	public List getQuestionareList() {
		return questionareList;
	}
	/**
	 * @param questionareList The questionareList to set.
	 */
	public void setQuestionareList(List questionareList) {
		this.questionareList = questionareList;
	}
}
