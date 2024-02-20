/*
 * ModuleSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.module;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.security.locatecriteria.ModuleLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.request.ModuleDeleteRequest;
import com.wellpoint.wpd.common.security.request.ModuleSearchRequest;
import com.wellpoint.wpd.common.security.response.ModuleDeleteResponse;
import com.wellpoint.wpd.common.security.response.ModuleSearchResponse;
import com.wellpoint.wpd.common.security.vo.ModuleVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleSearchBackingBean extends WPDBackingBean {
	
	private String moduleName;
	
	private String srdaFlag;
	
	private String taskId;
	
	private List validationMessage;
	
	private int selectedModuleId;
	
	private List searchResultList;
	
	public ModuleSearchBackingBean(){
	
		this.setBreadCrumbText("Administration >> Module >> Locate");
	}
	/**
	 * Method for module locate
	 * @return
	 */
	public String moduleSearch(){
		
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
        ModuleSearchRequest moduleSearchRequest = getSearchRequest();
        ModuleVO moduleVO = moduleSearchRequest.getModuleVO();
        //moduleVO.setSrdaFlag(this.srdaFlag);
        
        // Call the executeService() to get the response
        ModuleSearchResponse moduleSearchResponse = 
            (ModuleSearchResponse) this.executeService(moduleSearchRequest);
        
        // Get the result list from the response
        List moduleList = moduleSearchResponse.getModuleList();
        
        //to set size of description to 15
        //setDescriptionForDisplay(moduleList);
        if(this.srdaFlag.equalsIgnoreCase("SRDA")){
      	  setDescriptionForDisplaySrda(moduleList);
                 }
        else{
      	  setDescriptionForDisplay(moduleList);
                }
          
        
        // Set the bread crumb to locate
        this.setBreadCrumbText("Administration >> Module " + ">> Locate");
        // Return the forward string
	
		return "moduleSearch";
	}
	
	/**
	 * To set size of description to 15
     * @param moduleList
     */
    private void setDescriptionForDisplay(List moduleList) {
        // Check whether the result list is null or empty
        if(null != moduleList && !moduleList.isEmpty()){
        	// If not, set the list to the backing bean
            this.searchResultList = moduleList;
            for(int i = 0 ; i < searchResultList.size() ; i++){
                ModuleBO moduleBO = (ModuleBO) searchResultList.get(i);
                String description = "";
                if(moduleBO.getDescription().length() > 15){
                    description = moduleBO.getDescription();
                    description = description.substring(0,15).concat("...");
                    moduleBO.setDescription(description);
                }
            }
            
            
        }
    }
    
    private void setDescriptionForDisplaySrda(List moduleList2) {
        // Check whether the result list is null or empty
        if(null != moduleList2 && !moduleList2.isEmpty()){
        	// If not, set the list to the backing bean
            this.searchResultList = moduleList2;
            for(int i = 0 ; i < searchResultList.size() ; i++){
                ModuleSrdaBO moduleBO = (ModuleSrdaBO) searchResultList.get(i);
                String description = "";
                if(moduleBO.getDescription().length() > 15){
                    description = moduleBO.getDescription();
                    description = description.substring(0,15).concat("...");
                    moduleBO.setDescription(description);
                }
            }
            
            
        }
    }
    /**
     * Method to create the request object and set the values to it
     * @return ModuleSearchRequest
     */
    private ModuleSearchRequest getSearchRequest() {
        // Create an instance of the request object
    	ModuleSearchRequest request = 
            (ModuleSearchRequest)this.getServiceRequest(ServiceManager.SEARCH_MODULE_REQUEST);
        
        // Create an instance of the VO
        ModuleVO moduleVO = new ModuleVO();
        
        //Set values from search Criteria to ModuleVO
        moduleVO=setValuesToVOFromSearchCriteria(moduleVO);
        
        // Set the VO in the request
        request.setModuleVO(moduleVO);
        
        request.setSrdaFlag(this.srdaFlag);
        
        // Return the request
        return request;
    }
    
    /**
     * Method to delete module
     * @return String
     */
    public String deleteModule() {
        Logger.logInfo("ModuleSearchBackingBean - Entering deleteModule(): Module");
        ModuleDeleteRequest moduleDeleteRequest = (ModuleDeleteRequest) this
                .getServiceRequest(ServiceManager.DELETE_MODULE_REQUEST);
        ModuleVO moduleVO = new ModuleVO();
        moduleVO.setSelectedModuleId(this.getSelectedModuleId());
        moduleVO=setValuesToVOFromSearchCriteria(moduleVO);
        moduleDeleteRequest.setModuleVO(moduleVO);
        HttpSession session = getSession();
        String srdaFlag = (String) session.getAttribute("SRDA_FLAG");
        ModuleLocateCriteria moduleCriteria = new ModuleLocateCriteria(); 
        moduleCriteria.setSrdaFlag(srdaFlag);
        moduleDeleteRequest.setModuleLocateCriteria(moduleCriteria);
    
		ModuleDeleteResponse moduleDeleteResponse= (ModuleDeleteResponse) executeService(moduleDeleteRequest);
		
		 // Get the result list from the response
        List moduleList = moduleDeleteResponse.getModuleList();
        
        //to set size of description to 15
        if(srdaFlag.equalsIgnoreCase("SRDA")){
	    	  setDescriptionForDisplaySrda(moduleList);
	               }
	      else{
	    	  setDescriptionForDisplay(moduleList);
	      }

       
			
		this.setBreadCrumbText("Administration >>" + " Module >> Locate");
		
			
        Logger.logInfo("ModuleSearchBackingBean - Returning deleteModule(): Module");
        return WebConstants.EMPTY_STRING;
    }
    
	
	 /**
	 * Set values from searchCriteria to moduleVO
     * @param moduleVO
     */
    private ModuleVO setValuesToVOFromSearchCriteria(ModuleVO moduleVO) {
        
        //Set the values in the VO
        if(null != this.moduleName && !this.moduleName.trim().equals("")){
            
            // Check for the case when '%' or '_' is entered in the search criteria
            String name = this.moduleName;
            if(name.indexOf("%") >= 0 ){
                name = name.replaceAll("%", "`%");
        	}else if(name.indexOf("_") >= 0){
        	    name = name.replaceAll("_", "`_");
        	}
            moduleVO.setModuleName(name.trim());
        }
        
        if(null != this.taskId && !this.taskId.equals("")){
            
            // Get the selected catalog ids to a list
            List taskIdList = WPDStringUtil.getListFromTildaString(this.taskId, 2, 2, 1);
            
            // Set the catalog id list to the VO
            moduleVO.setTaskIdList(taskIdList);
        }
        
        return moduleVO;
    }
    /**
     * Method to validate the search page fields
     * @return boolean
     */
    private boolean validateFields() {
        validationMessage = new ArrayList(1);
        // Set the breadcrumb to lacate
        this.setBreadCrumbText("Administration >> Module " + ">> Locate");
        // Check whether all the fields in the search page are empty
        if((null == this.moduleName.trim() || this.moduleName.trim().equals(""))
                && (null == this.taskId || this.taskId.equals(""))){
            
        	// If so, set the required error message 
            validationMessage.add(new ErrorMessage(BusinessConstants.REQUIRED_FIELDS));
            return false;
        }
        
        return true;
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
	 * @return Returns the taskId.
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
