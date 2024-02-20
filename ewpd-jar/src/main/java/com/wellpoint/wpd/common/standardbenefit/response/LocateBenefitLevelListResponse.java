/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateBenefitLevelListResponse extends WPDResponse{
		List BenefitlevelList;
	

	/**
	 * @return Returns the benefitlevelList.
	 */
	public List getBenefitlevelList() {
		return BenefitlevelList;
	}
	/**
	 * @param benefitlevelList The benefitlevelList to set.
	 */
	public void setBenefitlevelList(List benefitlevelList) {
		BenefitlevelList = benefitlevelList;
	}
}
