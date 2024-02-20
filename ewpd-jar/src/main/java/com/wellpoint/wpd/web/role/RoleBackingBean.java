/*
 * RoleBackingBean.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.security.bo.RoleBO;
import com.wellpoint.wpd.common.security.bo.RoleSrdaBo;
import com.wellpoint.wpd.common.security.request.DeleteRoleModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.DeleteTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveRoleAssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveRoleModuleAsssociationRequest;
import com.wellpoint.wpd.common.security.request.RetrieveRoleRequest;
import com.wellpoint.wpd.common.security.request.RoleModuleLookUpRequest;
import com.wellpoint.wpd.common.security.request.RoleTaskLookUpRequest;
import com.wellpoint.wpd.common.security.request.SaveRoleModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.SaveRoleRequest;
import com.wellpoint.wpd.common.security.request.SaveSubTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.SaveTaskAssociationRequest;
import com.wellpoint.wpd.common.security.response.DeleteTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleResponse;
import com.wellpoint.wpd.common.security.response.RoleModuleLookUpResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleResponse;
import com.wellpoint.wpd.common.security.response.SaveSubTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.TaskLookUpResponse;
import com.wellpoint.wpd.common.security.vo.RoleVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleBackingBean extends WPDBackingBean {

    private String roleName;

    private int roleId;
    
    private String srdaFlag;

    private List associatedSubTaskId;

    private int associatedModuleId;

    private List associatedTaskId;

    private String task;

    private String description;

    private List moduleList;

    private List taskList;

    private String subTaskName;

    private String taskTxt;

    private List searchResultList;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private List validationMessages;

    private List associatedTaskList;

    private boolean requiredRoleName;

    private List associatedSubTaskList;

    private String selectedModuleId;

    private int subTaskId;

    private int viewRoleId;

    private int selectedTaskId;

    private String treeBreadCrumbText;

    private String configureBreadCrumbText;

    private String viewBreadCrumbText;

    private String loadModulePopUpList;
    
    private String selectedModuleIds;
    
    private String selectedTaskIds;
    
    private String selectedSubTaskIds;
    
    private String roleIdHidden;
    

    /**
     * @return configureBreadCrumbText Returns the configureBreadCrumbText.
     */
    public String getConfigureBreadCrumbText() {
        if (null != getSession().getAttribute("SESSION_ROLE_NAME")) {
            this.setBreadCrumbText("Administration >> Role ("
                    + getSession().getAttribute("SESSION_ROLE_NAME")
                    + ") >> Configure");
        }

        return configureBreadCrumbText;
    }

    /**
     * @param configureBreadCrumbText
     *            Sets the configureBreadCrumbText.
     */
    public void setConfigureBreadCrumbText(String configureBreadCrumbText) {
        this.configureBreadCrumbText = configureBreadCrumbText;
    }

    /**
     * @return viewBreadCrumbText Returns the viewBreadCrumbText.
     */
    public String getTreeBreadCrumbText() {
        if (null != getSession().getAttribute("SESSION_ROLE_NAME")) {
            this.setBreadCrumbText("Administration >> Role ("
                    + getSession().getAttribute("SESSION_ROLE_NAME")
                    + ") >> Edit");
        }
        return treeBreadCrumbText;
    }

    /**
     * @param viewBreadCrumbText
     *            Sets the viewBreadCrumbText.
     */
    public void setTreeBreadCrumbText(String treeBreadCrumbText) {
        this.treeBreadCrumbText = treeBreadCrumbText;
    }

    /**
     * @return subTaskId
     * 
     * Returns the subTaskId.
     */
    public int getSubTaskId() {
        return subTaskId;
    }

    /**
     * @param subTaskId
     * 
     * Sets the subTaskId.
     */
    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }


    /**
     * @return associatedTaskList
     * 
     * Returns the associatedTaskList.
     */
    public List getAsscTaskList() {
    	
        RetrieveRoleAssociationRequest request = (RetrieveRoleAssociationRequest) 
			getServiceRequest(ServiceManager.ROLE_ASSOCIATION_REQUEST);

        // Get HttpSession
        HttpSession httpSession = getSession();

        // Get the roleId and moduleId from session
        String roleId = (String) httpSession
                .getAttribute("SESSION_ROLE_SYS_ID");
        String roleName = (String) httpSession
                .getAttribute("SESSION_ROLE_NAME");
        this.setRoleName(roleName);
        
        if("".equals(getRequest().getAttribute("RETAIN_Value")))
        	this.task=null;

        String moduleId = (String) httpSession.getAttribute("moduleIdFromTree");
        String action = (String) httpSession.getAttribute("SESSION_ACTION");

        // Set the subEntityType as 'Task' in the request
        request.setSubEntityType("Task");

        // Set the values in the request
        if (null != roleId && !roleId.equals(WebConstants.EMPTY_STRING) && null != moduleId
                && !moduleId.equals(WebConstants.EMPTY_STRING)) {
            request.setRoleId(Integer.parseInt(roleId));
            request.setAssociatedModuleId(Integer.parseInt(moduleId));
        }
        request.setSrdaFlag( (String)httpSession.getAttribute("SRDA_FLAG"));

        // Call the executeService() to get the response list
        RetrieveRoleAssociationResponse response = (RetrieveRoleAssociationResponse) executeService(request);

        // Check whether the list is null or empty
        if (null != response.getAssociatedEntityList()
                && !response.getAssociatedEntityList().isEmpty()) {

            // Set the list if true
            this.associatedTaskList = response.getAssociatedEntityList();

        } else {
            // Set the list to null if false
            this.associatedTaskList = null;
        }
        httpSession.setAttribute("SESSION_ROLE_NAME", roleName);
        return associatedTaskList;
    }
    
    public List getAssociatedTaskList() {
    	this.associatedTaskList = getAsscTaskList();
       return associatedTaskList;
    }

    /**
     * @param associatedTaskList
     * 
     * Sets the associatedTaskList.
     */
    public void setAssociatedTaskList(List associatedTaskList) {
        this.associatedTaskList = associatedTaskList;
    }

    // Sets the breadcrumb while loading the page initially

    public RoleBackingBean() {
        super();
        setBreadCrump();
    }

    protected void setBreadCrump() {
        this.setBreadCrumbText("Administration >> Role >> Create");
    }

    /**
     * Method to create a role
     * 
     * @return empty string
     */
    public String create() {

        SaveRoleRequest saveRoleRequest = null;
        SaveRoleResponse saveRoleResponse = null;

        //To validate the mandatory fields
        if (!validateRequiredFields()) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }

        saveRoleRequest = this.getSaveRoleRequest();

        //Setting that this is for create
        saveRoleRequest.setCreateFlag(true);
        saveRoleRequest.setSrdaFlag(this.srdaFlag);
        HttpSession httpSession = getSession();
         httpSession.setAttribute("SRDA_FLAG", this.srdaFlag);
        
        saveRoleResponse = (SaveRoleResponse) this
                .executeService(saveRoleRequest);

        if (null != saveRoleResponse) {
            if (saveRoleResponse.isSuccess()) {
                if (null != saveRoleResponse.getRoleBO()) {
                    //To set the values from the response to the backing bean
                    RoleBO roleBO = saveRoleResponse.getRoleBO();
                    setValuesFromBOToBackingBean(roleBO);
                   
                    //Sets the role_id and role_name to the session
                    httpSession.setAttribute("SESSION_ROLE_SYS_ID",
                            roleBO.getRoleId() + WebConstants.EMPTY_STRING);
                    httpSession.setAttribute("SESSION_ROLE_NAME", roleBO
                            .getRoleName());
                }else{

                    //To set the values from the response to the backing bean
                    RoleSrdaBo roleSrdaBO = saveRoleResponse.getRoleSrdaBO();
                    setValuesFromBOToBackingBean(roleSrdaBO);
                   
                    //Sets the role_id and role_name to the session
                    httpSession.setAttribute("SESSION_ROLE_SYS_ID",
                    		roleSrdaBO.getRoleId() + WebConstants.EMPTY_STRING);
                    httpSession.setAttribute("SESSION_ROLE_NAME", roleSrdaBO
                            .getRoleName());
                
                }
            } else {
                return WebConstants.EMPTY_STRING;
            }
        }

        this.setBreadCrumbText("Administration >> Role (" + this.roleName
                + ") >> Edit");
        return WebConstants.ROLE_EDIT;

    }

    /**
     * Sets the values from the business object to the backing bean
     * 
     * @param roleBO
     */
    private void setValuesFromBOToBackingBean(RoleBO roleBO) {
        this.setRoleName(roleBO.getRoleName().trim());
        this.setRoleId(roleBO.getRoleId());
        this.setDescription(roleBO.getDescription().trim());
        this.setCreatedUser(roleBO.getCreatedUser());
        this.setCreatedTimestamp(roleBO
                .getCreatedTimestamp());
        this.lastUpdatedUser = roleBO.getLastUpdatedUser();
        this.setLastUpdatedTimestamp(roleBO
                .getLastUpdatedTimestamp());
    }

    /**
     * 
     * @return SaveRoleRequest
     */
    private SaveRoleRequest getSaveRoleRequest() {

        SaveRoleRequest request = (SaveRoleRequest) this
                .getServiceRequest(ServiceManager.SAVE_ROLE_REQUEST);
        HttpSession session = getSession();
        RoleVO roleVO = new RoleVO();
        roleVO.setRoleName(this.getRoleName());
        roleVO.setRoleId(this.getRoleId());
        if (null != this.description && !this.description.equals(WebConstants.EMPTY_STRING)) {
            roleVO.setDescription(this.description.trim());
        } else {
            roleVO.setDescription(" ");
        }
        request.setRoleVO(roleVO);
        request.setSrdaFlag((String)session.getAttribute("SRDA_FLAG"));
        return request;
    }
    /**
     * method to load the role for editing
     */
    public String loadRoleForEdit() {

        this.setBreadCrumbText("Administration >> Role (" + this.roleName
                + ") >> Edit");
        
      
        RetrieveRoleResponse response = retrieveRole("edit");
        if (null!= response.getRoleBO()) {
            setValuesFromBOToBackingBean(response.getRoleBO());
            //Set the session values
            HttpSession httpSession = getSession();
            httpSession.setAttribute("SESSION_ROLE_SYS_ID", response
                    .getRoleBO().getRoleId() + WebConstants.EMPTY_STRING);
            httpSession.setAttribute("SESSION_ROLE_NAME", response.getRoleBO()
                    .getRoleName());
            // Set the tree state to null
            if (null != httpSession.getAttribute("SESSION_TREE_STATE_ROLE")) {
                httpSession.removeAttribute("SESSION_TREE_STATE_ROLE");

            }
            this.setBreadCrumbText("Administration >> Role ("
                    + response.getRoleBO().getRoleName() + ") >> Edit");
            httpSession.setAttribute("SESSION_ACTION", "edit");
        }
        else{
        	  setValuesFromBOToBackingBean(response.getRoleSrdaBo());
        	  //Set the session values
              HttpSession httpSession = getSession();
              httpSession.setAttribute("SESSION_ROLE_SYS_ID",response.getRoleSrdaBo().getRoleId() + WebConstants.EMPTY_STRING);
              httpSession.setAttribute("SESSION_ROLE_NAME", response.getRoleSrdaBo()
                      .getRoleName());
              // Set the tree state to null
              if (null != httpSession.getAttribute("SESSION_TREE_STATE_ROLE")) {
                  httpSession.removeAttribute("SESSION_TREE_STATE_ROLE");

              }
              this.setBreadCrumbText("Administration >> Role ("
                      + response.getRoleSrdaBo().getRoleName() + ") >> Edit");
              httpSession.setAttribute("SESSION_ACTION", "edit");
        }
      
        return WebConstants.ROLE_EDIT;
    }

    /**
     * Method to retrieve the specific role based upon the roleId
     * 
     * @param action
     * @return RetrieveRoleResponse
     */
    private RetrieveRoleResponse retrieveRole(String action) {
        HttpSession session = getSession();
        String roleName = ((String) session.getAttribute("SESSION_ROLE_NAME"));
        String roleID = ((String) session.getAttribute("SESSION_ROLE_SYS_ID"));
        RetrieveRoleRequest retrieveRoleRequest = 
        	(RetrieveRoleRequest) getServiceRequest(ServiceManager.RETRIEVE_ROLE_REQUEST);
        RoleBO roleBO = new RoleBO();
        RoleSrdaBo roleSrdaBO = new RoleSrdaBo();
        //Sets the roleId to the request
        if (this.getRoleId() != 0)
            retrieveRoleRequest.setRoleId(this.getRoleId());
        else if (null!= roleID)
            retrieveRoleRequest.setRoleId(Integer.parseInt(roleID));
        
        	retrieveRoleRequest.setSrdaFlag((String)session.getAttribute("SRDA_FLAG"));
        //Sets the action for normal retrieve
        retrieveRoleRequest.setAction(1);
        RetrieveRoleResponse retrieveRoleResponse = (RetrieveRoleResponse) executeService(retrieveRoleRequest);
        //Sets the value to the BO
        if(session.getAttribute("SRDA_FLAG").equals("SRDA")){
        	 if (null!= retrieveRoleResponse
                     && null!= retrieveRoleResponse.getRoleSrdaBo()) {
        		 roleSrdaBO = retrieveRoleResponse.getRoleSrdaBo();
                 if (null!= retrieveRoleResponse.getRoleSrdaBo().getRoleName())
                     roleName = retrieveRoleResponse.getRoleSrdaBo().getRoleName();
                 setValuesFromBOToBackingBeanSrda(roleSrdaBO);
             }
        }else{
        	 if (null!= retrieveRoleResponse
                     && null!= retrieveRoleResponse.getRoleBO()) {
                 roleBO = retrieveRoleResponse.getRoleBO();
                 if (null!= retrieveRoleResponse.getRoleBO().getRoleName())
                     roleName = retrieveRoleResponse.getRoleBO().getRoleName();
                 setValuesFromBOToBackingBean(roleBO);
             }
        }
       
        if (action.equals("edit"))
            this.setBreadCrumbText("Administration >> Role (" + roleName
                    + ") >> Edit");
        else if (action.equals("view")) {
            this.setBreadCrumbText("Administration >> Role (" + roleName
                    + ") >> View");
            session.setAttribute("SESSION_ROLE_NAME", roleName);
            session.setAttribute("SESSION_ROLE_SYS_ID", roleID);
        }
        return retrieveRoleResponse;
    }

    private void setValuesFromBOToBackingBeanSrda(RoleSrdaBo roleSrdaBO) {
    	  this.setRoleName(roleSrdaBO.getRoleName().trim());
          this.setRoleId(roleSrdaBO.getRoleId());
          this.setDescription(roleSrdaBO.getDescription().trim());
          this.setCreatedUser(roleSrdaBO.getCreatedUser());
          this.setCreatedTimestamp(roleSrdaBO
                  .getCreatedTimestamp());
          this.lastUpdatedUser = roleSrdaBO.getLastUpdatedUser();
          this.setLastUpdatedTimestamp(roleSrdaBO
                  .getLastUpdatedTimestamp());
	}

	/**
     * Method to validate the required fields
     * 
     * @return
     */
    public boolean validateRequiredFields() {
        validationMessages = new ArrayList(5);
        boolean requiredField = false;

        if (null == this.roleName || WebConstants.EMPTY_STRING.equals(this.roleName.trim())) {
            this.requiredRoleName = true;
            requiredField = true;
        }else{
        	this.roleName = this.roleName.trim();
        }
        if(this.roleName.length() > 30){
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.INVALID_NAME));
            return false;
        }
        //Validates the description length
        if (!this.description.trim().equals(WebConstants.EMPTY_STRING)
                && this.description.length() > 250) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_SIZE_1_250));
            return false;
        }
        //Validates description for special characters
        if(null != this.srdaFlag && this.srdaFlag.equalsIgnoreCase("SRDA")){
        	 if (!this.description.trim().matches("^[\\d|a-z|A-Z|\\s-]*$") ) {
                 this.validationMessages.add(new ErrorMessage(
                         WebConstants.DESCRIPTION_INVALID));
                 return false;
             }
        }else{
        	 if (!this.description.trim().matches("^[\\d|a-z|A-Z|\\s]*$") ) {
                 this.validationMessages.add(new ErrorMessage(
                         WebConstants.DESCRIPTION_INVALID));
                 return false;
             }
        }
       
        //Validates name for special characters
        if (!this.roleName.matches("^[\\d|a-z|A-Z|\\s]*$") ) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.CATALOG_NAME_INVALID));
            return false;
        }

        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }

        return true;
    }

    public boolean validationMessages() {
        return true;
    }

    /**
     * Method to get the associated subtasks of a role
     * 
     * @return associatedSubTask Returns the associatedSubTask.
     */
    public List getAssociatedSubTaskList() {

        HttpSession httpSession = getSession();
        // Get the role, module and task ids from session
        String roleId = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String moduleId = (String) httpSession.getAttribute("moduleIdFromTree");
        String taskId = (String) httpSession.getAttribute("taskIdFromTree");
        this.setRoleName((String)httpSession.getAttribute("SESSION_ROLE_NAME"));
        // Set the values in the request
        RetrieveRoleAssociationRequest request = (RetrieveRoleAssociationRequest) getServiceRequest(ServiceManager.ROLE_ASSOCIATION_REQUEST);
        if (null != roleId && !roleId.equals(WebConstants.EMPTY_STRING)) {
            request.setRoleId(Integer.parseInt(roleId));
        }
        if (null != moduleId && !moduleId.equals(WebConstants.EMPTY_STRING)) {
            request.setAssociatedModuleId(Integer.parseInt(moduleId));
        }
        if (null != taskId && !taskId.equals(WebConstants.EMPTY_STRING)) {
            request.setAssociatedTaskId(Integer.parseInt(taskId));
        }
        // If the list is for pop up, then get the action from HttpRequest
        String action = (String) getRequest().getParameter("subTaskAction");
        if (null != action) {
            request.setAction(action);
        }
        //Sets the entity type as subtask
        request.setSubEntityType("SubTask");
        // Call the executeService() to get the response
        RetrieveRoleAssociationResponse response = (RetrieveRoleAssociationResponse) this
                .executeService(request);
        // Check whether the response list is null or empty
        if (null != response.getAssociatedEntityList()
                && !response.getAssociatedEntityList().isEmpty()) {
            // If not, set it to the backing bean list
            associatedSubTaskList = response.getAssociatedEntityList();
        } else {
            // If true, set the list to null
            associatedSubTaskList = null;
        }
        request.setAction(null);
        httpSession.setAttribute("SESSION_ROLE_NAME", this.roleName);
        return associatedSubTaskList;
    }

    /**
     * Method to associate tasks to a role
     * 
     * @return
     */
    public String saveTaskAssociation() {
    	getRequest().setAttribute("RETAIN_Value","");
        SaveTaskAssociationRequest request = (SaveTaskAssociationRequest) this
                .getServiceRequest(ServiceManager.SAVE_TASK_ASSOCIATION_REQUEST);
        RoleVO roleVO = new RoleVO();
        HttpSession httpSession = getSession();
        String roleId = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String srdaFlag = (String)httpSession.getAttribute("SRDA_FLAG");
        //Get the list of taskId and rolModtaskId
        associatedTaskId = WPDStringUtil.getListFromTildaString(this.task, 3,
                1, 1);
        List rolMdTaskIdList = WPDStringUtil.getListFromTildaString(this.task,
                3, 3, 1);
        if (null != rolMdTaskIdList && !rolMdTaskIdList.isEmpty()) {
            int rlModTaskId = ((Integer) rolMdTaskIdList.get(0)).intValue();
            request.setRoleModTaskId(rlModTaskId);
        }
        if (null != this.task && !this.task.equals(WebConstants.EMPTY_STRING)) {
            roleVO.setTaskNameList(associatedTaskId);
        } else {
            roleVO.setTaskNameList(null);
        }
        roleVO.setRoleId(Integer.parseInt(roleId));
        request.setRoleVO(roleVO);
        request.setSrdaFlag(srdaFlag);
        // Call the executeService() to get the response if list is not empty
        if (null != associatedTaskId && !associatedTaskId.isEmpty()) {
            SaveTaskAssociationResponse response = (SaveTaskAssociationResponse) executeService(request);
            this.task = null;
        }
        this.setBreadCrumbText("Administration >> Role (" + this.roleName
                + ") >> Configure");

        return WebConstants.EMPTY_STRING;

    }

    /**
     * Method to associate subtasks to a role
     * 
     * @return
     */
    public String saveSubTaskAssociation() {
    	getRequest().setAttribute("RETAIN_Value","");
        SaveSubTaskAssociationRequest request = (SaveSubTaskAssociationRequest) this
                .getServiceRequest(ServiceManager.SAVE_SUBTASK_ASSOCIATION_REQUEST);

        // Set the Role_Mode_Task_Sys_Id and the subTask id to the request
        RoleVO roleVO=new RoleVO();
        HttpSession httpSession = getSession();
        String roleId = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        request.setTaskId(Integer.parseInt(roleId));
        request.setRoleModTaskId(this.roleId);
        if (null != this.subTaskName && !this.subTaskName.equals(WebConstants.EMPTY_STRING)) {

            // Get the selected sub Task Names to a list
            associatedSubTaskId = WPDStringUtil.getListFromTildaString(
                    this.subTaskName, 3, 1, 1);
            roleVO.setSubTaskNameList(associatedSubTaskId);
            request.setRoleVO(roleVO);
        }
        List rolMdTaskIdList = WPDStringUtil.getListFromTildaString(
                this.subTaskName, 3, 3, 1);
        if (null != rolMdTaskIdList && !rolMdTaskIdList.isEmpty()) {
            int rlModTaskId = ((Integer) rolMdTaskIdList.get(0)).intValue();
            request.setRoleModTaskId(rlModTaskId);
        }
        if (null != associatedSubTaskId && !associatedSubTaskId.isEmpty()) {
            // Call the executeService() to get the response
            SaveSubTaskAssociationResponse response = (SaveSubTaskAssociationResponse) this
                    .executeService(request);
            this.subTaskName = null;
        }
        this.setBreadCrumbText("Administration >> Role (" + this.roleName
                + ") >> Configure");
        return WebConstants.TASK_DETAILS_PAGE;

    }
   
    /**
     * Method to delete an associated task from a role
     * 
     * @return
     */
     public String deleteTaskAssociation() {
     	if(null==this.task||"".equals(this.task))
        	getRequest().setAttribute("RETAIN_Value", "");
    	List taskIdList = new ArrayList();
		//Get the selected Task Ids from selectedTaskIds
		String [] selectedTaskId = selectedTaskIds.split("~");
		if(selectedTaskId != null && selectedTaskId.length > 0) {
    		
    		for(int i=0; i<selectedTaskId.length; i++) {
    			//Adding selected taskIds to a list
    			taskIdList.add(selectedTaskId[i]);
    		}
    	}
        DeleteTaskAssociationRequest request = (DeleteTaskAssociationRequest) this
                .getServiceRequest(ServiceManager.DELETE_SUBTASK_ASSOCIATION_REQUEST);
       
        HttpSession httpSession = getSession();
        String roleId = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String srdaFlag = (String)httpSession.getAttribute("SRDA_FLAG");
        //setting roleId to subTaskId
        request.setSubTaskId(Integer.parseInt(roleId));
        // Set the Role_Mod_Task_Sys_Id and the task id to the request
        request.setAction(1);
        //?String moduleId = (String) httpSession.getAttribute("moduleIdFromTree");
        request.setRoleModTaskId(Integer.parseInt(this.getRoleIdHidden()));
        //?request.setRoleModTaskId(Integer.parseInt(moduleId));
        //setting the list of selected tasks to request
        request.setTaskIdList(taskIdList);
        //set the srda and eWPD flag
        request.setSrdaFlag(srdaFlag);
        request.setModuleId(this.associatedModuleId);

        // Call the executeService() to get the response
        DeleteTaskAssociationResponse response = (DeleteTaskAssociationResponse) this
                .executeService(request);
        return WebConstants.EMPTY_STRING;
    }

    /**
     * Method to delete an associated subtask from a role
     * 
     * @return
     */
    public String deleteSubTaskAssociation() {
    	if(null==this.subTaskName||"".equals(this.subTaskName))
        	getRequest().setAttribute("RETAIN_Value", "");
    	List subTaskIdList = new ArrayList();
		//Get the selected Task Ids from selectedTaskIds
		String [] selectedSubTaskId = selectedSubTaskIds.split("~");
		if(selectedSubTaskId != null && selectedSubTaskId.length > 0) {
    		
    		for(int i=0; i<selectedSubTaskId.length; i++) {
    			//Adding selected taskIds to a list
    			subTaskIdList.add(selectedSubTaskId[i]);
    		}
    	}
        DeleteTaskAssociationRequest request = (DeleteTaskAssociationRequest) this
                .getServiceRequest(ServiceManager.DELETE_SUBTASK_ASSOCIATION_REQUEST);
        HttpSession httpSession = getSession();
        String roleId = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String moduleId = (String) httpSession.getAttribute("moduleIdFromTree");
        //setting roleId to subTaskId
        request.setTaskId(Integer.parseInt(roleId));
        // Set the Role_Mod_Task_Sys_Id and the subTask id to the request
        request.setAction(2);
        request.setRoleModTaskId(Integer.parseInt(roleIdHidden));
       // request.setSubTaskId(this.subTaskId);
        request.setTaskIdList(subTaskIdList);
        
        // Call the executeService() to get the response
        DeleteTaskAssociationResponse response = (DeleteTaskAssociationResponse) this
                .executeService(request);
        return WebConstants.TASK_DETAILS_PAGE;
    }

    /**
     * @param associatedSubTask
     *            Sets the associatedSubTask.
     */
    public void setAssociatedSubTaskList(List associatedSubTaskList) {
        this.associatedSubTaskList = associatedSubTaskList;
    }

    /**
     * @return Returns the roleId.
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            The roleId to set.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the roleName.
     * @return String roleName
     */
    public String getRoleName() {
        setBreadCrump();
        return roleName;
    }

    /**
     * Sets the roleName
     * 
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @param moduleList
     *            The moduleList to set.
     */
    public void setModuleList(List moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * @return Returns the taskList.
     */
    public List getTaskList() {
        HttpSession httpSession = getSession();
        //Get the values of roleId and moduleId from session
        String roleId = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String srdaFlag = (String) httpSession.getAttribute("SRDA_FLAG");
        String moduleId = (String) httpSession.getAttribute("moduleIdFromTree");

        RoleTaskLookUpRequest request = (RoleTaskLookUpRequest) getServiceRequest(ServiceManager.ROLE_TASK_LOOKUP_REQUEST);
        //Sets the value to the request
        request.setRoleId(Integer.parseInt(roleId));
        request.setModuleId(Integer.parseInt(moduleId));
        request.setSrdaFlag(srdaFlag);
        // Call the executeService() to get the response
        TaskLookUpResponse response = (TaskLookUpResponse) executeService(request);
        //Sets the retrived list to the backing bean
        if (null!= response.getTaskList())
            this.setTaskList(response.getTaskList());
        httpSession.setAttribute("SESSION_ROLE_SYS_ID", roleId
                + WebConstants.EMPTY_STRING);

        return taskList;
    }

    
    /**
     * @param taskList
     *            The taskList to set.
     */
    public void setTaskList(List taskList) {
        this.taskList = taskList;
    }

      
    /**
     * @return Returns the task.
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task
     *            The task to set.
     */
    public void setTask(String task) {
        this.task = task;
    }

   
    /**
     * @return Returns the taskTxt.
     */
    public String getTaskTxt() {
        return taskTxt;
    }

    /**
     * @param taskTxt
     *            The taskTxt to set.
     */
    public void setTaskTxt(String taskTxt) {
        this.taskTxt = taskTxt;
    }

    /**
     * @param searchResultList
     *            The searchResultList to set.
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }

    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
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
     * @param createdUser
     *            The createdUser to set.
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
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
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
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    /**
     * Method to get the role general info page navigating from the tree
     * 
     * @return String
     */
    public String getMainPage() {

        // Get the role id from session
        String roleId = (String) getSession().getAttribute("SESSION_ROLE_SYS_ID");
        String action = (String) getSession().getAttribute("SESSION_ACTION");
        String roleName = ((String) getSession().getAttribute("SESSION_ROLE_NAME"));

        // Set the roleId in the backing bean
        if (null != roleId && !roleId.equals(WebConstants.EMPTY_STRING)) {
            this.roleId = Integer.parseInt(roleId);
        }

        // Call the retrieveRole() based upon the action
        if (null != action && !action.equals(WebConstants.EMPTY_STRING)) {
            if (action.equals("view")) {
                retrieveRole("view");
                this.setBreadCrumbText("Administration >> Role (" + roleName
                        + ") >> View");
                return WebConstants.LOAD_GENERAL_INFORMATION_VIEW;
            }
        } else {
            retrieveRole("edit");
            return WebConstants.ROLE_EDIT;
        }
        return WebConstants.EMPTY_STRING;
    }

    /**
     * Method to get the associated module detail page. It is used to retrieve
     * all the associated tasks of a particular module while navigating from the
     * tree
     * 
     * @return String
     */
    public String getModuleDetailPage() {

        HttpSession httpSession = getSession();
        String action = (String) getSession().getAttribute("SESSION_ACTION");
        // Get role id from session
        String roleId = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String roleName = (String) httpSession.getAttribute("SESSION_ROLE_NAME");

        // Get the module Id from session
        String moduleId = (String) httpSession.getAttribute("moduleIdFromTree");

        // Set the value in the backing bean
        if (null != roleId && !roleId.equals(WebConstants.EMPTY_STRING) && null != moduleId
                && !moduleId.equals(WebConstants.EMPTY_STRING)) {
            this.roleId = Integer.parseInt(roleId);

        }
        //call the getAssociatedTaskList() if the action is view
        if (null != action && !action.equals(WebConstants.EMPTY_STRING)) {
            if (action.equals("view")) {
                this.getAssociatedTaskList();
                this.setBreadCrumbText("Administration >> Role (" + roleName
                        + ") >> View");
                return WebConstants	.LOAD_TASK_ASSOCIATION_VIEW;
            }
        }
        // Call the retrieveRoleModuleConfiguration()
        return WebConstants.MODULE_DETAIL_PAGE;
    }

    /**
     * Method to get the associated task detail page It is used to retrieve all
     * the associated subtasks of a particular task. while navigating from the
     * tree
     * 
     * @return String
     */
    public String getTaskDetailPage() {

        HttpSession httpSession = getSession();
        // Get the task id from session
        String taskId = (String) httpSession.getAttribute("taskIdFromTree");
        String action = (String) httpSession.getAttribute("SESSION_ACTION");

        // Call the retrieveRoleTaskConfiguration()
        if (null != action && !action.equals(WebConstants.EMPTY_STRING)) {
            if (action.equals("view")) {
                this.getAssociatedSubTaskList();
                return WebConstants.LOAD_SUB_TASK_CONFIGURATION_VIEW;
            }
        }
        //for setting the sub-task div value null while navigating from
        //one task to another
        this.setSubTaskName(null);
        return WebConstants.TASK_DETAILS_PAGE;
    }

    /**
     * Method to load the Role general info page
     * 
     * @return
     */
    public String loadEditPage() {

        // Create an instance of HttpSession
        HttpSession httpSession = getSession();

        // Get the roleId from session
        String roleIdFromSession = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String action = (String) httpSession.getAttribute("SESSION_ACTION");

        // Check whether roleIdFromSession is null. Done when page needs to be
        // loaded from tree
        if (null != roleIdFromSession) {
            this.roleId = Integer.parseInt(roleIdFromSession);
        }
        //Call the retrieveRole() if the action is view
        if (null != action && !action.equals(WebConstants.EMPTY_STRING)) {
            if (action.equals("view")) {
                retrieveRole("view");
                return WebConstants.LOAD_GENERAL_INFORMATION_VIEW;
            }
        }
        // Call the retrieveRole(). The roleId is obtained from the backingbean
        retrieveRole("edit");

        return WebConstants.ROLE_EDIT;
    }

    /**
     * @return validationMessages
     * 
     * Returns the validationMessages.
     */
    public List getValidationMessages() {
        return validationMessages;
    }

    /**
     * @param validationMessages
     * 
     * Sets the validationMessages.
     */

    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }

    /**
     * Method to edit the role
     * 
     * @return
     */
    public String editRole() {

        SaveRoleRequest saveRoleRequest = null;
      
        SaveRoleResponse saveRoleResponse = null;
        //Validates the required fields
        
        if (!validateRequiredFields()) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }

        //Creating the request object
        saveRoleRequest = this.getSaveRoleRequest();

        saveRoleRequest.setCreateFlag(false);

        //Creating the response object
        
        saveRoleResponse = (SaveRoleResponse) this
                .executeService(saveRoleRequest);

        //Sets the value to the backing bean
        if (null != saveRoleResponse) {
        	
            if (null != saveRoleResponse.getRoleBO()) {
                if (saveRoleResponse.isSuccess()) {
                    RoleBO roleBO = saveRoleResponse.getRoleBO();
                    setValuesFromBOToBackingBean(roleBO);

                } else {
                    return WebConstants.EMPTY_STRING;
                }
            }else{
            	 if (saveRoleResponse.isSuccess()) {
                     RoleSrdaBo roleSrdaBO = saveRoleResponse.getRoleSrdaBO();
                     setValuesFromBOToBackingBean(roleSrdaBO);

                 } else {
                     return WebConstants.EMPTY_STRING;
                 }
            }
        }

        this.setBreadCrumbText("Administration >> Role (" + this.roleName
                + ") >> Edit");
        return WebConstants.ROLE_EDIT;

    }

    /**
     * Sets the values from the business object to the backing bean
     * 
     * @param roleBO
     */
    private void setValuesFromBOToBackingBean(RoleSrdaBo roleSrdaBO) {
		// TODO Auto-generated method stub
    	 this.setRoleName(roleSrdaBO.getRoleName().trim());
         this.setRoleId(roleSrdaBO.getRoleId());
         this.setDescription(roleSrdaBO.getDescription().trim());
         this.setCreatedUser(roleSrdaBO.getCreatedUser());
         this.setCreatedTimestamp(roleSrdaBO
                 .getCreatedTimestamp());
         this.lastUpdatedUser = roleSrdaBO.getLastUpdatedUser();
         this.setLastUpdatedTimestamp(roleSrdaBO
                 .getLastUpdatedTimestamp());
	}
    
    

	/**
     * @return Returns the searchResultList.
     */
    public List getSearchResultList() {
        HttpSession session = getSession();
        //Gets the role_id and role_name from session
        String roleIdFromSession = (String) session.getAttribute("SESSION_ROLE_SYS_ID");
        String roleName = (String) session.getAttribute("SESSION_ROLE_NAME");
        String srdaFlag = (String)session.getAttribute("SRDA_FLAG");
        this.setRoleName(roleName);
        int selectedRoleId = Integer.parseInt(roleIdFromSession);
        RetrieveRoleModuleAsssociationRequest request = 
        	(RetrieveRoleModuleAsssociationRequest) getServiceRequest(ServiceManager.RETRIEVE_ROLE_MODULE_REQUEST);
        //Sets the role_id to the request
        request.setRoleId(selectedRoleId);
        //sets srda and ewpd flag
        request.setSrdaFlag(srdaFlag);
        //Calls the execute service to obtain the response
        RetrieveRoleModuleAssociationResponse response = 
        	(RetrieveRoleModuleAssociationResponse) executeService(request);
        //Sets the obtained list from response to backing bean
        if (null!= response &&null!= response.getModuleAssociationList()
                && !response.getModuleAssociationList().isEmpty())
            this.setSearchResultList(response.getModuleAssociationList());
        else
            this.setSearchResultList(null);
        return searchResultList;
    }

    /**
     * Sets the session values and breadcrumb
     * 
     * @return
     */
    public String loadRoleConfiguration() {
        int selectedRoleId = this.getRoleId();
        HttpSession session = getSession();
        session.setAttribute("SESSION_ROLE_SYS_ID", selectedRoleId
                + WebConstants.EMPTY_STRING);
        session.setAttribute("SESSION_ROLE_NAME", this.roleName);
        session.setAttribute("SESSION_ACTION", "edit");
        this.setBreadCrumbText("Administration >> Role (" + this.roleName
                + ") >> Configure");
        return WebConstants.LOAD_ROLE_CONFIGURATION;
    }

    /**
     * Method to associate the selected modules with the role
     * 
     * @return
     */
    public String saveRoleModuleAssociation() {
    	getRequest().setAttribute("RETAIN_Value","");
        HttpSession session = getSession();
        SaveRoleModuleAssociationRequest request = 
        	(SaveRoleModuleAssociationRequest) getServiceRequest(
        			ServiceManager.SAVE_ROLE_MODULE_ASSOCIATION_REQUEST);
        RoleVO roleVO = new RoleVO();
        //Gets the list of module_id
        List moduleId = WPDStringUtil.getListFromTildaString(this
                .getSelectedModuleId(), 2, 2, 1);
        //Gets the values of role_id and role_name from session
        String roleIdFromSession = (String) session.getAttribute("SESSION_ROLE_SYS_ID");
        int selectedRoleId = Integer.parseInt(roleIdFromSession);
        String roleName = ((String) session.getAttribute("SESSION_ROLE_NAME"));
        //Sets the values to the rolevO
        if (null!= moduleId && !moduleId.isEmpty())
            roleVO.setModuleIdList(moduleId);
        else
            roleVO.setModuleIdList(null);
        roleVO.setRoleId(selectedRoleId);
        //Sets the values to the request
        request.setRoleVO(roleVO);
        //Call the execute service() to obtain the response
        SaveRoleModuleAssociationResponse response = (SaveRoleModuleAssociationResponse) executeService(request);
        this.setBreadCrumbText("Administration >> Role (" + roleName
                + ") >> Configure");
        session.setAttribute("SESSION_ROLE_SYS_ID", selectedRoleId
                + WebConstants.EMPTY_STRING);
        //Sets the selected module_id to null
        this.setSelectedModuleId(null);
        return WebConstants.EMPTY_STRING;
    }

    /**
     * Method for retrieving the non-associated modules of the selected role
     * 
     * @return Returns the moduleList.
     */
    public List getModuleList() {
        return moduleList;
    }
 
    /**
     * Method to delete the association with the role of the selected module
     * 
     * @return
     */
    public String deleteModuleAssociation() {
    	if(null==this.selectedModuleId||"".equals(this.selectedModuleId))
    	getRequest().setAttribute("RETAIN_Value", "");
    	List moduleIdList = new ArrayList();
		//Get the selected Module Ids from selectedModuleIds
		String [] selectedModuleId = selectedModuleIds.split("~");
		if(selectedModuleIds != null && selectedModuleId.length > 0) {
    		
    		for(int i=0; i<selectedModuleId.length; i++) {
    			//Adding selected taskIds to a list
    			moduleIdList.add(selectedModuleId[i]);
    		}
    	}
        HttpSession session = getSession();
        //Obtain the values of role_id and role_name from session
        String roleName = ((String) session.getAttribute("SESSION_ROLE_NAME"));
        String roleIdFromSession = (String) session.getAttribute("SESSION_ROLE_SYS_ID");
        DeleteRoleModuleAssociationRequest request = 
        	(DeleteRoleModuleAssociationRequest) getServiceRequest(
        			ServiceManager.DELETE_ROLE_MODULE_ASSOCIATION_REQUEST);
        int selectedRoleId = Integer.parseInt(roleIdFromSession);
        //Sets the values to the request
        request.setRoleModuleId(selectedRoleId);
        request.setModuleIdList(moduleIdList);
       //* request.setModuleId(this.getAssociatedModuleId());
        //Calls the execute service() to obtain response
        RoleModuleLookUpResponse response = (RoleModuleLookUpResponse) executeService(request);
        this.setBreadCrumbText("Administration >> Role (" + roleName
                + ") >> Configure");
        return WebConstants.EMPTY_STRING;
    }

    /**
     * @return Returns the viewRoleId.
     * @return String viewRoleId
     */
    public int getViewRoleId() {
        HttpSession httpSession = getSession();
        int id = 0;
        String action = ESAPI.encoder().encodeForHTML(getRequest().getParameter("action"));
        if(null!=action  && action.matches("[0-9a-zA-Z_]+")){
        	action = action;
		}
        if(null == action){
        	action = (String)httpSession.getAttribute("SESSION_ACTION");
        }
        // Get the roleId from session
        String roleID = getRequest().getParameter("roleId");
        if (null!= roleID) {
            id = Integer.parseInt(roleID);
            httpSession.setAttribute("SESSION_ROLE_SYS_ID", id
                    + WebConstants.EMPTY_STRING);
            // Set the tree state to null
            if (null != httpSession.getAttribute("SESSION_TREE_STATE_ROLE")) {
                httpSession.setAttribute("SESSION_TREE_STATE_ROLE", null);
            }
        }

        // Call the retrieveRole(). The roleId is obtained from the backingbean
        if(action.equals("view")){
        	httpSession.setAttribute("SESSION_ACTION", action);
        }
        retrieveRole(action);
        return viewRoleId;
    }

    public String loadGeneralInformationView() {
        return WebConstants.LOAD_GENERAL_INFORMATION_VIEW;
    }

    /**
     * Method to display the associated modules in the role configuration view
     * page
     * 
     * @return
     */
    public String loadRoleConfigurationView() {
        HttpSession httpSession = getSession();
        //      Obtain the values of role_id and role_name from session
        String roleID = (String) httpSession.getAttribute("SESSION_ROLE_SYS_ID");
        String roleName = ((String) httpSession.getAttribute("SESSION_ROLE_NAME"));
        String srdaFlag = (String)httpSession.getAttribute("SRDA_FLAG");
        int selectedRoleId = 0;
        if (null!= roleID)
            selectedRoleId = Integer.parseInt(roleID);
        RetrieveRoleModuleAsssociationRequest request = 
        	(RetrieveRoleModuleAsssociationRequest) getServiceRequest(
        			ServiceManager.RETRIEVE_ROLE_MODULE_REQUEST);
        //      Sets the values to the request
        request.setRoleId(selectedRoleId);
        request.setSrdaFlag(srdaFlag);
        //      Calls the execute service() to obtain response
        RetrieveRoleModuleAssociationResponse response = 
        	(RetrieveRoleModuleAssociationResponse) executeService(request);
        //Sets the search-result_list obtained from the response
        if (null!= response&& null!= response.getModuleAssociationList()
                && !response.getModuleAssociationList().isEmpty()){
            this.setSearchResultList(response.getModuleAssociationList());
        }else{
            this.setSearchResultList(null);
        }
        this.setBreadCrumbText("Administration >> Role (" + roleName
                + ") >> View");
        httpSession.setAttribute("SESSION_ROLE_SYS_ID", 
                selectedRoleId + WebConstants.EMPTY_STRING);
        httpSession.setAttribute("SESSION_ROLE_NAME", roleName);
        return WebConstants.ROLE_CONFIGURATION_VIEW_PAGE;
    }

    /**
     * @return requiredRoleName
     * 
     * Returns the requiredRoleName.
     */
    public boolean isRequiredRoleName() {
        return requiredRoleName;
    }

    /**
     * @return subTaskName
     * 
     * Returns the subTaskName.
     */
    public String getSubTaskName() {
        return subTaskName;
    }

    /**
     * @param subTaskName
     * 
     * Sets the subTaskName.
     */
    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    /**
     * @param requiredRoleName
     * 
     * Sets the requiredRoleName.
     */
    public void setRequiredRoleName(boolean requiredRoleName) {
        this.requiredRoleName = requiredRoleName;
    }


    /**
     * Sets the selectedModuleId
     * 
     * @param selectedModuleId
     */
    public void setSelectedModuleId(String selectedModuleId) {
        this.selectedModuleId = selectedModuleId;
    }

    /**
     * @return Returns the selectedModuleId.
     * @return String selectedModuleId
     */
    public String getSelectedModuleId() {
        return selectedModuleId;
    }

   

    /**
     * Sets the viewRoleId
     * 
     * @param viewRoleId
     */
    public void setViewRoleId(int viewRoleId) {
        this.viewRoleId = viewRoleId;
    }

    /**
     * @return associatedModuleId
     * 
     * Returns the associatedModuleId.
     */
    public int getAssociatedModuleId() {
        return associatedModuleId;
    }

    /**
     * @param associatedModuleId
     * 
     * Sets the associatedModuleId.
     */
    public void setAssociatedModuleId(int associatedModuleId) {
        this.associatedModuleId = associatedModuleId;
    }

    /**
     * @return associatedSubTaskId
     * 
     * Returns the associatedSubTaskId.
     */
    public List getAssociatedSubTaskId() {
        return associatedSubTaskId;
    }

    /**
     * @param associatedSubTaskId
     * 
     * Sets the associatedSubTaskId.
     */
    public void setAssociatedSubTaskId(List associatedSubTaskId) {
        this.associatedSubTaskId = associatedSubTaskId;
    }

    /**
     * @return associatedTaskId
     * 
     * Returns the associatedTaskId.
     */
    public List getAssociatedTaskId() {
        return associatedTaskId;
    }

    /**
     * @param associatedTaskId
     * 
     * Sets the associatedTaskId.
     */
    public void setAssociatedTaskId(List associatedTaskId) {
        this.associatedTaskId = associatedTaskId;
    }

    /**
     * @return selectedTaskId
     * 
     * Returns the selectedTaskId.
     */
    public int getSelectedTaskId() {
        return selectedTaskId;
    }

    /**
     * @param selectedTaskId
     * 
     * Sets the selectedTaskId.
     */
    public void setSelectedTaskId(int selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }


    /**
     * @return Returns the viewBreadCrumbText.
     * @return String viewBreadCrumbText
     */
    public String getViewBreadCrumbText() {
        if (null != getSession().getAttribute("SESSION_ROLE_NAME")) {
            this.setBreadCrumbText("Administration >> Role ("
                    + getSession().getAttribute("SESSION_ROLE_NAME")
                    + ") >> View");
        }
        return viewBreadCrumbText;
    }

    /**
     * Sets the viewBreadCrumbText
     * @param viewBreadCrumbText
     */
    public void setViewBreadCrumbText(String viewBreadCrumbText) {
        this.viewBreadCrumbText = viewBreadCrumbText;
    }
    
    
    
	/**
	 * @return Returns the loadModulePopUpList.
	 */
	public String getLoadModulePopUpList() {
		
        HttpSession session = getSession();
        RoleModuleLookUpRequest request = 
        	(RoleModuleLookUpRequest) getServiceRequest(
        			ServiceManager.ROLE_MODULE_LOOKUP_REQUEST);
        //Gets the value of role_id from session
        String roleIdFromSession = (String) session.getAttribute("SESSION_ROLE_SYS_ID");
        int selectedRoleId = Integer.parseInt(roleIdFromSession);
        //Sets the role_id to the request
        request.setRoleId(selectedRoleId);
        //Calls the execute service() to obtain response
        RoleModuleLookUpResponse response = 
        	(RoleModuleLookUpResponse) executeService(request);
        //Sets the module_list obtained from the response
        if (null!= response   && !response.getModuleList().isEmpty()){
            this.setModuleList(response.getModuleList());
        }else{
            this.setModuleList(null);
        }
		return loadModulePopUpList;
	}
	/**
	 * @param loadModulePopUpList The loadModulePopUpList to set.
	 */
	public void setLoadModulePopUpList(String loadModulePopUpList) {
		this.loadModulePopUpList = loadModulePopUpList;
	}
	
	
	/**
	 * @return Returns the selectedModuleIds.
	 */
	public String getSelectedModuleIds() {
		return selectedModuleIds;
	}
	/**
	 * @param selectedModuleIds The selectedModuleIds to set.
	 */
	public void setSelectedModuleIds(String selectedModuleIds) {
		this.selectedModuleIds = selectedModuleIds;
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
	 * @return Returns the roleIdHidden.
	 */
	public String getRoleIdHidden() {
		return roleIdHidden;
	}
	/**
	 * @param roleIdHidden The roleIdHidden to set.
	 */
	public void setRoleIdHidden(String roleIdHidden) {
		this.roleIdHidden = roleIdHidden;
	}

	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}