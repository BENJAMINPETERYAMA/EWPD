/*
 * Created on Aug 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.tierdefinition.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u20656
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteLevelFromTierResponse extends WPDResponse {

	private boolean statusFlag;

	public boolean isStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(boolean statusFlag) {
		this.statusFlag = statusFlag;
	}
	
	
}
