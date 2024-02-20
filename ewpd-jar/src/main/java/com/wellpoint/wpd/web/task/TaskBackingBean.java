/*
 * TaskBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.ibm.ws.http.HttpRequest;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;
import com.wellpoint.wpd.common.security.bo.SubTaskSrdaBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
import com.wellpoint.wpd.common.security.request.RetrieveTaskRequest;
import com.wellpoint.wpd.common.security.request.SaveSubTaskRequest;
import com.wellpoint.wpd.common.security.request.SaveTaskRequest;
import com.wellpoint.wpd.common.security.request.SubTaskLookUpRequest;
import com.wellpoint.wpd.common.security.response.RetrieveTaskResponse;
import com.wellpoint.wpd.common.security.response.SaveSubTaskResponse;
import com.wellpoint.wpd.common.security.response.SaveTaskResponse;
import com.wellpoint.wpd.common.security.response.SubTaskLookUpResponse;
import com.wellpoint.wpd.common.security.vo.TaskVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 *  
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskBackingBean extends WPDBackingBean {
    
    private String taskName;
    
    private String description;
    
    private String srdaFlag;
    
    private String taskType;
    
    private String selectedModule;
    
    private String selectedTask;
    
    private List taskTypeList;
    
    private String moduleName;
    
    private String oldParentModuleValue;
    
    private int taskId;
    
    private int selectedTaskId;
    
    private String selectedTaskType;
    
    private String subTaskParent;
    
    private List searchResultList;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private List validationMessages;
    
    private int viewTaskId;
    
    private List taskLookUpList;
    
    private List taskList;
    
    private List subTaskLookUpList;
    
    private List moduleLookUpList;
    
    public boolean requiredTaskName = false;
    
    public boolean requiredTaskType = false;
    
    public boolean requiredTask = false;
    
    public boolean requiredModule = false;
    
    public boolean requiredDescription = false;
    
    private String selectedModuleId;
    
    private String loadModuleLookUpList;
    
    public TaskBackingBean(){
        this.setBreadCrumbText("Administration >> Task >> Create");
    }
    
    
    public String getLoadModuleTaskLookUpList(){

		SubTaskLookUpRequest lookUpRequest = (SubTaskLookUpRequest) 
        this.getServiceRequest(ServiceManager.SUB_TASK_LOOK_UP_REQUEST);
    
    lookUpRequest.setAction(1);
   
    lookUpRequest.setSrdaFlag("eWPD");
       
    //Call the execute service to obtain the response
    SubTaskLookUpResponse lookUpResponse = (SubTaskLookUpResponse)
    	this.executeService(lookUpRequest);
    //If the response list is not empty set the list
    if(lookUpResponse.getLookUpList() != null && 
                !lookUpResponse.getLookUpList().isEmpty()){
        this.moduleLookUpList = lookUpResponse.getLookUpList();
    }else{
        this.moduleLookUpList = null;
    }
    	
  
		return loadModuleLookUpList;
	
    }
    
	/**
	 * @return Returns the loadModuleLookUpList.
	 */
	public String getLoadModuleLookUpList() {
		SubTaskLookUpRequest lookUpRequest = (SubTaskLookUpRequest) 
        this.getServiceRequest(ServiceManager.SUB_TASK_LOOK_UP_REQUEST);
    
    lookUpRequest.setAction(1);
   
    	lookUpRequest.setSrdaFlag("SRDA");
       
    //Call the execute service to obtain the response
    SubTaskLookUpResponse lookUpResponse = (SubTaskLookUpResponse)
    	this.executeService(lookUpRequest);
    //If the response list is not empty set the list
    if(lookUpResponse.getLookUpList() != null && 
                !lookUpResponse.getLookUpList().isEmpty()){
        this.moduleLookUpList = lookUpResponse.getLookUpList();
    }else{
        this.moduleLookUpList = null;
    }
    	
		return loadModuleLookUpList;
	}
	/**
	 * @param loadModuleLookUpList The loadModuleLookUpList to set.
	 */
	public void setLoadModuleLookUpList(String loadModuleLookUpList) {
		this.loadModuleLookUpList = loadModuleLookUpList;
	}

    
    /**
     * Method for creating a task or sub-task
     * @return forward String
     */
    public String create(){
        //Validates the fields
        if (!validateRequiredFields(1)) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        
        // Save the task
        return saveTaskAndSubTask(1);
    }
    /**
     * Method for updating a task or sub-task
     * @return forward String
     */
    public String updateTask(){
        //Validates the fields
        if (!validateRequiredFields(2)) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        
        // Update the task
        return saveTaskAndSubTask(2);
    }

    /**
     * Method to create or update a task or subtask
     * 
     */
    private String saveTaskAndSubTask(int action) {
        // Set the values from the backing bean to the Value object
        TaskVO taskVO = copyValueObjectFromBackingBean();
        TaskSrdaBO taskSrdaBO = null ;
        TaskBO taskBO = null ;
        // Check whether to create a task or subtask
        if(WebConstants.SECURITY_MOD_PARENT.equals(this.taskType)){
            // Get the request object to create a task 
            SaveTaskRequest saveTaskRequest = getSaveTaskRequest(action, taskVO);
            
            // Call the executeService() to get the response
            SaveTaskResponse saveTaskResponse = (SaveTaskResponse)executeService(saveTaskRequest);
            
            // Get the BO from the response
         
            
            // Check whether the BO is null
            	if(null !=saveTaskResponse.getTaskSrdaBo()){
                  	 taskSrdaBO = saveTaskResponse.getTaskSrdaBo();
                
                // Set the values from the BO to the backing bean
            	
             	copyBusinessObjectValuesToBackingBeanForTaskSRDA(taskSrdaBO);
                
                // Set the breadcrumb text
                // Set the breadcrumb text
                this.setBreadCrumbText("Administration >> Task ("+ taskSrdaBO.getTaskName() +")"+" >> Edit");
                getSession().setAttribute("TASK_ID", new Integer(this.taskId));
                getSession().setAttribute("TASK_TYPE", this.taskType);
                return WebConstants.TASK_EDIT;
                
                
             }else if(null !=saveTaskResponse.getTaskBO()){
                	 taskBO = saveTaskResponse.getTaskBO();
                
                // Set the values from the BO to the backing bean
            	
                copyBusinessObjectValuesToBackingBeanForTask(taskBO);
                
                // Set the breadcrumb text
                this.setBreadCrumbText("Administration >> Task ("+ taskBO.getTaskName() +")"+" >> Edit");
                getSession().setAttribute("TASK_ID", new Integer(this.taskId));
                getSession().setAttribute("TASK_TYPE", this.taskType);
                return WebConstants.TASK_EDIT;
                
            }
            
        }else if(WebConstants.SECURITY_MOD_CHILD.equals(this.taskType)){
            // Get the request object to create a subtask
            SaveSubTaskRequest saveSubTaskRequest = getSaveSubTaskRequest(action, taskVO);
            
            // Call the executeService() to get the response
            SaveSubTaskResponse saveSubTaskResponse = (SaveSubTaskResponse)executeService(saveSubTaskRequest);
            
            // Get the BO from the response
            SubTaskBO subTaskBO = saveSubTaskResponse.getSubTaskBO();
            
            // Check whether the BO is null
            if(null!= subTaskBO){
                
                // Set the values from the BO to the backing bean
                copyBusinessObjectValuesToBackingBeanForSubTask(subTaskBO);
                
                // Set the breadcrumb text
                this.setBreadCrumbText("Administration >> SubTask ("+ subTaskBO.getSubTaskName() +")"+" >> Edit");
                getSession().setAttribute("TASK_ID", new Integer(this.taskId));
                getSession().setAttribute("TASK_TYPE", this.taskType);
                return WebConstants.TASK_EDIT;
                
            }
        }

        return WebConstants.EMPTY_STRING;
        
    }
    /**
     * Method to load the task for edit
     * @return
     */
    public String loadTaskForEdit(){
        
        RetrieveTaskResponse response = retrieveTask();
        
        if(this.selectedTaskType.equals(WebConstants.SECURITY_MOD_PARENT)){
            if(null!=response.getTaskBO()){
                //Sets the values to the backing bean
	            copyBusinessObjectValuesToBackingBeanForTask(response.getTaskBO());
	            this.setBreadCrumbText("Administration >> Task ("+ response.getTaskBO().getTaskName() +") >> Edit");
	        }else{
	        	copyBusinessObjectValuesToBackingBeanForTaskSRDA(response.getTaskSrdaBo());
		            this.setBreadCrumbText("Administration >> Task ("+ response.getTaskSrdaBo().getTaskName() +") >> Edit");
	        }
        }else if(this.selectedTaskType.equals(WebConstants.SECURITY_MOD_CHILD)){
            if(null!=response.getSubTaskBO()){
                //Sets the values to the backing bean
                copyBusinessObjectValuesToBackingBeanForSubTask(response.getSubTaskBO());
	            this.setBreadCrumbText("Administration >> SubTask ("+ 
	                    response.getSubTaskBO().getSubTaskName() +") >> Edit");
	        }
        }
        getSession().setAttribute("TASK_ID", new Integer(this.taskId));
        getSession().setAttribute("TASK_TYPE", this.selectedTaskType);
        return WebConstants.TASK_EDIT;
    }
    
    private void copyBusinessObjectValuesToBackingBeanForTaskSRDA(
			TaskSrdaBO taskSrdaBO) {

        this.setTaskName(taskSrdaBO.getTaskName().trim());
        this.setDescription(taskSrdaBO.getDescription().trim());
        this.setTaskType(WebConstants.SECURITY_MOD_PARENT);
        
        this.setTaskName(taskSrdaBO.getTaskName().trim());
        
        this.setLastUpdatedTimestamp(taskSrdaBO.getLastUpdatedTimestamp());
        this.setLastUpdatedUser(taskSrdaBO.getLastUpdatedUser());
        this.setTaskId(taskSrdaBO.getTaskId());		
	}

	/**
     * Method to retrieve the task
     * @return
     */
    private RetrieveTaskResponse retrieveTask() {
        
        RetrieveTaskRequest request = (RetrieveTaskRequest) getServiceRequest(ServiceManager.RETRIEVE_TASK_REQUEST);
        //Set the values to the request
        request.setTaskId(this.getTaskId());
        request.setTaskType(this.getSelectedTaskType());
        request.setSrdaFlag((String)getSession().getAttribute("SRDA_FLAG"));
        //Call the execute service to obtain response
        RetrieveTaskResponse response = (RetrieveTaskResponse) executeService(request);
        return response;
    }
