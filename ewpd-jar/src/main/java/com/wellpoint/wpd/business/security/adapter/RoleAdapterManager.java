/*
 * RoleAdapterManager.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
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
import com.wellpoint.wpd.business.security.locatecriteria.RoleLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.bo.RoleBO;
import com.wellpoint.wpd.common.security.bo.RoleConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleConfigSrdaBO;
import com.wellpoint.wpd.common.security.bo.RoleSrdaBo;
import com.wellpoint.wpd.common.security.bo.RoleSubTaskConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleTaskConfigBO;
import com.wellpoint.wpd.common.security.bo.RoleTaskSrdaConfigBO;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleAdapterManager {
    /**
     * Method to create a role
     * 
     * @param roleBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean createRole(RoleBO roleBO, Audit audit
            ) throws SevereException, AdapterException {

        try {
			AdapterUtil
			        .performInsert(roleBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method to update a role
     * 
     * @param roleBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean updateRole(RoleBO roleBO, Audit audit
            ) throws SevereException, AdapterException {
        try {
			AdapterUtil
			        .performUpdate(roleBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }
    
    /**
     * Method to create a role
     * 
     * @param roleBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean createRole(RoleSrdaBo roleSrdaBO, Audit audit
            ) throws SevereException, AdapterException {

        try {
			AdapterUtil
			        .performInsert(roleSrdaBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * @author AF37757
     * @return Role Sequnce ID for SRDA application
     * @throws SevereException
     * @throws AdapterException
     */
    public List getNextSecurityRoleSequence() throws SevereException, AdapterException {
    	
    	SearchResults searchResults;
    	try{
    		 Logger
             .logInfo("RoleAdapterManager - Entering getNextSecurityRoleSequence(): Role Create");
    		 HashMap valueSet = new HashMap();
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					RoleSrdaBo.class.getName(), "roleSequenceId", valueSet,
			        99999);
	
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
     Logger
             .logInfo("RoleAdapterManager - Returning Create(): Module Create");
     return searchResults.getSearchResults();
    	
    	
    }
    /**
     * Method to update a role
     * 
     * @param roleBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean updateRole(RoleSrdaBo roleSrdaBO, Audit audit
            ) throws SevereException, AdapterException {
        try {
			AdapterUtil
			        .performUpdate(roleSrdaBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }
    /**
     * Method to update a role
     * 
     * @param roleBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean updateRole(RoleBO roleBO, Audit audit,
            AdapterServicesAccess access) throws SevereException, AdapterException {
        try {
			AdapterUtil
			        .performUpdate(roleBO, audit.getUser(),access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method to update a role
     * 
     * @param roleBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean updateRole(RoleSrdaBo roleSrdaBO, Audit audit,
            AdapterServicesAccess access) throws SevereException, AdapterException {
        try {
			AdapterUtil
			        .performUpdate(roleSrdaBO, audit.getUser(),access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }
    /**
     * method to retrieve a role
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public RoleBO retrieve(RoleBO roleBO) throws SevereException, AdapterException{
        try {
			AdapterUtil.performRetrieve(roleBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return roleBO;
    }

    /**
     * method to retrieve a role
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public RoleSrdaBo retrieve(RoleSrdaBo roleSrdaBO) throws SevereException, AdapterException{
        try {
			AdapterUtil.performRetrieve(roleSrdaBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return roleSrdaBO;
    }
    /**
     * method to delete a role
     * 
     * @param roleBO
     * @param user
     * @param roleAdapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteRole(RoleBO roleBO, User user)
            throws SevereException, AdapterException {
        Logger
                .logInfo("RoleAdapterManager - Entering deleteRole(): Role Remove");
        try {
			AdapterUtil.performRemove(roleBO, user.getUserId());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("RoleAdapterManager - Returning deleteRole(): Role Remove");
        return true;
    }
    /**
     * method to delete a role
     * 
     * @param roleBO
     * @param user
     * @param roleAdapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteRole(RoleSrdaBo roleBO, User user)
            throws SevereException, AdapterException {
        Logger
                .logInfo("RoleAdapterManager - Entering deleteRole(): Role Remove");
        try {
			AdapterUtil.performRemove(roleBO, user.getUserId());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("RoleAdapterManager - Returning deleteRole(): Role Remove");
        return true;
    }

    /**
     * ' Method for deleting the associated module
     * 
     * @param roleConfigBO
     * @param user
     * @param roleAdapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,
            User user,AdapterServicesAccess access)
            throws SevereException, AdapterException {
        try {
			AdapterUtil.performRemove(roleConfigBO, user.getUserId(),access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method to locate the associated modules
     * 
     * @param roleId
     * @return
     * @throws SevereException
     */
    public List getModuleBO(long roleId) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Long(roleId));
        SearchResults searchResults;
		try {
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        "com.wellpoint.wpd.common.security.bo.ModuleBO",
			        "retrieveModuleDetails", valueSet, 99999);
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return searchResults.getSearchResults();
    }

    /**
     * Method to locate the list of associated tasks
     * 
     * @param moduleId
     * @param roleId
     * @return
     * @throws SevereException
     */
    public List getTaskBO(long moduleId, long roleId) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Long(moduleId));
        valueSet.put("roleId", new Long(roleId));
        SearchResults searchResults;
		try {
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        "com.wellpoint.wpd.common.security.bo.TaskBO",
			        "retrieveTaskBO", valueSet, 99999);
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return searchResults.getSearchResults();
    }

    /**
     * Method to locate the list of associated subtasks
     * 
     * @param moduleId
     * @param taskId
     * @param roleId
     * @return
     * @throws SevereException
     */
    public List getSubTaskBO(long moduleId, long taskId, long roleId)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("taskID", new Long(taskId));
        valueSet.put("moduleID", new Long(moduleId));
        valueSet.put("roleId", new Long(roleId));
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        "com.wellpoint.wpd.common.security.bo.SubTaskBO",
			        "retrieveSubTaskBO", valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return searchResults.getSearchResults();
    }

    /**
     * Method to check whether there is any role with same name
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public boolean isRoleDuplicate(RoleBO roleBO) throws SevereException, AdapterException{
        HashMap valueSet = new HashMap();
        valueSet.put("roleName", roleBO.getRoleName());
        SearchCriteria searchCriteria;
        SearchResults searchResults;
		
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        roleBO.getClass().getName(), "findDuplicate", valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        if (searchResults.getSearchResults() != null
                && searchResults.getSearchResults().size() > 0) {
            roleBO
                    .setRoleId(((RoleBO) (searchResults.getSearchResults()
                            .get(0))).getRoleId());
        }

        if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * Method to check whether there is any role with same name
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public boolean isRoleDuplicate(RoleSrdaBo roleSrdaBO) throws SevereException, AdapterException{
        HashMap valueSet = new HashMap();
        valueSet.put("roleName", roleSrdaBO.getRoleName());
        SearchCriteria searchCriteria;
        SearchResults searchResults;
		
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					roleSrdaBO.getClass().getName(), "findDuplicate", valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        if (searchResults.getSearchResults() != null
                && searchResults.getSearchResults().size() > 0) {
        	roleSrdaBO
                    .setRoleId(((RoleSrdaBo) (searchResults.getSearchResults()
                            .get(0))).getRoleId());
        }

        if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * Method to locate roles
     * 
     * @param roleLocateCriteria
     * @return
     * @throws SevereException
     */
    public List locate(RoleLocateCriteria roleLocateCriteria)
            throws SevereException, AdapterException {

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        // Create a reference of searchResults
        SearchResults searchResults = null;

        HashMap criteriaValues = new HashMap();

        // Set the search criteria values in the hash map
        criteriaValues = getSearchCriteriaMap(criteriaValues,
                roleLocateCriteria);

        // Set the criteria to the searchCriteria instance
       if(roleLocateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
    	   searchCriteria = AdapterUtil.getAdapterSearchCriteria(RoleSrdaBo.class
			        .getName(), "locateRoleSRDA", criteriaValues);
       }else{
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(RoleBO.class
			        .getName(), "locateRole", criteriaValues);
       }
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
     * Method to create the criteria values from roleLocateCriteria
     * 
     * @param criteriaValues
     * @param roleLocateCriteria
     * @return
     */
    private HashMap getSearchCriteriaMap(HashMap criteriaValues,
            RoleLocateCriteria roleLocateCriteria) {
        // Set the values to the hashmap from the locatCriteria
        if (null != roleLocateCriteria.getRoleName()
                && !roleLocateCriteria.getRoleName().equals("")) {
            criteriaValues.put("roleName", "%"
                    + roleLocateCriteria.getRoleName() + "%");
        } else {
            criteriaValues.put("roleName", null);
        }

        if (null != roleLocateCriteria.getModuleNameList()
                && !roleLocateCriteria.getModuleNameList().isEmpty()) {
            criteriaValues.put("moduleNameList", roleLocateCriteria
                    .getModuleNameList());
        } else {
            criteriaValues.put("moduleNameList", null);
        }

        if (null != roleLocateCriteria.getTaskNameList()
                && !roleLocateCriteria.getTaskNameList().isEmpty()) {
            criteriaValues.put("taskNameList", roleLocateCriteria
                    .getTaskNameList());
        } else {
            criteriaValues.put("taskNameList", null);
        }

        if (null != roleLocateCriteria.getSubTaskNameList()
                && !roleLocateCriteria.getSubTaskNameList().isEmpty()) {
            criteriaValues.put("subTaskNameList", roleLocateCriteria
                    .getSubTaskNameList());
        } else {
            criteriaValues.put("subTaskNameList", null);
        }

        return criteriaValues;
    }

    /**
     * Method to locate the tasks associated with the role
     * 
     * @param roleLocateCriteria
     * @return
     * @throws SevereException
     */
    public List locateTaskAssociatedToRole(RoleLocateCriteria roleLocateCriteria)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        if (roleLocateCriteria.getRoleId() != 0) {
            valueSet.put("roleId", new Integer(roleLocateCriteria.getRoleId()));
        } else {
            valueSet.put("roleId", null);
        }
        if (roleLocateCriteria.getAssociatedModuleId() != 0) {
            valueSet.put("moduleId", new Integer(roleLocateCriteria
                    .getAssociatedModuleId()));
        } else {
            valueSet.put("moduleId", null);
        }
        SearchResults searchResults;
        SearchCriteria searchCriteria;
		
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        TaskBO.class.getName(), "taskRoleAsssociationQuery", valueSet,
			        99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("RoleAdapterManager - Returning locateRoleAssociaion(): Role Locate");
        return searchResults.getSearchResults();
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
                .logInfo("RoleAdapterManager - Entering locateTask(): Role Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Integer(taskBO.getRoleId()));
        valueSet.put("moduleId", new Integer(taskBO.getModuleId()));
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        taskBO.getClass().getName(), "roleTaskPopupQuery", valueSet,
			        99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        Logger
                .logInfo("RoleAdapterManager - Returning locateTask(): Module Locate");
        return searchResults.getSearchResults();
    }

    /**
     * Method for locating the non-associated tasks with the module
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateTask(TaskSrdaBO taskSrdaBO) throws SevereException, AdapterException {
        Logger
                .logInfo("RoleAdapterManager - Entering locateTask(): Role Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Integer(taskSrdaBO.getRoleId()));
        valueSet.put("moduleId", new Integer(taskSrdaBO.getModuleId()));
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					taskSrdaBO.getClass().getName(), "roleTaskPopupQuery", valueSet,
			        99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        Logger
                .logInfo("RoleAdapterManager - Returning locateTask(): Module Locate");
        return searchResults.getSearchResults();
    }
    
    /**
     * Method for locating the associated modules of the selected role
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public List retrieveRoleModuleAssociation(ModuleBO moduleBO)
            throws SevereException, AdapterException {
        Logger
                .logInfo("RoleAdapterManager - Entering retrieveRoleModuleAssociation(): Module Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Integer(moduleBO.getRoleId()));
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        moduleBO.getClass().getName(), "retrieveModuleDetails",
			        valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        Logger
                .logInfo("RoleAdapterManager - Returning retrieveRoleModuleAssociation(): Module Locate");
        return searchResults.getSearchResults();
    }

    /**
     * Method for locating the associated modules of the selected role
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public List retrieveRoleModuleAssociation(ModuleSrdaBO modulesrdaBO)
            throws SevereException, AdapterException {
        Logger
                .logInfo("RoleAdapterManager - Entering retrieveRoleModuleAssociation(): Module Locate");
        HashMap valueSet = new HashMap();
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					modulesrdaBO.getClass().getName(), "retrieveModuleDetails",
			        valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        Logger
                .logInfo("RoleAdapterManager - Returning retrieveRoleModuleAssociation(): Module Locate");
        return searchResults.getSearchResults();
    }
    /**
     * Method for locating the non-associated modules of the selected role
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public List locateModuleNonAssociation(ModuleBO moduleBO)
            throws SevereException, AdapterException {
        Logger
                .logInfo("RoleAdapterManager - Entering retrieveRoleModuleAssociation(): Module Locate");
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Integer(moduleBO.getRoleId()));
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        moduleBO.getClass().getName(), "locateModulePopUpQuery",
			        valueSet);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        Logger
                .logInfo("RoleAdapterManager - Returning retrieveRoleModuleAssociation(): Module Locate");
        return searchResults.getSearchResults();
    }

    /**
     * Method for saving the role module association
     * 
     * @param roleConfigBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean createRoleModuleAssociation(RoleConfigBO roleConfigBO,
            Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws SevereException, AdapterException {
        Logger
                .logInfo("RoleAdapterManager - Entering createRoleModuleAssociation(): Module CreateAssociation");
        try {
			AdapterUtil.performInsert(roleConfigBO, audit.getUser(),
			        adapterServicesAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        Logger
                .logInfo("RoleAdapterManager - Entering createRoleModuleAssociation(): Module CreateAssociation");
        return true;
    }

    /**
     * Method for saving the role module association
     * 
     * @param roleConfigBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean createRoleTaskAssociation(RoleTaskConfigBO configBO,
            Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws SevereException, AdapterException {
        try {
			AdapterUtil.performInsert(configBO, audit.getUser(),
			        adapterServicesAccess);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return true;
    }


    /**
     * Method for saving the role module association
     * 
     * @param roleConfigBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean createRoleTaskAssociation(RoleTaskSrdaConfigBO configBO,
            Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws SevereException, AdapterException {
        try {
			AdapterUtil.performInsert(configBO, audit.getUser(),
			        adapterServicesAccess);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return true;
    }
    /**
     * Method to associate a subtask with the role
     * 
     * @param configBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean createRoleSubTaskAssociation(RoleSubTaskConfigBO configBO,
            Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws SevereException, AdapterException {
        try {
			AdapterUtil.performInsert(configBO, audit.getUser(),
			        adapterServicesAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return true;
    }

    /**
     * Method to locate the associated modules for a role
     * 
     * @param moduleBO
     * @return List
     * @throws SevereException
     */
    public List locateAssociatedModules(ModuleBO moduleBO)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Integer(moduleBO.getRoleIdForTree()));
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        moduleBO.getClass().getName(), "findAssociatedModuleForRole",
			        valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}

        return searchResults.getSearchResults();

    }

    /**
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateAssociatedTasks(TaskBO taskBO) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(taskBO.getModuleId()));
        SearchResults searchResults;
		
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        taskBO.getClass().getName(), "findAssociatedModuleForRole",
			        valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}

        return searchResults.getSearchResults();
    }


    /**
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public List locateRoleAssociations(RoleLocateCriteria locateCriteria)
            throws SevereException, AdapterException {
        SearchCriteria searchCriteria = null;
        HashMap valueSet = new HashMap();
        if (locateCriteria.getSubEntityType().equals("Module")) {
            valueSet.put("roleId", new Integer(locateCriteria.getRoleId()));
            if(locateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
            	 searchCriteria = AdapterUtil.getAdapterSearchCriteria(TaskSrdaBO.class
                         .getName(), "findAssociatedModuleForRole", valueSet, 99999);
            }else{
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(TaskBO.class
                    .getName(), "findAssociatedModuleForRole", valueSet, 99999);
            }
        }
        if (locateCriteria.getSubEntityType().equals("Task")) {
            valueSet.put("roleId", new Integer(locateCriteria.getRoleId()));
            valueSet.put("moduleId", new Integer(locateCriteria
                    .getAssociatedModuleId()));
            if(locateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
            	 searchCriteria = AdapterUtil.getAdapterSearchCriteria(TaskSrdaBO.class
                         .getName(), "roleTaskAssociationQuery", valueSet, 99999);
            }else{
            	 searchCriteria = AdapterUtil.getAdapterSearchCriteria(TaskBO.class
                         .getName(), "roleTaskAssociationQuery", valueSet, 99999);
            }
           
        }
        if (locateCriteria.getSubEntityType().equals("SubTask")) {

            valueSet.put("roleId", new Integer(locateCriteria.getRoleId()));
            valueSet.put("moduleId", new Integer(locateCriteria
                    .getAssociatedModuleId()));
            valueSet.put("taskId", new Integer(locateCriteria
                    .getAssociatedTaskId()));
            String queryName = "";
            if (null != locateCriteria.getAction()
                    && locateCriteria.getAction().equals("lookUpList")) {
                queryName = "findSubTasksForRolePopUp";
            } else {
                queryName = "findAssociatedSubTaskForRole";
            }
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    SubTaskBO.class.getName(), queryName, valueSet, 99999);
        }
        SearchResults searchResults = null;
        try {
			if (null != searchCriteria) {
			    searchResults = AdapterUtil.performSearch(searchCriteria);
			}
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return searchResults.getSearchResults();
    }

    /**
     * @param configBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean saveTaskToRole(RoleTaskConfigBO configBO, String user,AdapterServicesAccess access)
            throws SevereException, AdapterException {
        try {
			AdapterUtil.performInsert(configBO, user,access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return false;
    }

    /**
     * @param configBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean saveSubTaskToRole(RoleSubTaskConfigBO configBO, String user,AdapterServicesAccess access)
            throws SevereException, AdapterException {
        try {
			AdapterUtil.performInsert(configBO, user,access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return false;
    }

    /**
     * @param configBO
     * @return
     * @throws SevereException
     */
    public boolean deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO,
            String user,AdapterServicesAccess access) throws SevereException, AdapterException {
        try {
			AdapterUtil.performRemove(configBO, user,access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return false;
    }

    /**
     * @param configBO
     * @return
     * @throws SevereException
     */
    public boolean deleteAssociationOfTask(RoleTaskConfigBO configBO,
            String user,AdapterServicesAccess access) throws SevereException, AdapterException {
        try {
			AdapterUtil.performRemove(configBO, user,access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return false;
    }

    /**
     * @param configBO
     * @return
     * @throws SevereException
     */
    public boolean deleteAssociationOfTask(RoleTaskSrdaConfigBO configBO,
            String user,AdapterServicesAccess access) throws SevereException, AdapterException {
        try {
			AdapterUtil.performRemove(configBO, user,access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
        return false;
    }

    /**
     * Method for checking whether the selected role is associated with any
     * module
     * 
     * @param roleConfigBO
     * @return
     * @throws SevereException
     */
    public List isRoleAssociated(RoleConfigBO roleConfigBO)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Integer(roleConfigBO.getRoleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                RoleConfigBO.class.getName(), "isRoleAssociated", valueSet,
                99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
		return searchResults.getSearchResults();
    }
    
    /**
     * Method for checking whether the selected role is associated with any
     * module
     * 
     * @param roleConfigBO
     * @return
     * @throws SevereException
     */
    public List isRoleAssociated(RoleConfigSrdaBO roleConfigBO)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("roleId", new Integer(roleConfigBO.getRoleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		RoleConfigSrdaBO.class.getName(), "isRoleAssociated", valueSet,
                99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
		return searchResults.getSearchResults();
    }

    /**
     * Method to check whether the selected module is associated
     * 
     * @param configBO
     * @return
     * @throws SevereException
     */
    public List isModuleAssociated(RoleTaskConfigBO configBO)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(configBO.getModuleId()));
        valueSet.put("roleId", new Integer(configBO.getRolModTaskId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                RoleTaskConfigBO.class.getName(), "isModuleAssociated",
                valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
		return searchResults.getSearchResults();

    }

    /**
     * Method to check whether the task is associated with the role/module
     * 
     * @param roleConfigBO
     * @return
     * @throws SevereException
     */
    public List isRoleModuleTaskAssociated(RoleTaskConfigBO roleConfigBO)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("roleModTaskId", new Integer(roleConfigBO
                .getRolModTaskId()));
        valueSet.put("taskId",new Integer(roleConfigBO.getTaskId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                RoleSubTaskConfigBO.class.getName(),
                "isRoleModuleTaskAssociated", valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);	
		}
		return searchResults.getSearchResults();
    }

}