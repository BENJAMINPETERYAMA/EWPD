/*
 * RoleSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.role;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.security.bo.RoleBO;
import com.wellpoint.wpd.common.security.bo.RoleSrdaBo;
import com.wellpoint.wpd.common.security.request.RoleDeleteRequest;
import com.wellpoint.wpd.common.security.request.SearchRoleRequest;
import com.wellpoint.wpd.common.security.response.RoleDeleteResponse;
import com.wellpoint.wpd.common.security.response.SearchRoleResponse;
import com.wellpoint.wpd.common.security.vo.RoleVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleSearchBackingBean extends WPDBackingBean {

    private String roleName;

    private int selectedRoleId;
    
    private String srdaFlag;

    private List searchResultList;

    private String selectedModuleName;

    private String selectedTaskName;

    private String selectedSubTaskName;

    private List validationMessage;
    
    private String selectedRadioFlag;

    /**
	 * @return the selectedRadioFlag
	 */
	public String getSelectedRadioFlag() {
		return selectedRadioFlag;
	}

	/**
	 * @param selectedRadioFlag the selectedRadioFlag to set
	 */
	public void setSelectedRadioFlag(String selectedRadioFlag) {
		this.selectedRadioFlag = selectedRadioFlag;
	}

	private static final int MAX_SEARCH_RESULT_SIZE = 50;

    public RoleSearchBackingBean() {
        this.setBreadCrumbText("Administration >> Role " + ">> Locate");
    }

    public String roleSearch() {

    	
        // Validate the fields
        if (!validateFields()) {
            // Add the messages to the request
            addAllMessagesToRequest(this.validationMessage);

            // Return the forward string
            return WebConstants.EMPTY_STRING;
        }
        //String s1=getRequest().getParameter("SrdaFlag");
        HttpSession httpSession = getSession();
        httpSession.setAttribute("SRDA_FLAG", this.srdaFlag);
        
        SearchRoleRequest searchRoleRequest = null;
        SearchRoleResponse searchRoleResponse = null;
        searchRoleRequest = this.getSearchRoleRequest();
        searchRoleResponse = (SearchRoleResponse) this
                .executeService(searchRoleRequest);
        if (null != searchRoleResponse.getRoleSearchResultList()
                && !searchRoleResponse.getRoleSearchResultList().isEmpty()) {
            // If not, set the list to the backing bean
            this.searchResultList = searchRoleResponse
                    .getRoleSearchResultList();
            
            if(httpSession.getAttribute("SRDA_FLAG").equals("SRDA")){
            	forSrdaDescriptionDisplay(searchResultList);
            }else{
            	forDescriptionDisplay(searchResultList);
            }
            
        } else {
            // If yes, set the list to null in the backing bean
            this.searchResultList = null;
        }
        // Set the bread crumb to locate
        this.setBreadCrumbText("Administration >> Role " + ">> Locate");
        return WebConstants.EMPTY_STRING;
    }

    /**
     * for displaying decription with size '15'
     */
    private void forDescriptionDisplay(List searchResultList) {
        for(int i = 0 ; i < searchResultList.size() ; i++){
            RoleBO roleBO = (RoleBO) searchResultList.get(i);
            String description = "";
            if(roleBO.getDescription().length() > 15){
                description = roleBO.getDescription();
                description = description.substring(0,15).concat("...");
                roleBO.setDescription(description);
            }
        }
    }

    private void forSrdaDescriptionDisplay(List searchResultList2) {
 		// TODO Auto-generated method stub
         for(int i = 0 ; i < searchResultList.size() ; i++){
             RoleSrdaBo roleBO = (RoleSrdaBo) searchResultList.get(i);
             String description = "";
             if(roleBO.getDescription().length() > 15){
                 description = roleBO.getDescription();
                 description = description.substring(0,15).concat("...");
                 roleBO.setDescription(description);
             }
         }
 	} 
    private SearchRoleRequest getSearchRoleRequest() {
        SearchRoleRequest searchRoleRequest = (SearchRoleRequest) this
                .getServiceRequest(ServiceManager.SEARCH_ROLE_REQUEST);
        RoleVO roleVO = new RoleVO();

        roleVO=setValuesToVOFromSearchCriteria(roleVO);
        searchRoleRequest.setRoleVO(roleVO);
        searchRoleRequest.setSrdaFlag(this.srdaFlag);
        return searchRoleRequest;
    }

    /**
     * Method to delete module
     * 
     * @return String
     */
    public String deleteRole() {
        Logger.logInfo("RoleSearchBackingBean - Entering deleteRole(): Role");
        RoleDeleteRequest roleDeleteRequest = (RoleDeleteRequest) this
                .getServiceRequest(ServiceManager.DELETE_ROLE_REQUEST);
        RoleVO roleVO = new RoleVO();
        roleVO.setRoleId(this.selectedRoleId);
        roleVO=setValuesToVOFromSearchCriteria(roleVO);
        //Set the VO to request
        HttpSession httpSession = getSession();
        if(null != httpSession.getAttribute("SRDA_FLAG")){
        	roleDeleteRequest.setSrdaFlag( (String)httpSession.getAttribute("SRDA_FLAG"));
        }
        roleDeleteRequest.setRoleVO(roleVO);
        RoleDeleteResponse roleDeleteResponse = (RoleDeleteResponse) executeService(roleDeleteRequest);
        if (null != roleDeleteResponse.getRoleList()
                && roleDeleteResponse.getRoleList().size() == 0) {
            this.setSearchResultList(null);
        } else {
            if (null != roleDeleteResponse.getRoleList()) {
            	if(httpSession.getAttribute("SRDA_FLAG").equals("SRDA")){
            		searchResultList = roleDeleteResponse.getRoleList();
                    forDescriptionSrdaDisplay(searchResultList);
            	}else{
            		searchResultList = roleDeleteResponse.getRoleList();
                    forDescriptionDisplay(searchResultList);
            	}
                
            }
        }
        this.setBreadCrumbText("Administration >>" + " Role >> Locate");

        Logger.logInfo("RoleSearchBackingBean - Returning deleteRole(): Role");
        return "";
    }

    private void forDescriptionSrdaDisplay(List searchResultList2) {

    	 for(int i = 0 ; i < searchResultList.size() ; i++){
             RoleSrdaBo roleBO = (RoleSrdaBo) searchResultList.get(i);
             String description = "";
             if(roleBO.getDescription().length() > 15){
                 description = roleBO.getDescription();
                 description = description.substring(0,15).concat("...");
                 roleBO.setDescription(description);
             }
         }
	}

	/**
     * To set Values to RoleVO from searchCriteria
     * @param roleVO
     */
    private RoleVO setValuesToVOFromSearchCriteria(RoleVO roleVO) {
        if (null != this.roleName && !this.roleName.trim().equals("")) {

            // Check for the case when '%' or '_' is entered in the search
            // criteria
            String name = this.roleName;
            if (name.indexOf("%") >= 0) {
                name = name.replaceAll("%", "`%");
            } else if (name.indexOf("_") >= 0) {
                name = name.replaceAll("_", "`_");
            }
            roleVO.setRoleName(name.trim());
            
           
          
        }

        if (null != this.selectedModuleName
                && !this.selectedModuleName.equals("")) {

            // Get the selected Module Names to a list
            List moduleNameList = WPDStringUtil.getListFromTildaString(
                    this.selectedModuleName, 2, 2, 1);

            // Set the Module Names to the VO
            roleVO.setModuleNameList(moduleNameList);
        }
        if (null != this.selectedTaskName && !this.selectedTaskName.equals("")) {

            List taskNameList = WPDStringUtil.getListFromTildaString(
                    this.selectedTaskName, 2, 2, 1);
            roleVO.setTaskNameList(taskNameList);
        }
        if (null != this.selectedSubTaskName
                && !this.selectedSubTaskName.equals("")) {

            List subTaskNameList = WPDStringUtil.getListFromTildaString(
                    this.selectedSubTaskName, 2, 2, 1);
            //Set the task names to VO
            roleVO.setSubTaskNameList(subTaskNameList);
        }
        
        return roleVO;
    }

    /**
     * Method to validate the search page fields
     * 
     * @return boolean
     */

    public boolean validationMessages() {

        return true;
    }

    /**
     * Method to validate the search page fields
     * 
     * @return boolean
     */
    private boolean validateFields() {
        validationMessage = new ArrayList(2);
        // Set the breadcrumb to locate
        this.setBreadCrumbText("Administration >> Role " + ">> Locate");
        // Check whether all the fields in the search page are empty
        if ((null == this.roleName.trim() || this.roleName.trim().equals(""))
                && (null == this.selectedModuleName || this.selectedModuleName
                        .equals(""))
                && (null == this.selectedTaskName || this.selectedTaskName
                        .equals(""))
                && (null == this.selectedSubTaskName || this.selectedSubTaskName
                        .equals(""))) {

            // If so, set the required error message
            validationMessage.add(new ErrorMessage(
                    BusinessConstants.REQUIRED_FIELDS));
            return false;
        }
        
        //Validates name for special characters
        if (!this.roleName.trim().matches("^[\\d|a-z|A-Z|\\s]*$")) {
        	validationMessage.add(new ErrorMessage(
                    WebConstants.CATALOG_NAME_INVALID));
            return false;
        }

        return true;
    }


    /**
     * @return Returns the roleName.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     *            The roleName to set.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    /**
     * @return Returns the searchResultList.
     */
    public List getSearchResultList() {
        return searchResultList;
    }

    /**
     * @param searchResultList
     *            The searchResultList to set.
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }

    /**
     * @return Returns the selectedModuleName.
     */
    public String getSelectedModuleName() {
        return selectedModuleName;
    }

    /**
     * @param selectedModuleName
     *            The selectedModuleName to set.
     */
    public void setSelectedModuleName(String selectedModuleName) {
        this.selectedModuleName = selectedModuleName;
    }

    /**
     * @return Returns the selectedTaskName.
     */
    public String getSelectedTaskName() {
        return selectedTaskName;
    }

    /**
     * @param selectedTaskName
     *            The selectedTaskName to set.
     */
    public void setSelectedTaskName(String selectedTaskName) {
        this.selectedTaskName = selectedTaskName;
    }

    /**
     * @return selectedSubTaskName
     * 
     * Returns the selectedSubTaskName.
     */
    public String getSelectedSubTaskName() {
        return selectedSubTaskName;
    }

    /**
     * @param selectedSubTaskName
     * 
     * Sets the selectedSubTaskName.
     */
    public void setSelectedSubTaskName(String selectedSubTaskName) {
        this.selectedSubTaskName = selectedSubTaskName;
    }


    /**
     * @return selectedRoleId
     * 
     * Returns the selectedRoleId.
     */
    public int getSelectedRoleId() {
        return selectedRoleId;
    }

    /**
     * @param selectedRoleId
     * 
     * Sets the selectedRoleId.
     */
    public void setSelectedRoleId(int selectedRoleId) {
        this.selectedRoleId = selectedRoleId;
    }

	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}