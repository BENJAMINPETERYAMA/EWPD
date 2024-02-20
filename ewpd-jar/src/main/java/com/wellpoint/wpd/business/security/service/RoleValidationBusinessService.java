/*
 * RoleValidationBusinessService.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.security.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.security.builder.RoleBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.security.bo.RoleBO;
import com.wellpoint.wpd.common.security.bo.RoleConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleConfigSrdaBO;
import com.wellpoint.wpd.common.security.bo.RoleSrdaBo;
import com.wellpoint.wpd.common.security.bo.RoleTaskConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleTaskSrdaConfigBO;
import com.wellpoint.wpd.common.security.request.DeleteRoleModuleAssociationRequest;
import com.wellpoint.wpd.common.security.request.DeleteTaskAssociationRequest;
import com.wellpoint.wpd.common.security.request.RoleDeleteRequest;
import com.wellpoint.wpd.common.security.request.SaveRoleRequest;
import com.wellpoint.wpd.common.security.response.DeleteTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.RoleDeleteResponse;
import com.wellpoint.wpd.common.security.response.RoleModuleLookUpResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleResponse;
import com.wellpoint.wpd.common.security.vo.RoleVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleValidationBusinessService extends WPDBusinessValidationService {

    List messageList = null;

    /**
     * Method for validating whether the entered role is present
     * 
     * @param SaveRoleRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveRoleRequest request) throws ServiceException {
        // Getting the response object from the Response Factory
        SaveRoleResponse response = (SaveRoleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_ROLE_RESPONSE);
        //Set the values from request to VO
        RoleVO roleVO = request.getRoleVO();
        //Set the values from VO to BO
      
        RoleBusinessObjectBuilder roleBuilder = new RoleBusinessObjectBuilder();
        messageList = new ArrayList(1);
        try {
            //If there is role present with the same name setsuccess flag as
            // false
            //and add the error message
            //If not set flag as true
        	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		  RoleSrdaBo roleSrdaBO = getRoleBusinessObjectSrda(roleVO);
        		 if (request.isCreateFlag() && roleBuilder.isRoleDuplicate(roleSrdaBO)) {
                     response.setSuccess(false);
                     messageList.add(new ErrorMessage("role.name.duplicate"));
                     response.setMessages(messageList);
                 } else {
                     response.setSuccess(true);
                 }
        	}else{
        		  RoleBO roleBO = getRoleBusinessObject(roleVO);
        		 if (request.isCreateFlag() && roleBuilder.isRoleDuplicate(roleBO)) {
                     response.setSuccess(false);
                     messageList.add(new ErrorMessage("role.name.duplicate"));
                     response.setMessages(messageList);
                 } else {
                     response.setSuccess(true);
                 }
        	}
           
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing Save role Request";
            throw new ServiceException(logMessage, logParameters, e);
        }

        return response;
    }
    
    private RoleSrdaBo getRoleBusinessObjectSrda(RoleVO roleVO) {
    	RoleSrdaBo roleSrdaBO = new RoleSrdaBo();
    	roleSrdaBO.setRoleId(roleVO.getRoleId());

        if (null != roleVO.getRoleName()) {
        	roleSrdaBO.setRoleName(roleVO.getRoleName().toUpperCase().trim());
        }
        if (null != roleVO.getDescription()) {
        	roleSrdaBO.setDescription(roleVO.getDescription());
        }
        return roleSrdaBO;
    }

    /**
     * Method for validating whether the selected role is associated with any
     * module before deletion
     * 
     * @param RoleDeleteRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(RoleDeleteRequest request)
            throws ServiceException {
        // Getting the response object from the Response Factory
        RoleDeleteResponse response = (RoleDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_ROLE_RESPONSE);
       
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        messageList = new ArrayList(1);
        List locateResults = null;
        try {
            //If the role is associated set the success flag as false
            //If not set the success flag as true
        	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		RoleConfigSrdaBO roleConfigBO = new RoleConfigSrdaBO();
    	        //Set the values from request to BO
    	        roleConfigBO.setRoleId(request.getRoleVO().getRoleId());
    	        locateResults = builder.isRoleAssociated(roleConfigBO);
        	}else{
        		 	RoleConfigBO roleConfigBO = new RoleConfigBO();
        	        //Set the values from request to BO
        	        roleConfigBO.setRoleId(request.getRoleVO().getRoleId());
        	        locateResults = builder.isRoleAssociated(roleConfigBO);
        	}
         
            if (null!= locateResults && !locateResults.isEmpty()) {
                response.setSuccess(false);
                messageList.add(new ErrorMessage("role.module.associated"));
                response.setMessages(messageList);
            } else
                response.setSuccess(true);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RoleDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Validates whether the task is associated
     * 
     * @param DeleteTaskAssociationRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteTaskAssociationRequest request)
            throws ServiceException {
        // Getting the response object from the Response Factory
        DeleteTaskAssociationResponse response = (DeleteTaskAssociationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_TASK_ASSOCIATION_RESPONSE);
        //Set the values from request to BO
        RoleTaskConfigBO roleTaskConfigBO = new RoleTaskConfigBO();
        RoleTaskSrdaConfigBO roleTaskConfigSrdaBO = new RoleTaskSrdaConfigBO();
        if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        	
        	roleTaskConfigSrdaBO.setRolModTaskId(request.getRoleModTaskId());
        	roleTaskConfigSrdaBO.setTaskIdList(request.getTaskIdList());
            //*roleTaskConfigBO.setTaskId(request.getTaskId());
        	roleTaskConfigSrdaBO.setModuleId(request.getModuleId());
        }else{
      
        	roleTaskConfigBO.setRolModTaskId(request.getRoleModTaskId());
        	roleTaskConfigBO.setTaskIdList(request.getTaskIdList());
        	//*roleTaskConfigBO.setTaskId(request.getTaskId());
        	roleTaskConfigBO.setModuleId(request.getModuleId());
        }
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        messageList = new ArrayList(1);
        List locateResults = null;
        try {
            //If the task is associated set the success flag as false
            //If not set the success flag as true
        	if(request.getSrdaFlag().equalsIgnoreCase("SRDA")){
        		//for Srda there is no subtask
        		response.setSuccess(true);
        	}else{
        		 locateResults = builder
                         .isRoleModuleTaskAssociated(roleTaskConfigBO);
        	}
           
            if (null!= locateResults  && !locateResults.isEmpty()) {
                response.setSuccess(false);
                messageList.add(new ErrorMessage("role.task.associated"));
                response.setMessages(messageList);
            } else
                response.setSuccess(true);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteTaskAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }

    /**
     * Validates whether the module is associated with role
     * 
     * @param DeleteRoleModuleAssociationRequest
     * @return
     * @throws ServiceException
     */

    public WPDResponse execute(DeleteRoleModuleAssociationRequest request)
            throws ServiceException {
        // Getting the response object from the Response Factory
        RoleModuleLookUpResponse response = (RoleModuleLookUpResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ROLE_MODULE_LOOKUP_RESPONSE);
        //Set the values from request to BO
        RoleTaskConfigBO configBO = new RoleTaskConfigBO();
        //*configBO.setModuleId(request.getModuleId());
        configBO.setModuleIdList(request.getModuleIdList());
        configBO.setRolModTaskId(request.getRoleModuleId());
        RoleBusinessObjectBuilder builder = new RoleBusinessObjectBuilder();
        messageList = new ArrayList(1);
        List locateResults = null;
        try {
            //If the module is associated set the success flag as false
            //If not set the success flag as true
            locateResults = builder.isModuleAssociated(configBO);
            if (null!= locateResults  && !locateResults.isEmpty()) {
                response.setSuccess(false);
                messageList.add(new ErrorMessage("module.task.association"));
            } else
                response.setSuccess(true);

        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteRoleModuleAssociationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        response.setMessages(messageList);
        return response;
    }

    /**
     * Method to set the values from VO to BO
     * 
     * @param roleVO
     * @return
     */
    private RoleBO getRoleBusinessObject(RoleVO roleVO) {
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleId(roleVO.getRoleId());

        if (null != roleVO.getRoleName()) {
            roleBO.setRoleName(roleVO.getRoleName().toUpperCase().trim());
        }
        if (null != roleVO.getDescription()) {
            roleBO.setDescription(roleVO.getDescription());
        }
        return roleBO;
    }

}