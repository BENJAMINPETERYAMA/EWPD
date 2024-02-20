/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmaintain.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodSearchResponse extends WPDResponse {
	
	private List searchList=new ArrayList();
	

	/**
	 * @return Returns the searchList.
	 */
	public List getSearchList() {
		return searchList;
	}
	/**
	 * @param searchList The searchList to set.
	 */
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}
}
