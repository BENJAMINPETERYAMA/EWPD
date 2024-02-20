package com.wellpoint.ets.ebx.systemconfiguration.repository;

import java.sql.SQLException;
import java.util.List;

import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public interface ConfigurationInfoRepository {
	
	/**
	 * This method retrieves all configured systems from DB and gets it 
	 * @param systemConfigurationVO
	 * @return List<SystemConfigurationVO>
	 * @throws SQLException
	 */
	List<SystemConfigurationVO> getAllSystemConfigurations() throws SQLException;

	/**
	 * This method lets IT user to add a new system configured
	 * @param systemConfigurationVO
	 * @return int
	 * @throws SQLException
	 */
	int addSystemConfiguration(
			SystemConfigurationVO systemConfigurationVO)throws SQLException;

	/**
	 * This method lets IT user to edit a configured system
	 * @param systemConfigurationVO
	 * @return int
	 * @throws SQLException
	 */
	int editSystemConfiguration(
			SystemConfigurationVO systemConfigurationVO) throws SQLException;

	/**
	 * This method lets IT user to delete a configured system from DB
	 * @param systemConfigurationID
	 * @return int
	 * @throws SQLException
	 */
	int deleteSystemConfiguration(
			int systemConfigurationID) throws SQLException;

	/**
	 * This method gets the backend description region details based on functionality, environment,backend region
	 * @param systemConfigurationVO
	 * @return String
	 * @throws SQLException
	 */
	List<SystemConfigurationVO> getBackEndRegionDescription(
			SystemConfigurationVO systemConfigurationVO) throws SQLException;
	
	
	/**
	 * This method checks if a System configuration is already done for a particular combination of System,
	 * Functionality, Environment and BackEndRegion
	 * @param systemConfigurationVO
	 * @return
	 * @throws SQLException
	 */
	List<SystemConfigurationVO> checkIfSystemConfigurationAlreadyExists(
			SystemConfigurationVO systemConfigurationVO) throws SQLException;

	/**
	 * This function loads back end region for a particular combination of functionality,
	 * system and environment
	 * @param functionality
	 * @param system
	 * @param environment
	 */	
	List<SystemConfigurationVO> loadBackEndRegionBasedOnSystem(String functionality,
			String system, String environment) throws SQLException;	

}
