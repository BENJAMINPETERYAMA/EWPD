/*
 * Created on Mar 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.refdata.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author u13154
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefDataResponse extends WPDResponse{

	private List list;
	/**
	 * 
	 */
	public RefDataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return Returns the list.
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList(List list) {
		this.list = list;
	}



}
