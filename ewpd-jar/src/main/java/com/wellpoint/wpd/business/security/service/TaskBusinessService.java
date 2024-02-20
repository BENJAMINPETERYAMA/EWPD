/*
 * TaskBusinessService.java
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
import com.wellpoint.wpd.business.security.builder.TaskBusinessObjectBuilder;
import com.wellpoint.wpd.business.task.locatecriteria.TaskLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;
import com.wellpoint.wpd.common.security.bo.SubTaskSrdaBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskConfigBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
import com.wellpoint.wpd.common.security.request.LocateSubTaskRequest;
import com.wellpoint.wpd.common.security.request.RetrieveTaskRequest;
import com.wellpoint.wpd.common.security.request.SaveSubTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.SaveSubTaskRequest;
import com.wellpoint.wpd.common.security.request.SaveTaskRequest;
import com.wellpoint.wpd.common.security.request.SubTaskLookUpRequest;
import com.wellpoint.wpd.common.security.response.LocateSubTaskResponse;
import com.wellpoint.wpd.common.security.response.RetrieveTaskResponse;
import com.wellpoint.wpd.common.security.response.SaveSubTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveSubTaskResponse;
import com.wellpoint.wpd.common.security.response.SaveTaskResponse;
import com.wellpoint.wpd.common.security.response.SubTaskLookUpResponse;
import com.wellpoint.wpd.common.security.vo.TaskVO;
import com.wellpoint.wpd.common.task.request.TaskDeleteRequest;
import com.wellpoint.wpd.common.task.request.TaskSearchRequest;
import com.wellpoint.wpd.common.task.response.TaskDeleteResponse;
import com.wellpoint.wpd.common.task.response.TaskSearchResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskBusinessService extends WPDService {

    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }

    /**
     * Method to create or edit a task
     * 
     * @param SaveTaskRequest
     * @return SaveTaskResponse
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(SaveTaskRequest request) throws ServiceException {
        // Getting the response object from the Response Factory
        SaveTaskResponse response = (SaveTaskResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_TASK_RESPONSE);
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
        List messages = new ArrayList(1);
        try {
            //Validates whether the task already exixts
            response = (SaveTaskResponse) new ValidationServiceController()
                    .execute(request);
            TaskBO taskBO = null;
            TaskSrdaBO taskSrdaBO = null;
            //Copies the VO to BO
          
            	taskSrdaBO = copyValueObjectToBusinessObjectForTaskSrda(request
                        .getTaskVO());
 
            	 taskBO = copyValueObjectToBusinessObjectForTask(request
                    .getTaskVO());
            //If the response is success call the builder persist()
            if (response.isSuccess()) {
                //If the create flag is true create a task
                //If the create flag is false update the task
                if (request.isCreateFlag()) {
                    builder.persist(taskBO, request.getUser(), true);
                    //Call the builder to retrieve the created task
                    taskBO = builder.retrieve(taskBO);
                    response.setTaskBO(taskBO);
                    messages.add(new InformationalMessage(
                            "task.save.success.info"));
                } else if (!request.isCreateFlag()) {
                	
                	if(null != request.getSrdaFlag() && request.getSrdaFlag().equalsIgnoreCase("SRDA")){
                		builder.persist(taskSrdaBO, request.getUser(), false);
                        //Call the builder to retrieve the updated task
                		taskSrdaBO = builder.retrieve(taskSrdaBO);
                        response.setTaskSrdaBo(taskSrdaBO);
                        messages.add(new InformationalMessage(
                                "task.update.success.info"));
                	}else{
                    builder.persist(taskBO, request.getUser(), false);
                    //Call the builder to retrieve the updated task
                    taskBO = builder.retrieve(taskBO);
                    response.setTaskBO(taskBO);
                    messages.add(new InformationalMessage(
                            "task.update.success.info"));
                	}
                }
                response.setMessages(messages);
            }

        } catch (SevereException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveTaskRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return response;
    }

    private TaskSrdaBO copyValueObjectToBusinessObjectForTaskSrda(TaskVO taskVO) {

    	TaskSrdaBO taskSrdaBO = new TaskSrdaBO();

        taskSrdaBO.setTaskName(taskVO.getTaskName());
        taskSrdaBO.setDescription(taskVO.getDescription());
        taskSrdaBO.setTaskId(taskVO.getTaskId());

        return taskSrdaBO;
	}

    /**
     * Method to create or edit a subtask
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(SaveSubTaskRequest request)
            throws ServiceException {
        // Getting the response object from the Response Factory
        SaveSubTaskResponse response = (SaveSubTaskResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_SUB_TASK_RESPONSE);
        //Sets the vaues from VO to BO
        SubTaskBO subTaskBO = copyValueObjectToBusinessObjectForSubTask(request
                .getTaskVO());
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
        List messages = new ArrayList(1);
        try {
            //Validates whether the subtask already exists
            response = (SaveSubTaskResponse) new ValidationServiceController()
                    .execute(request);
            //If the response is success call the builder persist()
            if (response.isSuccess()) {
                //If the create flag is true creates a subtask
                //If the create flag is false updates a subtask
                if (request.isCreateFlag()) {
                    builder.persist(subTaskBO, request.getUser(), true);
                    //Call the builder to retrieve the created subtask
                    subTaskBO = builder.retrieve(subTaskBO);
                    response.setSubTaskBO(subTaskBO);
                    messages.add(new InformationalMessage(
                            "subtask.save.success.info"));
                } else if (!request.isCreateFlag()) {
                    builder.persist(subTaskBO, request.getUser(), false);
                    //Call the builder to retrieve the updated subtask
                    subTaskBO = builder.retrieve(subTaskBO);
                    response.setSubTaskBO(subTaskBO);
                    messages.add(new InformationalMessage(
                            "subtask.update.success.info"));
                }
                response.setMessages(messages);
            }

        } catch (SevereException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveSubTaskRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return response;
    }

    /**
     * Execute method for TaskDeleteRequest
     * 
     * @param request
     * @return
     * @throws SevereException
     */
    public WPDResponse execute(TaskDeleteRequest request)
            throws ServiceException {
        Logger
                .logInfo("TaskBusinessService - Entering execute(): Task Delete");
        TaskDeleteResponse taskDeleteResponse = null;
        List locateResultList = null;
        TaskSrdaBO taskSrdaBO = null;
        TaskBO taskBO = null;
        TaskBusinessObjectBuilder securityBusinessObjectBuilder = new TaskBusinessObjectBuilder();
        //Gets the values for BO from request
        if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        	  taskSrdaBO = getTaskSrdaBOFromTaskVO(request);
        }else{
        	   taskBO = getTaskBOFromTaskVO(request);
        }
     
        	  SubTaskBO subTaskBO = getSubTaskBOFromTaskVO(request);
        
        // Getting the response object from the Response Factory
        taskDeleteResponse = (TaskDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_TASK_RESPONSE);
        try {
			//Validates whether the task is associated
			taskDeleteResponse = (TaskDeleteResponse) new ValidationServiceController()
			        .execute(request);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        //If the selected task type is parent and validation is success
        //calls the builder delete()
        if (taskDeleteResponse.isTaskSuccess()) {
        if (request.getTaskVO().getSelectedTaskType().equals("Parent")) {
        	 try {
            	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
            		
                        securityBusinessObjectBuilder.delete(taskSrdaBO, request
                                .getUser());
            	}else{
                    securityBusinessObjectBuilder.delete(taskBO, request
                            .getUser());
            	}
            	}catch (SevereException e) {
                     List logParameters = new ArrayList(1);
                     logParameters.add(request);
                     String logMessage = "Failed while processing TaskDeleteRequest";
                     throw new ServiceException(logMessage, logParameters, e);
                 }
                 taskDeleteResponse.addMessage(new InformationalMessage(
                         BusinessConstants.MSG_TASK_DELETE_SUCCESS));

            }
        } else if (request.getTaskVO().getSelectedTaskType().equals("Child")) {
            //If the selected task type is child and validation is success
            //calls the builder delete()
            if (taskDeleteResponse.isSubTaskSuccess()) {
                try {
                    securityBusinessObjectBuilder.delete(subTaskBO, request
                            .getUser());

                } catch (SevereException e) {
                    List logParameters = new ArrayList(1);
                    logParameters.add(request);
                    String logMessage = "Failed while processing SubTaskDeleteRequest";
                    throw new ServiceException(logMessage, logParameters, e);
                }
                taskDeleteResponse.addMessage(new InformationalMessage(
                        BusinessConstants.MSG_SUBTASK_DELETE_SUCCESS));

            }
        }
        List locateResults = null;
        TaskVO taskVO = request.getTaskVO();
        //for getting the search results after deletion
        try {
            TaskLocateCriteria taskLocateCriteria = (TaskLocateCriteria) getTaskLocateCriteria(taskVO);
            taskLocateCriteria.setSrdaFlag(request.getSrdaFlag());
            locateResults = securityBusinessObjectBuilder
                    .locate(taskLocateCriteria);
            if (null != locateResults && !locateResults.isEmpty()) {
                taskDeleteResponse.setTaskList(locateResults);
            }

        } catch (SevereException e2) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e2);
        }

        Logger
                .logInfo("TaskBusinessService - Returning execute(): Task Delete");
        return taskDeleteResponse;
    }

    /**
     * Method to retrieve the task
     * 
     * @param RetrieveTaskRequest
     * @return RetrieveTaskResponse
     * @throws ServiceException
     * @throws AdapterException
     */
    public WPDResponse execute(RetrieveTaskRequest request)
            throws ServiceException {
        // Getting the response object from the Response Factory
        RetrieveTaskResponse response = (RetrieveTaskResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_TASK_RESPONSE);
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
        try {
            //If the selected task type is parent call the builder retrieve()
            // the task
            //If the selected task type is child call the builder retrieve()
            // for subtask
            if (request.getTaskType().equals("Parent")) {
            	if(request.getSrdaFlag().equalsIgnoreCase("SRDA"))
            	{
            		 TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
                     taskSrdaBO.setTaskId(request.getTaskId());
                     taskSrdaBO = builder.retrieve(taskSrdaBO);
                     response.setTaskSrdaBo(taskSrdaBO);
            	}else
            	{
            		TaskBO taskBO = new TaskBO();
                    taskBO.setTaskId(request.getTaskId());
                    taskBO = builder.retrieve(taskBO);
                    response.setTaskBO(taskBO);
            	}
            } else if (request.getTaskType().equals("Child")) {
                SubTaskBO subTaskBO = new SubTaskBO();
                subTaskBO.setSubTaskId(request.getTaskId());
                subTaskBO = builder.retrieve(subTaskBO);
                response.setSubTaskBO(subTaskBO);
            }

        } catch (SevereException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrieveTaskRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return response;
    }

    /**
     * Method to search the tasks based on the search criteria entered
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(TaskSearchRequest request)
            throws ServiceException {

        // Create a list to store all the messages
        List messageList = new ArrayList(1);

        // Create the response object from the response factory
        TaskSearchResponse taskSearchResponse = (TaskSearchResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TASK_SEARCH_RESPONSE);

        TaskVO taskVO = request.getTaskVO();

        //Set the values to the locate criteria
        TaskLocateCriteria taskLocateCriteria = getTaskLocateCriteria(taskVO);
        taskLocateCriteria.setSrdaFlag(request.getSrdaFlag());

        // Create an instance of the builder
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();

        // Call the builder locate() to get the result list
        List taskList = null;
        try {
            taskList = builder.locate(taskLocateCriteria);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskSearchRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        // Check whether the list is null or empty
        if (null != taskList && !taskList.isEmpty()) {

            // If no, set the list to the response
            taskSearchResponse.setTaskList(taskList);
        } else {
            // If yes, set the message to the response
            messageList.add(new InformationalMessage(
                    BusinessConstants.SEARCH_RESULT_NOT_FOUND));
            taskSearchResponse.setMessages(messageList);
        }
        // Return the response
        return taskSearchResponse;
    }

    /**
     * Method for retrieving the list of associated sub tasks with a task
     * 
     * @param SubTaskLookUpRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SubTaskLookUpRequest request)
            throws ServiceException {
        List locateResults = null;
        List errorList = new ArrayList(1);
        // Getting the response object from the Response Factory
        SubTaskLookUpResponse response = (SubTaskLookUpResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SUB_TASK_LOOKUP_RESPONSE);
        try {
            //If the action==1 call the builder locate() for module
            //If the action==2 call the builder locate() for task
            //If the action==3 call the builder locate() for subtask
            //If the action==4 call the builder locate() for task create
            //If the action==5 call the builder locate() for role
            if (request.getAction() == 1) {
                ModuleBusinessObjectBuilder builder = new ModuleBusinessObjectBuilder();
                locateResults = builder.locateModuleForLookUp(request.getSrdaFlag());
            } else if (request.getAction() == 2) {
                TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
                locateResults = builder.locateTaskForLookUp();
            } else if (request.getAction() == 3) {
                TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
                locateResults = builder.locatesubTaskForLookUp();
            } else if (request.getAction() == 4) {
                TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
                TaskBO taskBO = new TaskBO();
                taskBO.setModuleId(request.getTaskVO().getModuleId());
                locateResults = builder.locateTaskForCreate(taskBO);
            } else if (request.getAction() == 5) {
                TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
                locateResults = builder.locateTaskForRole(request.getSrdaFlag());
            }
            //If the locateresults is not empty set the list to response
            if (null != locateResults && !locateResults.isEmpty()) {
                response.setLookUpList(locateResults);
            } else {
                response.setLookUpList(null);
                errorList.add(new InformationalMessage(
                        BusinessConstants.SEARCH_RESULT_NOT_FOUND));
                response.setMessages(errorList);

            }

        } catch (SevereException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SubTaskLookUpRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return response;
    }

    /**
     * Method for locating the list of sub-tasks which are not associated with
     * the selected task
     * 
     * @param request
     * @return
     * @throws SevereException
     */
    public WPDResponse execute(LocateSubTaskRequest request)
            throws ServiceException {
        List locateResultList;
        int locateResultCount = 0;
        List errorList = new ArrayList(1);
        // Getting the response object from the Response Factory
        LocateSubTaskResponse response = (LocateSubTaskResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SUB_TASK_LOCATE_RESPONSE);
        TaskVO taskVO = request.getTaskVO();

        //       Set the values to the locate criteria
        TaskLocateCriteria taskLocateCriteria = getSubTaskLocateCriteria(taskVO);
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
        //Calls the builder locate() for subtask
        try {
            LocateResults locateResults = builder
                    .locateSubTask(taskLocateCriteria);
            //If the locateresults is not empty and resultcount is >0
            //adds the list to the response
            if (null != locateResults) {
                locateResultList = locateResults.getLocateResults();
                locateResultCount = locateResultList.size();
                if (locateResultCount > 0) {
                    response.setSubTaskList(locateResults.getLocateResults());
                } else if (locateResultCount == 0) {
                    //If the resultcount is <0 adds the error message
                    errorList.add(new InformationalMessage(
                            BusinessConstants.SEARCH_RESULT_NOT_FOUND));
                    response.setMessages(errorList);

                }
            }
        } catch (SevereException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SubTaskLookUpRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return response;
    }

    /**
     * Method to associate the sub-task with the selected task
     * 
     * @param SaveSubTaskAssociationRequest
     * @return SaveSubTaskAssociationResponse
     * @throws SevereException
     */
    public WPDResponse execute(SaveSubTaskAssociationRequest request)
            throws ServiceException {
        // Getting the response object from the Response Factory
        SaveSubTaskAssociationResponse response = (SaveSubTaskAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_SUBTASK_ASSOCIATION_RESPONSE);
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
        //Sets the task_id to the BO
        TaskConfigBO taskConfigBO = new TaskConfigBO();
        taskConfigBO.setSubTaskId(request.getTaskId());
        //Calls the builder saveTaskAssociation()
        try {
            builder.saveTaskAssociation(taskConfigBO, request.getUser());
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing SaveSubTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Method to return the TaskLocateCriteria
     */
    private TaskLocateCriteria getTaskLocateCriteria(TaskVO taskVO) {
        //Create an instance of the locate criteria
        TaskLocateCriteria taskLocateCriteria = new TaskLocateCriteria();

        // Set the values from the VO to the locateCriteria
        if (null != taskVO.getTaskName()) {
            taskLocateCriteria.setTaskName(taskVO.getTaskName());
        }

        return taskLocateCriteria;
    }

    /**
     * Method to get TaskBO for TaskDeleteRequest
     * 
     * @param request
     * @return
     */
    private TaskBO getTaskBOFromTaskVO(TaskDeleteRequest request) {
        TaskBO taskBO = new TaskBO();
        TaskVO taskVO = (TaskVO) request.getTaskVO();
        taskBO.setTaskId(taskVO.getSelectedTaskId());
        //?taskBO.setSubTaskList(taskVO.getSelectedSubTaskList());
        return taskBO;
    }
    /**
     * Method to get TaskSrdaBO for TaskDeleteRequest
     * 
     * @param request
     * @return
     */
    private TaskSrdaBO getTaskSrdaBOFromTaskVO(TaskDeleteRequest request) {
    	TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
        TaskVO taskVO = (TaskVO) request.getTaskVO();
        taskSrdaBO.setTaskId(taskVO.getSelectedTaskId());
        //?taskBO.setSubTaskList(taskVO.getSelectedSubTaskList());
        return taskSrdaBO;
    }

    /**
     * Method to get SubTaskBO for TaskDeleteRequest
     * 
     * @param request
     * @return
     */
    private SubTaskBO getSubTaskBOFromTaskVO(TaskDeleteRequest request) {
        SubTaskBO subTaskBO = new SubTaskBO();
        TaskVO taskVO = (TaskVO) request.getTaskVO();
        subTaskBO.setSubTaskId(taskVO.getSelectedTaskId());
        if(taskVO.getAction()==1){
        subTaskBO.setSubTaskList(taskVO.getSelectedSubTaskList());
        subTaskBO.setAction(1);
        }
        return subTaskBO;
    }

    /**
     * Method to copy taskVO to taskBO
     * 
     * @param taskVO
     * @return
     */
    private TaskBO copyValueObjectToBusinessObjectForTask(TaskVO taskVO) {
        TaskBO taskBO = new TaskBO();

        taskBO.setTaskName(taskVO.getTaskName());
        taskBO.setDescription(taskVO.getDescription());
        taskBO.setTaskId(taskVO.getTaskId());

        return taskBO;
    }

    /**
     * Method to copy taskVO to SubTaskBO
     * 
     * @param taskVO
     * @return
     */
    private SubTaskBO copyValueObjectToBusinessObjectForSubTask(TaskVO taskVO) {
        SubTaskBO subTaskBO = new SubTaskBO();

        subTaskBO.setSubTaskName(taskVO.getTaskName());
        subTaskBO.setDescription(taskVO.getDescription());
        subTaskBO.setSubTaskId(taskVO.getTaskId());
        subTaskBO.setModuleId(taskVO.getModuleId());
        subTaskBO.setTaskId(taskVO.getParentTaskId());
        return subTaskBO;
    }

    /**
     * Method to obtain the TaskLocateCriteria
     * 
     * @param taskVO
     * @return
     */
    private TaskLocateCriteria getSubTaskLocateCriteria(TaskVO taskVO) {
        TaskLocateCriteria taskLocateCriteria = new TaskLocateCriteria();
        taskLocateCriteria.setTaskId(taskVO.getTaskId());

        return taskLocateCriteria;
    }
}