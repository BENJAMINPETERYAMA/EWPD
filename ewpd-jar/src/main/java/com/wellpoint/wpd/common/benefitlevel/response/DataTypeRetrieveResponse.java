/*
 * Created on Mar 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author U12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTypeRetrieveResponse extends WPDResponse{
	
	private List dataTypeList;
	/**
	 * @return Returns the dataTypeList.
	 */
	public List getDataTypeList() {
		return dataTypeList;
	}
	/**
	 * @param dataTypeList The dataTypeList to set.
	 */
	public void setDataTypeList(List dataTypeList) {
		this.dataTypeList = dataTypeList;
	}
}
