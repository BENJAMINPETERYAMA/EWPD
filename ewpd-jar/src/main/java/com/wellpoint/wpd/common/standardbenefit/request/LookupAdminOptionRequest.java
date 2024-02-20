/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.AdministrationOptionVO;


/**
 * @author u13664
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LookupAdminOptionRequest extends WPDRequest {
	
	private int maxSearchResultSize;
	
	private AdministrationOptionVO administrationOptionVO;
	
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return Returns the maxSearchResultSize.
	 */
	public int getMaxSearchResultSize() {
		return maxSearchResultSize;
	}
	/**
	 * @param maxSearchResultSize The maxSearchResultSize to set.
	 */
	public void setMaxSearchResultSize(int maxSearchResultSize) {
		this.maxSearchResultSize = maxSearchResultSize;
	}
	/**
	 * @return Returns the administrationOptionVO.
	 */
	public AdministrationOptionVO getAdministrationOptionVO() {
		return administrationOptionVO;
	}
	/**
	 * @param administrationOptionVO The administrationOptionVO to set.
	 */
	public void setAdministrationOptionVO(
			AdministrationOptionVO administrationOptionVO) {
		this.administrationOptionVO = administrationOptionVO;
	}
}
