/*
 * Created on Aug 14, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.tierdefinition.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u20776
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductTierDefSaveResponse extends WPDResponse{

	private boolean statusFlag;
	
	/**
	 * @return Returns the statusFlag.
	 */
	public boolean isStatusFlag() {
		return statusFlag;
	}
	/**
	 * @param statusFlag The statusFlag to set.
	 */
	public void setStatusFlag(boolean statusFlag) {
		this.statusFlag = statusFlag;
	}
}
