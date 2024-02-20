/*
 * Created on Nov 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.ebx.simulation.domain.service;

import java.util.List;

import javax.jms.JMSException;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;



/**
 * @author U17810
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SimulationService {

    public String get27xHIPAABX(HIPAA270BXVO  hipaa27xBX,String environment, String responseFormat, String senderID) throws EBXException, JMSException;

	public boolean is4010Exists() throws EBXException, Exception; 
    
    /**
     * This method gets the back end region description details based on functionality, environment,back end region
     * @param systemConfigurationVO
     * @return
     */
	public List<SystemConfigurationVO> getBackEndRegionDescription(SystemConfigurationVO systemConfigurationVO)throws EBXException;

	/**
	 * This function loads back end region for a particular combination of functionality,
	 * system and environment
	 * @param functionality
	 * @param system
	 * @param environment
	 */	
	public List<SystemConfigurationVO> loadBackEndRegionBasedOnSystem(String functionality, String system,
			String environment) throws EBXException;; 
    
}
