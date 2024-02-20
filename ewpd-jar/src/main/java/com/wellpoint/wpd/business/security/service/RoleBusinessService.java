/*
 * RoleBusinessService.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.security.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.security.builder.RoleBusinessObjectBuilder;
import com.wellpoint.wpd.business.security.locatecriteria.RoleLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.RoleImpl;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.bo.RoleBO;
import com.wellpoint.wpd.common.security.bo.RoleConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleSrdaBo;
import com.wellpoint.wpd.common.security.bo.RoleSubTaskConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleTaskConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleTaskSrdaConfigBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
import com.wellpoint.wpd.common.security.request.DeleteRoleModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.DeleteTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveRoleAssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveRoleModuleAsssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveRoleRequest;
import com.wellpoint.wpd.common.security.request.RoleDeleteRequest;
import com.wellpoint.wpd.common.security.request.RoleModuleLookUpRequest;
import com.wellpoint.wpd.common.security.request.RoleTaskLookUpRequest;
import com.wellpoint.wpd.common.security.request.SaveRoleModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.SaveRoleRequest;
import com.wellpoint.wpd.common.security.request.SaveSubTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.SaveTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.SearchRoleRequest;
import com.wellpoint.wpd.common.security.request.TaskLookUpRequest;
import com.wellpoint.wpd.common.security.response.DeleteTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleResponse;
import com.wellpoint.wpd.common.security.response.RoleDeleteResponse;
import com.wellpoint.wpd.common.security.response.RoleModuleLookUpResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleResponse;
import com.wellpoint.wpd.common.security.response.SaveSubTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.SearchRoleResponse;
import com.wellpoint.wpd.common.security.response.TaskLookUpResponse;
import com.wellpoint.wpd.common.security.vo.RoleVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleBusinessService extends WPDService {

    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }

    /**
     * Service method to create or update a role
     * 
     * @param SaveRoleRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveRoleRequest request) throws ServiceException {
        // Getting the response object from the Response Factory
        SaveRoleResponse response = (SaveRoleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_ROLE_RESPONSE);
        RoleBO roleBO = null;
        RoleSrdaBo roleSrdaBO = null;
        if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        	 roleSrdaBO = copyValueObjectToBusinessObjectSrda(request.getRoleVO());
        }else{
        	 roleBO = copyValueObjectToBusinessObject(request.getRoleVO());
        }
        
        RoleBusinessObjectBuilder roleBuilder = new RoleBusinessObjectBuilder();
        List messages = new ArrayList(1);
        try {
            //Validation to check whether the role with same identity exists
            response = (SaveRoleResponse) new ValidationServiceController()
                    .execute(request);
            if (response.isSuccess()) {
                //If the create flag is true,creates a new role
                if (request.isCreateFlag()) {
                	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
                		roleBuilder.persist(roleSrdaBO, request.getUser(), true);
                        //Retrieves the created roleBO
                		roleSrdaBO = roleBuilder.retrieve(roleSrdaBO);
                        response.setRoleSrdaBO(roleSrdaBO);
                        messages.add(new InformationalMessage(
                                "role.save.success.info"));
                	}else{
                		roleBuilder.persist(roleBO, request.getUser(), true);
                        //Retrieves the created roleBO
                        roleBO = (RoleBO) roleBuilder.retrieve(roleBO);
                        response.setRoleBO(roleBO);
                        messages.add(new InformationalMessage(
                                "role.save.success.info"));
                	}
                    
                } else if (!request.isCreateFlag()) {
                	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
                		 //If the create flag is false,updates the role
                        roleBuilder.persist(roleSrdaBO, request.getUser(), false);
                        //Retrieves the updated roleBO
                        roleSrdaBO = (RoleSrdaBo) roleBuilder.retrieve(roleSrdaBO);
                        response.setRoleSrdaBO(roleSrdaBO);
                        messages.add(new InformationalMessage(
                                "role.update.success.info"));
                	}else{
                		 //If the create flag is false,updates the role
                        roleBuilder.persist(roleBO, request.getUser(), false);
                        //Retrieves the updated roleBO
                        roleBO = (RoleBO) roleBuilder.retrieve(roleBO);
                        response.setRoleBO(roleBO);
                        messages.add(new InformationalMessage(
                                "role.update.success.info"));
                	}
                   
                }
                response.setMessages(messages);
            }

        } catch (SevereException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveRoleRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return response;
    }

    private RoleSrdaBo copyValueObjectToBusinessObjectSrda(RoleVO roleVO) {
    	RoleSrdaBo roleSrdaBO = new RoleSrdaBo();
    	roleSrdaBO.setRoleName(roleVO.getRoleName().trim().toUpperCase());
    	roleSrdaBO.setDescription(roleVO.getDescription().toUpperCase());
    	roleSrdaBO.setRoleId(roleVO.getRoleId());
        return roleSrdaBO;
	}

	/**
     * Method to retrieve the role details from the db. Also used to retrieve
     * the associated modules, tasks and subtasks of a role when the value of
     * the request action is 2.
     * 
     * @param retrieveRoleRequest
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(RetrieveRoleRequest retrieveRoleRequest)
            throws ServiceException {
        // Getting the response object from the Response Factory
        RetrieveRoleResponse retrieveRoleResponse = (RetrieveRoleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_ROLE_RESPONSE);
        RoleBusinessObjectBuilder roleBuilder = new RoleBusinessObjectBuilder();
        RoleBO roleBO = new RoleBO();
        RoleSrdaBo roleSrdaBo = new RoleSrdaBo();
        RoleImpl roleImpl = null;
        List roleList = null;
        if (retrieveRoleRequest.getRoleList() != null
                && !retrieveRoleRequest.getRoleList().isEmpty()){
        	if(retrieveRoleRequest.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		roleSrdaBo.setRoleId(((Integer) retrieveRoleRequest.getRoleList()
                        .get(0)).intValue());
        	}else{
        		 roleBO.setRoleId(((Integer) retrieveRoleRequest.getRoleList()
                         .get(0)).intValue());
        	}
           
            
        }
        try {
        	if(retrieveRoleRequest.getSrdaFlag().equalsIgnoreCase("SRDA")){


                // If action = 1, then do the normal retreive for role details
                if (retrieveRoleRequest.getAction() == 1) {
                	roleSrdaBo.setRoleId(retrieveRoleRequest.getRoleId());
                	roleSrdaBo =roleBuilder.retrieve(roleSrdaBo);
                    retrieveRoleResponse.setRoleSrdaBo(roleSrdaBo);
                    // If action == 2, then it retrieves all the associated modules,
                    // tasks and subtasks of that particular role.
                    // Done for Login authentication purpose
                } else if (retrieveRoleRequest.getAction() == 2) {

                    roleList = new ArrayList(0);
                    for (int i = 0; i < retrieveRoleRequest.getRoleList().size(); i++) {
                        roleImpl = new RoleImpl();
                        roleImpl.setId(((Integer) retrieveRoleRequest.getRoleList()
                                .get(i)).intValue());
                    }

                }
                //Sets the retrieved role_list to response
                retrieveRoleResponse.setRolelist(roleList);
            	
        	}
        	else{
            // If action = 1, then do the normal retreive for role details
            if (retrieveRoleRequest.getAction() == 1) {
                roleBO.setRoleId(retrieveRoleRequest.getRoleId());
                roleBO = roleBuilder.retrieve(roleBO);
                retrieveRoleResponse.setRoleBO(roleBO);
                // If action == 2, then it retrieves all the associated modules,
                // tasks and subtasks of that particular role.
                // Done for Login authentication purpose
            } else if (retrieveRoleRequest.getAction() == 2) {

                roleList = new ArrayList(0);
                for (int i = 0; i < retrieveRoleRequest.getRoleList().size(); i++) {
                    roleImpl = new RoleImpl();
                    roleImpl.setId(((Integer) retrieveRoleRequest.getRoleList()
                            .get(i)).intValue());
                }

            }
            //Sets the retrieved role_list to response
            retrieveRoleResponse.setRolelist(roleList);
        	}
        } catch (SevereException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(retrieveRoleRequest);
            String logMessage = "Failed while processing RetrieveRoleRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return retrieveRoleResponse;
    }

    /**
     * 
     * @param searchRoleRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SearchRoleRequest searchRoleRequest)
            throws ServiceException {
        Logger.logInfo("Inside Role Search Search Sevice");
        //Getting the response object from the Response Factory
        SearchRoleResponse searchRoleResponse = (SearchRoleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SEARCH_ROLE_RESPONSE);
        List messageList = new ArrayList(1);
        LocateResults locateResults = null;
        //Gets the locateCriteria from request
        RoleLocateCriteria roleLocateCriteria = getRoleSearchObject(searchRoleRequest);
    
        roleLocateCriteria.setSrdaFlag(searchRoleRequest.getSrdaFlag());
        RoleBusinessObjectBuilder roleBuilder = new RoleBusinessObjectBuilder();
        List roleList = null;
        try {
            roleList = roleBuilder.locate(roleLocateCriteria);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(searchRoleRequest);
            String logMessage = "Failed while processing SearchRoleRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        if (null != roleList && !roleList.isEmpty()) {
            // If no, set the list to the response
            searchRoleResponse.setRoleSearchResultList(roleList);
        } else {
            // If yes, set the message to the response
            messageList.add(new InformationalMessage(
                    BusinessConstants.SEARCH_RESULT_NOT_FOUND));
            searchRoleResponse.setMessages(messageList);
        }
        // Return the response

        return searchRoleResponse;

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
            throws ServiceException {
        //Getting the response object from the Response Factory
        TaskLookUpResponse response = (TaskLookUpResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ROLE_TASK_LOOKUP_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List locateResults = null;
        //Sets the values from request to BO
        TaskBO taskBO = new TaskBO();
        taskBO.setModuleId(request.getModuleId());
        taskBO.setRoleId(request.getRoleId());
        try {
            locateResults = builder.locateTask(taskBO, request.getUser());
            if (null != locateResults && !locateResults.isEmpty()) {
                response.setTaskList(locateResults);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskLookUpRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Method for removing an associated module
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteRoleModuleAssociationRequest request)
            throws ServiceException {
        //Getting the response object from the Response Factory
        RoleModuleLookUpResponse response = (RoleModuleLookUpResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ROLE_MODULE_LOOKUP_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List messages = new ArrayList(1);
        //Sets the valus from request to the roleConfigBO
        RoleConfigBO roleConfigBO = new RoleConfigBO();
        //*roleConfigBO.setModuleId(request.getModuleId());
        roleConfigBO.setModuleIdLIst(request.getModuleIdList());
        roleConfigBO.setRoleId(request.getRoleModuleId());
        try {
            //Validates whether the module is associated with any task/subTask
            response = (RoleModuleLookUpResponse) new ValidationServiceController()
                    .execute(request);
            //If the response is success i.e. if the module is not associated
            // with the task/subtask
            //deletes the associated module
            if (response.isSuccess()) {
                builder.deleteRoleModuleAssociation(roleConfigBO, request
                        .getUser());
                messages.add(new InformationalMessage(
                        "role.module.delete.association.info"));
                response.setMessages(messages);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskLookUpRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Method to obtain the rolelocatecriteria
     * 
     * @param roleVO
     * @return
     */
    private RoleLocateCriteria getRoleLocateCriteria(RoleVO roleVO) {
        //Create an instance of the locate criteria
        RoleLocateCriteria roleLocateCriteria = new RoleLocateCriteria();
        roleLocateCriteria
                .setRoleId(new Integer(roleVO.getRoleId()).intValue());
        // Set the values from the VO to the locateCriteria
        if (null != roleVO.getRoleName()) {
            roleLocateCriteria.setRoleName(roleVO.getRoleName());
        }

        if (null != roleVO.getModuleNameList()) {
            roleLocateCriteria.setModuleNameList(roleVO.getModuleNameList());
        }

        if (null != roleVO.getTaskNameList()) {
            roleLocateCriteria.setTaskNameList(roleVO.getTaskNameList());
        }
        if (null != roleVO.getSubTaskNameList()) {
            roleLocateCriteria.setSubTaskNameList(roleVO.getSubTaskNameList());
        }
        return roleLocateCriteria;
    }

    /**
     * Method to delete a role
     * 
     * @param RoleDeleteRequest
     * @return
     */
    public WPDResponse execute(RoleDeleteRequest request)
            throws ServiceException {
        Logger.logInfo("RoleBusinessService - Entering execute(): Role Delete");
        RoleDeleteResponse roleDeleteResponse = null;
        List locateResultList = null;
        RoleBusinessObjectBuilder roleBusinessObjectBuilder = new RoleBusinessObjectBuilder();
        //Gets the roleBO from request
        
        //Getting the response object from the Response Factory
        roleDeleteResponse = (RoleDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_ROLE_RESPONSE);

        try {
            //Validates whether the role is associated with any module
            roleDeleteResponse = (RoleDeleteResponse) new ValidationServiceController()
                    .execute(request);
            //If not associated with any module deletes the role
            if (roleDeleteResponse.isSuccess()) {
            	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
            		RoleSrdaBo roleSrdaBO = getRoleObjectFromRoleSrdaVO(request);
            		roleBusinessObjectBuilder.delete(roleSrdaBO, request.getUser());
            	}else{
            		RoleBO roleBO = getRoleObjectFromRoleVO(request);
                    roleBusinessObjectBuilder.delete(roleBO, request.getUser());
            	}
            	  
                roleDeleteResponse.addMessage(new InformationalMessage(
                        BusinessConstants.MSG_ROLE_DELETE_SUCCESS));
            }
        }catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RoleDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        List locateResults = null;

        RoleVO roleVO = request.getRoleVO();

        //for getting the search results after deletion
        try {
            RoleLocateCriteria roleLocateCriteria = (RoleLocateCriteria) getRoleLocateCriteria(roleVO);
            roleLocateCriteria.setSrdaFlag(request.getSrdaFlag());
            locateResults = roleBusinessObjectBuilder
                    .locate(roleLocateCriteria);
            if (null != locateResults && !locateResults.isEmpty()) {
                roleDeleteResponse.setRoleList(locateResults);
            }

        } catch (SevereException e2) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RoleDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e2);
        }

        Logger
                .logInfo("RoleBusinessService - Returning execute(): Role Delete");
        return roleDeleteResponse;
    }

    private RoleSrdaBo getRoleObjectFromRoleSrdaVO(RoleDeleteRequest request) {
    	 RoleSrdaBo roleBO = new RoleSrdaBo();
         RoleVO roleVO = (RoleVO) request.getRoleVO();
         roleBO.setRoleId(roleVO.getRoleId());
         return roleBO;
    	
	}

    /**
     * Method for retrieving the associated tasks
     * 
     * @param request
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(RetrieveRoleAssociationRequest request)
            throws ServiceException {
        //Getting the response object from the Response Factory
        RetrieveRoleAssociationResponse response = (RetrieveRoleAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ROLE_ASSOCIATION_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List locateResults = null;
        //Sets the values to the BO from request
        TaskBO taskBO = new TaskBO();
        taskBO.setRoleId(request.getRoleId());
        taskBO.setModuleId(request.getAssociatedModuleId());
        //Sets the values to the locatecriteria
        RoleLocateCriteria locateCriteria = new RoleLocateCriteria();
        locateCriteria.setRoleId(request.getRoleId());
        locateCriteria.setAssociatedModuleId(request.getAssociatedModuleId());
        locateCriteria.setAssociatedTaskId(request.getAssociatedTaskId());
        locateCriteria.setSubEntityType(request.getSubEntityType());
        locateCriteria.setSrdaFlag(request.getSrdaFlag());
        //Set the action to the locatecriteria
        if (null != request.getAction()) {
            locateCriteria.setAction(request.getAction());
        }
        // Get the subEntityType from the request
        String subEntityType = request.getSubEntityType();

        try {

            if (null != subEntityType && !subEntityType.equals("")) {
                locateCriteria.setSubEntityType(subEntityType);
                // If subEntityType is task
                if (subEntityType.equals("Task")) {

                    locateResults = builder
                            .locateRoleAssociations(locateCriteria);

                } else if (subEntityType.equals("SubTask")) {
                    locateResults = builder
                            .locateRoleAssociations(locateCriteria);

                }
                response.setAssociatedEntityList(locateResults);
            }

        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrievModuleAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Service method to save a task association
     * 
     * @param SaveTaskAssociationRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveTaskAssociationRequest request)
            throws ServiceException {
        //Getting the response object from the Response Factory
        SaveTaskAssociationResponse response = (SaveTaskAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_TASK_ASSOCIATION_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List messages = new ArrayList(1);
      
        //Obtain the task_id_list from the request
        List taskIdList = request.getRoleVO().getTaskNameList();
       
        try {
        	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		//Set the rol_mod_task_id from request to roleTaskConfigBO
                RoleTaskSrdaConfigBO configSrdaBO = new RoleTaskSrdaConfigBO();
                configSrdaBO.setRolModTaskId(request.getRoleModTaskId());
                
                //Setting roleId to RoleTaskId for retrieving role details for timestamp updation
                configSrdaBO.setRoleTaskId(request.getRoleVO().getRoleId());
                
                builder.persist(taskIdList, configSrdaBO, request.getUser());
        	}else{
        		//Set the rol_mod_task_id from request to roleTaskConfigBO
                RoleTaskConfigBO configBO = new RoleTaskConfigBO();
                configBO.setRolModTaskId(request.getRoleModTaskId());
                
                //Setting roleId to RoleTaskId for retrieving role details for timestamp updation
                configBO.setRoleTaskId(request.getRoleVO().getRoleId());
                
                builder.persist(taskIdList, configBO, request.getUser());
        	}
        	  
            messages.add(new InformationalMessage(
                    "role.task.association.success.info"));
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveRoleTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        //Sets the messages to the response
        response.setMessages(messages);
        return response;

    }

    /**
     * Method to save the subtask association with the role
     * 
     * @param SaveSubTaskAssociationRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveSubTaskAssociationRequest request)
            throws ServiceException {

        // Get the response object
        SaveSubTaskAssociationResponse response = (SaveSubTaskAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_SUBTASK_ASSOCIATION_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List messages = new ArrayList(1);
        // Get the values from the request and set to the BO
        RoleSubTaskConfigBO configBO = new RoleSubTaskConfigBO();
        configBO.setRolModTaskId(request.getRoleModTaskId());
        configBO.setRoleSubTaskId(request.getTaskId());
        List subTaskIdList = request.getRoleVO().getSubTaskNameList();
        try {
            builder.persist(subTaskIdList, configBO, request.getUser());
            messages.add(new InformationalMessage(
                    "role.subtask.association.success.info"));
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveRoleTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        response.setMessages(messages);
        return response;
    }

    /**
     * Service method to delete the task/subtask association with the role
     * 
     * @param DeleteTaskAssociationRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteTaskAssociationRequest request)
            throws ServiceException,AdapterException {

        // Get the response object
        DeleteTaskAssociationResponse response = (DeleteTaskAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_TASK_ASSOCIATION_RESPONSE);
        List messages = new ArrayList(1);
        // Call the builder delete()
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        try {
			//Validation to check whether the task/subTask is associated with any
			// subtask
			response = (DeleteTaskAssociationResponse) new ValidationServiceController()
			        .execute(request);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        try {
            //If the action==1 and if the task is not associated then the task
            // is deleted
            if (request.getAction() == 1) {
                if (response.isSuccess()) {
                	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){

                        //Sets the values from request to roletaskconfigBO
                        RoleTaskSrdaConfigBO configBO = new RoleTaskSrdaConfigBO();
                        configBO.setRolModTaskId(request.getRoleModTaskId());
                        configBO.setTaskIdList(request.getTaskIdList());
                       //* configBO.setTaskId(request.getTaskId());
                        configBO.setModuleId(request.getModuleId());
                        //setting roleID
                        configBO.setRoleTaskId(request.getSubTaskId());
                        builder
                                .deleteAssociationOfTask(configBO, request
                                        .getUser());
                        messages.add(new InformationalMessage(
                                "role.task.delete.association.info"));
                        response.setMessages(messages);
                	}else{

                        //Sets the values from request to roletaskconfigBO
                        RoleTaskConfigBO configBO = new RoleTaskConfigBO();
                        configBO.setRolModTaskId(request.getRoleModTaskId());
                        configBO.setTaskIdList(request.getTaskIdList());
                       //* configBO.setTaskId(request.getTaskId());
                        configBO.setModuleId(request.getModuleId());
                        //setting roleID
                        configBO.setRoleTaskId(request.getSubTaskId());
                        builder
                                .deleteAssociationOfTask(configBO, request
                                        .getUser());
                        messages.add(new InformationalMessage(
                                "role.task.delete.association.info"));
                        response.setMessages(messages);
                	}
                }
                //If the action==2 and if the subtask is not associated then
                // the subtask is deleted
            } else if (request.getAction() == 2) {
                RoleSubTaskConfigBO configBO = new RoleSubTaskConfigBO();
                configBO.setRolModTaskId(request.getRoleModTaskId());
                //*configBO.setSubTaskId(request.getSubTaskId());
                configBO.setTaskIdList(request.getTaskIdList());
				//setting role Id
                configBO.setRoleSubTaskId(request.getTaskId());
                builder.deleteAssociationOfSubTask(configBO, request.getUser());
                messages.add(new InformationalMessage(
                        "role.subtask.delete.association.info"));
                response.setMessages(messages);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        // Return the response
        return response;
    }

    /**
     * Service method to retrieve the modules associated with the role
     * 
     * @param RetrieveRoleModuleAsssociationRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveRoleModuleAsssociationRequest request)
            throws ServiceException {
        //Getting the response object from the Response Factory
        RetrieveRoleModuleAssociationResponse response = (RetrieveRoleModuleAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_ROLE_MODULE_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List locateResults = null;
        //Set the role_id from request to moduleBO
        ModuleBO moduleBO = new ModuleBO();
        ModuleSrdaBO moduleSrdaBo = new ModuleSrdaBO();
        
        try {
        	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		
        		moduleSrdaBo.setRoleId(request.getRoleId());
        		locateResults = builder.retrieveRoleModuleAssociation(moduleSrdaBo,
                        request.getUser());
        	}else{
        		moduleBO.setRoleId(request.getRoleId());
        		locateResults = builder.retrieveRoleModuleAssociation(moduleBO,
                        request.getUser());
        	}
            
            //If locateresults is not empty set the list to the response
            if (null != locateResults && !locateResults.isEmpty())
                response.setModuleAssociationList(locateResults);
            //If locateresults is empty set the ModuleAssociationList to null
            else
                response.setModuleAssociationList(null);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrieveRoleModuleAsssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * For retrieving the non-associated modules of the selected role
     * 
     * @param RoleModuleLookUpRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(RoleModuleLookUpRequest request)
            throws ServiceException {
        //Getting the response object from the Response Factory
        RoleModuleLookUpResponse response = (RoleModuleLookUpResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ROLE_MODULE_LOOKUP_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List locateResults = null;
        //Set the role_id from request to moduleBO
        ModuleBO moduleBO = new ModuleBO();
        moduleBO.setRoleId(request.getRoleId());
        try {
            locateResults = builder.locateModuleNonAssociation(moduleBO,
                    request.getUser());
            response.setModuleList(locateResults);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RoleModuleLookUpRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Methos for creating the role-module association
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveRoleModuleAssociationRequest request)
            throws ServiceException {
        //Getting the response object from the Response Factory
        SaveRoleModuleAssociationResponse response = (SaveRoleModuleAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_ROLE_MODULE_ASSOCIATION_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        //Set the role_id from request to roleconfigBO
        RoleConfigBO roleConfigBO = new RoleConfigBO();
        roleConfigBO.setRoleId(request.getRoleVO().getRoleId());
        List messages = new ArrayList(1);
        List moduleList = request.getRoleVO().getModuleIdList();
        try {
            if (null!=  moduleList && !moduleList.isEmpty()) {
                builder.persist(moduleList, roleConfigBO, request.getUser());
                messages.add(new InformationalMessage(
                        "role.module.association.success.info"));
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveRoleModuleAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        response.setMessages(messages);
        return response;
    }

    /**
     * Method for locating the tasks which are not associated with the module
     * 
     * @param request
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(RoleTaskLookUpRequest request)
            throws ServiceException {
        //Getting the response object from the Response Factory
        TaskLookUpResponse response = (TaskLookUpResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ROLE_TASK_LOOKUP_RESPONSE);
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        List locateResults = null;
       
    
        try {
        	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		
        		TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
        		taskSrdaBO.setModuleId(request.getModuleId());
        		taskSrdaBO.setRoleId(request.getRoleId());
                locateResults = builder.locateTask(taskSrdaBO, request.getUser());
        	}else{
        		 //Set the role_id,module_id from request to taskBO
        		TaskBO taskBO = new TaskBO();
                taskBO.setModuleId(request.getModuleId());
                taskBO.setRoleId(request.getRoleId());
                locateResults = builder.locateTask(taskBO, request.getUser());
        	}
            
            if (null != locateResults && !locateResults.isEmpty()) {
                response.setTaskList(locateResults);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskLookUpRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Method to set values from request to BO
     * 
     * @param RoleDeleteRequest
     * @return RoleBO
     */
    private RoleBO getRoleObjectFromRoleVO(RoleDeleteRequest request) {
        RoleBO roleBO = new RoleBO();
        RoleVO roleVO = (RoleVO) request.getRoleVO();
        roleBO.setRoleId(roleVO.getRoleId());
        return roleBO;
    }

    /**
     * Method to set values from request to the locate criteria
     * 
     * @param searchRoleRequest
     * @return RoleLocateCriteria
     */
    private RoleLocateCriteria getRoleSearchObject(
            SearchRoleRequest searchRoleRequest) {

        RoleVO roleVO = searchRoleRequest.getRoleVO();

        RoleLocateCriteria roleLocateCriteria = new RoleLocateCriteria();

        if (null != roleVO.getRoleName()) {
            roleLocateCriteria.setRoleName(roleVO.getRoleName());
        }

        if (null != roleVO.getModuleNameList()) {
            roleLocateCriteria.setModuleNameList(roleVO.getModuleNameList());
        }

        if (null != roleVO.getTaskNameList()) {
            roleLocateCriteria.setTaskNameList(roleVO.getTaskNameList());
        }
        if (null != roleVO.getSubTaskNameList()) {
            roleLocateCriteria.setSubTaskNameList(roleVO.getSubTaskNameList());
        }
        return roleLocateCriteria;
    }

    /**
     * Method to set values from VO to BO 
     * @param roleVO
     * @return RoleBO
     */
    private RoleBO copyValueObjectToBusinessObject(RoleVO roleVO) {
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(roleVO.getRoleName().trim().toUpperCase());
        roleBO.setDescription(roleVO.getDescription().toUpperCase());
        roleBO.setRoleId(roleVO.getRoleId());
        return roleBO;
    }
}

