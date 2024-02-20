package com.wellpoint.ets.ebx.systemconfiguration.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.systemconfiguration.domain.service.SystemConfigurationService;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public class SystemConfigurationFacadeImpl implements SystemConfigurationFacade {
	
	private SystemConfigurationService systemConfigurationService;	

	/**
	 * 
	 * @return SystemConfigurationService
	 */
	public SystemConfigurationService getSystemConfigurationService() {
		return systemConfigurationService;
	}

	/**
	 * 
	 * @param systemConfigurationService the SystemConfigurationService type to set
	 */
	public void setSystemConfigurationService(
			SystemConfigurationService systemConfigurationService) {
		this.systemConfigurationService = systemConfigurationService;
	}



	@Override
	public List<SystemConfigurationVO> getAllSystemConfigurations() throws EBXException {
		return systemConfigurationService.getAllSystemConfigurations();
	}

	@Override
	public int addSystemConfiguration(
			SystemConfigurationVO systemConfigurationVO) throws EBXException {
		return systemConfigurationService.addSystemConfiguration(systemConfigurationVO);
		
	}

	@Override
	public int editSystemConfiguration(
			SystemConfigurationVO systemConfigurationVO) throws EBXException {
		return systemConfigurationService.editSystemConfiguration(systemConfigurationVO);
		
	}

	@Override
	public int deleteSystemConfiguration(int systemConfigurationID) throws EBXException {
		return systemConfigurationService.deleteSystemConfiguration(systemConfigurationID);
	}

}
