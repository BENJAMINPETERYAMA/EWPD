package com.wellpoint.ets.ebx.systemconfiguration.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.systemconfiguration.application.SystemConfigurationFacade;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;


public class SystemConfigurationController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(SystemConfigurationController.class);
	private SystemConfigurationFacade systemConfigurationFacade;
	
	public SystemConfigurationFacade getSystemConfigurationFacade() {
		return systemConfigurationFacade;
	}



	public void setSystemConfigurationFacade(
			SystemConfigurationFacade systemConfigurationFacade) {
		this.systemConfigurationFacade = systemConfigurationFacade;
	}
	
	/**
	 * This method gets invoked when user tries to view all configured systems
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws EBXException
	 */
	public ModelAndView getAllSystemConfigurations(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		ModelAndView modelAndView = null;	
		List<SystemConfigurationVO> systemConfigurationVOList = null;		
		try{
			SystemConfigurationVO systemConfigurationVO = null;
			modelAndView = new ModelAndView("viewSystemConfigurations");
			systemConfigurationVO = new SystemConfigurationVO();
			systemConfigurationVOList = systemConfigurationFacade.getAllSystemConfigurations();
			Map< String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("systemConfigurationList", systemConfigurationVOList);
			modelAndView.addAllObjects(dataMap);
			//modelAndView.addObject("systemConfigurationList",systemConfigurationVOList);
		}		
		catch (Exception exception) {
			if(exception instanceof EBXException) {
				throw ((EBXException)exception);
			}else{
				logger.error("Unexpected exception caught at method getAllSystemConfigurations in " +
				"SystemConfigurationController class");				
				String message = "Unexpected Error";
				String displayMessage = "Unexpected Error. Contact System Administrator";
				String displayDescription = "Unexpected Error. Contact System Administrator";
				throw new EBXException(message,exception,displayMessage,displayDescription);
			}
		}
		return modelAndView;
	}
	
	/**
	 * This method gets invoked when User tries to add a new system configuration
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws EBXException
	 */
	public ModelAndView addSystemConfigurations(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		ModelAndView modelAndView = null;
		SystemConfigurationVO systemConfigurationVO = null;
		try{
			modelAndView = new ModelAndView("jsonView");
			systemConfigurationVO = new SystemConfigurationVO();
			String system = (String)request.getParameter("system");
			String functionality = (String)request.getParameter("functionality");
			String environment = (String)request.getParameter("env");
			String backEndRegion = (String)request.getParameter("bckEndRegion");
			String backEndRegionDescription = (String)request.getParameter("bckEndRegionDesc");
			String senderID = (String)request.getParameter("senderID");
			String sourceEnvironment = (String)request.getParameter("srcEnv");
			String destinationEnvironment = (String)request.getParameter("destEnv");
			
			systemConfigurationVO.setSystem(system.trim());
			systemConfigurationVO.setFunctionality(functionality.trim());
			systemConfigurationVO.setEnvironment(environment.trim());
			systemConfigurationVO.setBackEndRegion(backEndRegion.trim());
			systemConfigurationVO.setBackEndRegionDescription(backEndRegionDescription.trim());
			systemConfigurationVO.setSenderID(senderID.trim());
			systemConfigurationVO.setSourceEnvironment(sourceEnvironment.trim());
			systemConfigurationVO.setDestinationEnvironment(destinationEnvironment.trim());
			int numberOFUpdatedRows = systemConfigurationFacade.addSystemConfiguration(systemConfigurationVO);
			if(numberOFUpdatedRows == 1){
				modelAndView.addObject("updatestatus", "inserted");
				List<String> infoMessageList = new ArrayList<String>();
				infoMessageList.add("System Configuration added successfully");
				modelAndView.addObject("info_messages", infoMessageList);
				List<SystemConfigurationVO> systemConfigurationVOList = systemConfigurationFacade.getAllSystemConfigurations();
				JSONArray configurationAsJsonArray = JSONArray.fromObject(systemConfigurationVOList);
				modelAndView.addObject("configurationAsJsonArray", configurationAsJsonArray);
			}			
		}catch (Exception exception) {
			if(exception instanceof EBXException) {
				List<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(exception.getMessage());
				modelAndView.addObject("error_messages", errorMessageList);
				modelAndView.addObject("updatestatus", "validationfailed");				
				//throw ((EBXException)exception);
			}else{
				logger.error("Unexpected exception caught at method addSystemConfigurations in " +
						"SystemConfigurationController class");
				String message = "Unexpected Error";
				String displayMessage = "Unexpected Error. Contact System Administrator";
				String displayDescription = "Unexpected Error. Contact System Administrator";
				throw new EBXException(message,exception,displayMessage,displayDescription);
			}
		}	
		return modelAndView;
	}
	
	/**
	 * This method gets invoked when User tries to edit a system configuration
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws EBXException
	 */
	public ModelAndView editSystemConfiguration(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		ModelAndView modelAndView = null;
		SystemConfigurationVO systemConfigurationVO = null;
		try{
			modelAndView = new ModelAndView("jsonView");
			systemConfigurationVO = new SystemConfigurationVO();
			
			String systemConfigurationID = (String)request.getParameter("id");
			String system = (String)request.getParameter("system");
			String functionality = (String)request.getParameter("functionality");
			String environment = (String)request.getParameter("env");
			String backEndRegion = (String)request.getParameter("bckEndRegion");
			String backEndRegionDescription = (String)request.getParameter("bckEndRegionDesc");
			String senderID = (String)request.getParameter("senderID");
			String sourceEnvironment = (String)request.getParameter("srcEnv");
			String destinationEnvironment = (String)request.getParameter("destEnv");
			systemConfigurationVO.setSystemConfigurationID(systemConfigurationID.trim());
			systemConfigurationVO.setSystem(system.trim());
			systemConfigurationVO.setFunctionality(functionality.trim());
			systemConfigurationVO.setEnvironment(environment.trim());
			systemConfigurationVO.setBackEndRegion(backEndRegion.trim());
			systemConfigurationVO.setBackEndRegionDescription(backEndRegionDescription.trim());
			systemConfigurationVO.setSenderID(senderID.trim());
			systemConfigurationVO.setSourceEnvironment(sourceEnvironment.trim());
			systemConfigurationVO.setDestinationEnvironment(destinationEnvironment.trim());
			int numberOFUpdatedRows = systemConfigurationFacade.editSystemConfiguration(systemConfigurationVO);			
			if(numberOFUpdatedRows == 1){
				modelAndView.addObject("updatestatus", "updated");
				List<String> infoMessageList = new ArrayList<String>();
				infoMessageList.add("System Configuration updated successfully");
				modelAndView.addObject("info_messages", infoMessageList);
				List<SystemConfigurationVO> systemConfigurationVOList = systemConfigurationFacade.getAllSystemConfigurations();
				JSONArray configurationAsJsonArray = JSONArray.fromObject(systemConfigurationVOList);
				modelAndView.addObject("configurationAsJsonArray", configurationAsJsonArray);
			}
			
		}catch (Exception exception) {
			if(exception instanceof EBXException) {
				List<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(exception.getMessage());
				modelAndView.addObject("error_messages", errorMessageList);
				modelAndView.addObject("updatestatus", "validationfailed");
				//return modelAndView;
				//throw ((EBXException)exception);
			}else{
				logger.error("Unexpected exception caught at method editSystemConfiguration in " +
						"SystemConfigurationController class");
				String message = "Unexpected Error";
				String displayMessage = "Unexpected Error. Contact System Administrator";
				String displayDescription = "Unexpected Error. Contact System Administrator";
				throw new EBXException(message,exception,displayMessage,displayDescription);
			}
		}
		return modelAndView;		
		
	}
	
	/**
	 * This method gets invoked when User tries to delete a system configuration
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws EBXException 
	 */
	public ModelAndView deleteSystemConfiguration(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		ModelAndView modelAndView = null;
		try{
			modelAndView = new ModelAndView("jsonView");
			String systemConfigurationID = (String)request.getParameter("id");
			int numberOFUpdatedRows = systemConfigurationFacade.deleteSystemConfiguration(
					Integer.parseInt(systemConfigurationID));	
			if(numberOFUpdatedRows == 1){
				modelAndView.addObject("updatestatus", "deleted");
				List<String> infoMessageList = new ArrayList<String>();
				infoMessageList.add("System Configuration deleted successfully");
				modelAndView.addObject("info_messages", infoMessageList);
				List<SystemConfigurationVO> systemConfigurationVOList = systemConfigurationFacade.getAllSystemConfigurations();
				JSONArray configurationAsJsonArray = JSONArray.fromObject(systemConfigurationVOList);
				modelAndView.addObject("configurationAsJsonArray", configurationAsJsonArray);
			}			
		}
		catch (Exception exception) {
			if(exception instanceof EBXException) {
				throw ((EBXException)exception);
			}else{
				logger.error("Unexpected exception caught at method deleteSystemConfiguration in " +
						"SystemConfigurationController class");
				String message = "Unexpected Error";
				String displayMessage = "Unexpected Error. Contact System Administrator";
				String displayDescription = "Unexpected Error. Contact System Administrator";
				throw new EBXException(message,exception,displayMessage,displayDescription);
			}
		}
		return modelAndView;
	}	
	
}

