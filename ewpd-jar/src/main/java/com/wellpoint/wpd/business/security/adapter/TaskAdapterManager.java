/*
 * SecurityAdapterManager.java
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

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.task.locatecriteria.TaskLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;
import com.wellpoint.wpd.common.security.bo.SubTaskSrdaBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskConfigBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskAdapterManager {
    /**
     * Method to create a task
     * 
     * @param taskBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean createTask(TaskBO taskBO, Audit audit) throws SevereException,AdapterException{

        try {
			AdapterUtil
			        .performInsert(taskBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }
    /**
     * Method to create a task
     * 
     * @param taskBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean createTask(TaskSrdaBO taskSrdaBO, Audit audit) throws SevereException,AdapterException{

        try {
			AdapterUtil
			        .performInsert(taskSrdaBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method to retrieve a task
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public TaskBO retrieve(TaskBO taskBO) throws SevereException, AdapterException {
        try {
			AdapterUtil.performRetrieve(taskBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return taskBO;
    }
    /**
     * Method to retrieve a task
     * 
     * @param taskSrdaBO
     * @return
     * @throws SevereException
     */
    public TaskSrdaBO retrieve(TaskSrdaBO taskSrdaBO) throws SevereException, AdapterException {
        try {
			AdapterUtil.performRetrieve(taskSrdaBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return taskSrdaBO;
    }

    /**
     * method for updating a task
     * 
     * @param securityBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */

    public boolean updateTask(TaskBO taskBO, Audit audit) throws SevereException, AdapterException {
        try {
			AdapterUtil
			        .performUpdate(taskBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * method for updating a task
     * 
     * @param securityBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */

    public boolean updateTask(TaskSrdaBO taskSrdaBO, Audit audit) throws SevereException, AdapterException {
        try {
			AdapterUtil
			        .performUpdate(taskSrdaBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method t create a subTask
     * 
     * @param subTaskBO
     * @param audit
     * @param adapterServiceAccess
     * @return boolean
     * @throws SevereException
     */
    public boolean createSubTask(SubTaskBO subTaskBO, Audit audit) throws SevereException, AdapterException {

        try {
			AdapterUtil.performInsert(subTaskBO, audit.getUser());
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method to update a subtask
     * 
     * @param subTaskBO
     * @param audit
     * @param adapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean updateSubTask(SubTaskBO subTaskBO, Audit audit) throws SevereException, AdapterException{
        try {
			AdapterUtil.performUpdate(subTaskBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method to retrieve a subtask
     * 
     * @param subTaskBO
     * @return SubTaskBO
     * @throws SevereException
     */
    public SubTaskBO retrieve(SubTaskBO subTaskBO) throws SevereException,AdapterException {
        try {
			AdapterUtil.performRetrieve(subTaskBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return subTaskBO;
    }

    /**
     * Method to locate task based on the search criteria
     * 
     * @return List
     * @throws SevereException
     */
    public List locate(TaskLocateCriteria taskLocateCriteria)
            throws SevereException, AdapterException {

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        // Create a reference of searchResults
        SearchResults searchResults = null;

        HashMap criteriaValues = new HashMap();

        // Set the search criteria values in the hash map
        criteriaValues = getSearchCriteriaMap(taskLocateCriteria);

        // Set the criteria to the searchCriteria instance
        if(taskLocateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
        	  searchCriteria = AdapterUtil.getAdapterSearchCriteria(SubTaskSrdaBO.class
                      .getName(), "locateTaskAndSubTaskQuerySrda", criteriaValues);
        }else{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(SubTaskBO.class
                .getName(), "locateTaskAndSubTaskQuery", criteriaValues);
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
     * Method to delete task
     * 
     * @param TaskBO
     * @param user
     * @param securityAdapterServiceAccess
     * @return boolean
     * @throws SevereException
     */

    public boolean deleteTask(TaskBO taskBO, User user)
            throws SevereException, AdapterException {
        Logger
                .logInfo("SecurityAdapterManager - Entering deleteTask(): Task Remove");
        try {
			AdapterUtil.performRemove(taskBO, user.getUserId());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("SecurityAdapterManager - Returning deleteTask(): Task Remove");
        return true;
    }

    /**
     * Method to delete SubTask
     * 
     * @param SubTaskBO
     * @param user
     * @param securityAdapterServiceAccess
     * @return boolean
     * @throws SevereException
     */

    public boolean deleteSubTask(SubTaskBO subTaskBO, User user)
            throws SevereException, AdapterException {
        Logger
                .logInfo("SecurityAdapterManager - Entering deleteSubTask(): SubTask Remove");
        try {
			AdapterUtil.performRemove(subTaskBO, user.getUserId());
		} catch (Exception ex){		
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("SecurityAdapterManager - Returning deleteSubTask(): SubTask Remove");
        return true;
    }
    
    /**
     * Method to delete TaskSrda
     * 
     * @param SubTaskSrdaBO
     * @param user
     * @param securityAdapterServiceAccess
     * @return boolean
     * @throws SevereException
     */

    public boolean deleteTaskSrda(TaskSrdaBO taskSrdaBO, User user)
            throws SevereException, AdapterException {
        Logger
                .logInfo("SecurityAdapterManager - Entering deleteTaskSrda(): TaskSrda Remove");
        try {
			AdapterUtil.performRemove(taskSrdaBO, user.getUserId());
		} catch (Exception ex){		
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("SecurityAdapterManager - Returning deleteTaskSrda(): TaskSrda Remove");
        return true;
    }
    
    

    /**
     * Method to delete the association of a task
     * 
     * @param taskConfigBO
     * @param user
     * @param securityAdapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteTaskAssociation(TaskConfigBO taskConfigBO, User user)
            throws SevereException, AdapterException{
        Logger
                .logInfo("SecurityAdapterManager - Entering deleteTaskAssociation(): TaskAssociation Delete");
        try {
			AdapterUtil.performRemove(taskConfigBO, user.getUserId());
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("SecurityAdapterManager - Returning deleteTaskAssociation(): TaskAssociation Delete");
        return true;
    }

    /**
     * Get the Criteria for search
     * 
     * @param taskLocateCriteria
     *            TaskLocateCriteria Object
     * @return HashMap.
     */
    private HashMap getSearchCriteriaMap(TaskLocateCriteria taskLocateCriteria) {
        Logger
                .logInfo("Entering the method for getting criteria for task locate criteria bo");
        HashMap criteriaValues = new HashMap();
        String name = taskLocateCriteria.getTaskName();
        if (name != null && name.trim().length() > 0) {
            name = "%" + name.toUpperCase() + "%";
        } else
            name = null;
        criteriaValues.put("subTaskName", name);
        /*
         * String type = taskLocateCriteria.getSecurityType(); if (type != null &&
         * type.trim().length() > 0) { type = "%" + type.toUpperCase() + "%"; }
         * else type = null; criteriaValues.put("entityType", type);
         */
        Logger
                .logInfo("Returning the method for getting criteria for task locate criteria bo");
        return criteriaValues;
    }

    /**
     * 
     * @param taskLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public List locateModuleTaskAssociation(
            TaskLocateCriteria taskLocateCriteria) throws SevereException, AdapterException {
        SearchResults searchResults = null;

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        HashMap criteriValues = new HashMap();

        Integer id = new Integer(taskLocateCriteria.getTaskId());

        criteriValues.put("taskId", id);
        
        if(taskLocateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
      	  searchCriteria = AdapterUtil.getAdapterSearchCriteria(TaskSrdaBO.class
                    .getName(), "retrieveAssociatedModulesOfTask", criteriValues);
      }else{
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(TaskBO.class
                .getName(), "retrieveAssociatedModulesOfTask", criteriValues);
      }

        try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}

        return searchResults.getSearchResults();
    }

    /**
     * 
     * @param taskLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public List locateTaskSubTaskAssociation(
            TaskLocateCriteria taskLocateCriteria) throws SevereException, AdapterException {
        SearchResults searchResults = null;

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        HashMap criteriValues = new HashMap();

        Integer id = new Integer(taskLocateCriteria.getTaskId());

        criteriValues.put("taskId", id);

        searchCriteria = AdapterUtil.getAdapterSearchCriteria(SubTaskBO.class
                .getName(), "retrieveAssociatedSubTaskOfTask", criteriValues);

        try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}

        return searchResults.getSearchResults();
    }

    /**
     * 
     * @param taskLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public List locateRoleSubTaskAssociation(
            TaskLocateCriteria taskLocateCriteria) throws SevereException, AdapterException {
        SearchResults searchResults = null;

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        HashMap criteriValues = new HashMap();

        Integer id = new Integer(taskLocateCriteria.getTaskId());

        criteriValues.put("subTaskId", id);

        searchCriteria = AdapterUtil.getAdapterSearchCriteria(SubTaskBO.class
                .getName(), "locateRoleSubTaskAssociation", criteriValues);

        try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}

        return searchResults.getSearchResults();
    }

    /**
     * Method to locate the list of sub-tasks which are not associated with the
     * selected task
     * 
     * @param taskLocateCriteria
     * @return
     * @throws SevereException
     */
    public List locateSubTask(TaskLocateCriteria taskLocateCriteria)
            throws SevereException, AdapterException {
        SearchResults searchResults = null;

        // Create an instance of the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();

        HashMap criteriValues = new HashMap();

        Integer id = new Integer(taskLocateCriteria.getTaskId());

        criteriValues.put("entityId", id);

        searchCriteria = AdapterUtil.getAdapterSearchCriteria(TaskBO.class
                .getName(), "subTaskPopUpQuery", criteriValues);

        try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}

        return searchResults.getSearchResults();
    }

    /**
     * Method to save the subtask association
     * 
     * @param taskConfigBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean saveSubTaskAssociation(TaskConfigBO taskConfigBO,
            Audit audit)
            throws SevereException, AdapterException {
        try {
			AdapterUtil.performInsert(taskConfigBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return true;
    }

    /**
     * Method to find whether the task with same name already exists
     * @param securityBO
     * @return
     * @throws SevereException
     */
    public boolean isTaskDuplicate(TaskBO taskBO) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("taskName", taskBO.getTaskName());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                taskBO.getClass().getName(), "findDuplicate", valueSet, 99999);
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
        return false;
    }
    
    /**
     * Method to find whether the task with same name already exists
     * @param securityBO
     * @return
     * @throws SevereException
     */
    public boolean isTaskSrdaDuplicate(TaskSrdaBO taskSrdaBO) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("taskName", taskSrdaBO.getTaskName());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		taskSrdaBO.getClass().getName(), "findDuplicate", valueSet, 99999);
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
        return false;
    }

    /**
     * Method to check for duplicate subtask
     * 
     * @param securityBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isSubTaskDuplicate(SubTaskBO subTaskBO)
            throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("subTaskName", subTaskBO.getSubTaskName());
        valueSet.put("taskId", new Integer(subTaskBO.getTaskId()));
        valueSet.put("moduleId", new Integer(subTaskBO.getModuleId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                subTaskBO.getClass().getName(), "findDuplicate", valueSet,
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
        return false;
    }

    /**
     * method for locating the tasks
     * 
     * @return
     * @throws SevereException
     */
    public List locateTaskForLookUp() throws SevereException, AdapterException {
        HashMap map = new HashMap();
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                TaskBO.class.getName(), "taskLookUpQuery", map, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		return searchResults.getSearchResults();
    }

    /**
     * Method for locating the subtask
     * 
     * @return
     * @throws SevereException
     */
    public List locateSubTaskForLookUp() throws SevereException, AdapterException {
        HashMap map = new HashMap();
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                SubTaskBO.class.getName(), "subTaskLookUpQuery", map, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		return searchResults.getSearchResults();
    }

    /**
     * Method for locating the task for creation
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateTaskForCreate(TaskBO taskBO) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("moduleId", new Integer(taskBO.getModuleId()).toString());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                TaskBO.class.getName(), "taskAsssociationQuery", valueSet,
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
     * Method for locating the task for role association
     * @return
     * @throws SevereException
     */
    public List locateTaskForRole(String srdaFlag) throws SevereException,AdapterException {
        HashMap map = new HashMap();
        SearchCriteria searchCriteria;
        
      if(null != srdaFlag && srdaFlag.equalsIgnoreCase("SRDA")){
    	  searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                  TaskSrdaBO.class.getName(), "TaskLookUpQueryForRole", map, 99999);
      }else{
         searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                TaskBO.class.getName(), "TaskLookUpQueryForRole", map, 99999);
      }
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		return searchResults.getSearchResults();
    }

}