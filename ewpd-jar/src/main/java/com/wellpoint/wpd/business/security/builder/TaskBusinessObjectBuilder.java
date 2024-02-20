/*
 * SecurityBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.security.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.security.adapter.TaskAdapterManager;
import com.wellpoint.wpd.business.task.locatecriteria.TaskLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
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
public class TaskBusinessObjectBuilder {
    /**
     * Method for creating/updating a task
     * 
     * @param taskBO
     * @param user
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public boolean persist(TaskBO taskBO, User user, boolean insertFlag)
            throws SevereException {

        TaskAdapterManager adapter = new TaskAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {

            taskBO.setCreatedUser(audit.getUser());
            taskBO.setCreatedTimestamp(audit.getTime());
            taskBO.setLastUpdatedTimestamp(audit.getTime());
            taskBO.setLastUpdatedUser(audit.getUser());
            if (insertFlag) {

                taskBO.setTaskId(sequenceAdapterManager.getNextTaskSequence());
                adapter.createTask(taskBO, audit);
            } else {
                adapter.updateTask(taskBO, audit);
            }
        } catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(TaskBO taskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch (AdapterException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(TaskBO taskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(TaskBO taskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        return true;
    }

    /**
     * Method for creating/updating a task
     * 
     * @param taskBO
     * @param user
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public boolean persist(TaskSrdaBO taskSrdaBO, User user, boolean insertFlag)
            throws SevereException {

        TaskAdapterManager adapter = new TaskAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {

        	taskSrdaBO.setCreatedUser(audit.getUser());
        	taskSrdaBO.setCreatedTimestamp(audit.getTime());
        	taskSrdaBO.setLastUpdatedTimestamp(audit.getTime());
        	taskSrdaBO.setLastUpdatedUser(audit.getUser());
            if (insertFlag) {

            	
                adapter.createTask(taskSrdaBO, audit);
            } else {
                adapter.updateTask(taskSrdaBO, audit);
            }
        } catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(TaskBO taskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch (AdapterException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(TaskBO taskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(TaskBO taskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        return true;
    }
    /**
     * Method to Create/Update a subtask
     * 
     * @param subTaskBO
     * @param user
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(SubTaskBO subTaskBO, User user, boolean insertFlag)
            throws SevereException {

        TaskAdapterManager adapter = new TaskAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {
            subTaskBO.setLastUpdatedTimestamp(audit.getTime());
            subTaskBO.setLastUpdatedUser(audit.getUser());
            if (insertFlag) {
                subTaskBO.setCreatedUser(audit.getUser());
                subTaskBO.setCreatedTimestamp(audit.getTime());
                subTaskBO.setSubTaskId(sequenceAdapterManager
                        .getNextSecuritySubTaskSequence());
                adapter.createSubTask(subTaskBO, audit);
            } else {
                adapter.updateSubTask(subTaskBO, audit);
            }
        } catch (SevereException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(SubTaskBO subTaskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(SubTaskBO subTaskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(SubTaskBO subTaskBO, User user, boolean insertFlag), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        return true;
    }

    /**
     * Method to locate the task/subtask based on the locate criteria
     * 
     * @return List
     * @throws SevereException
     */
    public List locate(TaskLocateCriteria taskLocateCriteria)
            throws SevereException {

        // Create an instance of the Adapter Manager
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();

        // Call the adapter locate()
        List taskSearchResultList;
		try {
			taskSearchResultList = securityAdapterManager
			        .locate(taskLocateCriteria);
		}catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
		}catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
		if(taskLocateCriteria.getSrdaFlag().equalsIgnoreCase("SRDA")){
			assignTaskTypeSrda(taskSearchResultList);
		}else{
			assignTaskType(taskSearchResultList);
		}

        // Return to the business service
        return taskSearchResultList;
    }

    
    
    private void assignTaskTypeSrda(List taskSearchResultList) {
		
    	// Check whether list is null or empty
        if (null != taskSearchResultList && !taskSearchResultList.isEmpty()) {

            // Iterate through the list
            for (int i = 0; i < taskSearchResultList.size(); i++) {
                // Get the SubTaskBO
                SubTaskSrdaBO subTaskBO = (SubTaskSrdaBO) taskSearchResultList.get(i);

                // Check whether the module id is 0
                if (subTaskBO.getModuleId() == 0) {

                    // If null, then set the Task type as 'Parent'
                    subTaskBO.setTaskType("Parent");
                } else {
                    // Else, set the Task type as 'Child'
                    subTaskBO.setTaskType("Child");
                }
            }
        }
	}

	private void assignTaskType(List taskSearchResultList) {
    	
    	// Check whether list is null or empty
        if (null != taskSearchResultList && !taskSearchResultList.isEmpty()) {

            // Iterate through the list
            for (int i = 0; i < taskSearchResultList.size(); i++) {
                // Get the SubTaskBO
                SubTaskBO subTaskBO = (SubTaskBO) taskSearchResultList.get(i);

                // Check whether the module id is 0
                if (subTaskBO.getModuleId() == 0) {

                    // If null, then set the Task type as 'Parent'
                    subTaskBO.setTaskType("Parent");
                } else {
                    // Else, set the Task type as 'Child'
                    subTaskBO.setTaskType("Child");
                }
            }
        }

       
    }

    /**
     * Method to delete the task
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(TaskBO taskBO, User user) throws SevereException{
  
        Logger
                .logInfo("SecurityBusinessObjectBuilder - Entering deleteTask(): Task");
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        try {
            securityAdapterManager.deleteTask(taskBO, user);
        } catch (SevereException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(TaskBO taskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(TaskBO taskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(TaskBO taskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        Logger
                .logInfo("SecurityBusinessObjectBuilder - Returning deleteTask(): Task");
        return true;
    }

    /**
     * Method to delete the SubTask
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(SubTaskBO subTaskBO, User user)
            throws SevereException {
        Logger
                .logInfo("SecurityBusinessObjectBuilder - Entering deleteTask(): Task");
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        try {
        	//if multiple delete
        	 if(subTaskBO.getAction()==1)
        	 {
        	 	List subTaskList=subTaskBO.getSubTaskList();
        	 	for(int i=0;i<subTaskList.size();i++){
        	 		subTaskBO.setSubTaskId(Integer.parseInt((String) subTaskList.get(i)));
        	 		securityAdapterManager.deleteSubTask(subTaskBO, user);
        	 	}
        	 }
        	 else
        	 	securityAdapterManager.deleteSubTask(subTaskBO, user);
        } catch (SevereException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(SubTaskBO subTaskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(SubTaskBO subTaskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);	
	    }catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(SubTaskBO subTaskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        Logger
                .logInfo("SecurityBusinessObjectBuilder - Returning deleteTask(): Task");
        return true;
    }
    
    /**
     * Method to delete the TaskSrdaBO
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    
    public boolean delete(TaskSrdaBO taskSrdaBO, User user) throws SevereException{
    	  
        Logger
                .logInfo("SecurityBusinessObjectBuilder - Entering deleteTask(): Task");
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        try {
        	 securityAdapterManager.deleteTaskSrda(taskSrdaBO, user);
        } catch (SevereException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(TaskBO taskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(TaskBO taskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(TaskBO taskBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        Logger
                .logInfo("SecurityBusinessObjectBuilder - Returning deleteTask(): Task");
        return true;
    }
   
    /**
     * Method to delete the task association
     * 
     * @param taskConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean deleteTaskAssociation(TaskConfigBO taskConfigBO, User user)
            throws SevereException{
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        try {
            securityAdapterManager.deleteTaskAssociation(taskConfigBO, user);
        } catch (SevereException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteTaskAssociation(TaskConfigBO taskConfigBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteTaskAssociation(TaskConfigBO taskConfigBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteTaskAssociation(TaskConfigBO taskConfigBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        return true;
    }

    /**
     * Method to retrieve the task
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public TaskBO retrieve(TaskBO taskBO) throws SevereException{
        TaskAdapterManager adapter = new TaskAdapterManager();
        try {
			return adapter.retrieve(taskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }

    }
    
    
    /**
     * Method to retrieve the task
     * 
     * @param taskSrdaBO
     * @return
     * @throws SevereException
     */
    public TaskSrdaBO retrieve(TaskSrdaBO taskSrdaBO) throws SevereException{
        TaskAdapterManager adapter = new TaskAdapterManager();
        try {
			return adapter.retrieve(taskSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(TaskSrdaBO taskSrdaBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(TaskSrdaBO taskSrdaBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(TaskSrdaBO taskSrdaBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }

    }

    /**
     * Method to retrieve a subtask
     * 
     * @param subTaskBO
     * @return
     * @throws SevereException
     */
    public SubTaskBO retrieve(SubTaskBO subTaskBO) throws SevereException{
        TaskAdapterManager adapter = new TaskAdapterManager();
        try {
			return adapter.retrieve(subTaskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(SubTaskBO subTaskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(SubTaskBO subTaskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(SubTaskBO subTaskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }

    }

    /**
     * Method to return the list of subtasks associated with the task
     * 
     * @param taskLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public LocateResults locateModuleTaskAssociation(
            TaskLocateCriteria taskLocateCriteria) throws SevereException {
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        LocateResults locateResults = new LocateResults();
        List resultList;
		try {
			resultList = securityAdapterManager
			        .locateModuleTaskAssociation(taskLocateCriteria);
		}catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleTaskAssociation(TaskLocateCriteria taskLocateCriteria) method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
		}catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleTaskAssociation(TaskLocateCriteria taskLocateCriteria) method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleTaskAssociation(TaskLocateCriteria taskLocateCriteria) method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
		locateResults.setLocateResults(resultList);
        return locateResults;
    }

    /**
     * Method to return the list of subtasks associated with the task
     * 
     * @param taskLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public LocateResults locateTaskSubTaskAssociation(
            TaskLocateCriteria taskLocateCriteria) throws SevereException {
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        LocateResults locateResults = new LocateResults();
        List resultList;
		try {
			resultList = securityAdapterManager
			        .locateTaskSubTaskAssociation(taskLocateCriteria);
		}catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskSubTaskAssociation(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
		}catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskSubTaskAssociation(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskSubTaskAssociation(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
		locateResults.setLocateResults(resultList);
        return locateResults;
    }

    /**
     * Method to return the list of parentTask associated with the task
     * 
     * @param taskLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public LocateResults locateRoleSubTaskAssociation(
            TaskLocateCriteria taskLocateCriteria) throws SevereException {
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        LocateResults locateResults = new LocateResults();
        List resultList;
		try {
			resultList = securityAdapterManager
			        .locateRoleSubTaskAssociation(taskLocateCriteria);
		}catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleSubTaskAssociation( TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
		}catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleSubTaskAssociation( TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleSubTaskAssociation( TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
		locateResults.setLocateResults(resultList);
        return locateResults;
    }

    /**
     * @param boolean
     * @throws SevereException
     */
    public boolean isTaskDuplicate(TaskBO taskBO) throws SevereException {
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();

        try {
			return securityAdapterManager.isTaskDuplicate(taskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isTaskDuplicate(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isTaskDuplicate(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isTaskDuplicate(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    
    /**
     * @param boolean
     * @throws SevereException
     */
    public boolean isTaskSrdaDuplicate(TaskSrdaBO taskSrdaBO) throws SevereException {
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();

        try {
			return securityAdapterManager.isTaskSrdaDuplicate(taskSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isTaskDuplicate(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isTaskDuplicate(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isTaskDuplicate(TaskBO taskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method to check for duplicate subtask
     * 
     * @param boolean
     * @throws SevereException
     */
    public boolean isSubTaskDuplicate(SubTaskBO subTaskBO)
            throws SevereException {
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();

        try {
			return securityAdapterManager.isSubTaskDuplicate(subTaskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isSubTaskDuplicate(SubTaskBO subTaskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isSubTaskDuplicate(SubTaskBO subTaskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isSubTaskDuplicate(SubTaskBO subTaskBO), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method to return the list of sub-task which are not associated with the
     * selected task
     * 
     * @param taskLocateCriteria
     * @return
     * @throws SevereException
     */
    public LocateResults locateSubTask(TaskLocateCriteria taskLocateCriteria)
            throws SevereException {
        TaskAdapterManager securityAdapterManager = new TaskAdapterManager();
        LocateResults locateResults = new LocateResults();

        List resultList;
		try {
			resultList = securityAdapterManager
			        .locateSubTask(taskLocateCriteria);
		}catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateSubTask(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
		}catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateSubTask(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateSubTask(TaskLocateCriteria taskLocateCriteria), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
		locateResults.setLocateResults(resultList);
        return locateResults;
    }

    /**
     * Method to save the task association
     * 
     * @param taskConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean saveTaskAssociation(TaskConfigBO taskConfigBO, User user)
            throws SevereException{
        TaskAdapterManager adapter = new TaskAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {

            taskConfigBO.setCreatedUser(audit.getUser());
            taskConfigBO.setCreatedTimestamp(audit.getTime());
            taskConfigBO.setLastUpdatedTimestamp(audit.getTime());
            taskConfigBO.setLastUpdatedUser(audit.getUser());
            adapter.saveSubTaskAssociation(taskConfigBO, audit);
        } catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveTaskAssociation(TaskConfigBO taskConfigBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }catch (SevereException ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveTaskAssociation(TaskConfigBO taskConfigBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    } catch (Exception ex) {
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveTaskAssociation(TaskConfigBO taskConfigBO, User user), in TaskBusinessObjectBuilder",
                        errorParams, ex);
	    }
        return true;
    }

    /**
     * Method to locate the list of task
     * 
     * @return List
     * @throws SevereException
     */
    public List locateTaskForLookUp() throws SevereException {
        TaskAdapterManager adapter = new TaskAdapterManager();
        try {
			return adapter.locateTaskForLookUp();
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForLookUp(), in TaskBusinessObjectBuilder",
                        errorParams, ex);	
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForLookUp(), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForLookUp(), in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method to locate the subtask
     * 
     * @return
     * @throws SevereException
     */
    public List locatesubTaskForLookUp() throws SevereException {
        TaskAdapterManager adapter = new TaskAdapterManager();
        try {
			return adapter.locateSubTaskForLookUp();
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locatesubTaskForLookUp method, in TaskBusinessObjectBuilder",
                        errorParams, ex);	
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locatesubTaskForLookUp method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locatesubTaskForLookUp method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method to locate the list os task
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateTaskForCreate(TaskBO taskBO) throws SevereException {
        TaskAdapterManager adapter = new TaskAdapterManager();
        try {
			return adapter.locateTaskForCreate(taskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForCreate method, in TaskBusinessObjectBuilder",
                        errorParams, ex);	
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForCreate method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForCreate method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method to locate the task for role 
     * @return
     * @throws SevereException
     */
    public List locateTaskForRole(String srdaFlag) throws SevereException {
        TaskAdapterManager adapter = new TaskAdapterManager();
        try {
			return adapter.locateTaskForRole(srdaFlag);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForRole method, in TaskBusinessObjectBuilder",
                        errorParams, ex);	
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForRole method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskForRole method, in TaskBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

}