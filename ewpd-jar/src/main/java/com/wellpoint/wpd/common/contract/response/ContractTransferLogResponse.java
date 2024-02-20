/*
 * Created on Sep 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.web.contract.ContractTransferLogBO;
/**
 * @author U19129
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractTransferLogResponse extends WPDResponse{
	
	private List contractTransferLogList;
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
	
	
	/**
	 * @return Returns the contractTransferLogList.
	 */
	public List getContractTransferLogList() {
		return contractTransferLogList;
	}
	/**
	 * @param contractTransferLogList The contractTransferLogList to set.
	 */
	public void setContractTransferLogList(List contractTransferLogList) {
		this.contractTransferLogList = contractTransferLogList;
	}
}