/**
 * Method to validate the required fields
 * @param action
 * @return
 */
    public boolean validateRequiredFields(int action){
        validationMessages = new ArrayList(5);
        boolean requiredField = false;
       
        if(action == 2){
            this.setBreadCrumbText("Administration >> SubTask ("+ 
                    this.getTaskName() +") >> Edit");
        }
        
        if(this.taskName == null || WebConstants.EMPTY_STRING.equals(this.taskName.trim())){
            this.requiredTaskName = true;
            requiredField = true;
        }else{
        	this.taskName = this.taskName.trim();
        }
        
        if(this.taskType == null || WebConstants.EMPTY_STRING.equals(this.taskType)){
            this.requiredTaskType = true;
            requiredField = true;
        }
        
        if(this.taskType.equals(WebConstants.SECURITY_MOD_CHILD)){
            if(this.selectedModule == null || this.selectedModule.equals(WebConstants.EMPTY_STRING)){
                this.requiredModule = true;
                requiredField = true;
            }
            
            if(this.selectedTask == null || this.selectedTask.equals(WebConstants.EMPTY_STRING)){
                this.requiredTask = true;
                requiredField = true;
            }
        }
        
        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        //Validates special character for taskname
        if(!this.taskName.matches("^[\\d|a-z|A-Z|\\s]*$")){
            this.validationMessages.add(new ErrorMessage(WebConstants.CATALOG_NAME_INVALID));
            this.requiredTaskName = true;
            return false;
        }
//      Validates for task name length
        if (this.taskName.length() > 30) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.INVALID_NAME));            
            return false;
        }
        if(null != this.description){
        	this.description = this.description.trim();
        }
        //Validates for description length
        if (!this.description.trim().equals(WebConstants.EMPTY_STRING) && this.description.length() > 250) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_SIZE_1_250));
            this.requiredDescription = true;
            return false;
        }
        //Validates description for special characters
        if (!this.description.trim().matches("^[\\d|a-z|A-Z|\\s]*$")) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_INVALID));
            return false;
        }
        return true;
    }
    
    /**
     * Method to get the request for Task create or edit requests.
     * @param action
     * @param taskVO
     * @return SaveTaskRequest
     */
    private SaveTaskRequest getSaveTaskRequest(int action, TaskVO taskVO){
        // Get the request object
        SaveTaskRequest saveTaskRequest = (SaveTaskRequest) 
        	getServiceRequest(ServiceManager.SAVE_TASK_REQUEST);
        
        
        
        // Set the request property to true or false to show whether to 
        // create or update
        if(action == 1){
            // Set the VO to the request
            saveTaskRequest.setTaskVO(taskVO);
            saveTaskRequest.setCreateFlag(true);
        }if(action == 2){
            // Set the VO to the request
            taskVO.setTaskId(this.getTaskId());
            saveTaskRequest.setTaskVO(taskVO);
            saveTaskRequest.setCreateFlag(false);
        }
        
        saveTaskRequest.setSrdaFlag((String)getSession().getAttribute("SRDA_FLAG"));
        return saveTaskRequest;
    }
    
    /**
     * Method to get the request for SubTask create or edit requests.
     * @param action
     * @param taskVO
     * @return SaveTaskRequest
     */
    private SaveSubTaskRequest getSaveSubTaskRequest(int action, TaskVO taskVO) {
        // Get the request object
        SaveSubTaskRequest saveSubTaskRequest = (SaveSubTaskRequest) 
        	getServiceRequest(ServiceManager.SAVE_SUB_TASK_REQUEST);
        
        // Set the VO to the request
        saveSubTaskRequest.setTaskVO(taskVO);
        
        // Set the request property to true or false to show whether to 
        // create or update       
        if (action == 1) {
            saveSubTaskRequest.setCreateFlag(true);
        } else if (action == 2) {
            
            saveSubTaskRequest.setCreateFlag(false);
        }
        return saveSubTaskRequest;
    }
    /**
     * Method to copy values from backing bean to taskVO
     * @return
     */
    private TaskVO copyValueObjectFromBackingBean(){
        TaskVO taskVO = new TaskVO();
        
        taskVO.setTaskName(this.getTaskName().trim().toUpperCase());
        if(null != this.getDescription() && !this.getDescription().trim().equals(WebConstants.EMPTY_STRING)){
            taskVO.setDescription(this.getDescription().trim().toUpperCase());
        }else{
            taskVO.setDescription(" ");
        }
        taskVO.setTaskId(this.taskId);
        taskVO.setSelectedTaskId(this.taskId);
        
        // Get the parent task id in the case of subtask
        if(null != this.selectedTask && !this.selectedTask.equals(WebConstants.EMPTY_STRING)){
            List taskIdList = WPDStringUtil.getListFromTildaString(this.selectedTask, 2, 2, 1); 
            taskVO.setParentTaskId(((Integer)taskIdList.get(0)).intValue());
            
        }
        
        // Get the module id in the case of task
        if(null != this.selectedModule && !this.selectedModule.equals(WebConstants.EMPTY_STRING)){
            List moduleIdList = WPDStringUtil.getListFromTildaString(this.selectedModule, 2, 2, 1); 
            taskVO.setModuleId(((Integer)moduleIdList.get(0)).intValue());
            
        }
        return taskVO;
    }
    /**
     * Method to copy BO to backing bean for task
     * @param taskBO
     */
    private void copyBusinessObjectValuesToBackingBeanForTask(TaskBO taskBO){

        this.setTaskName(taskBO.getTaskName().trim());
        this.setDescription(taskBO.getDescription().trim());
        this.setTaskType(WebConstants.SECURITY_MOD_PARENT);
        
        this.setTaskName(taskBO.getTaskName().trim());
        this.setCreatedTimestamp(taskBO.getCreatedTimestamp());
        this.setCreatedUser(taskBO.getCreatedUser());
        this.setLastUpdatedTimestamp(taskBO.getLastUpdatedTimestamp());
        this.setLastUpdatedUser(taskBO.getLastUpdatedUser());
        this.setTaskId(taskBO.getTaskId());
    }
    /**
     * Method to copy BO to backing bean for subtask
     * @param subTaskBO
     */
    private void copyBusinessObjectValuesToBackingBeanForSubTask(SubTaskBO subTaskBO){

        this.setTaskId(subTaskBO.getSubTaskId());
        this.setTaskName(subTaskBO.getSubTaskName().trim());
        this.setDescription(subTaskBO.getDescription().trim());
        this.setSubTaskParent(subTaskBO.getTaskName().trim());
        this.setModuleName(subTaskBO.getModuleName().trim());
        this.setTaskType(WebConstants.SECURITY_MOD_CHILD);
        this.setCreatedTimestamp(subTaskBO.getCreatedTimestamp());
        this.setCreatedUser(subTaskBO.getCreatedUser());
        this.setLastUpdatedTimestamp(subTaskBO.getLastUpdatedTimestamp());
        this.setLastUpdatedUser(subTaskBO.getLastUpdatedUser());
        
        // Set the parent task id and name as tilda separated String
        String parentTask =  subTaskBO.getTaskName() + WebConstants.TILDA + subTaskBO.getTaskId();
        this.setSelectedTask(parentTask);
       
        
        // Set the module id and name as tilda separated String
        String module =  subTaskBO.getModuleName() + WebConstants.TILDA + subTaskBO.getModuleId();
        this.setSelectedModule(module);
        this.setOldParentModuleValue(module);
    }
    
    /**
     * Method to copy BO to backing bean for task
     * @param TaskSrdaBO
     */
    private void copyBusinessObjectValuesToBackingBeanForTaskSrda(TaskSrdaBO taskSrdaBO){

        this.setTaskId(taskSrdaBO.getTaskId());
        this.setTaskName(taskSrdaBO.getTaskName().trim());
        this.setDescription(taskSrdaBO.getDescription().trim());
        this.setTaskType(WebConstants.SECURITY_MOD_PARENT);
        this.setLastUpdatedTimestamp(taskSrdaBO.getLastUpdatedTimestamp());
        this.setLastUpdatedUser(taskSrdaBO.getLastUpdatedUser());
        
        // Set the parent task id and name as tilda separated String
        String parentTask =  taskSrdaBO.getTaskName() + WebConstants.TILDA + taskSrdaBO.getTaskId();
        this.setSelectedTask(parentTask);
       
    }
    
    /**
     * @return moduleLookUpList
     * Returns the moduleLookUpList.
     */
    public List getModuleLookUpList() {

    	return moduleLookUpList;
    }
	/**
     * @param moduleLookUpList
     * Sets the moduleLookUpList.
     */
    public void setModuleLookUpList(List moduleLookUpList) {
        this.moduleLookUpList = moduleLookUpList;
    }
    /**
     * @return taskLookUpList
     * Returns the taskLookUpList.
     */
    public List getTaskLookUpList() {
        //Gets the module_id from request parameter
        String modID = getRequest().getParameter("selectedModule");
        SubTaskLookUpRequest lookUpRequest = (SubTaskLookUpRequest) 
        this.getServiceRequest(ServiceManager.SUB_TASK_LOOK_UP_REQUEST);
        //Sets action==4 
	    lookUpRequest.setAction(4);
	    TaskVO taskVO = new TaskVO();
        //Sets the moduleid list to the VO
        if(null != modID && !modID.equals(WebConstants.EMPTY_STRING)){
            List moduleIdList = WPDStringUtil.getListFromTildaString(modID, 2, 2, 1); 
            taskVO.setModuleId(((Integer)moduleIdList.get(0)).intValue());   
        }
        lookUpRequest.setTaskVO(taskVO);
        
        //Call the execute service to obtain the response
	    SubTaskLookUpResponse lookUpResponse = (SubTaskLookUpResponse)
	    	this.executeService(lookUpRequest);
	    //If the response list is not empty set the list to taskLookUpList
	    if(null != lookUpResponse.getLookUpList() &&
	            !lookUpResponse.getLookUpList().isEmpty()){
	        taskLookUpList = lookUpResponse.getLookUpList();
	    }else{
	        taskLookUpList = null;
	    }
        return taskLookUpList;
    }
    /**
     * @return Returns the taskList.
     * @return List taskList
     */
    public List getTaskList() {
        SubTaskLookUpRequest lookUpRequest = (SubTaskLookUpRequest) 
        this.getServiceRequest(ServiceManager.SUB_TASK_LOOK_UP_REQUEST);
        //Sets the action==5
        lookUpRequest.setAction(5);
       
        	lookUpRequest.setSrdaFlag("eWPD");
        //Call the execute service to obtain the response
        SubTaskLookUpResponse lookUpResponse = (SubTaskLookUpResponse)
    	this.executeService(lookUpRequest);
	    //If the response list is not empty set the list to taskList
	    if(null != lookUpResponse.getLookUpList() &&
	            !lookUpResponse.getLookUpList().isEmpty()){
	        taskList = lookUpResponse.getLookUpList();
	    }else{
	        taskList = null;
	    }
	        return taskList;
    }
    
    /**
     * @return Returns the taskList.
     * @return List taskList
     */
    public List getSrdaTaskList() {
        SubTaskLookUpRequest lookUpRequest = (SubTaskLookUpRequest) 
        this.getServiceRequest(ServiceManager.SUB_TASK_LOOK_UP_REQUEST);
        //Sets the action==5
        lookUpRequest.setAction(5);
       
        	lookUpRequest.setSrdaFlag("SRDA");
        //Call the execute service to obtain the response
        SubTaskLookUpResponse lookUpResponse = (SubTaskLookUpResponse)
    	this.executeService(lookUpRequest);
	    //If the response list is not empty set the list to taskList
	    if(null != lookUpResponse.getLookUpList() &&
	            !lookUpResponse.getLookUpList().isEmpty()){
	        taskList = lookUpResponse.getLookUpList();
	    }else{
	        taskList = null;
	    }
	        return taskList;
    }
    /**
     * @param taskLookUpList
     * Sets the taskLookUpList.
     */
    public void setTaskLookUpList(List taskLookUpList) {
        this.taskLookUpList = taskLookUpList;
    }
    
    /**
     * @return subTaskLookUpList
     * 
     * Returns the subTaskLookUpList.
     */
    public List getSubTaskLookUpList() {
        
        
        SubTaskLookUpRequest lookUpRequest = (SubTaskLookUpRequest) 
        this.getServiceRequest(ServiceManager.SUB_TASK_LOOK_UP_REQUEST);
        //Sets the action==3
        lookUpRequest.setAction(3);
        //Call the execute service to obtain the response
        SubTaskLookUpResponse lookUpResponse = (SubTaskLookUpResponse)
    	this.executeService(lookUpRequest);
	    //If the response list is not empty set the list to subTaskLookUpList
	    if(null != lookUpResponse.getLookUpList() && 
	            !lookUpResponse.getLookUpList().isEmpty()){
	        subTaskLookUpList = lookUpResponse.getLookUpList();
	    }else{
	        subTaskLookUpList = null;
	    }
	    
	        return subTaskLookUpList;
    }
    /**
     * @param subTaskLookUpList
     * 
     * Sets the subTaskLookUpList.
     */
    public void setSubTaskLookUpList(List subTaskLookUpList) {
        this.subTaskLookUpList = subTaskLookUpList;
    }
    /**
     * @return selectedModule
     * Returns the selectedModule.
     */
    public String getSelectedModule() {
        return selectedModule;
    }
    /**
     * @param selectedModule
     * Sets the selectedModule.
     */
    public void setSelectedModule(String selectedModule) {
        this.selectedModule = selectedModule;
    }
    /**
     * @return selectedTask
     * Returns the selectedTask.
     */
    public String getSelectedTask() {
        return selectedTask;
    }
    /**
     * @param selectedTask
     * Sets the selectedTask.
     */
    public void setSelectedTask(String selectedTask) {
        this.selectedTask = selectedTask;
    }
    /**
     * @return Returns the description.
     * @return String description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Returns the taskName.
     * @return String taskName
     */
    public String getTaskName() {
        return taskName;
    }
    /**
     * Sets the taskName
     * @param taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    /**
     * @return Returns the taskType.
     * @return String taskType
     */
    public String getTaskType() {
        return taskType;
    }
    /**
     * Sets the taskType
     * @param taskType
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    /**
     * @return Returns the taskTypeList.
     * @return List taskTypeList
     */
    public List getTaskTypeList() {
       if(taskTypeList == null){
           //Sets the task type list
           taskTypeList = new ArrayList(3);
           taskTypeList.add(new SelectItem(WebConstants.EMPTY_STRING));
           taskTypeList.add(new SelectItem(WebConstants.SECURITY_MOD_PARENT));
           taskTypeList.add(new SelectItem(WebConstants.SECURITY_MOD_CHILD));
       }
        return taskTypeList;
    }
    /**
     * Sets the taskTypeList
     * @param taskTypeList
     */
    public void setTaskTypeList(List taskTypeList) {
        this.taskTypeList = taskTypeList;
    }
    
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
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
     * @return subTaskParent
     * Returns the subTaskParent.
     */
    public String getSubTaskParent() {
        return subTaskParent;
    }
    /**
     * @param subTaskParent
     * Sets the subTaskParent.
     */
    public void setSubTaskParent(String subTaskParent) {
        this.subTaskParent = subTaskParent;
    }

    /**
     * @return Returns the requiredTaskName.
     * @return boolean requiredTaskName
     */
    public boolean isRequiredTaskName() {
        return requiredTaskName;
    }
    /**
     * Sets the requiredTaskName
     * @param requiredTaskName
     */
    public void setRequiredTaskName(boolean requiredTaskName) {
        this.requiredTaskName = requiredTaskName;
    }
    /**
     * @return Returns the requiredTaskType.
     * @return boolean requiredTaskType
     */
    public boolean isRequiredTaskType() {
        return requiredTaskType;
    }
    /**
     * Sets the requiredTaskType
     * @param requiredTaskType
     */
    public void setRequiredTaskType(boolean requiredTaskType) {
        this.requiredTaskType = requiredTaskType;
    }
    
    /**
     * @return Returns the validationMessages.
     * @return List validationMessages
     */
    public List getValidationMessages() {
        return validationMessages;
    }
    /**
     * Sets the validationMessages
     * @param validationMessages
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }
    /**
     * @return Returns the taskId.
     * @return int taskId
     */
    public int getTaskId() {
        return taskId;
    }
    /**
     * Sets the taskId
     * @param taskId
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
     * @return selectedTaskType
     * Returns the selectedTaskType.
     */
    public String getSelectedTaskType() {
        return selectedTaskType;
    }
    /**
     * @param selectedTaskType
     * Sets the selectedTaskType.
     */
    public void setSelectedTaskType(String selectedTaskType) {
        this.selectedTaskType = selectedTaskType;
    }
     /**
     * @return requiredModule
     * Returns the requiredModule.
     */
    public boolean isRequiredModule() {
        return requiredModule;
    }
    /**
     * @param requiredModule
     * Sets the requiredModule.
     */
    public void setRequiredModule(boolean requiredModule) {
        this.requiredModule = requiredModule;
    }
    /**
     * @return requiredTask
     * Returns the requiredTask.
     */
    public boolean isRequiredTask() {
        return requiredTask;
    }
    /**
     * @param requiredTask
     * Sets the requiredTask.
     */
    public void setRequiredTask(boolean requiredTask) {
        this.requiredTask = requiredTask;
    }
    
	/**
	 * @return Returns the moduleName.
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName The moduleName to set.
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	/**
	 * @return Returns the viewTaskId.
	 */
	public int getViewTaskId() {
		 HttpSession httpSession = getSession();
	        int taskId = 0;
	        //Get the action and task type from request parameter
	        String action = getRequest().getParameter("action");
	        String taskType=ESAPI.encoder().encodeForHTML(getRequest().getParameter("taskType"));
	        if(null!=taskType  && taskType.matches("^[0-9a-zA-Z_]+$")){
	        	taskType = taskType.toString();
			}else{
				taskType=null;
			}
	        this.setSelectedTaskType(taskType);
	        //If action==view get the task_id from request parameter and
	        //Set the task_id and task_type to session
	        //If the action==print get task_id and task_type values from session
	        if(null != action && action.equals("view")){
	            taskId = new Integer(getRequest().getParameter("taskId")).intValue();
	            httpSession.setAttribute("TASK_ID", new Integer(taskId));
	            httpSession.setAttribute("TASK_TYPE", taskType);
	        }else if(null != action && action.equals("print")){
	            taskId = ((Integer)httpSession.getAttribute("TASK_ID")).intValue();
	            taskType=((String)httpSession.getAttribute("TASK_TYPE"));
	        }
	        this.taskId = taskId;
	        //Get the task_type value from session 
	        this.selectedTaskType=((String)httpSession.getAttribute("TASK_TYPE"));
	        //Retrieve the task
	        RetrieveTaskResponse response = retrieveTask();
	        //Gets the task_type value from session
	        this.taskType=((String)httpSession.getAttribute("TASK_TYPE"));
	        
	        if(taskType.equals(WebConstants.SECURITY_MOD_PARENT))
	        {
		        if(null!= response && (null!=response.getTaskBO() || null != response.getTaskSrdaBo())){
		        	if(null!=response.getTaskBO()){
		        		copyBusinessObjectValuesToBackingBeanForTask(response.getTaskBO());
		        		this.setBreadCrumbText("Administration >> Task (" + response.getTaskBO().getTaskName() + ") >> View");
		        	}else{
		        		copyBusinessObjectValuesToBackingBeanForTaskSrda(response.getTaskSrdaBo());
		        		this.setBreadCrumbText("Administration >> Task (" + response.getTaskSrdaBo().getTaskName() + ") >> View");
		        	}
		            	
		            	this.selectedTaskType = "Task";
		        }
	        }
	        
	        else if(taskType.equals(WebConstants.SECURITY_MOD_CHILD))
	        {
	        	 if(null!= response && null!=response.getSubTaskBO()){
		            copyBusinessObjectValuesToBackingBeanForSubTask(response.getSubTaskBO());
		            this.setBreadCrumbText("Administration >> SubTask (" + response.getSubTaskBO().getSubTaskName() + ") >> View");
		            this.selectedTaskType = "SubTask";
		        }
	        }
		return viewTaskId;
	}
	/**
	 * @param viewTaskId The viewTaskId to set.
	 */
	public void setViewTaskId(int viewTaskId) {
		this.viewTaskId = viewTaskId;
	}    /**
     * @return Returns the selectedModuleId.
     * @return String selectedModuleId
     */
    public String getSelectedModuleId() {
        return selectedModuleId;
    }
    /**
     * Sets the selectedModuleId
     * @param selectedModuleId
     */
    public void setSelectedModuleId(String selectedModuleId) {
        this.selectedModuleId = selectedModuleId;
    }

    /**
     * Sets the taskList
     * @param taskList
     */
    public void setTaskList(List taskList) {
        this.taskList = taskList;
    }
    /**
     * @return Returns the requiredDescription.
     * @return boolean requiredDescription
     */
    public boolean isRequiredDescription() {
        return requiredDescription;
    }
    /**
     * Sets the requiredDescription
     * @param requiredDescription
     */
    public void setRequiredDescription(boolean requiredDescription) {
        this.requiredDescription = requiredDescription;
    }
    private String getString(String value){
    	int i=0;StringBuffer stringBuffer=new StringBuffer();
    	while(i+20<=value.length()){
    		
    		stringBuffer.append(value.substring(i,i+20));
    		i=i+20;
    		stringBuffer.append("\n");
    	}
    	
    	return stringBuffer.toString();
    }
	/**
	 * @return Returns the oldParentModuleValue.
	 */
	public String getOldParentModuleValue() {
		return oldParentModuleValue;
	}
	/**
	 * @param oldParentModuleValue The oldParentModuleValue to set.
	 */
	public void setOldParentModuleValue(String oldParentModuleValue) {
		this.oldParentModuleValue = oldParentModuleValue;
	}

	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
	
	
}
