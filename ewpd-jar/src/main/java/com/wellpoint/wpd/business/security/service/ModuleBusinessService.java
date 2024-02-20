/*
 * ModuleBusinessService.java
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

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.security.builder.ModuleBusinessObjectBuilder;
import com.wellpoint.wpd.business.security.locatecriteria.ModuleLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigSrdaBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
import com.wellpoint.wpd.common.security.request.DeleteModuleTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.ModuleDeleteRequest;
import com.wellpoint.wpd.common.security.request.ModuleSearchRequest;
import com.wellpoint.wpd.common.security.request.RetrieveModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveModuleRequest;
import com.wellpoint.wpd.common.security.request.SaveModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.SaveModuleRequest;
import com.wellpoint.wpd.common.security.request.TaskLookUpRequest;
import com.wellpoint.wpd.common.security.response.DeleteModuleTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.ModuleDeleteResponse;
import com.wellpoint.wpd.common.security.response.ModuleSearchResponse;
import com.wellpoint.wpd.common.security.response.RetrieveModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveModuleResponse;
import com.wellpoint.wpd.common.security.response.TaskLookUpResponse;
import com.wellpoint.wpd.common.security.vo.ModuleVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleBusinessService extends WPDService {

    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }

    /**
     * Method for saving and updating a module
     * 
     * @param request
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(SaveModuleRequest request)
            throws ServiceException {
        // Create the response object from the response factory
        SaveModuleResponse response = (SaveModuleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_MODULE_RESPONSE);
        // Create an instance of the builder
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        // Create a list to store all the messages
        List messages = new ArrayList(1);
        try {
            //Validates whether the module with same name already exists
            response = (SaveModuleResponse) new ValidationServiceController()
                    .execute(request);
            ModuleBO moduleBO = null;
            ModuleSrdaBO moduleSrdaBO = null;
            if (response.isSuccess()) {
                //If the response is success copy request to BO 
            
            		 moduleSrdaBO =copyValueObjectToBusinessObjectSrda(request
                             .getModuleVO());   
            
            		 moduleBO = copyValueObjectToBusinessObject(request
                             .getModuleVO());
            	
                //If create flag is true create a new module
                if (request.isCreateFlag()) {
                    builder.persist(moduleBO, request.getUser(), true);
                    moduleBO = builder.retrieve(moduleBO);
                    response.setModuleBO(moduleBO);
                    messages.add(new InformationalMessage(
                            "module.save.success.info"));
                }
                //If create flag is false update the existing module
                if (!request.isCreateFlag()) {
                	if(null != request.getSrdaFlag() && request.getSrdaFlag().equalsIgnoreCase("SRDA") ){
                		builder.persist(moduleSrdaBO, request.getUser(), false);
                		moduleSrdaBO = builder.retrieve(moduleSrdaBO);
	                    response.setModuleSrdaBO(moduleSrdaBO);
	                    messages.add(new InformationalMessage(
	                            "module.update.success.info"));	
                	}else{
                		builder.persist(moduleBO, request.getUser(), false);
                		moduleBO = builder.retrieve(moduleBO);
	                    response.setModuleBO(moduleBO);
	                    messages.add(new InformationalMessage(
	                            "module.update.success.info"));		
                	}
                }
            } else
                messages.add(new ErrorMessage("module.name.duplicate"));
            response.setMessages(messages);
        } catch (SevereException e) {
            List logParameters = new ArrayList(2);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveModuleRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        // Return the response
        return response;
    }

    private ModuleSrdaBO copyValueObjectToBusinessObjectSrda(ModuleVO moduleVO) {
		// TODO Auto-generated method stub
    	 ModuleSrdaBO moduleBO = new ModuleSrdaBO();

         moduleBO.setModuleName(moduleVO.getModuleName());
         moduleBO.setDescription(moduleVO.getDescription());
         moduleBO.setModuleId(moduleVO.getModuleId());

         return moduleBO;
	}

	/**
     * Method to search the modules based on the search criteria entered
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(ModuleSearchRequest request)
            throws ServiceException {

        // Create a list to store all the messages
        List messageList = new ArrayList(1);

        // Create the response object from the response factory
        ModuleSearchResponse moduleSearchResponse = (ModuleSearchResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SEARCH_MODULE_RESPONSE);

        ModuleVO moduleVO = request.getModuleVO();
        // Set the values to the locate criteria
        ModuleLocateCriteria moduleLocateCriteria = getModuleLocateCriteria(moduleVO);

        moduleLocateCriteria.setSrdaFlag(request.getSrdaFlag());
        // Create an instance of the builder
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();

        // Call the builder locate() to get the result list
        List moduleList = null;
        try {
            moduleList = builder.locate(moduleLocateCriteria);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing ModuleSearchRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        // Check whether the list is null or empty
        if (null != moduleList && !moduleList.isEmpty()) {

            // If no, set the list to the response
            moduleSearchResponse.setModuleList(moduleList);
        } else {
            // If yes, set the message to the response
            messageList.add(new InformationalMessage(
                    BusinessConstants.SEARCH_RESULT_NOT_FOUND));
            moduleSearchResponse.setMessages(messageList);
        }
        // Return the response
        return moduleSearchResponse;
    }

    /**
     * Execute method for SubCatalogDeleteRequest
     * 
     * @param request
     * @return
     * @throws SevereException
     */

    public WPDResponse execute(ModuleDeleteRequest request)
            throws ServiceException {
        ModuleDeleteResponse moduleDeleteResponse = null;
		// Create an instance of the builder
        ModuleBusinessObjectBuilder moduleBusinessObjectBuilder = null;
		//Sets the values from request to BO
        ModuleBO moduleBO = null;
        ModuleSrdaBO moduleSrdaBO = null;
		try {
			if(request.getModuleLocateCriteria().getSrdaFlag().equalsIgnoreCase("SRDA")){
			Logger
			        .logInfo("ModuleBusinessService - Entering execute(): Module Delete");
			moduleDeleteResponse = null;
			List locateResultList = null;
			moduleBusinessObjectBuilder = new ModuleBusinessObjectBuilder();
			moduleSrdaBO = getModuleObjectFromModuleVOSrda(request);
			// Create the response object from the response factory
			moduleDeleteResponse = (ModuleDeleteResponse) WPDResponseFactory
			        .getResponse(WPDResponseFactory.DELETE_MODULE_RESPONSE);
			//Validates whether the module is associated 
			moduleDeleteResponse = (ModuleDeleteResponse) new ValidationServiceController()
			        .execute(request);
			}else{
				Logger
		        .logInfo("ModuleBusinessService - Entering execute(): Module Delete");
		moduleDeleteResponse = null;
		List locateResultList = null;
		moduleBusinessObjectBuilder = new ModuleBusinessObjectBuilder();
		moduleBO = getModuleObjectFromModuleVO(request);
		// Create the response object from the response factory
		moduleDeleteResponse = (ModuleDeleteResponse) WPDResponseFactory
		        .getResponse(WPDResponseFactory.DELETE_MODULE_RESPONSE);
		//Validates whether the module is associated 
		moduleDeleteResponse = (ModuleDeleteResponse) new ValidationServiceController()
		        .execute(request);
			}
		} catch (ServiceException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        //If the module is no associated with any role and task
        //call the builder delete()
        if ((moduleDeleteResponse.isModuleRole())
                && (moduleDeleteResponse.isModuleTask())) {
        	try {
        	if(request.getModuleLocateCriteria().getSrdaFlag().equalsIgnoreCase("SRDA")){
        		
                    moduleBusinessObjectBuilder.delete(moduleSrdaBO, request.getUser());
        		}
        		else{
        		
                    moduleBusinessObjectBuilder.delete(moduleBO, request.getUser());
            	}
        	} catch (SevereException e) {
                List logParameters = new ArrayList(1);
                logParameters.add(request);
                String logMessage = "Failed while processing ModuleDeleteRequest";
                throw new ServiceException(logMessage, logParameters, e);
            }
            moduleDeleteResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_MODULE_DELETE_SUCCESS));

        }
        List locateResults = null;
        ModuleVO moduleVO = request.getModuleVO();
        //for getting the search results after deletion
        try {
            ModuleLocateCriteria moduleLocateCriteria = (ModuleLocateCriteria) getModuleLocateCriteria(moduleVO);
            moduleLocateCriteria.setSrdaFlag(request.getModuleLocateCriteria().getSrdaFlag());
            locateResults = moduleBusinessObjectBuilder
                    .locate(moduleLocateCriteria);
            //Sets the locateresults to response if list is not empty
            if (null != locateResults && !locateResults.isEmpty()) {
                moduleDeleteResponse.setModuleList(locateResults);
            }

        } catch (SevereException e2) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing ModuleDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e2);
        }

        Logger
                .logInfo("ModuleBusinessService - Returning execute(): Module Delete");
        // Return the response
        return moduleDeleteResponse;
    }

    /**
     * Method to return the ModuleLocateCriteria
     */
    private ModuleLocateCriteria getModuleLocateCriteria(ModuleVO moduleVO) {
        //Create an instance of the locate criteria
        ModuleLocateCriteria locateCriteria = new ModuleLocateCriteria();
        locateCriteria.setModuleId(new Integer(moduleVO.getModuleId())
                .intValue());
        // Set the values from the VO to the locateCriteria
        if (null != moduleVO.getModuleName()) {
            locateCriteria.setModuleName(moduleVO.getModuleName());
        }
        if (null != moduleVO.getTaskIdList()) {
            locateCriteria.setTaskList(moduleVO.getTaskIdList());
        }
        return locateCriteria;
    }

    /**
     * Method to get ModuleBO for ModuleDeleteRequest
     * 
     * @param request
     * @return
     */
    private ModuleBO getModuleObjectFromModuleVO(ModuleDeleteRequest request) {
        ModuleBO moduleBO = new ModuleBO();
        ModuleVO moduleVO = (ModuleVO) request.getModuleVO();
        moduleBO.setModuleId(moduleVO.getSelectedModuleId());
        return moduleBO;
    }
    
    /**
     * Method to get ModuleSrdaBO for ModuleDeleteRequest
     * 
     * @param request
     * @return
     */
    private ModuleSrdaBO getModuleObjectFromModuleVOSrda(ModuleDeleteRequest request) {
        ModuleSrdaBO moduleSrdaBO = new ModuleSrdaBO();
        ModuleVO moduleVO = (ModuleVO) request.getModuleVO();
        moduleSrdaBO.setModuleId(moduleVO.getSelectedModuleId());
        return moduleSrdaBO;
    }


    /**
     * Method for locating the tasks which are not associated with the module
     * 
     * @param request
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(TaskLookUpRequest request)
            throws ServiceException, AdapterException {
        // Create the response object from the response factory
        TaskLookUpResponse response = (TaskLookUpResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.MODULE_TASK_LOOKUP_RESPONSE);
        // Create an instance of the builder
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        List locateResults = null;
        //Sets the value from request to BO
        
        //Calls the builder locate()
        try {
        	if(request.getSrdaFlag() != null && request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
        		taskSrdaBO.setModuleId(request.getModuleId());
        		 locateResults = builder.locateTaskSrda(taskSrdaBO, request.getUser());
             	
                 //If the locateresults is not empty sets the list to response
                 if (null != locateResults && !locateResults.isEmpty()) {
                     response.setTaskList(locateResults);
                 }
        	}else{
        		TaskBO taskBO = new TaskBO();
        		  taskBO.setModuleId(request.getModuleId());
        		  locateResults = builder.locateTask(taskBO, request.getUser());
        	
        		  //If the locateresults is not empty sets the list to response
        		  if (null != locateResults && !locateResults.isEmpty()) {
        			  response.setTaskList(locateResults);
        		  }
        		}
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskLookUpRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        // Return the response
        return response;
    }

    /**
     * Method for retrieving the associated tasks
     * 
     * @param request
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(RetrieveModuleAssociationRequest request)
            throws ServiceException {
        // Create the response object from the response factory
        RetrieveModuleAssociationResponse response = (RetrieveModuleAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.MODULE_ASSOCIATION_RESPONSE);
        // Create an instance of the builder
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        TaskBO taskBO = new TaskBO();
        TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
        List locateResults = null;
        //Sets the module_id to BO
        taskBO.setModuleId(request.getModuleId());
        taskSrdaBO.setModuleId(request.getModuleId());
       
        // Get the subEntityType from the request
        String subEntityType = request.getSubEntityType();
        try {

            if (null != subEntityType && !subEntityType.equals("")) {
                // If subEntityType is task
                if (subEntityType.equals("Task")) {
                	
		
					if(request.getSrdaFlag() != null && request.getSrdaFlag().equalsIgnoreCase("SRDA")){
                    locateResults = builder.locateModuleAssociationSrda(taskSrdaBO);
                	}else{
                		  locateResults = builder.locateModuleAssociation(taskBO);
                	}
                } else if (subEntityType.equals("SubTask")) {

                    // Set the values from the request in SubTaskBO
                    ModuleLocateCriteria locateCriteria = new ModuleLocateCriteria();

                    locateCriteria.setModuleId(request.getModuleId());
                    locateCriteria.setAssociatedTaskId(request
                            .getAssociatedTaskId());

                    locateResults = builder
                            .locateSubTaskAsociatedToModule(locateCriteria);
                }
                response.setModuleAssociationList(locateResults);
            }

        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrievModuleAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        // Return the response
        return response;
    }

    /**
     * to retrieve the module information for loading in the edit
     * 
     * @param RetrieveModuleRequest
     * @return response
     */
    public WPDResponse execute(RetrieveModuleRequest request)
            throws ServiceException {
        // Create the response object from the response factory
        RetrieveModuleAssociationResponse response = (RetrieveModuleAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.MODULE_ASSOCIATION_RESPONSE);
        // Create an instance of the builder
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        //Sets the values from request to VO
        ModuleVO moduleVO = request.getModuleVO();
        //Sets the values from VO to BO
        ModuleBO moduleBO = new ModuleBO();
        ModuleSrdaBO moduleSrdaBO = new ModuleSrdaBO();
        moduleBO.setModuleId(moduleVO.getModuleId());
        moduleSrdaBO.setModuleId(moduleVO.getModuleId());
        //Call the builder retrieve()
        if(request.getSrdaFlag() != null && request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        	   try {
                builder.retrieve(moduleSrdaBO);
                response.setModuleSrdaBO(moduleSrdaBO);
               } catch (SevereException e) {
                List logParameters = new ArrayList(1);
                logParameters.add(request);
                String logMessage = "Failed while processing RetrieveModuleRequest";
                throw new ServiceException(logMessage, logParameters, e);
            }
        }else{
             try {
            builder.retrieve(moduleBO);
            response.setModuleBO(moduleBO);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrieveModuleRequest";
            throw new ServiceException(logMessage, logParameters, e);
          } 
        }
        // Return the response
        return response;
    }

    /**
     * Method to create the selected task association with the module
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveModuleAssociationRequest request)
            throws ServiceException {
        // Create the response object from the response factory
        SaveModuleAssociationResponse response = (SaveModuleAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_MODULE_ASSOCIATION_RESPONSE);
        // Create an instance of the builder
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        TaskBO taskBO = new TaskBO();
        TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
        List locateResults = null;
        // Create a list to store all the messages
        List messages = new ArrayList(1);

        int moduleId = request.getModuleVO().getModuleId();
        List taskList = request.getModuleVO().getTaskIdList();
    
        try {
            //If the module_id list is not empty call builder persist()
            if (null != taskList && !taskList.isEmpty()) {
            	if(request.getSrdaFlag() != null && request.getSrdaFlag().equalsIgnoreCase("SRDA")){
            			taskSrdaBO.setModuleId(moduleId);
	            		builder.persistSrda(taskList, request.getUser(), moduleId);
	                    messages.add(new InformationalMessage(
	                            "module.associate.success.info"));
	                    response.setMessages(messages);
	                
	                    //Retrieve the associated tasks after saving association
	                    locateResults = builder.locateModuleAssociationSrda(taskSrdaBO);
	                    //Set the locateresult to the response
	                    response.setTaskList(locateResults);
            	}else{
            			taskBO.setModuleId(moduleId);
		                builder.persist(taskList, request.getUser(), moduleId);
		                messages.add(new InformationalMessage(
		                        "module.associate.success.info"));
		                response.setMessages(messages);
		            
		                //Retrieve the associated tasks after saving association
		                locateResults = builder.locateModuleAssociation(taskBO);
		                //Set the locateresult to the response
		                response.setTaskList(locateResults);
            	}
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveModuleAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        // Return the response
        return response;
    }

    /**
     * Method for deleting the task association of a module
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteModuleTaskAssociationRequest request)
            throws ServiceException {
        // Create the response object from the response factory
        DeleteModuleTaskAssociationResponse response = (DeleteModuleTaskAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_MODULE_ASSOCIATION_RESPONSE);
        // Create an instance of the builder
        ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
        List locateResults = null;
        
        // Create a list to store all the messages
        List messages = new ArrayList(1);
        try {
			response = (DeleteModuleTaskAssociationResponse) new ValidationServiceController()
			.execute(request);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteModuleTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        //Call the buider delete()
        try {
        	if(request.getSrdaFlag() != null && request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		 TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
        		 taskSrdaBO.setModuleId(request.getModuleId());
        	     taskSrdaBO.setTaskId(request.getTaskId());
        	     ModuleConfigSrdaBO moduleConfigSrdaBO = getModuleConfigSrdaBO(request);
        		 if(response.isSuccess()){
        			 
                     builder.deleteModuleAssociationSrda(moduleConfigSrdaBO, request.getUser());
                     messages.add(new InformationalMessage(
                     "module.associate.delete.success.info"));
                 }else
                     messages.add(new ErrorMessage("task.role.module.subtask.association"));
                 
        		 	locateResults = builder.locateModuleAssociationSrda(taskSrdaBO);
        		 	//Sets the locateresults to response if list is not empty
        		 	if (locateResults != null && !locateResults.isEmpty())
        		 		response.setTaskList(locateResults);
        		 	else
        		 		response.setTaskList(null);
        	}else{
        		  //Sets the values from request to BO
                TaskBO taskBO = new TaskBO();
                taskBO.setModuleId(request.getModuleId());
                taskBO.setTaskId(request.getTaskId());
                ModuleConfigBO moduleConfigBO = getModuleConfigBO(request);
            if(response.isSuccess()){
                builder.deleteModuleAssociation(moduleConfigBO, request.getUser());
                messages.add(new InformationalMessage(
                "module.associate.delete.success.info"));
            }else
                messages.add(new ErrorMessage("task.role.module.subtask.association"));
            
            locateResults = builder.locateModuleAssociation(taskBO);
            //Sets the locateresults to response if list is not empty
            if (locateResults != null && !locateResults.isEmpty())
                response.setTaskList(locateResults);
            else
                response.setTaskList(null);
        	}

        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteModuleTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        } 
        response.setMessages(messages);
        // Return the response
        return response;
    }

    /**
     * Method for copying the business object to the value object
     * 
     * @param moduleVO
     * @return
     */

    private ModuleBO copyValueObjectToBusinessObject(ModuleVO moduleVO) {
        ModuleBO moduleBO = new ModuleBO();

        moduleBO.setModuleName(moduleVO.getModuleName());
        moduleBO.setDescription(moduleVO.getDescription());
        moduleBO.setModuleId(moduleVO.getModuleId());

        return moduleBO;
    }

    /**
     * Method for copying values from request to business object
     * 
     * @param request
     * @return
     */
    private ModuleConfigBO getModuleConfigBO(
            DeleteModuleTaskAssociationRequest request) {
        ModuleConfigBO moduleConfigBO = new ModuleConfigBO();

        moduleConfigBO.setModuleId(request.getModuleId());
        //moduleConfigBO.setTaskId(request.getTaskId());
        moduleConfigBO.setTaskIdList(request.getTaskIdList());
        return moduleConfigBO;
    }
    /**
     * Method for copying values from request to business object
     * 
     * @param request
     * @return
     */
    private ModuleConfigSrdaBO getModuleConfigSrdaBO(
            DeleteModuleTaskAssociationRequest request) {
        ModuleConfigSrdaBO moduleConfigSrdaBO = new ModuleConfigSrdaBO();

        moduleConfigSrdaBO.setModuleId(request.getModuleId());
        //moduleConfigBO.setTaskId(request.getTaskId());
        moduleConfigSrdaBO.setTaskIdList(request.getTaskIdList());
        return moduleConfigSrdaBO;
    }
}