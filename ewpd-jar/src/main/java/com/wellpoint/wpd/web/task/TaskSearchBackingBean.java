/*
 * TaskSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.task;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;
import com.wellpoint.wpd.common.security.bo.SubTaskSrdaBO;
import com.wellpoint.wpd.common.security.vo.TaskVO;
import com.wellpoint.wpd.common.task.request.TaskDeleteRequest;
import com.wellpoint.wpd.common.task.request.TaskSearchRequest;
import com.wellpoint.wpd.common.task.response.TaskDeleteResponse;
import com.wellpoint.wpd.common.task.response.TaskSearchResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class TaskSearchBackingBean extends WPDBackingBean {

	private String taskName;
	
	private String srdaFlag;

	private int selectedTaskId;
	
	private String selectedType;
	
	private List validationMessage;
	
	private List searchResultList;

	/**
     * Constructor of the class
     *
     */
    public TaskSearchBackingBean(){
        this.setBreadCrumbText("Administration >> Task " + ">> Locate");
    }
	
	public void validate() throws ValidationException {
	}
	
	
	
	public boolean validationMessages(){
	
		return true;
	}
	
	/**
	 * Method to validate the search page fields
	 * 
	 * @return boolean
	 */
	private boolean validateFields() {
		validationMessage = new ArrayList(1);
		// Set the breadcrumb to lacate
		this.setBreadCrumbText("Administration >> Task " + ">> Locate");
		// Check whether all the fields in the search page are empty
		if ((null == this.taskName.trim() || this.taskName.trim().equals(""))) {
			// If so, set the required error message
			validationMessage.add(new ErrorMessage(
					BusinessConstants.REQUIRED_FIELDS));
			return false;
		}
		return true;
	}

	/**
	 * Method to search the task based on the search criteria entered by the
	 * user
	 * 
	 * @return String
	 */
	public String taskSearch(){
        
		//String Flag2 = srdaFlag;
		//String flag = this.srdaFlag;
        // Validate the fields
        if(!validateFields()){
            // Add the messages to the request
            addAllMessagesToRequest(this.validationMessage);
            
            // Return the forward string
            return WebConstants.EMPTY_STRING;
        }
        HttpSession httpSession = getSession();
        httpSession.setAttribute("SRDA_FLAG", this.srdaFlag);
        // Create the request object
        TaskSearchRequest taskSearchRequest = getSearchRequest();
        
        // Call the executeService() to get the response
       TaskSearchResponse taskSearchResponse = 
            (TaskSearchResponse) this.executeService(taskSearchRequest);
        
        // Get the result list from the response
        List taskList = taskSearchResponse.getTaskList();
        
        //setDescriptionForDisplay(taskList);
       // setDescriptionForDisplaySrda(taskList2);
        
      if(this.srdaFlag.equalsIgnoreCase("SRDA")){
    	  setDescriptionForDisplaySrda(taskList);
               }
      else{
    	  setDescriptionForDisplay(taskList);
      }
        
        // Set the bread crumb to locate
        this.setBreadCrumbText("Administration >> Task " + ">> Locate");

        // Return the forward string
        return WebConstants.EMPTY_STRING;
    }
	
	public void getloadPage(){
		this.srdaFlag = "eWPD";
	}

	/**
	 * @param taskList
	 */
	private void setDescriptionForDisplay(List taskList) {
        // Check whether the result list is null or empty
        if(null != taskList && !taskList.isEmpty()){
        	// If not, set the list to the backing bean
            this.setSearchResultList(taskList);
            for(int i = 0 ; i < searchResultList.size() ; i++){
                SubTaskBO subTaskBO = (SubTaskBO) searchResultList.get(i);
                String description = "";
                if(subTaskBO.getDescription().length() > 15){
                    description = subTaskBO.getDescription();
                    description = description.substring(0,15).concat("...");
                    subTaskBO.setDescription(description);
                }
            }
        } 
       }
	private void setDescriptionForDisplaySrda(List taskList2) {
        // Check whether the result list is null or empty
        if(null != taskList2 && !taskList2.isEmpty()){
        	// If not, set the list to the backing bean
            this.setSearchResultList(taskList2);
            for(int i = 0 ; i < searchResultList.size() ; i++){
                SubTaskSrdaBO subTaskBO = (SubTaskSrdaBO) searchResultList.get(i);
                String description = "";
                if(subTaskBO.getDescription().length() > 15){
                    description = subTaskBO.getDescription();
                    description = description.substring(0,15).concat("...");
                    subTaskBO.setDescription(description);
                }
            }
        } 
       }

    /**
     * Method to delete task
     * @return String
     */
    public String deleteTask() {
        Logger.logInfo("TaskSearchBackingBean - Entering deleteTask(): Task");
        TaskDeleteRequest taskDeleteRequest = (TaskDeleteRequest) this
                .getServiceRequest(ServiceManager.DELETE_TASK_REQUEST);
        TaskVO taskVO = new TaskVO();
        taskVO.setSelectedTaskId(this.getSelectedTaskId());
        taskVO.setSelectedTaskType(this.getSelectedType());
        if(null != this.taskName && !this.taskName.equals("")){
            String name = this.taskName;
            // Check for the case when % is entered as search criteria
            if(name.indexOf("%") >= 0 ){
                name = name.replaceAll("%", "`%");
        	}else if(name.indexOf("_") >= 0){
        	    name = name.replaceAll("_", "`_");
        	}

            taskVO.setTaskName(name);

		}
		HttpSession httpSession = getSession();
        String srdaFlag = (String)httpSession.getAttribute("SRDA_FLAG");

		taskDeleteRequest.setTaskVO(taskVO);
		taskDeleteRequest.setSrdaFlag(srdaFlag);
		TaskDeleteResponse taskDeleteResponse = (TaskDeleteResponse) executeService(taskDeleteRequest);

		// Get the result list from the response
		List taskList = taskDeleteResponse.getTaskList();

		//setDescriptionForDisplay(taskList);
		if(srdaFlag.equalsIgnoreCase("SRDA")){
	    	  setDescriptionForDisplaySrda(taskList);
	               }
	      else{
	    	  setDescriptionForDisplay(taskList);
	      }

		Logger.logInfo("TaskSearchBackingBean - Returning deleteTask(): Task");
		return "";
	}

	/**
	 * Method to create the request object and set the values to it
	 * 
	 * @return TaskSearchRequest
	 */
	private TaskSearchRequest getSearchRequest() {
		// Create an instance of the request object
		TaskSearchRequest request = (TaskSearchRequest) this
				.getServiceRequest(ServiceManager.TASK_SEARCH_REQUEST);

		// Create an instance of the VO
		TaskVO taskVO = new TaskVO();

		// Set the values in the VO
		if (null != this.taskName.trim() && !this.taskName.trim().equals("")) {

			// Check for the case when '%' or '_' is entered in the search
			// criteria
			String name = this.taskName;
			if (name.indexOf("%") >= 0) {
				name = name.replaceAll("%", "`%");
			} else if (name.indexOf("_") >= 0) {
				name = name.replaceAll("_", "`_");
			}
			taskVO.setTaskName(name.trim());
			request.setSrdaFlag(this.srdaFlag);
			
		}

		// Set the VO in the request
		request.setTaskVO(taskVO);

		// Return the request
		return request;
	}

	/**
	 * @return selectedType Returns the selectedType.
	 */
	public String getSelectedType() {
		return selectedType;
	}

	/**
	 * @param selectedType
	 *            Sets the selectedType.
	 */
	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	/**
	 * @return Returns the searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList The searchResultList to set.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
		
	}
	
	/**
	 * @return Returns the validationMessage.
	 */
	public List getValidationMessage() {
		return validationMessage;
	}
	/**
	 * @param validationMessage The validationMessage to set.
	 */
	public void setValidationMessage(List validationMessage) {
		this.validationMessage = validationMessage;
	}
	
	
	/**
	 * @return Returns the selectedTaskId.
	 */
	public int getSelectedTaskId() {
		return selectedTaskId;
	}
	/**
	 * @param selectedTaskId The selectedTaskId to set.
	 */
	public void setSelectedTaskId(int selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}
	/**
	 * @return Returns the taskName.
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName The taskName to set.
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}

	
}
