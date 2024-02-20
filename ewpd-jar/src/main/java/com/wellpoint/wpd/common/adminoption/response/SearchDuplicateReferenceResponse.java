/*
 * Created on Jul 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U14609
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchDuplicateReferenceResponse extends WPDResponse{
	
	private List resultList;

	/**
	 * @return Returns the resultList.
	 */
	public List getResultList() {
		return resultList;
	}
	/**
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}
