/*
 * ModuleAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.security.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.security.locatecriteria.ModuleLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigSrdaBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.bo.RoleTaskConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleTaskSrdaConfigBO;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class ModuleAdapterManager {
    /**
     * 
	 * Method for creating a module
     * @param moduleBO
     * @param audit
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean createModule(ModuleBO moduleBO, Audit audit
            ) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering createModule(): Module Insert");
        try {
			AdapterUtil.performInsert(moduleBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning createModule(): Module Insert");
        return true;
    }
    
    /**
     * 
	 * Method for creating a module
     * @param moduleBO
     * @param audit
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean createModuleSrda(ModuleSrdaBO moduleSrdaBO, Audit audit
            ) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering createModule(): Module Insert");
        try {
			AdapterUtil.performInsert(moduleSrdaBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning createModule(): Module Insert");
        return true;
    }

    /**
	 * Method for updating a module
     * 
     * @param moduleBO
     * @param audit
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean updateModule(ModuleBO moduleBO, Audit audit
            ) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering updateModule(): Module Update");
        try {
			AdapterUtil.performUpdate(moduleBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning updateModule(): Module Update");
        return true;
    }
    
    /**
	 * Method for updating a module
     * 
     * @param moduleBO
     * @param audit
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean updateModule(ModuleSrdaBO moduleSrdaBO, Audit audit
            ) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering updateModule(): Module Update");
        try {
			AdapterUtil.performUpdate(moduleSrdaBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning updateModule(): Module Update");
        return true;
    }
    /**
 	 * Method for updating a module
     * 
     * @param moduleBO
     * @param audit
     * @param access
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean updateModule(ModuleBO moduleBO, Audit audit,
            AdapterServicesAccess access) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering updateModule(): Module Update");
        try {
			AdapterUtil.performUpdate(moduleBO, audit.getUser(),access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning updateModule(): Module Update");
        return true;
    }
    
    /**
 	 * Method for updating a module
     * 
     * @param moduleBO
     * @param audit
     * @param access
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean updateModule(ModuleSrdaBO moduleSrdaBO, Audit audit,
            AdapterServicesAccess access) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering updateModule(): Module Update");
        try {
			AdapterUtil.performUpdate(moduleSrdaBO, audit.getUser(),access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning updateModule(): Module Update");
        return true;
    }
    /**
     * Method for retrieving a module
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public ModuleBO retrieve(ModuleBO moduleBO) throws SevereException, AdapterException{
        Logger
                .logInfo("ModuleAdapterManager - Entering retrieve(): Module Retrieve");
        try {
			AdapterUtil.performRetrieve(moduleBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning retrieve(): Module Retrieve");
        return moduleBO;
    }
    
    /**
     * Method for retrieving a module
     * 
     * @param moduleSrdaBO
     * @return
     * @throws SevereException
     */
    public ModuleSrdaBO retrieve(ModuleSrdaBO moduleSrdaBO) throws SevereException, AdapterException{
        Logger
                .logInfo("ModuleAdapterManager - Entering retrieve(): Module Retrieve");
        try {
			AdapterUtil.performRetrieve(moduleSrdaBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning retrieve(): Module Retrieve");
        return moduleSrdaBO;
    }

    /**
     * Method to delete module
     * 
     * @param moduleBO
     * @param user
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */

    public boolean deleteModule(ModuleBO moduleBO, User user)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering deleteModule(): Module Remove");
        try {
			AdapterUtil.performRemove(moduleBO, user.getUserId());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning deleteModule(): Module Remove");
        return true;
    }
    
    /**
     * Method to delete module
     * 
     * @param moduleSrdaBO
     * @param user
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */

    public boolean deleteModule(ModuleSrdaBO moduleSrdaBO, User user)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering deleteModule(): Module Remove");
        try {
			AdapterUtil.performRemove(moduleSrdaBO, user.getUserId());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning deleteModule(): Module Remove");
        return true;
    }

    /**
     * Method to check whether the with same name already exists
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public boolean isModuleDuplicate(ModuleBO moduleBO) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering isModuleDuplicate(): Module Retrieve");
        HashMap valueSet = new HashMap();
        valueSet.put("moduleName", moduleBO.getModuleName());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                moduleBO.getClass().getName(), "findDuplicateModule", valueSet,
                99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            return true;
        }
        Logger
                .logInfo("ModuleAdapterManager - Returning isModuleDuplicate(): Module Retrieve");
        return false;
    }
    
    /**
     * Method to check whether the with same name already exists
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public boolean isModuleDuplicateSrda(ModuleSrdaBO moduleSrdaBO) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering isModuleDuplicate(): Module Retrieve");
        HashMap valueSet = new HashMap();
        valueSet.put("moduleName", moduleSrdaBO.getModuleName());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(moduleSrdaBO.getClass().getName(), "findDuplicateModule", valueSet,
                99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            return true;
        }
        Logger
                .logInfo("ModuleAdapterManager - Returning isModuleDuplicate(): Module Retrieve");
        return false;
    }

    /**
     * Method to locate module for lookup
     * 
     * @return
     * @throws SevereException
     */
    public List locateModuleForLookUp(String srdaFlag) throws SevereException, AdapterException {
        HashMap map = new HashMap();
        SearchCriteria searchCriteria;
        if(srdaFlag.equalsIgnoreCase("SRDA")){
        	 
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                         ModuleSrdaBO.class.getName(), "moduleLookUpQuery", map, 99999);
        }else{

           searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ModuleBO.class.getName(), "moduleLookUpQuery", map, 99999);
        }
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		return searchResults.getSearchResults();
    }

    /**
     * Method to locate modules based on the search criteria
     * 
     * @return List
     * @throws SevereException
     */
    public List locate(ModuleLocateCriteria moduleLocateCriteria)
            throws SevereException, AdapterException {

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        // Create a reference of searchResults
        SearchResults searchResults = null;

        HashMap criteriaValues = new HashMap();

        // Set the search criteria values in the hash map
        criteriaValues = getSearchCriteriaMap(criteriaValues,
                moduleLocateCriteria);

        // Set the criteria to the searchCriteria instance
        if(moduleLocateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
      	  searchCriteria = AdapterUtil.getAdapterSearchCriteria(ModuleSrdaBO.class
                    .getName(), "locateModuleSrda", criteriaValues);
      }else
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(ModuleBO.class
                .getName(), "locateModule", criteriaValues);

        try {
			// Call the performSearch() of the adapterutil
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}

        List searchResultList = searchResults.getSearchResults();

        // Return the list
        return searchResultList;
    }

    /**
     * Method for locating the role configured modules
     * @param moduleLocateCriteria
     * @return 
     * @throws SevereException
     * @throws AdapterException
     */
    public List locateRoleConfiguration(
            ModuleLocateCriteria moduleLocateCriteria) throws SevereException, AdapterException {
        SearchResults searchResults = null;

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        HashMap criteriValues = new HashMap();

        Integer id = new Integer(moduleLocateCriteria.getModuleId());

        criteriValues.put("moduleId", id);
        if(moduleLocateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
        	  searchCriteria = AdapterUtil.getAdapterSearchCriteria(ModuleSrdaBO.class
                      .getName(), "locateRoleConfiguration", criteriValues);
        }else
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(ModuleBO.class
                .getName(), "locateRoleConfiguration", criteriValues);

        try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}

        return searchResults.getSearchResults();
    }

    /**
     * Method for locating thr role configured tasks
     * @param moduleLocateCriteria
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public List locateTaskModuleConfiguration(
            ModuleLocateCriteria moduleLocateCriteria) throws SevereException, AdapterException {
        SearchResults searchResults = null;

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        HashMap criteriValues = new HashMap();

        Integer id = new Integer(moduleLocateCriteria.getModuleId());

        criteriValues.put("moduleId", id);
        if(moduleLocateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
      	  searchCriteria = AdapterUtil.getAdapterSearchCriteria(ModuleSrdaBO.class
                    .getName(), "locateTaskModuleConfiguration", criteriValues);
      }else
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(ModuleBO.class
                .getName(), "locateTaskModuleConfiguration", criteriValues);

        try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}

        return searchResults.getSearchResults();
    }

    /**
     * @param criteriaValues
     * @param moduleLocateCriteria
     * @return
     */
    private HashMap getSearchCriteriaMap(HashMap criteriaValues,
            ModuleLocateCriteria moduleLocateCriteria) {
        // Set the values to the hashmap from the locatCriteria
        if (null != moduleLocateCriteria.getModuleName()
                && !moduleLocateCriteria.getModuleName().equals("")) {
            criteriaValues.put("moduleName", "%"
                    + moduleLocateCriteria.getModuleName() + "%");
        } else {
            criteriaValues.put("moduleName", null);
        }

        if (null != moduleLocateCriteria.getTaskList()
                && !moduleLocateCriteria.getTaskList().isEmpty()) {
            criteriaValues.put("taskList", moduleLocateCriteria.getTaskList());
        } else {
            criteriaValues.put("taskList", null);
        }

        return criteriaValues;
    }

    /**
     * Method for locating the non-associated tasks with the module
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateTask(TaskBO taskBO) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering locateTask(): Module Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(taskBO.getModuleId()));
        if (null != taskBO.getTaskName() && !taskBO.getTaskName().equals("")) {
            valueSet.put("taskName", taskBO.getTaskName());
        } else {
            valueSet.put("taskName", "%");
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                taskBO.getClass().getName(), "taskPopupQuery", valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning locateTask(): Module Locate");
        return searchResults.getSearchResults();
    }
    
    /**
     * Method for locating the non-associated tasks with the module
     * 
     * @param taskSrdaBO
     * @return
     * @throws SevereException
     */
    public List locateTaskSrda(TaskSrdaBO taskSrdaBO) throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering locateTask(): Module Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(taskSrdaBO.getModuleId()));
        if (null != taskSrdaBO.getTaskName() && !taskSrdaBO.getTaskName().equals("")) {
            valueSet.put("taskName", taskSrdaBO.getTaskName());
        } else {
            valueSet.put("taskName", "%");
        }
        
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		taskSrdaBO.getClass().getName(), "taskPopupQuery", valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning locateTask(): Module Locate");
        return searchResults.getSearchResults();
    }

    /**
     * Method for locating the task associated with the module
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateModuleAssociaion(TaskBO taskBO) throws SevereException, AdapterException{
        Logger
                .logInfo("ModuleAdapterManager - Entering locateModuleAssociaion(): Module Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(taskBO.getModuleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                taskBO.getClass().getName(), "taskAsssociationQuery", valueSet,
                99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning locateModuleAssociaion(): Module Locate");
        return searchResults.getSearchResults();
    }
    
   
    /**
     * Method for locating the task associated with the module
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateModuleAssociaion(TaskSrdaBO taskBO) throws SevereException, AdapterException{
        Logger
                .logInfo("ModuleAdapterManager - Entering locateModuleAssociaion(): Module Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(taskBO.getModuleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                taskBO.getClass().getName(), "taskAsssociationQuery", valueSet,
                99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning locateModuleAssociaion(): Module Locate");
        return searchResults.getSearchResults();
    }
    
    /**
     * Method for locating the task associated with the module
     * 
     * @param taskSrdaBO
     * @return
     * @throws SevereException
     */
    public List locateModuleAssociaionSrda(TaskSrdaBO taskSrdaBO) throws SevereException, AdapterException{
        Logger
                .logInfo("ModuleAdapterManager - Entering locateModuleAssociaion(): Module Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(taskSrdaBO.getModuleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(taskSrdaBO.getClass().getName(), "taskAsssociationQuery", valueSet,99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning locateModuleAssociaion(): Module Locate");
        return searchResults.getSearchResults();
    }

    /**
     * Method for creating the module-task association
     * 
     * @param moduleConfigBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean createModuleAssociation(ModuleConfigBO moduleConfigBO,
            Audit audit, AdapterServicesAccess adapterServiceAccess)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering createModuleAssociation(): Module Insert");
        try {
			AdapterUtil.performInsert(moduleConfigBO, audit.getUser(),
			        adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning createModuleAssociation(): Module Insert");
        return true;
    }
    
    /**
     * Method for creating the module-task association
     * 
     * @param moduleConfigSrdaBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean createModuleAssociationSrda(ModuleConfigSrdaBO moduleConfigSrdaBO,
            Audit audit, AdapterServicesAccess adapterServiceAccess)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering createModuleAssociation(): Module Insert");
        try {
			AdapterUtil.performInsert(moduleConfigSrdaBO, audit.getUser(),
			        adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning createModuleAssociation(): Module Insert");
        return true;
    }

    /**
     * Method for deleting module-task association
     * 
     * @param moduleConfigBO
     * @param user
     * @param access
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean deleteModuleAssociation(ModuleConfigBO moduleConfigBO,
            User user,AdapterServicesAccess access)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering deleteModuleAssociation(): Module Delete");
        try {
			AdapterUtil.performRemove(moduleConfigBO, user.getUserId(),access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning deleteModuleAssociation(): Module Delete");
        return true;
    }
    
    
    
    /**
     * Method for deleting module-task association
     * 
     * @param moduleConfigSrdaBO
     * @param user
     * @param access
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean deleteModuleAssociationSrda(ModuleConfigSrdaBO moduleConfigSrdaBO,
            User user,AdapterServicesAccess access)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ModuleAdapterManager - Entering deleteModuleAssociation(): Module Delete");
        try {
        	AdapterUtil.performRemove(moduleConfigSrdaBO, user.getUserId(),access);
		} catch (Exception ex) {
			
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ModuleAdapterManager - Returning deleteModuleAssociation(): Module Delete");
        return true;
    }

    /**
     * Method to find the subtasks associated to the module
     * 
     * @param moduleLocateCriteria
     * @return
     * @throws SevereException
     */
    public List locateSubTaskAsociatedToModule(
            ModuleLocateCriteria moduleLocateCriteria) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        if (moduleLocateCriteria.getModuleId() != 0) {
            valueSet.put("moduleId", new Integer(moduleLocateCriteria
                    .getModuleId()));
        } else {
            valueSet.put("moduleId", null);
        }
        if (moduleLocateCriteria.getAssociatedTaskId() != 0) {
            valueSet.put("taskId", new Integer(moduleLocateCriteria
                    .getAssociatedTaskId()));
        } else {
            valueSet.put("taskId", null);
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                SubTaskBO.class.getName(), "subTaskModuleAsssociationQuery",
                valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning locateModuleAssociaion(): Module Locate");
        return searchResults.getSearchResults();
    }

    /**
     * To be deleted method
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public boolean isSubTaskAsociatedToModule(ModuleBO moduleBO)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(moduleBO.getModuleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                SubTaskBO.class.getName(), "subTaskModuleAsssociationQuery",
                valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning locateModuleAssociaion(): Module Locate");
        if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * Method to find whether the selected task is aaociated with any role
     * @param configBO
     * @return
     * @throws SevereException
     */
    public boolean isTaskAssociated(ModuleConfigBO configBO)throws SevereException, AdapterException{
        HashMap valueSet = new HashMap();
        valueSet.put("taskId", new Integer(configBO.getTaskId()));
        valueSet.put("moduleId", new Integer(configBO.getModuleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                RoleTaskConfigBO.class.getName(), "isTaskAssociated",
                valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning roleTaskAssociaion(): Task Locate");
        if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * Method to find whether the selected task is aaociated with any role
     * @param configBO
     * @return
     * @throws SevereException
     */
    public boolean isTaskAssociated(ModuleConfigSrdaBO configSrdaBO)throws SevereException, AdapterException{
        HashMap valueSet = new HashMap();
        valueSet.put("taskId", new Integer(configSrdaBO.getTaskId()));
        //valueSet.put("moduleId", new Integer(configSrdaBO.getModuleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                RoleTaskSrdaConfigBO.class.getName(), "isTaskAssociated",
                valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
                .logInfo("ModuleAdapterManager - Returning roleTaskAssociaion(): Task Locate");
        if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            return true;
        }
        return false;
    }
}