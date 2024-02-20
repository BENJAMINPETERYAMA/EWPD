/**
 * 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;

/**
 * @author U30262
 *
 */
public class ContractWebServiceResponse extends ContractResponse {
	private List<ContractWebServiceVO> contractWSErrorList = null;
	List<EbxWebSerDisplayVO> wSErrorDisplayList = null;
	
	String wsProcessError;

	/**
	 * @return the contractWSErrorList
	 */
	public List<ContractWebServiceVO> getContractWSErrorList() {
		return contractWSErrorList;
	}

	/**
	 * @param contractWSErrorList the contractWSErrorList to set
	 */
	public void setContractWSErrorList(
			List<ContractWebServiceVO> contractWSErrorList) {
		this.contractWSErrorList = contractWSErrorList;
	}

	public List<EbxWebSerDisplayVO> getWSErrorDisplayList() {
		return wSErrorDisplayList;
	}

	public void setWSErrorDisplayList(List<EbxWebSerDisplayVO> errorDisplayList) {
		wSErrorDisplayList = errorDisplayList;
	}

	public String getWsProcessError() {
		return wsProcessError;
	}

	public void setWsProcessError(String wsProcessError) {
		this.wsProcessError = wsProcessError;
	}
	
	
}
