/*
 * SecurityBusinessValidationService.java
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
import com.wellpoint.wpd.business.security.builder.TaskBusinessObjectBuilder;
import com.wellpoint.wpd.business.task.locatecriteria.TaskLocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
import com.wellpoint.wpd.common.security.request.SaveSubTaskRequest;
import com.wellpoint.wpd.common.security.request.SaveTaskRequest;
import com.wellpoint.wpd.common.security.response.SaveSubTaskResponse;
import com.wellpoint.wpd.common.security.response.SaveTaskResponse;
import com.wellpoint.wpd.common.security.vo.TaskVO;
import com.wellpoint.wpd.common.task.request.TaskDeleteRequest;
import com.wellpoint.wpd.common.task.response.TaskDeleteResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskBusinessValidationService extends
        WPDBusinessValidationService {

    List messageList = null;

    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }

    /**
     * Method to check whether task is duplicate
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveTaskRequest request) throws ServiceException {
        // Getting the response object from the Response Factory
        SaveTaskResponse response = (SaveTaskResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_TASK_RESPONSE);
        //Gets the VO from request
        TaskVO taskVO = request.getTaskVO();
        //Sets the values from VO to BO
        TaskBO taskBO = getSecurityBusinessObjectForTask(taskVO);
        TaskSrdaBO taskSrdaBO = getSecurityBusinessObjectForTaskSrda(taskVO);
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
        messageList = new ArrayList(1);
        try {
            //If the task already exists sets the success flag as false
            //if not sets the success flag as true
            if ((builder.isTaskDuplicate(taskBO) && request.isCreateFlag())|| (builder.isTaskSrdaDuplicate(taskSrdaBO) && request.isCreateFlag())) {
                response.setSuccess(false);
                messageList.add(new ErrorMessage("task.name.duplicate"));
                response.setMessages(messageList);
            } else {
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
     * Method to validate a subtask before create/edit
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SaveSubTaskRequest request)
            throws ServiceException {
        // Getting the response object from the Response Factory
        SaveSubTaskResponse response = (SaveSubTaskResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_SUB_TASK_RESPONSE);
        //Gets the VO from request
        TaskVO subTaskVO = request.getTaskVO();
        //Sets the VO to BO
        SubTaskBO subTaskBO = getSecurityBusinessObjectForSubTask(subTaskVO);
        TaskBusinessObjectBuilder builder = new TaskBusinessObjectBuilder();
        messageList = new ArrayList(1);
        boolean validationRequired = true;
        try {
            // Check whether the action is to edit a subtask
            if (!request.isCreateFlag()) {

                // Retrieve the already existing subtask details from db
                SubTaskBO subTaskBOOld = getSecurityBusinessObjectForSubTask(subTaskVO);
                subTaskBOOld = builder.retrieve(subTaskBOOld);

                // Check whether the module and task ids are same for the
                // new and existing subtasks
                if (subTaskBOOld.getModuleId() == subTaskBO.getModuleId()
                        && subTaskBOOld.getTaskId() == subTaskBO.getTaskId()) {

                    // If the values have not changed, then validation is not
                    // required
                    validationRequired = false;
                }
            }

            // Check whether validation is required
            if (validationRequired) {
                if (builder.isSubTaskDuplicate(subTaskBO)) {
                    response.setSuccess(false);
                    messageList.add(new ErrorMessage("subtask.name.duplicate"));
                    response.setMessages(messageList);
                } else {
                    response.setSuccess(true);
                }
            } else {
                response.setSuccess(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing Save SubTask Request";
            throw new ServiceException(logMessage, logParameters, e);
        }

        return response;
    }

    /**
     * Method to check whether task associated to any item before deleting it
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(TaskDeleteRequest request)
            throws ServiceException {
        Logger
                .logInfo("SecurityBusinessValidationService - Entering execute():  Task Delete");
        TaskDeleteResponse taskDeleteResponse = null;
        TaskVO taskVO = request.getTaskVO();
        SubTaskBO subTaskBO = getSecurityBusinessObjectForSubTask(taskVO);
        TaskBO taskBO = getSecurityBusinessObjectForTask(taskVO);
        TaskSrdaBO taskSrdaBO = getSecurityBusinessObjectForTaskSrda(taskVO);
        taskSrdaBO.setSrdaFlag(request.getSrdaFlag());
        taskBO.setSrdaFlag(request.getSrdaFlag());
        // Getting the response object from the Response Factory
        taskDeleteResponse = (TaskDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_TASK_RESPONSE);
        List messageList = new ArrayList(1);
        try {
            if (request.getTaskVO().getSelectedTaskType().equals("Parent")) {
                //method for checking if item is associated
                //check if module is associated
            	if ((isModuleAssociatedForTask(taskBO)
                        || isTaskSubTaskAssociated(taskBO))|| (isModuleAssociatedForTaskSrda(taskSrdaBO))) {
                    messageList.add(new ErrorMessage(
                            "task.module.subtask.association"));
                    taskDeleteResponse.setTaskSuccess(false);
                } else {
                    taskDeleteResponse.setTaskSuccess(true);
                }
            } else if (request.getTaskVO().getSelectedTaskType()
                    .equals("Child")) {
                if (isRoleAssociatedForSubTask(subTaskBO)) {
                    messageList
                            .add(new ErrorMessage("role.subtask.association"));
                    taskDeleteResponse.setSubTaskSuccess(false);
                } else {
                    taskDeleteResponse.setSubTaskSuccess(true);
                }
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing TaskDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        taskDeleteResponse.setMessages(messageList);
        Logger
                .logInfo("SecurityBusinessValidationService - Returning execute(): Task Delete");
        return taskDeleteResponse;
    }

    /**
     * Sets the values from VO to BO
     * 
     * @param taskVO
     * @return TaskBO
     */
    private TaskBO getSecurityBusinessObjectForTask(TaskVO taskVO) {
        TaskBO taskBO = new TaskBO();
        taskBO.setTaskId(taskVO.getSelectedTaskId());

        if (null != taskVO.getTaskName()) {
            taskBO.setTaskName(taskVO.getTaskName());
        }
        return taskBO;
    }
   
    
    /**
     * Sets the values from VO to BO
     * 
     * @param taskVO
     * @return TaskSrdaBO
     */
    private TaskSrdaBO getSecurityBusinessObjectForTaskSrda(TaskVO taskVO) {
    	TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
    	taskSrdaBO.setTaskId(taskVO.getSelectedTaskId());

        if (null != taskVO.getTaskName()) {
        	taskSrdaBO.setTaskName(taskVO.getTaskName());
        }
        
        return taskSrdaBO;
    }

    /**
     * Sets the values from VO to BO
     * 
     * @param taskVO
     * @return SubTaskBO
     */
    private SubTaskBO getSecurityBusinessObjectForSubTask(TaskVO taskVO) {
        SubTaskBO subTaskBO = new SubTaskBO();
        subTaskBO.setTaskId(taskVO.getParentTaskId());
        subTaskBO.setModuleId(taskVO.getModuleId());
        subTaskBO.setSubTaskId(taskVO.getSelectedTaskId());
        subTaskBO.setSubTaskName(taskVO.getTaskName());
        if(taskVO.getAction()==1){
        	subTaskBO.setSubTaskList(taskVO.getSelectedSubTaskList());
        	subTaskBO.setAction(1);
        }
        return subTaskBO;
    }

    /**
     * Method to check whether module is associated to Task
     * 
     * @param securityBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isModuleAssociatedForTask(TaskBO taskBO)
            throws SevereException {
        TaskBusinessObjectBuilder securityBusinessObjectBuilder = new TaskBusinessObjectBuilder();
        TaskLocateCriteria taskLocateCriteria = new TaskLocateCriteria();
        taskLocateCriteria.setTaskId(taskBO.getTaskId());
        taskLocateCriteria.setSrdaFlag(taskBO.getSrdaFlag());
        LocateResults locateResults = null;

        try {
			locateResults = securityBusinessObjectBuilder
			        .locateModuleTaskAssociation(taskLocateCriteria);
        } catch (SevereException e) {
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
     * Method to check whether module is associated to Task
     * 
     * @param securityBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isModuleAssociatedForTaskSrda(TaskSrdaBO taskSrdaBO)
            throws SevereException {
        TaskBusinessObjectBuilder securityBusinessObjectBuilder = new TaskBusinessObjectBuilder();
        TaskLocateCriteria taskLocateCriteria = new TaskLocateCriteria();
        taskLocateCriteria.setTaskId(taskSrdaBO.getTaskId());
        taskLocateCriteria.setSrdaFlag(taskSrdaBO.getSrdaFlag());
        LocateResults locateResults = null;

        try {
			locateResults = securityBusinessObjectBuilder
			        .locateModuleTaskAssociation(taskLocateCriteria);
        } catch (SevereException e) {
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
     * Method to check whether subTask is associated to Task
     * 
     * @param securityBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isTaskSubTaskAssociated(TaskBO taskBO)
            throws SevereException {
        TaskBusinessObjectBuilder securityBusinessObjectBuilder = new TaskBusinessObjectBuilder();
        TaskLocateCriteria taskLocateCriteria = new TaskLocateCriteria();
        taskLocateCriteria.setTaskId(taskBO.getTaskId());
        LocateResults locateResults = null;

        try {
			locateResults = securityBusinessObjectBuilder
			        .locateTaskSubTaskAssociation(taskLocateCriteria);
        } catch (SevereException e) {
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
     * Method to check whether subTasks are associated to Task
     * 
     * @param securityBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isRoleAssociatedForSubTask(SubTaskBO subTaskBO)
            throws SevereException {
        TaskBusinessObjectBuilder securityBusinessObjectBuilder = new TaskBusinessObjectBuilder();
        TaskLocateCriteria taskLocateCriteria = new TaskLocateCriteria();
        taskLocateCriteria.setTaskId(subTaskBO.getSubTaskId());
        LocateResults locateResults = null;
        //if multiple delete
        if(subTaskBO.getAction()==1){
        	List subTaskList=subTaskBO.getSubTaskList();
        	for(int i=0;i<subTaskList.size();i++){
        		taskLocateCriteria.setTaskId((Integer.parseInt((String) subTaskList.get(i))));
        		 try {
        			locateResults = securityBusinessObjectBuilder.locateRoleSubTaskAssociation(taskLocateCriteria);
        			if (null != locateResults && null != locateResults.getLocateResults()&& !locateResults.getLocateResults().isEmpty())
        				break;
                } catch (SevereException e) {
                    throw new ServiceException(null, null, e);
                }
        	}
        }
        else{

		        try {
					locateResults = securityBusinessObjectBuilder
					        .locateRoleSubTaskAssociation(taskLocateCriteria);
		        } catch (SevereException e) {
		            throw new ServiceException(null, null, e);
		        }
        }


        //if locateResults is not null then 'true' is returned
        if (null != locateResults && null != locateResults.getLocateResults()
                && !locateResults.getLocateResults().isEmpty()) {
            return true;
        }
        //if locateResults is null then 'true' is returned
        return false;
    }
}