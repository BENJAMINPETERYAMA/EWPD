package com.wellpoint.ets.ebx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.domain.service.ContractService;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public class ContractBxReportFacadeImpl implements ContractBxReportFacade {

	private ContractService contractService;

	/**
	 * @param contract 
	 * @param 
	 * @return 
	 * @exception 
	 *
	 */
	public List getReportInfo(ContractVO contract,String environment, boolean eBxReportFlag,SystemConfigurationVO systemConfigurationVO) throws EBXException, Exception{
		List contractObj = null;
		contractObj =  contractService.getContractInfo(contract,environment, eBxReportFlag,systemConfigurationVO);
		return contractObj;
	}
	@Override
	public String getLatestVersion(String contractId) {
		return contractService.getLatestVersion(contractId);
	}
	
	@Override
	public List<String> getStartDates(String system, String contractId, String enviornment) {
		return contractService.getStartDates(system, contractId,enviornment);
	}
	
	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
}


