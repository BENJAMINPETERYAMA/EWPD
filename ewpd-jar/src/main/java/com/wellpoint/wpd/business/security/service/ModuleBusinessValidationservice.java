/*
 * ModuleBusinessValidationservice.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.security.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.security.builder.ModuleBusinessObjectBuilder;
import com.wellpoint.wpd.business.security.locatecriteria.ModuleLocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigSrdaBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.request.DeleteModuleTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.ModuleDeleteRequest;
import com.wellpoint.wpd.common.security.request.SaveModuleRequest;
import com.wellpoint.wpd.common.security.response.DeleteModuleTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.ModuleDeleteResponse;
import com.wellpoint.wpd.common.security.response.SaveModuleResponse;
import com.wellpoint.wpd.common.security.vo.ModuleVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleBusinessValidationservice extends
        WPDBusinessValidationService {
    /**
     * Validation Service to validate whether module with same name exists
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveModuleRequest request)
            throws ServiceException {
        // Create the response object from the response factory
        SaveModuleResponse response = (SaveModuleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_MODULE_RESPONSE);
        //Sets the value from request to VO
        ModuleVO moduleVO = request.getModuleVO();
        //Sets the value from VO to BO
        ModuleSrdaBO moduleSrdaBO = getSecurityBusinessObjectSrda(moduleVO);
        ModuleBO moduleBO = getSecurityBusinessObject(moduleVO);
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        List messageList = new ArrayList(1);
        try {
            //Checks whether the module with the same name exists only
            //the create flag is true
            if ((builder.isModuleDuplicate(moduleBO) || builder.isModuleDuplicateSrda(moduleSrdaBO)) && (request.isCreateFlag())) {
                //Sets success flag as false if duplicate exists
                response.setSuccess(false);
                messageList.add(new ErrorMessage("module.name.duplicate"));
                response.setMessages(messageList);
            } else {
                //sets the success flag as true if duplicate does not exists
                response.setSuccess(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing Save Task Request";
            throw new ServiceException(logMessage, logParameters, e);
        }

        return response;
    }

    /**
     * Method to check whether module comfigured to any role before deleting it
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(ModuleDeleteRequest request)
            throws ServiceException {
        Logger
                .logInfo("ModuleBusinessValidationService - Entering execute():  Module Delete");
        ModuleDeleteResponse moduleDeleteResponse = null;
        ModuleBO moduleBO = getModuleObject(request);
        ModuleSrdaBO moduleSrdaBO = getModuleSrdaObject(request);
        // Create the response object from the response factory
        moduleDeleteResponse = (ModuleDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_MODULE_RESPONSE);
        List messageList = new ArrayList(2);
        try {
            //method for checking if role is configured with the module
            if (isRoleConfiguredForModule(moduleBO)||isRoleConfiguredForModuleSrda(moduleSrdaBO)) {
                messageList.add(new ErrorMessage("module.role.association"));
                moduleDeleteResponse.setModuleRole(false);
            } else {
                moduleDeleteResponse.setModuleRole(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing ModuleDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        try {
            //method for checking if task is configured with the module
            if (isTaskModuleAssociated(moduleBO)
                    || isSubTaskAsociatedToModule(moduleBO) || isTaskModuleAssociatedSrda(moduleSrdaBO)) {
                messageList.add(new ErrorMessage("module.task.association"));
                moduleDeleteResponse.setModuleTask(false);
            } else {
                moduleDeleteResponse.setModuleTask(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing ModuleDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        moduleDeleteResponse.setMessages(messageList);
        Logger
                .logInfo("ModuleBusinessValidationService - Returning execute(): Module Delete");
        return moduleDeleteResponse;
    }
    
    public WPDResponse execute(DeleteModuleTaskAssociationRequest request) throws ServiceException{
        DeleteModuleTaskAssociationResponse response  = (DeleteModuleTaskAssociationResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.DELETE_MODULE_ASSOCIATION_RESPONSE);
      
        try{
        	
        	if(request.getSrdaFlag() != null && request.getSrdaFlag().equalsIgnoreCase("SRDA")) {  
        		ModuleConfigSrdaBO moduleConfigSrdaBO = new ModuleConfigSrdaBO();
	            // moduleConfigBO.setTaskId(request.getTaskId());
        		moduleConfigSrdaBO.setTaskIdList(request.getTaskIdList());
        		moduleConfigSrdaBO.setModuleId(request.getModuleId());
	        	    if(isTaskRoleAssociated(moduleConfigSrdaBO,"true"))
                    response.setSuccess(false);
                else
                    response.setSuccess(true);
        	}else{
        		  ModuleConfigBO moduleConfigBO = new ModuleConfigBO();
        	       // moduleConfigBO.setTaskId(request.getTaskId());
        	        moduleConfigBO.setTaskIdList(request.getTaskIdList());
        	        moduleConfigBO.setModuleId(request.getModuleId());
        	    if(isTaskRoleAssociated(moduleConfigBO,"true"))
                    response.setSuccess(false);
                else
                    response.setSuccess(true);
        	}
        
        }catch(SevereException e){
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteModuleTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    private boolean isTaskRoleAssociated(ModuleConfigBO configBO,String flag)throws SevereException{
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        boolean result = false; 
        ModuleLocateCriteria moduleLocateCriteria = new ModuleLocateCriteria();
        //moduleLocateCriteria.setAssociatedTaskId(configBO.getTaskId());
        moduleLocateCriteria.setTaskList(configBO.getTaskIdList());
        moduleLocateCriteria.setModuleId(configBO.getModuleId());
        
        try {
			result = builder.locateTaskAssociation(configBO);
        }catch(SevereException e){
            throw new ServiceException(null, null, e);
        }
        return result;
    }

    private boolean isTaskRoleAssociated(ModuleConfigSrdaBO configSrdaBO,String flag)throws SevereException{
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        boolean result = false; 
        ModuleLocateCriteria moduleLocateCriteria = new ModuleLocateCriteria();
        //moduleLocateCriteria.setAssociatedTaskId(configBO.getTaskId());
        moduleLocateCriteria.setTaskList(configSrdaBO.getTaskIdList());
        moduleLocateCriteria.setModuleId(configSrdaBO.getModuleId());
        
        try {
			result = builder.locateTaskAssociation(configSrdaBO);
        }catch(SevereException e){
            throw new ServiceException(null, null, e);
        }
        return result;
    }
    /**
     * Validates whether the task is associated with any module
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    private boolean isSubTaskAsociatedToModule(ModuleBO moduleBO)
            throws SevereException {
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        ModuleLocateCriteria moduleLocateCriteria = new ModuleLocateCriteria();
        moduleLocateCriteria.setModuleId(moduleBO.getModuleId());
        moduleLocateCriteria.setAssociatedTaskId(0);
        List locateResults = null;

        try {
			locateResults = builder
			        .locateSubTaskAsociatedToModule(moduleLocateCriteria);
        }catch(SevereException e){
            throw new ServiceException(null, null, e);
        }

        //if locateResults is not null then 'true' is returned
        if (null != locateResults && !locateResults.isEmpty()) {
            return true;
        }
        //if locateResults is null then 'true' is returned
        return false;
        //return builder.isSubTaskAsociatedToModule(moduleBO);
    }

    /**
     * Method to check whether role is configured with the module
     * 
     * @param moduleBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isRoleConfiguredForModule(ModuleBO moduleBO)
            throws SevereException {
        ModuleBusinessObjectBuilder moduleBusinessObjectBuilder = new ModuleBusinessObjectBuilder();
        ModuleLocateCriteria moduleLocateCriteria = new ModuleLocateCriteria();
        moduleLocateCriteria.setModuleId(moduleBO.getModuleId());
        moduleLocateCriteria.setSrdaFlag(moduleBO.getSrdaFlag());
        LocateResults locateResults = null;

        try {
			locateResults = moduleBusinessObjectBuilder
			        .locateRoleCofiguration(moduleLocateCriteria);
        }catch(SevereException e){
            throw new ServiceException(null, null, e);
        }

        //if locateResults is not null then 'true' is returned
        if (null != locateResults && null != locateResults.getLocateResults()
                && !locateResults.getLocateResults().isEmpty()) {
            return true;
        }
        //if locateResults is null then 'false' is returned
        return false;
    }
    
    /**
     * Method to check whether role is configured with the module
     * 
     * @param moduleSrdaBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isRoleConfiguredForModuleSrda(ModuleSrdaBO moduleSrdaBO)
            throws SevereException {
        ModuleBusinessObjectBuilder moduleBusinessObjectBuilder = new ModuleBusinessObjectBuilder();
        ModuleLocateCriteria moduleLocateCriteria = new ModuleLocateCriteria();
        moduleLocateCriteria.setModuleId(moduleSrdaBO.getModuleId());
        moduleLocateCriteria.setSrdaFlag(moduleSrdaBO.getSrdaFlag());
        LocateResults locateResults = null;

        try {
			locateResults = moduleBusinessObjectBuilder
			        .locateRoleCofiguration(moduleLocateCriteria);
        }catch(SevereException e){
            throw new ServiceException(null, null, e);
        }

        //if locateResults is not null then 'true' is returned
        if (null != locateResults && null != locateResults.getLocateResults()
                && !locateResults.getLocateResults().isEmpty()) {
            return true;
        }
        //if locateResults is null then 'true' is returned
        return false;
    }

    /**
     * Method to check whether task is configured with the module
     * 
     * @param moduleBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isTaskModuleAssociated(ModuleBO moduleBO)
            throws SevereException {
        ModuleBusinessObjectBuilder moduleBusinessObjectBuilder = new ModuleBusinessObjectBuilder();
        ModuleLocateCriteria moduleLocateCriteria = new ModuleLocateCriteria();
        moduleLocateCriteria.setModuleId(moduleBO.getModuleId());
        moduleLocateCriteria.setSrdaFlag(moduleBO.getSrdaFlag());
        LocateResults locateResults = null;

        try {
			locateResults = moduleBusinessObjectBuilder
			        .locateTaskModuleCofiguration(moduleLocateCriteria);
        }catch(SevereException e){
            throw new ServiceException(null, null, e);
        }

        //if locateResults is not null then 'true' is returned
        if (null != locateResults && null != locateResults.getLocateResults()
                && !locateResults.getLocateResults().isEmpty()) {
            return true;
        }
        //if locateResults is null then 'true' is returned
        return false;
    }
    
    /**
     * Method to check whether task is configured with the module
     * 
     * @param moduleSrdaBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isTaskModuleAssociatedSrda( ModuleSrdaBO moduleSrdaBO)
            throws SevereException {
        ModuleBusinessObjectBuilder moduleBusinessObjectBuilder = new ModuleBusinessObjectBuilder();
        ModuleLocateCriteria moduleLocateCriteria = new ModuleLocateCriteria();
        moduleLocateCriteria.setSrdaFlag(moduleSrdaBO.getSrdaFlag());
        moduleLocateCriteria.setModuleId(moduleSrdaBO.getModuleId());
        
        LocateResults locateResults = null;

        try {
			locateResults = moduleBusinessObjectBuilder
			        .locateTaskModuleCofiguration(moduleLocateCriteria);
        }catch(SevereException e){
            throw new ServiceException(null, null, e);
        }

        //if locateResults is not null then 'true' is returned
        if (null != locateResults && null != locateResults.getLocateResults()
                && !locateResults.getLocateResults().isEmpty()) {
            return true;
        }
        //if locateResults is null then 'true' is returned
        return false;
    }

    /**
     * Method to copy the ModuleVO to ModuleBO
     * 
     * @param moduleVO
     * @return
     */
    private ModuleBO getSecurityBusinessObject(ModuleVO moduleVO) {
        ModuleBO moduleBO = new ModuleBO();
        moduleBO.setModuleName(moduleVO.getModuleName());
        moduleBO.setDescription(moduleVO.getDescription());
        return moduleBO;
    }
    
    /**
     * Method to copy the ModuleVO to ModuleSrdaBO
     * 
     * @param moduleVO
     * @return
     */
    private ModuleSrdaBO getSecurityBusinessObjectSrda(ModuleVO moduleVO) {
    	ModuleSrdaBO moduleSrdaBO = new ModuleSrdaBO();
    	moduleSrdaBO.setModuleName(moduleVO.getModuleName());
    	moduleSrdaBO.setDescription(moduleVO.getDescription());
        return moduleSrdaBO;
    }

    /**
     * Method to get the ModuleBO from ModuleDeleteRequest
     * 
     * @param request
     * @return ModuleBO
     */
    private ModuleBO getModuleObject(ModuleDeleteRequest request) {
        ModuleBO moduleBO = new ModuleBO();
        ModuleLocateCriteria moduleCriteria = (ModuleLocateCriteria)request.getModuleLocateCriteria();
        ModuleVO moduleVO = (ModuleVO) request.getModuleVO();
        moduleBO.setModuleId(moduleVO.getSelectedModuleId());
        moduleBO.setSrdaFlag(moduleCriteria.getSrdaFlag());
        return moduleBO;

    }
    
    /**
     * Method to get the ModuleBO from ModuleDeleteRequest
     * 
     * @param request
     * @return ModuleSrdaBO
     */
    private ModuleSrdaBO getModuleSrdaObject(ModuleDeleteRequest request) {
    	ModuleSrdaBO moduleSrdaBO = new ModuleSrdaBO();
        ModuleVO moduleVO = (ModuleVO) request.getModuleVO();
        ModuleLocateCriteria moduleCriteria = (ModuleLocateCriteria)request.getModuleLocateCriteria();
        moduleSrdaBO.setModuleId(moduleVO.getSelectedModuleId());
        moduleSrdaBO.setSrdaFlag(moduleCriteria.getSrdaFlag());
        return moduleSrdaBO;

    }
}