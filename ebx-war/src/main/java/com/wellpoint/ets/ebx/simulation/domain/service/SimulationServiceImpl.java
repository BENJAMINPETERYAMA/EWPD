/*
 * Created on Nov 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.ebx.simulation.domain.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.ebx.simulation.repository.EWPDContractInfoRepositoryImpl;
import com.wellpoint.ets.ebx.simulation.util.SimulationHelper;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.systemconfiguration.repository.ConfigurationInfoRepository;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

/**
 * @author U17810
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SimulationServiceImpl implements SimulationService {
	private MQAdapter mqAdapter;

	private static Logger log = Logger.getLogger(SimulationServiceImpl.class);
	private EWPDContractInfoRepositoryImpl ewpdContractInfoRepository;
	private ConfigurationInfoRepository configurationInfoRepository;

	public ConfigurationInfoRepository getConfigurationInfoRepository() {
		return configurationInfoRepository;
	}

	public void setConfigurationInfoRepository(
			ConfigurationInfoRepository configurationInfoRepository) {
		this.configurationInfoRepository = configurationInfoRepository;
	}

	public String get27xHIPAABX(HIPAA270BXVO hipaa27xBX, String environment, String responseFormat, String senderID)
			throws EBXException, JMSException {
		long time;
		boolean productionFlag = false;
		SimulationHelper helper = new SimulationHelper();
		// create request
		String x12Request = helper
				.get27xHIPAABXRequest(hipaa27xBX, environment, responseFormat, senderID);		
		log.info("Request is :" +ESAPI.encoder().encodeForHTML(x12Request));

		if (DomainConstants.PRODUCTION.equals(environment)) {
			productionFlag = true;
		} else {
			productionFlag = false;
		}

		// invoke service
		time = System.currentTimeMillis();
		String id = mqAdapter.putX12Request(x12Request, productionFlag);
		String x12Response = mqAdapter.getX12Response(id, productionFlag);
		time = System.currentTimeMillis() - time;
		log.info(" The MQ turn around time for X12 Information is " + time
				+ " milliseconds.");
		log.debug("Response is :" + x12Response);

		if (x12Response == null) {
			log.error("NullPointerException: ");
			throw new NullPointerException("Request timed out");
		}
		// if (x12Response == null) {
		// throw new EBXException("Null Pointer Exception.");
		// }
		// processresponse
		String formattedResponse = helper.format27xHIPAABXResponse(x12Response);
		log.info("formattedResponse is :" + formattedResponse);		
		
		return formattedResponse;
	}

	public MQAdapter getMqAdapter() {
		return mqAdapter;
	}

	public void setMqAdapter(MQAdapter mqAdapter) {
		this.mqAdapter = mqAdapter;
	}

	public boolean is4010Exists() throws Exception {
		return 	ewpdContractInfoRepository.is4010Exists();
	}
	
	public EWPDContractInfoRepositoryImpl getEwpdContractInfoRepository() {
		return ewpdContractInfoRepository;
	}

	public void setEwpdContractInfoRepository(
			EWPDContractInfoRepositoryImpl ewpdContractInfoRepository) {
		this.ewpdContractInfoRepository = ewpdContractInfoRepository;
	}

	@Override
	public List<SystemConfigurationVO> getBackEndRegionDescription(
			SystemConfigurationVO systemConfigurationVO) throws EBXException {
		List<SystemConfigurationVO> systemConfigurationVOList = new ArrayList<SystemConfigurationVO>();
		try{
			systemConfigurationVOList = configurationInfoRepository.getBackEndRegionDescription(systemConfigurationVO);
			return systemConfigurationVOList;
			
		}catch(SQLException sqlException){
			String message = "Unexpected DataBase Error";
			String displayMessage = "Unexpected DataBase Error. Contact System Administrator";
			String displayDescription = "Unexpected DataBase Error. Contact System Administrator";
			throw new EBXException(message,sqlException,displayMessage,displayDescription);	
		}	
	}

	@Override
	public List<SystemConfigurationVO> loadBackEndRegionBasedOnSystem(String functionality, String system,
			String environment) throws EBXException {
		List<SystemConfigurationVO> systemConfigurationVOList = new ArrayList<SystemConfigurationVO>();		
		try{
			
			systemConfigurationVOList = configurationInfoRepository.loadBackEndRegionBasedOnSystem(functionality,system,environment);
			return systemConfigurationVOList;
			
		}catch(SQLException sqlException){
			String message = "Unexpected DataBase Error";
			String displayMessage = "Unexpected DataBase Error. Contact System Administrator";
			String displayDescription = "Unexpected DataBase Error. Contact System Administrator";
			throw new EBXException(message,sqlException,displayMessage,displayDescription);	
		}
	}
}
