/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.datatype.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTypeResponse extends WPDResponse{
	private List dataTypesList;
	/**
	 * @return Returns the dataTypesList.
	 */
	public List getDataTypesList() {
		return dataTypesList;
	}
	/**
	 * @param dataTypesList The dataTypesList to set.
	 */
	public void setDataTypesList(List dataTypesList) {
		this.dataTypesList = dataTypesList;
	}
}
