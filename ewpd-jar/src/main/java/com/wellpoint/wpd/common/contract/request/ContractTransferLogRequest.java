/*
 * Created on Sep 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.web.contract.ContractTransferLogBO;

/**
 * @author U19129
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractTransferLogRequest extends WPDRequest{

	ContractTransferLogBO contractTransferLogBO = new ContractTransferLogBO();
	
	/**
	 * @return Returns the contractTransferLogBO.
	 */
	public ContractTransferLogBO getContractTransferLogBO() {
		return contractTransferLogBO;
	}
	/**
	 * @param contractTransferLogBO The contractTransferLogBO to set.
	 */
	public void setContractTransferLogBO(
			ContractTransferLogBO contractTransferLogBO) {
		this.contractTransferLogBO = contractTransferLogBO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
