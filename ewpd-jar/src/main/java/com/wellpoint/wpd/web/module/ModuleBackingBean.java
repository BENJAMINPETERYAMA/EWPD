/*
 * ModuleBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.request.DeleteModuleTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveModuleRequest;
import com.wellpoint.wpd.common.security.request.SaveModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.SaveModuleRequest;
import com.wellpoint.wpd.common.security.request.TaskLookUpRequest;
import com.wellpoint.wpd.common.security.response.DeleteModuleTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveModuleResponse;
import com.wellpoint.wpd.common.security.response.TaskLookUpResponse;
import com.wellpoint.wpd.common.security.vo.ModuleVO;
import com.wellpoint.wpd.common.security.vo.TaskVO;
import com.wellpoint.wpd.common.task.request.TaskDeleteRequest;
import com.wellpoint.wpd.common.task.response.TaskDeleteResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleBackingBean extends WPDBackingBean {
    
    private String moduleName;
    
    private String description;
    
    private String task;
    
    private int viewModule;
    
    private List taskList;
    
    private String taskTxt;
    
    private List searchResultList;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private List validationMessages;
    
    private List tasksSubTaskList;
    
    private boolean requiredModuleName;
    
    private String moduleId;
    
    private int selectedModuleId;
    
    private int selectedTaskId;

    private int loadModuleConfigurationFromTree;
    
    private int breadCrumbForEdit;
    
    private String selectedTaskIds;
    
    private String selectedSubTaskIds;
    
    private int action;

	private String srdaFlag;
    
    /**
     * @return breadCrumbForEdit
     * Returns the breadCrumbForEdit.
     */
    public int getBreadCrumbForEdit() {
        if(null != getModuleName()){
            this.setModuleName(getModuleName());
            this.setBreadCrumbText("Administration >> Module ("+this.getModuleName()+")>> Edit");
        }
        return breadCrumbForEdit;
    }
    /**
     * @param breadCrumbForEdit
     * Sets the breadCrumbForEdit.
     */
    public void setBreadCrumbForEdit(int breadCrumbForEdit) {
        this.breadCrumbForEdit = breadCrumbForEdit;
    }
    /**
     * Constructor
     *
     */
    public ModuleBackingBean(){
        this.setBreadCrumbText("Administration >> Module >> Create");
    }
    /**
     * Method for creating a module
     * @return
     */
    public String create(){
        //validates the required fields
        if (!validateRequiredFields(1)) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        HttpSession httpSession = getSession();
       
        SaveModuleRequest request = getSaveModuleSaveRequest(1);
        //Call the execute service to get the response
        SaveModuleResponse response  = (SaveModuleResponse) executeService(request);
        //Set the values from response to the backing bean
        if(null!= response && null!= response.getModuleBO()){
            copyBusinessObjectValuesToBackingBean(response.getModuleBO());
            getBreadCrumbForEdit();
           // this.setBreadCrumbText("Administration >> Module ("+this.getModuleName()+")>> Edit");
            
            //Set the values of module_id and module_name to the session
            httpSession.setAttribute("SESSION_MOD_SYS_ID", 
            		response.getModuleBO().getModuleId() + WebConstants.EMPTY_STRING);
            httpSession.setAttribute("SESSION_MOD_NAME", response.getModuleBO().getModuleName());
            return WebConstants.MODULE_EDIT;
        }
        
     
        return WebConstants.EMPTY_STRING;
    }
    /**
     * Method for updating a module
     * @return
     */
    public String editModule() {
        //Validates the required fields
        if (!validateRequiredFields(2)) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        
        SaveModuleRequest request = getSaveModuleSaveRequest(2);
        //Call the execute service to get the response
        SaveModuleResponse response  = (SaveModuleResponse) executeService(request);
        //Set the values from response to the backing bean
        if(null!= response && null!= response.getModuleBO()){
            copyBusinessObjectValuesToBackingBean(response.getModuleBO());
            getBreadCrumbForEdit();
           // this.setBreadCrumbText("Administration >> Module ("+this.getModuleName()+")>> Edit");
            return WebConstants.MODULE_EDIT;
        } else if(null!= response && null!= response.getModuleSrdaBO()){
        	 copyBusinessObjectValuesToBackingBeanSrda(response.getModuleSrdaBO());
             getBreadCrumbForEdit();
            // this.setBreadCrumbText("Administration >> Module ("+this.getModuleName()+")>> Edit");
             return WebConstants.MODULE_EDIT;
        }else{
        	return WebConstants.EMPTY_STRING;	
        }
        
	}
    /**
     * For loading the module task configuration
     * @return
     */
    public String loadModuleConfiguration(){
        RetrieveModuleAssociationRequest request = 
        	(RetrieveModuleAssociationRequest)getServiceRequest
        					(ServiceManager.MODULE_ASSOCIATION_REQUEST);
        int selectedId = Integer.parseInt(this.getModuleId()); 
        String moduleNm = this.getModuleName();
        //Set the values to the request
        request.setModuleId(Integer.parseInt(this.getModuleId()));
        request.setSubEntityType("Task");
        request.setSrdaFlag((String)getSession().getAttribute("SRDA_FLAG"));
        
        //Call the execute service to get the response
        RetrieveModuleAssociationResponse response = 
        	(RetrieveModuleAssociationResponse) executeService(request);
        //If the list is not empty set the association list to searchresultlist
        if(null!=response.getModuleAssociationList() && 
        		!response.getModuleAssociationList().isEmpty()){
            this.setSearchResultList(response.getModuleAssociationList());
        }else{
            this.setSearchResultList(null);
        }
        HttpSession httpSession = getSession();
        //Set the values to the session
        httpSession.setAttribute("SESSION_MOD_SYS_ID", 
        		selectedId + WebConstants.EMPTY_STRING);
        
        if(null == this.getModuleName()){
            String modName = getModuleName();
            this.moduleName = modName;
        }
        this.setBreadCrumbText("Administration >> Module ("+this.getModuleName()+")>> Configure");
		return WebConstants.LOAD_MODULE_CONFIGURATION;
	}
    
    /**
     * Method to get the associated SubTasks of a task attached to a module
     * while navigating through the tree
     * @return tasksSubTaskList
     * Returns the tasksSubTaskList.
     */
    public List getTasksSubTaskList() {
    	getRequest().setAttribute("RETAIN_Value","");
        RetrieveModuleAssociationRequest request = 
        	(RetrieveModuleAssociationRequest)getServiceRequest
				(ServiceManager.MODULE_ASSOCIATION_REQUEST);
        
        // Get HttpSession
        HttpSession httpSession = getSession();
        
        // Get the moduleId and taskId from session
        String moduleId = (String)httpSession.getAttribute("SESSION_MOD_SYS_ID");
        
        String taskId = (String)httpSession.getAttribute("taskIdFromTree");
        
        // Set the subEntityType as 'SubTask' in the request
        request.setSubEntityType("SubTask");
        
        // Set the values in the request
        if(null != moduleId && !moduleId.equals(WebConstants.EMPTY_STRING) &&
        		null != taskId && !taskId.equals(WebConstants.EMPTY_STRING)){
        	request.setModuleId(Integer.parseInt(moduleId));
        	request.setAssociatedTaskId(Integer.parseInt(taskId));
        }
        
        // Call the executeService() to get the response list
        RetrieveModuleAssociationResponse response = 
        	(RetrieveModuleAssociationResponse) executeService(request);
        
        // Check whether the list is null or empty
        if(null != response.getModuleAssociationList() && 
        		!response.getModuleAssociationList().isEmpty()){
        
        	// Set the list if true
        	this.tasksSubTaskList = response.getModuleAssociationList();
        	
        }else{
           	// Set the list to null if false
        	this.tasksSubTaskList = null;
        }
        String modName = getModuleName();
        if(null != modName){
        	this.setModuleName(modName);
        }
        return tasksSubTaskList;
    }
    /**
     * @param tasksSubTaskList
     * Sets the tasksSubTaskList.
     */
    public void setTasksSubTaskList(List tasksSubTaskList) {
        
        this.tasksSubTaskList = tasksSubTaskList;
    }
    
    /**
     * Method to delete a subtask associated to a module and task
     * @return String
     */
    public String deleteAssociatedSubTask(){
    	
    	List subTaskIdList = new ArrayList();
		//Get the selected SubTask Ids from selectedTaskIds
		String [] selectedIds = selectedSubTaskIds.split("~");
		if(selectedIds != null && selectedIds.length > 0) {
    		
    		for(int i=0; i<selectedIds.length; i++) {
    			//Adding selected taskIds to a list
    			subTaskIdList.add(selectedIds[i]);
    		}
    	}
        //Get the module_name from session
        String modName = getModuleName();
  	
        // Create an instance of TaskVO
        TaskVO taskVO = new TaskVO();
        
    	// Set the values in the VO
        /*if(0 != this.selectedTaskId ){
        	taskVO.setSelectedTaskId(this.selectedTaskId);
        }*/
        
        //set selected subtask ids to TaskVO
        taskVO.setSelectedSubTaskList(subTaskIdList);
        
        taskVO.setSelectedTaskType("Child");
        
        taskVO.setAction(1);
    	
    	// Get the request object
        TaskDeleteRequest taskDeleteRequest = (TaskDeleteRequest) this
        	.getServiceRequest(ServiceManager.DELETE_TASK_REQUEST);
        
        // Set the VO in the request
        taskDeleteRequest.setTaskVO(taskVO);
    	
    	// Call the executeService() to get the response
        TaskDeleteResponse response = (TaskDeleteResponse)
			this.executeService(taskDeleteRequest);
        this.setBreadCrumbText("Administration >> Module ("+modName+")>> Configure");
    	return WebConstants.EMPTY_STRING;
    }
    
    /**
     * Method to load module for edit
     * @return String
     */
    public String loadEditPage() {
        Logger.logInfo("ModuleBackingBean - Entering loadModuleForEdit(): Module Edit");
        HttpSession httpSession = getSession();
        //Set the session action as edit
        httpSession.setAttribute("action","edit");
        //Remove the tree_state_module session attribute 
        if(null != httpSession.getAttribute("SESSION_TREE_STATE_MODULE")){
        	httpSession.removeAttribute("SESSION_TREE_STATE_MODULE");
        }
        String retrieveKey = null;
        //Get the value fo module_id
        if(0 != this.getSelectedModuleId()){
            retrieveKey = this.getSelectedModuleId() + WebConstants.EMPTY_STRING;
        }else if(null != this.getModuleId()){
            retrieveKey = (this.getModuleId()) + WebConstants.EMPTY_STRING;
        }
        //Call the retrieve() for module
        retrieveModuleDetails(retrieveKey,"edit");
        return WebConstants.MODULE_EDIT;
    }
    /**
     * Method to set the session action as null
     * @return
     */
    public String clearSession()
    {
    	HttpSession httpSession = getSession();
    	httpSession.setAttribute("action", null);
    	return WebConstants.EMPTY_STRING;
    }
    
    /**
     * @return loadModuleConfigurationFromTree
     * Returns the loadModuleConfigurationFromTree.
     */
    public int getLoadModuleConfigurationFromTree() {
    	getRequest().setAttribute("RETAIN_Value","");
	     Logger.logInfo("ModuleBackingBean - Entering loadModuleConfiguration(): Module Configuration View");
	   	 return loadModuleConfigurationFromTree;
    }
    /**
     * @param loadModuleConfigurationFromTree
     * Sets the loadModuleConfigurationFromTree.
     */
    public void setLoadModuleConfigurationFromTree(
            int loadModuleConfigurationFromTree) {
        this.loadModuleConfigurationFromTree = loadModuleConfigurationFromTree;
    }
   /**
    * Method to load the module configuration page
    * @return
    */   
    public String loadModuleConfigurationView()
    {
    	 Logger.logInfo("ModuleBackingBean - Entering loadModuleConfiguration(): Module Configuration View");
    	 HttpSession httpSession = getSession();
    	 //Get the module_id from session
    	 this.moduleId = (String)httpSession.getAttribute("SESSION_MOD_SYS_ID");
    	 //Call the load() method
    	 loadModuleConfiguration();
    	 this.setBreadCrumbText("Administration >> Module ("+this.getModuleName()+")>> View");
    	return WebConstants.LOAD_MODULE_CONFIGURATION_VIEW; 
    }
    /**
     * Method to retrieve module
     * @param retrieveKey
     */
    private void retrieveModuleDetails(String retrieveKey,String action) {
        Logger.logInfo("ModuleBackingBean - Entering retrieveModuleDetails(): Module");
        RetrieveModuleRequest moduleRetrieveRequest = 
        	(RetrieveModuleRequest) this
                .getServiceRequest(ServiceManager.MODULE_RETRIEVE_REQUEST);
        //Set the values from VO to request
        ModuleVO moduleVO=new ModuleVO();
        moduleVO.setModuleId(Integer.parseInt(retrieveKey));
        moduleRetrieveRequest.setModuleVO(moduleVO);
        moduleRetrieveRequest.setSrdaFlag((String)getSession().getAttribute("SRDA_FLAG"));
        //Call the execute service to get the response
        RetrieveModuleAssociationResponse moduleRetrieveResponse = 
        	(RetrieveModuleAssociationResponse) executeService(moduleRetrieveRequest);
        //If the response BO is not null set the values to the backing bean
        if (null != moduleRetrieveResponse) {
            
        	if(null != moduleRetrieveResponse.getModuleSrdaBO() || getSession().getAttribute("SRDA_FLAG").equals("SRDA") ){
        		ModuleSrdaBO moduleSrdaBO = moduleRetrieveResponse.getModuleSrdaBO();
        	    copyBusinessObjectValuesToBackingBeanSrda(moduleSrdaBO); 
        	}else{
        		ModuleBO moduleBO = moduleRetrieveResponse.getModuleBO();
        		copyBusinessObjectValuesToBackingBean(moduleBO);
        	}
            if(action.equals("edit"))
                this.setBreadCrumbText("Administration >> Module " 
                		+ "("+ this.moduleName + ") >> Edit");
            else if(action.equals("view"))
                this.setBreadCrumbText("Administration >> Module " 
                		+ "("+ this.moduleName + ") >> View");	
        }
        HttpSession httpSession = getSession();
        //Set the module_name and module_id to the session 
        httpSession.setAttribute("SESSION_MOD_NAME", this.moduleName);
        httpSession.setAttribute("SESSION_MOD_SYS_ID", this.moduleId);
        Logger.logInfo("ModuleBackingBean - Returning retrieveModuleDetails(): Module");
    }
    /**
     * Method for creating task association with the module
     * @return
     */
    public String saveModuleAssociation(){
    	 getRequest().setAttribute("RETAIN_Value","");
//    	Get the module_name from session
    	String modName = getModuleName();
        SaveModuleAssociationRequest request = (SaveModuleAssociationRequest)
        						getServiceRequest(
        								ServiceManager.SAVE_MODULE_ASSOCIATION_REQUEST);
        ModuleVO moduleVO = getmoduleVOForAssociation();
        HttpSession httpSession = getSession();
        
        //Set the moduleVO to the request
        request.setModuleVO(moduleVO);
        request.setSrdaFlag((String)httpSession.getAttribute("SRDA_FLAG"));
        //Call the execute service to obtain the response
        SaveModuleAssociationResponse response = 
        	(SaveModuleAssociationResponse)executeService(request);
        //If the response task list is not empty set the list to searchresultlist
        if(null!=response.getTaskList() && 
        		!response.getTaskList().isEmpty()){
            this.setSearchResultList(response.getTaskList());
        }else{
            this.setSearchResultList(null);
        }
        this.setBreadCrumbText("Administration >> Module ("+modName+") >> Configure");
        //Set the task to null
        this.setTask(null);
        return WebConstants.EMPTY_STRING;
    }
    /**
	 * @return Returns the searchResultList.
	 */
	public List getSearchResultList() {
	    
	    HttpSession httpSession = getSession();
	    //Get the module_id from session
	   	 this.moduleId = (String)httpSession.getAttribute("SESSION_MOD_SYS_ID");
	   	 
	   	 if(null != this.moduleId || !this.moduleId.equals(WebConstants.EMPTY_STRING)){
	   	     //If action==edit load module configuration for edit
	   	     //If action==view load the module configuration view page
	   	     if(null == httpSession.getAttribute("action") || httpSession.getAttribute("action").equals("edit")){
	   	         loadModuleConfiguration();
	   	     }else {
	   	         if(httpSession.getAttribute("action").equals("view")){
	   	         	loadModuleConfigurationView();
	   	         }
	   	     }
	   	 }
	    
		return searchResultList;
	}
	/**
	 * Method for retrieving the list of task non-associated with the selected task
	 * @return Returns the taskList for module association
	 */
	public List getTaskList() {
	    HttpSession httpSession =getSession();
	    int selectedId = 0;
	    //Get the module_id from session
	    if(null != httpSession.getAttribute("SESSION_MOD_SYS_ID")){
	    	selectedId = Integer.parseInt(
	    			(String)httpSession.getAttribute("SESSION_MOD_SYS_ID"));
	    }
	    TaskLookUpRequest request = (TaskLookUpRequest) 
			getServiceRequest(ServiceManager.MODULE_TASK_LOOKUP_REQUEST);
	    request.setSrdaFlag((String) httpSession.getAttribute("SRDA_FLAG"));
	    //Set the values to the request
	    request.setModuleId(selectedId);
        //Call the execute service to obtain the response
	    TaskLookUpResponse response = (TaskLookUpResponse)executeService(request);
        //If the response task list is not empty set the list to tasklist
	    if(null != response.getTaskList() && !response.getTaskList().isEmpty()){
	        this.setTaskList(response.getTaskList());
	    }else{
	        this.setTaskList(null);
	    }
	    //Set the module_id value to the session
	    httpSession.setAttribute("SESSION_MOD_SYS_ID", selectedId + WebConstants.EMPTY_STRING);
		return taskList;
	}
	/**
	 * 
	 * @return
	 */
	public String viewModuleLoad(){
	    HttpSession httpSession = getSession();
	    return WebConstants.VIEW_MODULE;
	}
	
	/**
	 * @return Returns the viewModule.
	 */
	public int getViewModule() {
		HttpSession httpSession = getSession();
		String moduleId=null;
		//Get the action from session
        String action = getRequest().getParameter("action");
        if(null != action && action.equals("view")){
            //Get the value of module_id from request parameter
        	moduleId=ESAPI.encoder().encodeForHTML((getRequest().getParameter("moduleId")));
        	if(null!=moduleId  && moduleId.matches("[0-9a-zA-Z_]+")){
        		moduleId =moduleId.toString();
        	}
        	 moduleId = moduleId + WebConstants.EMPTY_STRING;
        	 //Set the value of module_id to session
            httpSession.setAttribute("SESSION_MOD_SYS_ID", (moduleId) 
            		+ WebConstants.EMPTY_STRING);
            httpSession.setAttribute("action", "view");
            //Remove the tree_state_module from session 
            if(null != httpSession.getAttribute("SESSION_TREE_STATE_MODULE")){
                httpSession.removeAttribute("SESSION_TREE_STATE_MODULE");
            }
        }else {
        	moduleId = (String)httpSession.getAttribute("SESSION_MOD_SYS_ID");
        }
        
        this.moduleId = moduleId;
        String retrieveKey = this.getModuleId() + WebConstants.EMPTY_STRING;
        // This is done for Print details
        loadModuleConfiguration();
        retrieveModuleDetails(retrieveKey,"view");
        
		return viewModule;
	}
	/**
	 * Method to delete the associated task with the module
	 * @return
	 */
	public String deleteModuleTaskAssociation(){
		if(null==this.task||"".equals(this.task))
			getRequest().setAttribute("RETAIN_Value", "");
		List taskIdList = new ArrayList();
		//Get the selected Task Ids from selectedTaskIds
		String [] selectedIds = selectedTaskIds.split("~");
		if(selectedIds != null && selectedIds.length > 0) {
    		
    		for(int i=0; i<selectedIds.length; i++) {
    			//Adding selected taskIds to a list
    			taskIdList.add(selectedIds[i]);
    		}
    	}
	    //Get the module_name value from session
        String modName = getModuleName();
	    DeleteModuleTaskAssociationRequest request = (DeleteModuleTaskAssociationRequest)
					getServiceRequest(ServiceManager.DELETE_MODULE_TASK_ASSOCIATION_REQUEST);
	    //Set the values to the request
	    request.setTaskIdList(taskIdList);
	    HttpSession httpSession = getSession();
        
      
        request.setSrdaFlag((String)httpSession.getAttribute("SRDA_FLAG"));
	   // request.setTaskId(this.getSelectedTaskId());
	    request.setModuleId(Integer.parseInt(this.getModuleId()));
        //Call the execute service to obtain the response
	    DeleteModuleTaskAssociationResponse response = 
	    	(DeleteModuleTaskAssociationResponse)executeService(request);
        //If the response task list is not empty set the list to tasklist
	    if(null!=response.getTaskList() && 
	    		!response.getTaskList().isEmpty()){
	        this.setSearchResultList(response.getTaskList());
	    }else{
	        this.setSearchResultList(null);
	    }
	    this.setBreadCrumbText("Administration >> Module ("+modName+") >> Configure");
//	   getRequest().setAttribute("RETAIN_Value", ""); 
	   return WebConstants.EMPTY_STRING;
	}
   /**
    * Method for validating the required fields
    * @return
    */
    public boolean validateRequiredFields(int action){
        validationMessages = new ArrayList(5);
        boolean requiredField = false;
        
        if(action == 2){
            getBreadCrumbForEdit();
        }
        
        if(null == this.moduleName || WebConstants.EMPTY_STRING.equals(
        		this.moduleName.trim())){
            this.requiredModuleName = true;
            requiredField = true;
        }else{
        	this.moduleName = this.moduleName.trim();
        }
   
        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        //Validation for special characters
        if(!this.moduleName.trim().matches("^[\\d|a-z|A-Z|\\s]*$")){
            this.validationMessages.add(new ErrorMessage(WebConstants.CATALOG_NAME_INVALID));
            this.requiredModuleName = true;
            return false;
        }
        // validation for module name 
        if (this.moduleName.trim().length() > 30) {
	        this.validationMessages.add(new ErrorMessage(
	                WebConstants.INVALID_NAME));
	        return false;
	    }
        
        //Validation for description length
	    if (!this.description.trim().equals(WebConstants.EMPTY_STRING) && this.description.length() > 250) {
	        this.validationMessages.add(new ErrorMessage(
	                WebConstants.DESCRIPTION_SIZE_0_250));
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
     * For creating save module request
     * @return SaveModuleRequest
     */
    private SaveModuleRequest getSaveModuleSaveRequest(int action){
        SaveModuleRequest request = 
        	(SaveModuleRequest)getServiceRequest(
        			ServiceManager.SAVE_MODULE_REQUEST);
        
        ModuleVO moduleVO  = copyValueObjectFromBackingBean();
        if (action == 1){
            request.setCreateFlag(true);
        }
        if(action == 2){
            moduleVO.setModuleId(Integer.parseInt(this.getModuleId()));
            request.setCreateFlag(false);
        }
        request.setModuleVO(moduleVO);
        request.setSrdaFlag((String)getSession().getAttribute("SRDA_FLAG"));
       
        return request;
    }

    /**
     * Method for copying the value object from the backing bean
     * @return ModuleVO
     */
    private ModuleVO copyValueObjectFromBackingBean(){
        ModuleVO moduleVO = new ModuleVO();
        
        moduleVO.setModuleName(this.getModuleName().trim().toUpperCase());
        
        if(this.description.trim().equals(WebConstants.EMPTY_STRING))
            moduleVO.setDescription(" ");
        else
            moduleVO.setDescription(this.getDescription().trim().toUpperCase());
        
        return moduleVO;
    }
    /**
     * Method for copying the business object to the backing bean
     * @param moduleBO
     */
    public void copyBusinessObjectValuesToBackingBean(ModuleBO moduleBO){
        this.setModuleName(moduleBO.getModuleName().trim());
        this.setDescription(moduleBO.getDescription().trim());
        this.setCreatedUser(moduleBO.getCreatedUser());
        this.setCreatedTimestamp(moduleBO.getCreatedTimestamp());
        this.setLastUpdatedUser(moduleBO.getLastUpdatedUser());
        this.setLastUpdatedTimestamp(moduleBO.getLastUpdatedTimestamp());
        this.setModuleId(new Integer(moduleBO.getModuleId()).toString());
    }
    
    /**
     * Method for copying the business object to the backing bean
     * @param moduleSrdaBO
     */
    public void copyBusinessObjectValuesToBackingBeanSrda(ModuleSrdaBO moduleSrdaBO){
        this.setModuleName(moduleSrdaBO.getModuleName().trim());
        this.setDescription(moduleSrdaBO.getDescription().trim());
       
        this.setLastUpdatedUser(moduleSrdaBO.getLastUpdatedUser());
        this.setLastUpdatedTimestamp(moduleSrdaBO.getLastUpdatedTimestamp());
        this.setModuleId(new Integer(moduleSrdaBO.getModuleId()).toString());
    }
    
    /**
     * Method for getting module value object
     * @return ModuleVO
     */
    private ModuleVO getmoduleVOForAssociation(){
        ModuleVO moduleVO = new ModuleVO();
        HttpSession httpSession = getSession();
        int selectedId = 0;
        //Gets the mod_sys_id from session
        if(null != httpSession.getAttribute("SESSION_MOD_SYS_ID")){
        	selectedId = Integer.parseInt((String)httpSession.getAttribute("SESSION_MOD_SYS_ID"));
        }
        moduleVO.setModuleId(selectedId);
        //Gets the task_Id_list
        List taskIdList = WPDStringUtil.getListFromTildaString(
        		this.getTask(),2,1,1);
        //If the list is not empty sets the list to moduleVO
        if(null!=taskIdList && !taskIdList.isEmpty()){
            	moduleVO.setTaskIdList(taskIdList);
        }else{
            moduleVO.setTaskIdList(null);
        }
        //Set the module_id to session
        httpSession.setAttribute("SESSION_MOD_SYS_ID", selectedId 
        		+ WebConstants.EMPTY_STRING);
        return moduleVO;
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
     * @return Returns the moduleName.
     * @return String moduleName
     */
    public String getModuleName() {
        String modName = (String)getSession().getAttribute("SESSION_MOD_NAME");
        if(null != modName){
        	this.setModuleName(modName);
        }
        return moduleName;
    }
    /**
     * Sets the moduleName
     * @param moduleName
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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
	 * @param searchResultList The searchResultList to set.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}
	
	/**
	 * @param taskList The taskList to set.
	 */
	public void setTaskList(List taskList) {
		this.taskList = taskList;
	}
	/**
	 * @return Returns the taskTxt.
	 */
	public String getTaskTxt() {
		return taskTxt;
	}
	/**
	 * @param taskTxt The taskTxt to set.
	 */
	public void setTaskTxt(String taskTxt) {
		this.taskTxt = taskTxt;
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
	 * @return Returns the task.
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task The task to set.
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	
    /**
     * @return Returns the requiredModuleName.
     * @return boolean requiredModuleName
     */
    public boolean isRequiredModuleName() {
        return requiredModuleName;
    }
    /**
     * Sets the requiredModuleName
     * @param requiredModuleName
     */
    public void setRequiredModuleName(boolean requiredModuleName) {
        this.requiredModuleName = requiredModuleName;
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
     * @return Returns the moduleId.
     * @return String moduleId
     */
    public String getModuleId() {
        return moduleId;
    }
    /**
     * Sets the moduleId
     * @param moduleId
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    
	/**
	 * @return Returns the selectedModuleId.
	 */
	public int getSelectedModuleId() {
		return selectedModuleId;
	}
	/**
	 * @param selectedModuleId The selectedModuleId to set.
	 */
	public void setSelectedModuleId(int selectedModuleId) {
		this.selectedModuleId = selectedModuleId;
	}
    /**
     * @return Returns the selectedTaskId.
     * @return int selectedTaskId
     */
    public int getSelectedTaskId() {
        return selectedTaskId;
    }
    /**
     * Sets the selectedTaskId
     * @param selectedTaskId
     */
    public void setSelectedTaskId(int selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }
    
	
	/**
	 * @param viewModule The viewModule to set.
	 */
	public int setViewModule(int viewModule) {
		this.viewModule = viewModule;
		return this.viewModule;
	}
	
	public String getModuleMainPage(){
	    
	    HttpSession httpSession = getSession();
	    
	    // Get the module id from session
	    String moduleId = (String)httpSession.getAttribute("SESSION_MOD_SYS_ID");
	    
	    // Get the action from session
	    String action = (String)httpSession.getAttribute("action");
	    
	    // For view, get the moduleId from request
	    if(null == moduleId || moduleId.equals(WebConstants.EMPTY_STRING)){
	    	moduleId = getRequest().getParameter("moduleId"); 
	    	action = "view";
	    	getSession().setAttribute("action", "view");
	    }

	    //  Check whether action is not null and value equals 'View'
	    if(null!=action && action.equals("view"))
	    	//if true, forward to view page after setting breadcrumb
	    {
		    // Call the retrieveModule()
		    retrieveModuleDetails(moduleId, action);
	    	this.setBreadCrumbText("Administration >> Module " + "("
	    			+ this.moduleName + ") >> View");
	    	return WebConstants.VIEW_MODULE_DETAIL_PAGE;
	    }
	    // Else forward to normal edit page
	    else
	    {
		    // Call the retrieveModule()
		    retrieveModuleDetails(moduleId, "edit");
		    getBreadCrumbForEdit();
	    }
	    
	    return WebConstants.MODULE_MAIN_PAGE;
	}
	
	/**
	 * Method to navigate to the associated task details page
	 * while navigating from the tree 
	 * @return String
	 */
	public String getTaskDetailPage(){
	    HttpSession httpSession = getSession();
	    this.moduleName = (String)httpSession.getAttribute("SESSION_MOD_NAME");
	    this.setBreadCrumbText("Administration >> Module ("
	    		+ this.getModuleName() + ")>> Configure");
	    String action = (String)httpSession.getAttribute("action");
	    //  Check whether action is not null and value equals 'View'
	    if(null != action && action.equals("view"))
	    	//if true, forward to view page after setting breadcrumb
	    {
	    	this.setBreadCrumbText("Administration >> Module " + "(" 
	    			+ this.moduleName + ") >> View");
	    	return WebConstants.VIEW_TASK_DETAIL_PAGE;
	    }
	    // Else forward to normal edit page
	    else
	    {
	        getBreadCrumbForEdit();
	    }
	    return WebConstants.TASK_DETAILS_PAGE;
	}
	
	
	/**
	 * @return Returns the selectedTaskIds.
	 */
	public String getSelectedTaskIds() {
		return selectedTaskIds;
	}
	/**
	 * @param selectedTaskIds The selectedTaskIds to set.
	 */
	public void setSelectedTaskIds(String selectedTaskIds) {
		this.selectedTaskIds = selectedTaskIds;
	}
	
	/**
	 * @return Returns the selectedSubTaskIds.
	 */
	public String getSelectedSubTaskIds() {
		return selectedSubTaskIds;
	}
	/**
	 * @param selectedSubTaskIds The selectedSubTaskIds to set.
	 */
	public void setSelectedSubTaskIds(String selectedSubTaskIds) {
		this.selectedSubTaskIds = selectedSubTaskIds;
	}
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	public String getSrdaFlag() {
		return srdaFlag;
	}
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
