/*
 * Created on Jun 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.indicativemapping.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeMappingBO;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateIndicativeMappingResponse extends WPDResponse {
	
	private List searchList;
	
	private IndicativeMappingBO indicativeMappingBO;
	
	private boolean success;
	
	

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
	/**
	 * @return Returns the indicativeMappingBO.
	 */
	public IndicativeMappingBO getIndicativeMappingBO() {
		return indicativeMappingBO;
	}
	/**
	 * @param indicativeMappingBO The indicativeMappingBO to set.
	 */
	public void setIndicativeMappingBO(IndicativeMappingBO indicativeMappingBO) {
		this.indicativeMappingBO = indicativeMappingBO;
	}
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
