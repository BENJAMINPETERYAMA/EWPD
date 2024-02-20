package com.wellpoint.wpd.common.tierdefinition.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

public class ProductTierDeleteResponse extends WPDResponse{

	private boolean statusFlag;

	public boolean isStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(boolean statusFlag) {
		this.statusFlag = statusFlag;
	}
	
}
