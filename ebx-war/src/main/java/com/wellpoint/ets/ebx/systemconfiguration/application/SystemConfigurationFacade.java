package com.wellpoint.ets.ebx.systemconfiguration.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public interface SystemConfigurationFacade {
	
	/**
	 * This method retrieves all configured systems from DB and gets it 
	 * @param systemConfigurationVO
	 * @return List<SystemConfigurationVO>
	 * @throws EBXException
	 */
	public List<SystemConfigurationVO> getAllSystemConfigurations()throws EBXException;

	/**
	 * This method lets IT user to add a new system configured
	 * @param systemConfigurationVO
	 * @return int
	 * @throws EBXException
	 */
	public int addSystemConfiguration(SystemConfigurationVO systemConfigurationVO)throws EBXException;

	/**
	 * This method lets IT user to edit a configured system
	 * @param systemConfigurationVO
	 * @return int
	 * @throws EBXException
	 */
	public int editSystemConfiguration(SystemConfigurationVO systemConfigurationVO)throws EBXException;

	/**
	 * This method lets IT user to delete a configured system from DB
	 * @param systemConfigurationID
	 * @return int
	 * @throws EBXException
	 */
	public int deleteSystemConfiguration(int systemConfigurationID)throws EBXException;

}
