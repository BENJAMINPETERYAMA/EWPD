/*
 * ModuleBusinessObjectBuilder.java
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

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.security.adapter.ModuleAdapterManager;
import com.wellpoint.wpd.business.security.locatecriteria.ModuleLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigBO;
import com.wellpoint.wpd.common.security.bo.ModuleConfigSrdaBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleBusinessObjectBuilder {
    /**
     * Method for creating a nee module and updating the existing one
     * 
     * @param moduleBO
     * @param user
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public boolean persist(ModuleBO moduleBO, User user, boolean insertFlag)
            throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering createModule(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {
            moduleBO.setCreatedUser(audit.getUser());
            moduleBO.setCreatedTimestamp(audit.getTime());
            moduleBO.setLastUpdatedTimestamp(audit.getTime());
            moduleBO.setLastUpdatedUser(audit.getUser());

            if (insertFlag) {
                moduleBO.setModuleId(sequenceAdapterManager
                        .getNextModuleSequence());
                adapter.createModule(moduleBO, audit);
            } else {
                adapter.updateModule(moduleBO, audit);
            }
        } catch (SevereException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ModuleBO moduleBO, User user, boolean insertFlag) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    } catch(AdapterException ex){
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ModuleBO moduleBO, User user, boolean insertFlag) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ModuleBO moduleBO, User user, boolean insertFlag) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning createModule(): Module");
        return true;
    }
    
    /**
     * Method for creating a nee module and updating the existing one
     * 
     * @param moduleBO
     * @param user
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public boolean persist(ModuleSrdaBO moduleSrdaBO, User user, boolean insertFlag)
            throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering createModule(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {
        	moduleSrdaBO.setCreatedUser(audit.getUser());
        	moduleSrdaBO.setCreatedTimestamp(audit.getTime());
        	moduleSrdaBO.setLastUpdatedTimestamp(audit.getTime());
        	moduleSrdaBO.setLastUpdatedUser(audit.getUser());

            if (insertFlag) {
            	moduleSrdaBO.setModuleId(sequenceAdapterManager
                        .getNextModuleSequence());
               // adapter.createModule(moduleSrdaBO, audit);
            } else {
                adapter.updateModule(moduleSrdaBO, audit);
            }
        } catch (SevereException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ModuleBO moduleBO, User user, boolean insertFlag) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    } catch(AdapterException ex){
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ModuleBO moduleBO, User user, boolean insertFlag) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ModuleBO moduleBO, User user, boolean insertFlag) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning createModule(): Module");
        return true;
    }

    /**
     * Method to delete the module
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(ModuleBO moduleBO, User user) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering deleteModule(): Module");
        ModuleAdapterManager moduleAdapterManager = new ModuleAdapterManager();
        try {
            moduleAdapterManager.deleteModule(moduleBO, user);
        } catch (SevereException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete(ModuleBO moduleBO, User user) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    } catch(AdapterException ex){
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete(ModuleBO moduleBO, User user) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete(ModuleBO moduleBO, User user) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning deleteModule(): Module");
        return true;
    }
    
    /**
     * Method to delete the module
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(ModuleSrdaBO moduleSrdaBO, User user) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering deleteModule(): Module");
        ModuleAdapterManager moduleAdapterManager = new ModuleAdapterManager();
        try {
            moduleAdapterManager.deleteModule(moduleSrdaBO, user);
        } catch (SevereException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete(ModuleBO moduleBO, User user) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    } catch(AdapterException ex){
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete(ModuleBO moduleBO, User user) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete(ModuleBO moduleBO, User user) in ModuleBusinessObjectBuilder",
					errorParams, ex);
	    }
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning deleteModule(): Module");
        return true;
    }

    /**
     * Method to return the list of role associated with the module
     * 
     * @param moduleLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public LocateResults locateRoleCofiguration(
            ModuleLocateCriteria moduleLocateCriteria) throws SevereException {
        ModuleAdapterManager moduleAdapterManager = new ModuleAdapterManager();
        LocateResults locateResults = new LocateResults();

        List resultList;
		try {
			resultList = moduleAdapterManager
			        .locateRoleConfiguration(moduleLocateCriteria);
		 }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleCofiguration(ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
		 }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleCofiguration(ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleCofiguration(ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
		locateResults.setLocateResults(resultList);
        return locateResults;
    }

    /**
     * Method to return the list of task associated with the module
     * 
     * @param moduleLocateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public LocateResults locateTaskModuleCofiguration(
            ModuleLocateCriteria moduleLocateCriteria) throws SevereException {
        ModuleAdapterManager moduleAdapterManager = new ModuleAdapterManager();
        LocateResults locateResults = new LocateResults();

        List resultList;
		try {
			resultList = moduleAdapterManager
			        .locateTaskModuleConfiguration(moduleLocateCriteria);
		}catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskModuleCofiguration(locateTaskModuleCofiguration), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
		}catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskModuleCofiguration(locateTaskModuleCofiguration), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskModuleCofiguration(locateTaskModuleCofiguration), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
		locateResults.setLocateResults(resultList);
        return locateResults;
    }

    /**
     * Method for retrieving moduleBO
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public ModuleBO retrieve(ModuleBO moduleBO) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering retrieveModule(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning retrieveModule(): Module");
        try {
			return adapter.retrieve(moduleBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for retrieving moduleSrdaBO
     * 
     * @param moduleSrdaBO
     * @return
     * @throws SevereException
     */
    public ModuleSrdaBO retrieve(ModuleSrdaBO moduleSrdaBO) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering retrieveModule(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning retrieveModule(): Module");
        try {
			return adapter.retrieve(moduleSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    
    /**
     * Method for checking whether the module is existing
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public boolean isModuleDuplicate(ModuleBO moduleBO) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering isModuleDuplicate(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning isModuleDuplicate(): Module");
        try {
			return adapter.isModuleDuplicate(moduleBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleDuplicate (ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleDuplicate (ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleDuplicate (ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    
    /**
     * Method for checking whether the module is existing
     * 
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public boolean isModuleDuplicateSrda(ModuleSrdaBO moduleSrdaBO) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering isModuleDuplicate(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Returning isModuleDuplicate(): Module");
        try {
			return adapter.isModuleDuplicateSrda(moduleSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleDuplicate (ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleDuplicate (ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleDuplicate (ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for locating the module
     * 
     * @return
     * @throws SevereException
     */
    public List locateModuleForLookUp(String srdaFlag) throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
			return adapter.locateModuleForLookUp(srdaFlag);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleForLookUp(), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleForLookUp(), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleForLookUp(), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method to locate the modules based on the locate criteria
     * 
     * @return List
     * @throws SevereException
     */
    public List locate(ModuleLocateCriteria moduleLocateCriteria)
            throws SevereException {

        // Create an instance of the Adapter Manager
        ModuleAdapterManager adapterManager = new ModuleAdapterManager();

        try {
			// Call the adapter locate() and return to the business service
			return adapterManager.locate(moduleLocateCriteria);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate (ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate (ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate (ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for locating the non-associated task of a module
     * 
     * @param taskBO
     * @param user
     * @return
     * @throws SevereException
     */
    public List locateTask(TaskBO taskBO, User user) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering locateTask(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
			return adapter.locateTask(taskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    
    /**
     * Method for locating the non-associated task of a module
     * 
     * @param taskSrdaBO
     * @param user
     * @return
     * @throws SevereException
     */
    public List locateTaskSrda(TaskSrdaBO taskSrdaBO, User user) throws SevereException {
        Logger
                .logInfo("ModuleBusinessObjectBuilder - Entering locateTask(): Module");
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
			return adapter.locateTaskSrda(taskSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for locating the associated tasks of the selected module
     * 
     * @param taskBO
     * @return
     * @throws SevereException
     */
    public List locateModuleAssociation(TaskBO taskBO) throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
			return adapter.locateModuleAssociaion(taskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleAssociation (TaskBO taskBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleAssociation (TaskBO taskBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleAssociation (TaskBO taskBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    
    /**
     * Method for locating the associated tasks of the selected module
     * 
     * @param taskSrdaBO
     * @return
     * @throws SevereException
     */
    public List locateModuleAssociationSrda(TaskSrdaBO taskSrdaBO) throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
			return adapter.locateModuleAssociaionSrda(taskSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleAssociation (TaskSrdaBO taskSrdaBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleAssociation (TaskSrdaBO taskSrdaBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleAssociation (TaskBO taskBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for creating the module task association
     * 
     * @param taskIdList
     * @param user
     * @param moduleId
     * @return
     * @throws SevereException
     */
    public boolean persist(List taskIdList, User user, int moduleId)
            throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        ModuleConfigBO moduleConfigBO = new ModuleConfigBO();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        ModuleBO moduleBO=new ModuleBO();
        moduleBO.setModuleId(moduleId);
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
        	
            moduleConfigBO.setCreatedUser(audit.getUser());
            moduleConfigBO.setCreatedTimestamp(audit.getTime());
            moduleConfigBO.setLastUpdatedTimestamp(audit.getTime());
            moduleConfigBO.setLastUpdatedUser(audit.getUser());
            for (int i = 0; i < taskIdList.size(); i++) {
                moduleConfigBO.setModuleId(moduleId);
                moduleConfigBO.setTaskId(((Integer) taskIdList.get(i))
                        .intValue());
                adapter.createModuleAssociation(moduleConfigBO, audit,
                        adapterServiceAccess);
            }
            //retrieving module details for updating last created userId and timestamp
            moduleBO=adapter.retrieve(moduleBO);
            moduleBO.setLastUpdatedTimestamp(audit.getTime());
            moduleBO.setLastUpdatedUser(audit.getUser());
            //updating lastcreated User and timeStamp
            adapter.updateModule(moduleBO, audit);
            
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, User user, int moduleId), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, User user, int moduleId), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }catch (Exception ex) {
	    	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, User user, int moduleId), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }
        return true;
    }
    
    /**
     * Method for creating the module task association
     * 
     * @param taskIdList
     * @param user
     * @param moduleId
     * @return
     * @throws SevereException
     */
    public boolean persistSrda(List taskIdList, User user, int moduleId)
            throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        ModuleConfigSrdaBO moduleConfigSrdaBO = new ModuleConfigSrdaBO();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        ModuleSrdaBO moduleSrdaBO=new ModuleSrdaBO();
        moduleSrdaBO.setModuleId(moduleId);
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
        	
        	moduleConfigSrdaBO.setCreatedUser(audit.getUser());
        	moduleConfigSrdaBO.setCreatedTimestamp(audit.getTime());
        	moduleConfigSrdaBO.setLastUpdatedTimestamp(audit.getTime());
        	moduleConfigSrdaBO.setLastUpdatedUser(audit.getUser());
            for (int i = 0; i < taskIdList.size(); i++) {
            	moduleConfigSrdaBO.setModuleId(moduleId);
            	moduleConfigSrdaBO.setTaskId(((Integer) taskIdList.get(i))
                        .intValue());
                adapter.createModuleAssociationSrda(moduleConfigSrdaBO, audit,
                        adapterServiceAccess);
            }
            //retrieving module details for updating last created userId and timestamp
            moduleSrdaBO=adapter.retrieve(moduleSrdaBO);
            moduleSrdaBO.setLastUpdatedTimestamp(audit.getTime());
            moduleSrdaBO.setLastUpdatedUser(audit.getUser());
            //updating lastcreated User and timeStamp
            adapter.updateModule(moduleSrdaBO, audit);
            
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, User user, int moduleId), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, User user, int moduleId), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }catch (Exception ex) {
	    	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, User user, int moduleId)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, User user, int moduleId), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }
        return true;
    }

    /**
     * Method for deleting the associated task of a module
     * 
     * @param moduleConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean deleteModuleAssociation(ModuleConfigBO moduleConfigBO,
            User user) throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
        .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
        .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        ModuleBO moduleBO=new ModuleBO();
        List taskList=moduleConfigBO.getTaskIdList();
        //getting moduleId for retrieving module details 
        moduleBO.setModuleId(moduleConfigBO.getModuleId());
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user)");
        	for(int i=0;i<taskList.size();i++){
        		moduleConfigBO.setTaskId(Integer.parseInt((String)taskList.get(i)));
        	    adapter.deleteModuleAssociation(moduleConfigBO, user,adapterServiceAccess);
        	}   

            //retrieving module details for updating last created userId and timestamp of Module 
            moduleBO=adapter.retrieve(moduleBO);
            moduleBO.setLastUpdatedTimestamp(audit.getTime());
            moduleBO.setLastUpdatedUser(audit.getUser());
            //updating lastcreated User and timeStamp of Module
            adapter.updateModule(moduleBO, audit,adapterServiceAccess);
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user)");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
	    	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }catch (Exception ex) {
	    	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteModuleAssociation(ModuleConfigBO moduleConfigBO,User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }

        return true;
    }
    
    /**
     * Method for deleting the associated task of a module
     * 
     * @param moduleConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean deleteModuleAssociationSrda(ModuleConfigSrdaBO moduleConfigSrdaBO,
            User user) throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
        .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
        .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        ModuleSrdaBO moduleSrdaBO=new ModuleSrdaBO();
        List taskList=moduleConfigSrdaBO.getTaskIdList();
        //getting moduleId for retrieving module details 
        moduleSrdaBO.setModuleId(moduleConfigSrdaBO.getModuleId());
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user)");
        	for(int i=0;i<taskList.size();i++){
        		moduleConfigSrdaBO.setTaskId(Integer.parseInt((String)taskList.get(i)));
        	    adapter.deleteModuleAssociationSrda(moduleConfigSrdaBO, user,adapterServiceAccess);
        	}   

            //retrieving module details for updating last created userId and timestamp of Module 
        	moduleSrdaBO=adapter.retrieve(moduleSrdaBO);
        	moduleSrdaBO.setLastUpdatedTimestamp(audit.getTime());
        	moduleSrdaBO.setLastUpdatedUser(audit.getUser());
            //updating lastcreated User and timeStamp of Module
            adapter.updateModule(moduleSrdaBO, audit,adapterServiceAccess);
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user)");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    } catch(AdapterException ex){
	    	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }catch (Exception ex) {
	    	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteModuleAssociation(ModuleConfigSrdaBO moduleConfigSrdaBO,User user), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
	    }

        return true;
    }

    /**
     * Method to get all the subtask associated with the task and the module
     * @param subTaskBO
     * @return
     * @throws SevereException
     */
    public List locateSubTaskAsociatedToModule(
            ModuleLocateCriteria moduleLocateCriteria) throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
			return adapter.locateSubTaskAsociatedToModule(moduleLocateCriteria);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateSubTaskAsociatedToModule(ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateSubTaskAsociatedToModule(ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateSubTaskAsociatedToModule(ModuleLocateCriteria moduleLocateCriteria), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * @param moduleBO
     * @return
     * @throws SevereException
     */
    public boolean isSubTaskAsociatedToModule(ModuleBO moduleBO)
            throws SevereException {
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
			return adapter.isSubTaskAsociatedToModule(moduleBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isSubTaskAsociatedToModule(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isSubTaskAsociatedToModule(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isSubTaskAsociatedToModule(ModuleBO moduleBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    /**
     * Method to find whether the selected task is aaociated with any role
     * @param configBO
     * @return
     * @throws SevereException
     */
    public boolean locateTaskAssociation(ModuleConfigBO configBO)throws SevereException{
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
	       	 boolean isAssociatedTask=false;
	    	 List tasklist=configBO.getTaskIdList();
	    	 for(int i=0;i<tasklist.size();i++){
	    		configBO.setTaskId(Integer.parseInt((String)tasklist.get(i)));
	    	 	isAssociatedTask=adapter.isTaskAssociated(configBO);
	    	 	if(isAssociatedTask){
	    	 		break;
	    	 	}
	    	 }
	    	 return isAssociatedTask;

        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskAssociation(ModuleConfigBO configBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskAssociation(ModuleConfigBO configBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskAssociation(ModuleConfigBO configBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    /**
     * Method to find whether the selected task is aaociated with any role
     * @param configBO
     * @return
     * @throws SevereException
     */
    public boolean locateTaskAssociation(ModuleConfigSrdaBO configSrdaBO)throws SevereException{
        ModuleAdapterManager adapter = new ModuleAdapterManager();
        try {
	       	 boolean isAssociatedTask=false;
	    	 List tasklist=configSrdaBO.getTaskIdList();
	    	 for(int i=0;i<tasklist.size();i++){
	    		 configSrdaBO.setTaskId(Integer.parseInt((String)tasklist.get(i)));
	    	 	isAssociatedTask=adapter.isTaskAssociated(configSrdaBO);
	    	 	if(isAssociatedTask){
	    	 		break;
	    	 	}
	    	 }
	    	 return isAssociatedTask;

        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskAssociation(ModuleConfigBO configBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskAssociation(ModuleConfigBO configBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTaskAssociation(ModuleConfigBO configBO), in ModuleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
}