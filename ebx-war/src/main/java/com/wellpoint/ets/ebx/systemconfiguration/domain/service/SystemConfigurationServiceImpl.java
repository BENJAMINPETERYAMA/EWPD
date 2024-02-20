package com.wellpoint.ets.ebx.systemconfiguration.domain.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.systemconfiguration.repository.ConfigurationInfoRepository;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public class SystemConfigurationServiceImpl implements SystemConfigurationService {
	
	private static Logger logger = Logger.getLogger(SystemConfigurationServiceImpl.class);
	private ConfigurationInfoRepository configurationInfoRepository;

	/**
	 * 
	 * @return ConfigurationInfoRepository
	 */
	public ConfigurationInfoRepository getConfigurationInfoRepository() {
		return configurationInfoRepository;
	}

	/**
	 * 
	 * @param configurationInfoRepository the ConfigurationInfoRepository to set
	 */
	public void setConfigurationInfoRepository(
			ConfigurationInfoRepository configurationInfoRepository) {
		this.configurationInfoRepository = configurationInfoRepository;
	}

	@Override
	public List<SystemConfigurationVO> getAllSystemConfigurations() throws EBXException {
		List<SystemConfigurationVO> systemConfigurationVOList = null;
		try{
			systemConfigurationVOList = new ArrayList<SystemConfigurationVO>();
			systemConfigurationVOList = configurationInfoRepository.getAllSystemConfigurations();
		}
		catch(SQLException sqlException){
			String message = "Unexpected DataBase Error";
			String displayMessage = "Unexpected DataBase Error. Contact System Administrator";
			String displayDescription = "Unexpected DataBase Error. Contact System Administrator";
			throw new EBXException(message,sqlException,displayMessage,displayDescription);	
		}
		catch(Exception exception){
			logger.error("Unexpected exception caught at method getAllSystemConfigurations in " +
			"SystemConfigurationServiceImpl class");			
			String message = "Unexpected Error";
			String displayMessage = "Unexpected Error. Contact System Administrator";
			String displayDescription = "Unexpected Error. Contact System Administrator";
			throw new EBXException(message,exception,displayMessage,displayDescription);	
		}		
		return systemConfigurationVOList;
	}

	@Override
	public int addSystemConfiguration(
			SystemConfigurationVO systemConfigurationVO) throws EBXException {		
		int numberOfUpdatedRows = 0;
		boolean configurationAlreadyExists = false;
		try{
			String backEndRegion = systemConfigurationVO.getBackEndRegion();
			List<SystemConfigurationVO> systemConfigurationVOList = configurationInfoRepository.
				checkIfSystemConfigurationAlreadyExists(systemConfigurationVO);
			if(systemConfigurationVOList !=null && systemConfigurationVOList.size() > 0){
				configurationAlreadyExists = true;
			}
			if(configurationAlreadyExists){
				if(backEndRegion == null || backEndRegion.trim().equals("")){
					throw new EBXException("Back End Region is mandatory");
				}
				throw new EBXException("Configuration already exists");
			}
			numberOfUpdatedRows = configurationInfoRepository.addSystemConfiguration(systemConfigurationVO);
			return numberOfUpdatedRows;
		}
		catch(SQLException sqlException){
			String message = "Unexpected DataBase Error";
			String displayMessage = "Unexpected DataBase Error. Contact System Administrator";
			String displayDescription = "Unexpected DataBase Error. Contact System Administrator";
			throw new EBXException(message,sqlException,displayMessage,displayDescription);			
		}			
	}

	@Override
	public int editSystemConfiguration(
			SystemConfigurationVO systemConfigurationVO) throws EBXException {
		int numberOfUpdatedRows = 0;
		boolean configurationAlreadyExists = false;
		try{
			String backEndRegion = systemConfigurationVO.getBackEndRegion();
			List<SystemConfigurationVO> systemConfigurationVOList = configurationInfoRepository.
				checkIfSystemConfigurationAlreadyExists(systemConfigurationVO);
			Iterator<SystemConfigurationVO> configurationListIterator = systemConfigurationVOList.iterator();
			while (configurationListIterator.hasNext()) {
				SystemConfigurationVO systemConfigurationVOFromDB = 
					configurationListIterator.next();
				if((systemConfigurationVO.getSystemConfigurationID())
						.equals(systemConfigurationVOFromDB.getSystemConfigurationID())){
					continue;
				}else{
					configurationAlreadyExists = true;
					break;
				}
			}
			if(configurationAlreadyExists){
				if(backEndRegion == null || backEndRegion.trim().equals("")){
					throw new EBXException("Back End Region is mandatory");
				}
				throw new EBXException("Configuration already exists");				
			}
			
			numberOfUpdatedRows = configurationInfoRepository.editSystemConfiguration(systemConfigurationVO);
		}
		catch(SQLException sqlException){			
			String message = "Unexpected DataBase Error";
			String displayMessage = "Unexpected DataBase Error. Contact System Administrator";
			String displayDescription = "Unexpected DataBase Error. Contact System Administrator";
			throw new EBXException(message,sqlException,displayMessage,displayDescription);
		}		
		return numberOfUpdatedRows;
	}
	
	

	@Override
	public int deleteSystemConfiguration(int systemConfigurationID) throws EBXException {
		int numberOfUpdatedRows = 0;
		try{
			numberOfUpdatedRows = configurationInfoRepository.deleteSystemConfiguration(systemConfigurationID);			
		}
		catch(SQLException sqlException){
			String message = "Unexpected DataBase Error";
			String displayMessage = "Unexpected DataBase Error. Contact System Administrator";
			String displayDescription = "Unexpected DataBase Error. Contact System Administrator";
			throw new EBXException(message,sqlException,displayMessage,displayDescription);			
		}
		catch(Exception exception){
			logger.error("Unexpected exception caught at method deleteSystemConfiguration in " +
			"SystemConfigurationServiceImpl class");			
			String message = "Unexpected Error";
			String displayMessage = "Unexpected Error. Contact System Administrator";
			String displayDescription = "Unexpected Error. Contact System Administrator";
			throw new EBXException(message,exception,displayMessage,displayDescription);	
		}		
		return numberOfUpdatedRows;
	}

}
