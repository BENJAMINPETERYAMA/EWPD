/*
 * RoleBusinessObjectBuilder.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.security.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.security.adapter.RoleAdapterManager;
import com.wellpoint.wpd.business.security.locatecriteria.RoleLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.ModuleImpl;
import com.wellpoint.wpd.common.framework.security.domain.RoleImpl;
import com.wellpoint.wpd.common.framework.security.domain.SubTaskImpl;
import com.wellpoint.wpd.common.framework.security.domain.TaskImpl;
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
public class RoleBusinessObjectBuilder {

    public List locate(RoleLocateCriteria roleLocateCriteria)
            throws SevereException {

        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();

        try {
			// Call the adapter locate() and return to the business service
			return roleAdapterManager.locate(roleLocateCriteria);
        }catch (SevereException se) {
        	List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate(RoleLocateCriteria roleLocateCriteria) in RoleBusinessObjectBuilder",
					errorParams, se);
        }catch(AdapterException ae){
    		List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate(RoleLocateCriteria roleLocateCriteria) in RoleBusinessObjectBuilder",
                        errorParams, ae);
         } catch (Exception ex){
         	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate(RoleLocateCriteria roleLocateCriteria) in RoleBusinessObjectBuilder",
                        errorParams, ex);        }
    }

    /**
     * Method to save/update a role
     * 
     * @param roleBO
     * @param user
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public boolean persist(RoleBO roleBO, User user, boolean insertFlag)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {
            roleBO.setCreatedUser(audit.getUser());
            roleBO.setCreatedTimestamp(audit.getTime());
            roleBO.setLastUpdatedTimestamp(audit.getTime());
            roleBO.setLastUpdatedUser(audit.getUser());

            if (insertFlag) {
                //Obatains the sequence from the sequenceAdapterManager
                roleBO.setRoleId(sequenceAdapterManager
                        .getNextSecurityRoleSequence());
                // Call the adapter createRole()
                adapter.createRole(roleBO, audit);
            } else {
                // Call the adapter updateRole()
                adapter.updateRole(roleBO, audit);
            }
        } catch (SevereException se) {
        	List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(RoleBO roleBO, User user, boolean insertFlag) in RoleBusinessObjectBuilder",
                        errorParams, se);
        } catch (AdapterException ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(RoleBO roleBO, User user, boolean insertFlag) in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(RoleBO roleBO, User user, boolean insertFlag) in RoleBusinessObjectBuilder",
                        errorParams, ex);        }

        return true;
    }
    
    /**
     * Method to save/update a role
     * 
     * @param roleBO
     * @param user
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public boolean persist(RoleSrdaBo roleSrdaBO, User user, boolean insertFlag)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try {
        	roleSrdaBO.setCreatedUser(audit.getUser());
        	roleSrdaBO.setCreatedTimestamp(audit.getTime());
        	roleSrdaBO.setLastUpdatedTimestamp(audit.getTime());
        	roleSrdaBO.setLastUpdatedUser(audit.getUser());

            if (insertFlag) {
                //Obatains the sequence from the sequenceAdapterManager
            	List maxRoleId = adapter
                        .getNextSecurityRoleSequence();
            	if(null != maxRoleId && maxRoleId.size()>0){
            		RoleSrdaBo roleSrdaBo = (RoleSrdaBo)maxRoleId.get(0);
            		if(null != roleSrdaBo){
            		 	roleSrdaBO.setRoleId((roleSrdaBo.getRoleId()+1));
            		}
            	}
                // Call the adapter createRole()
                adapter.createRole(roleSrdaBO, audit);
            } else {
                // Call the adapter updateRole()
                adapter.updateRole(roleSrdaBO, audit);
            }
        } catch (SevereException se) {
        	List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(RoleBO roleBO, User user, boolean insertFlag) in RoleBusinessObjectBuilder",
                        errorParams, se);
        } catch (AdapterException ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(RoleBO roleBO, User user, boolean insertFlag) in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(RoleBO roleBO, User user, boolean insertFlag) in RoleBusinessObjectBuilder",
                        errorParams, ex);        }

        return true;
    }

    /**
     * Builder method to associate a list of modules with the role
     * 
     * @param moduleIdList
     * @param roleConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean persist(List moduleIdList, RoleConfigBO roleConfigBO,
            User user) throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        RoleBO roleBO=new RoleBO();
        roleBO.setRoleId(roleConfigBO.getRoleId());
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"persist(List moduleIdList, RoleConfigBO configBO,User user");

            roleConfigBO.setCreatedTimestamp(audit.getTime());
            roleConfigBO.setCreatedUser(audit.getUser());
            roleConfigBO.setLastUpdatedTimestamp(audit.getTime());
            roleConfigBO.setLastUpdatedUser(audit.getUser());

            for (int i = 0; i < moduleIdList.size(); i++) {
                roleConfigBO.setModuleId(((Integer) (moduleIdList.get(i)))
                        .intValue());
                //Obtains the role_module_id from the sequenceAdapterManager
                roleConfigBO.setRoleModuleId(sequenceAdapterManager
                        .getNextRoleModuleAssociationSequence());
                // Call the adapter createRoleModuleAssociation()
                adapter.createRoleModuleAssociation(roleConfigBO, audit,
                        adapterServiceAccess);
            }
            //retrieving role details for updating last updated user id and time stamp of Role
            roleBO=	(RoleBO) adapter.retrieve(roleBO);
            roleBO.setLastUpdatedUser(audit.getUser());
            roleBO.setLastUpdatedTimestamp(audit.getTime());
            //updating last updated user id and timestamp of Role
            adapter.updateRole(roleBO, audit,adapterServiceAccess);
            
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"persist(List moduleIdList, RoleConfigBO configBO,User user");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List moduleIdList, RoleConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List moduleIdList, RoleConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (AdapterException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);	
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List moduleIdList, RoleConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List moduleIdList, RoleConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List moduleIdList, RoleConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List moduleIdList, RoleConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);        }
        return true;
    }

    /**
     * 
     * @param taskIdList
     * @param RoleTaskConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean persist(List taskIdList, RoleTaskConfigBO configBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        RoleBO roleBO=new RoleBO();
        //getting roleId for retrieving role details for timestamp updation
        int roleId=configBO.getRoleTaskId();
        //setting roleId to roleBO to retrieve role details
        roleBO.setRoleId(roleId);
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");

            configBO.setCreatedTimestamp(audit.getTime());
            configBO.setCreatedUser(audit.getUser());
            configBO.setLastUpdatedTimestamp(audit.getTime());
            configBO.setLastUpdatedUser(audit.getUser());

            for (int i = 0; i < taskIdList.size(); i++) {
                configBO.setTaskId(((Integer) (taskIdList.get(i))).intValue());
                //Obtains the role_task_id from the sequenceAdapterManager
                configBO.setRoleTaskId(sequenceAdapterManager
                        .getNextRoleTaskAssociationSequence());
                //Call the adapter createRoleTaskAssociation()
                adapter.createRoleTaskAssociation(configBO, audit,
                        adapterServiceAccess);
            }
            //retrieving role details for updating last updated user id and time stamp of Role
            roleBO=	(RoleBO) adapter.retrieve(roleBO);
            roleBO.setLastUpdatedUser(audit.getUser());
            roleBO.setLastUpdatedTimestamp(audit.getTime());
            //updating last updated user id and timestamp of Role
            adapter.updateRole(roleBO, audit,adapterServiceAccess);
            
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (AdapterException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
         } catch (Exception ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
         }
        return true;
    }

    /**
     * 
     * @param taskIdList
     * @param RoleTaskConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean persist(List taskIdList, RoleTaskSrdaConfigBO configBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        RoleSrdaBo roleSrdaBO=new RoleSrdaBo();
        //getting roleId for retrieving role details for timestamp updation
        int roleId=configBO.getRoleTaskId();
        //setting roleId to roleBO to retrieve role details
        roleSrdaBO.setRoleId(roleId);
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");

           // configBO.setCreatedTimestamp(audit.getTime());
            //configBO.setCreatedUser(audit.getUser());
            configBO.setLastUpdatedTimestamp(audit.getTime());
            configBO.setLastUpdatedUser(audit.getUser());

            for (int i = 0; i < taskIdList.size(); i++) {
                configBO.setTaskId(((Integer) (taskIdList.get(i))).intValue());
                //Obtains the role_task_id from the sequenceAdapterManager
                //configBO.setRoleTaskId(sequenceAdapterManager
                       // .getNextRoleTaskAssociationSequence());
                //Call the adapter createRoleTaskAssociation()
                adapter.createRoleTaskAssociation(configBO, audit,
                        adapterServiceAccess);
            }
            //retrieving role details for updating last updated user id and time stamp of Role
            roleSrdaBO=	(RoleSrdaBo) adapter.retrieve(roleSrdaBO);
            roleSrdaBO.setLastUpdatedUser(audit.getUser());
            roleSrdaBO.setLastUpdatedTimestamp(audit.getTime());
            //updating last updated user id and timestamp of Role
            adapter.updateRole(roleSrdaBO, audit,adapterServiceAccess);
            
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (AdapterException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
         } catch (Exception ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List taskIdList, RoleTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List taskIdList, RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
         }
        return true;
    }

    /**
     * Method to associate a list of subtask with the role
     * 
     * @param subTaskIdList
     * @param RoleSubTaskConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean persist(List subTaskIdList, RoleSubTaskConfigBO configBO,
            User user) throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        RoleBO roleBO=new RoleBO();
        //setting roleId to roleBO to retrieve role details
        roleBO.setRoleId(configBO.getRoleSubTaskId());
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId,this,"persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user");
        	
            configBO.setCreatedTimestamp(audit.getTime());
            configBO.setCreatedUser(audit.getUser());
            configBO.setLastUpdatedTimestamp(audit.getTime());
            configBO.setLastUpdatedUser(audit.getUser());

            for (int i = 0; i < subTaskIdList.size(); i++) {
                configBO.setSubTaskId(((Integer) (subTaskIdList.get(i)))
                        .intValue());
                //Call the adapter createRoleSubTaskAssociation()
                adapter.createRoleSubTaskAssociation(configBO, audit,
                        adapterServiceAccess);
            }
            //retrieving role details for updating last updated user id and time stamp of Role
            roleBO=	(RoleBO) adapter.retrieve(roleBO);
            roleBO.setLastUpdatedUser(audit.getUser());
            roleBO.setLastUpdatedTimestamp(audit.getTime());
            //updating last updated user id and timestamp of Role
            adapter.updateRole(roleBO, audit,adapterServiceAccess);
            
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user");
        } catch (SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (AdapterException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch (Exception ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in persist(List subTaskIdList, RoleSubTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        return true;
    }

    /**
     * Method to delete the role
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(RoleBO roleBO, User user) throws SevereException{
        Logger
                .logInfo("RoleBusinessObjectBuilder - Entering deleteRole(): Role");
        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();
        try {
            //Call the adapter deleteRole()
            roleAdapterManager.deleteRole(roleBO, user);
        } catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(RoleBO roleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(RoleBO roleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(RoleBO roleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        Logger
                .logInfo("RoleBusinessObjectBuilder - Returning deleteRole(): Role");
        return true;
    }
    /**
     * Method to delete the role
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(RoleSrdaBo roleBO, User user) throws SevereException{
        Logger
                .logInfo("RoleBusinessObjectBuilder - Entering deleteRole(): Role");
        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();
        try {
            //Call the adapter deleteRole()
            roleAdapterManager.deleteRole(roleBO, user);
        } catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(RoleBO roleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(RoleBO roleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(RoleBO roleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        Logger
                .logInfo("RoleBusinessObjectBuilder - Returning deleteRole(): Role");
        return true;
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
                .logInfo("RoleBusinessObjectBuilder - Entering locateTask(): Role");
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			// Call the adapter locateTask() and return to the business service
			return adapter.locateTask(taskBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in RoleBusinessObjectBuilder",
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
    public List locateTask(TaskSrdaBO taskSrdaBO, User user) throws SevereException {
        Logger
                .logInfo("RoleBusinessObjectBuilder - Entering locateTask(): Role");
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			// Call the adapter locateTask() and return to the business service
			return adapter.locateTask(taskSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateTask(TaskBO taskBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    /**
     * Method to check whether there is any role already present with same
     * identity
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public boolean isRoleDuplicate(RoleBO roleBO) throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();
        try {
			// Call the adapter isRoleDuplicate()
			return roleAdapterManager.isRoleDuplicate(roleBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleDuplicate(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleDuplicate(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleDuplicate(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method to check whether there is any role already present with same
     * identity
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public boolean isRoleDuplicate(RoleSrdaBo roleSrdaBO) throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();
        try {
			// Call the adapter isRoleDuplicate()
			return roleAdapterManager.isRoleDuplicate(roleSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleDuplicate(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleDuplicate(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleDuplicate(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    /**
     * method to retrieve a role
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public RoleBO retrieve(RoleBO roleBO) throws SevereException{
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			// Call the adapter retrieve() and return to the business service
			return adapter.retrieve(roleBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }

    }

    /**
     * method to retrieve a role
     * 
     * @param roleBO
     * @return
     * @throws SevereException
     */
    public RoleSrdaBo retrieve(RoleSrdaBo roleSrdaBO) throws SevereException{
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			// Call the adapter retrieve() and return to the business service
			return adapter.retrieve(roleSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(RoleBO roleBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }

    }
    /**
     * Method for deleting the associated module
     * 
     * @param roleConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,
            User user) throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
        .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
        .getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		RoleBO roleBO=new RoleBO();
		roleBO.setRoleId(roleConfigBO.getRoleId());
		List moduleList=roleConfigBO.getModuleIdLIst();
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
    		AdapterUtil.logBeginTranstn(transactionId,this,"deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user)");
    		 for(int i=0;i<moduleList.size();i++){
    		 	roleConfigBO.setModuleId(Integer.parseInt((String)moduleList.get(i)));
    		 	 //Call the adapter deleteRoleModuleAssociation()
                adapter.deleteRoleModuleAssociation(roleConfigBO, user,adapterServiceAccess);
    		 }
            //retrieving role details for updating last updated user id and time stamp of Role
            roleBO=	(RoleBO) adapter.retrieve(roleBO);
            roleBO.setLastUpdatedUser(audit.getUser());
            roleBO.setLastUpdatedTimestamp(audit.getTime());
            //updating last updated user id and timestamp of Role
            adapter.updateRole(roleBO, audit,adapterServiceAccess);
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId,this,"deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user)");
        }catch(SevereException ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteRoleModuleAssociation(RoleConfigBO roleConfigBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        return true;
    }

    /**
     * Method to retrieve a module
     * 
     * @param roleImpl
     * @return
     * @throws SevereException
     */
    public RoleImpl retrieveModule(RoleImpl roleImpl) throws SevereException {

        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(roleImpl.getName());
        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();
        try {
			roleAdapterManager.isRoleDuplicate(roleBO);
        
        roleImpl.setId(roleBO.getRoleId());
        List moduleList, taskList, moduleIdList = new ArrayList(0), taskIdList = new ArrayList(0), subtaskList;
        //Call the adapter getModuleBO()
        moduleList = roleAdapterManager.getModuleBO(roleImpl.getId());
        moduleIdList=new ArrayList(moduleList==null?0:moduleList.size());
        for (int i = 0; i < moduleList.size(); i++) {
            ModuleBO moduleBO = (ModuleBO) moduleList.get(i);
            moduleIdList.add(new Integer(moduleBO.getModuleId()));
            ModuleImpl moduleImpl = new ModuleImpl();
            moduleImpl.setId(moduleBO.getModuleId());
            moduleImpl.setName(moduleBO.getModuleName());
            getTasks(roleImpl.getId(), moduleImpl.getId(), moduleImpl);
            roleImpl.addModule(moduleImpl);

        }
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveModule(RoleImpl roleImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveModule(RoleImpl roleImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveModule(RoleImpl roleImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        return roleImpl;
    }

    /**
     * Method for creating the list of taskBO
     * 
     * @param roleId
     * @param moduleId
     * @param moduleImpl
     * @throws SevereException
     */
    private void getTasks(long roleId, long moduleId, ModuleImpl moduleImpl)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();
        List taskList = null, taskIdList = new ArrayList(0);
        try {
			//Call the adapter getTaskBO()
			taskList = roleAdapterManager.getTaskBO(moduleId, roleId);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in getTasks(long roleId, long moduleId, ModuleImpl moduleImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in getTasks(long roleId, long moduleId, ModuleImpl moduleImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in getTasks(long roleId, long moduleId, ModuleImpl moduleImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        for (int i = 0; i < taskList.size(); i++) {
            TaskBO taskBO = (TaskBO) taskList.get(i);
            TaskImpl taskImpl = new TaskImpl();
            taskImpl.setId(taskBO.getTaskId());
            taskImpl.setName(taskBO.getTaskName());
            moduleImpl.addTask(taskImpl);
        }

    }

    /**
     * Method for creating the list of subtaskBO
     * 
     * @param roleId
     * @param moduleId
     * @param taskId
     * @param taskImpl
     * @throws SevereException
     */
    private void getSubTasks(long roleId, long moduleId, long taskId,
            TaskImpl taskImpl) throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager roleAdapterManager = new RoleAdapterManager();
        List subTaskList = null;
        Map subtaskMap = new HashMap();
        try {
			//Call the adapter getSubTaskBO()
			subTaskList = roleAdapterManager.getSubTaskBO(moduleId, taskId, roleId);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in getSubTasks(long roleId, long moduleId, long taskId,TaskImpl taskImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in getSubTasks(long roleId, long moduleId, long taskId,TaskImpl taskImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in getSubTasks(long roleId, long moduleId, long taskId,TaskImpl taskImpl), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        for (int i = 0; i < subTaskList.size(); i++) {
            SubTaskBO subTaskBO = (SubTaskBO) subTaskList.get(i);
            SubTaskImpl subTaskImpl = new SubTaskImpl();
            subTaskImpl.setId(subTaskBO.getSubTaskId());
            subTaskImpl.setName(subTaskBO.getSubTaskName());
            taskImpl.addSubtask(subTaskImpl);
        }

    }

    /**
     * Method for locating the role associations
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public List locateRoleAssociations(RoleLocateCriteria locateCriteria)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        try {
			// Call the adapter locateRoleAssociations() and return to the business
			// service
			return adapterManager.locateRoleAssociations(locateCriteria);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleAssociations(RoleLocateCriteria locateCriteria), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleAssociations(RoleLocateCriteria locateCriteria), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateRoleAssociations(RoleLocateCriteria locateCriteria), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for creating the task association with role
     * 
     * @param configBO
     * @throws SevereException
     */
    public boolean saveTaskToRole(RoleTaskConfigBO configBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
        .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        RoleBO roleBO=new RoleBO();
        //setting roleId to roleBO for retrieving role details
        roleBO.setRoleId(configBO.getRolModTaskId());
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
			AdapterUtil.logBeginTranstn(transactionId,this,"saveTaskToRole(RoleTaskConfigBO configBO, User user)");
	        configBO.setLastUpdatedTimestamp(audit.getTime());
	        configBO.setLastUpdatedUser(audit.getUser());
	        configBO.setCreatedTimestamp(audit.getTime());
	        configBO.setCreatedUser(audit.getUser());
	        //Call the adapter saveTaskToRole()
	        adapterManager.saveTaskToRole(configBO, user.getUserId(),adapterServiceAccess);
	        //retrieving role details for updating last updated user id and time stamp of Role
	        roleBO=	(RoleBO) adapterManager.retrieve(roleBO);
	        roleBO.setLastUpdatedUser(audit.getUser());
	        roleBO.setLastUpdatedTimestamp(audit.getTime());
	        //updating last updated user id and timestamp of Role
	        adapterManager.updateRole(roleBO, audit,adapterServiceAccess);
	        AdapterUtil.endTransaction(adapterServiceAccess);
	        AdapterUtil.logTheEndTransaction(transactionId,this,"saveTaskToRole(RoleTaskConfigBO configBO, User user)");
       	}catch(SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"saveTaskToRole(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveTaskToRole(RoleTaskConfigBO configBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"saveTaskToRole(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveTaskToRole(RoleTaskConfigBO configBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"saveTaskToRole(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveTaskToRole(RoleTaskConfigBO configBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        return true;

    }

    /**
     * Method for creating the association of subtask with role
     * 
     * @param configBO
     * @throws SevereException
     */
    public boolean saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
														.getAdapterService();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        long transactionId = AdapterUtil.getTransactionId();
        RoleBO roleBO=new RoleBO();
        //setting roleId to roleBO for retrieving role details
        roleBO.setRoleId(configBO.getRolModTaskId());
        try {
        	AdapterUtil.beginTransaction(adapterServiceAccess);
			AdapterUtil.logBeginTranstn(transactionId,this,"saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user)");
	        configBO.setLastUpdatedTimestamp(audit.getTime());
	        configBO.setLastUpdatedUser(audit.getUser());
	        configBO.setCreatedTimestamp(audit.getTime());
	        configBO.setCreatedUser(audit.getUser());
	        //Call the adapter saveSubTaskToRole()
	        adapterManager.saveSubTaskToRole(configBO, user.getUserId(),adapterServiceAccess);
	        //retrieving role details for updating last updated user id and time stamp of Role
	        roleBO=	(RoleBO) adapterManager.retrieve(roleBO);
	        roleBO.setLastUpdatedUser(audit.getUser());
	        roleBO.setLastUpdatedTimestamp(audit.getTime());
	        //updating last updated user id and timestamp of Role
	        adapterManager.updateRole(roleBO, audit,adapterServiceAccess);
	        AdapterUtil.endTransaction(adapterServiceAccess);
	        AdapterUtil.logTheEndTransaction(transactionId,this,"saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user)");
        }catch(SevereException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex) {
        	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in saveSubTaskToRole(RoleSubTaskConfigBO configBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
        return true;
    }

    /**
     * Method for deleting the association of task
     * 
     * @param configBO
     * @throws SevereException
     */
    public boolean deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
        												.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
		.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		RoleBO roleBO=new RoleBO();
		//setting roleModTaskId to roleId for retrieving role details
		roleBO.setRoleId(configBO.getRoleTaskId());
		
		List taskList=configBO.getTaskIdList();
		try {
			AdapterUtil.beginTransaction(adapterServiceAccess);
			AdapterUtil.logBeginTranstn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
			for(int i=0;i<taskList.size();i++){
				configBO.setTaskId(Integer.parseInt((String)taskList.get(i)));
		        //Call the adapter deleteAssociationOfTask()
		        adapterManager.deleteAssociationOfTask(configBO, user
		                .getUserId(),adapterServiceAccess);
			}
	        //retrieving role details for updating last updated user id and time stamp of Role
	        roleBO=	(RoleBO) adapterManager.retrieve(roleBO);
	        roleBO.setLastUpdatedUser(audit.getUser());
	        roleBO.setLastUpdatedTimestamp(audit.getTime());
	        //updating last updated user id and timestamp of Role
	        adapterManager.updateRole(roleBO, audit,adapterServiceAccess);
	        AdapterUtil.endTransaction(adapterServiceAccess);
	        AdapterUtil.logTheEndTransaction(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
		}catch(SevereException ex) {
		 	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfTask(RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex) {
         	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfTask(RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex) {
         	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfTask(RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } 
        return true;
    }

    /**
     * Method for deleting the association of task
     * 
     * @param configBO
     * @throws SevereException
     */
    public boolean deleteAssociationOfTask(RoleTaskSrdaConfigBO configBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
        												.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
		.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		RoleSrdaBo roleBO=new RoleSrdaBo();
		//setting roleModTaskId to roleId for retrieving role details
		roleBO.setRoleId(configBO.getRoleTaskId());
		
		List taskList=configBO.getTaskIdList();
		try {
			AdapterUtil.beginTransaction(adapterServiceAccess);
			AdapterUtil.logBeginTranstn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
			for(int i=0;i<taskList.size();i++){
				configBO.setTaskId(Integer.parseInt((String)taskList.get(i)));
		        //Call the adapter deleteAssociationOfTask()
		        adapterManager.deleteAssociationOfTask(configBO, user
		                .getUserId(),adapterServiceAccess);
			}
	        //retrieving role details for updating last updated user id and time stamp of Role
	        roleBO=	(RoleSrdaBo) adapterManager.retrieve(roleBO);
	        roleBO.setLastUpdatedUser(audit.getUser());
	        roleBO.setLastUpdatedTimestamp(audit.getTime());
	        //updating last updated user id and timestamp of Role
	        adapterManager.updateRole(roleBO, audit,adapterServiceAccess);
	        AdapterUtil.endTransaction(adapterServiceAccess);
	        AdapterUtil.logTheEndTransaction(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
		}catch(SevereException ex) {
		 	AdapterUtil.abortTransaction(adapterServiceAccess);
	    	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfTask(RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex) {
         	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfTask(RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex) {
         	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfTask(RoleTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfTask(RoleTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } 
        return true;
    }
    /**
     * Method for deleting the association of subtask
     * 
     * @param configBO
     * @throws SevereException
     */
    public boolean deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO,
            User user) throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapterManager = new RoleAdapterManager();
		 AdapterServicesAccess adapterServiceAccess = AdapterUtil
	        .getAdapterService();
		 long transactionId = AdapterUtil.getTransactionId();
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
		.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		RoleBO roleBO=new RoleBO();
		//setting roleModTaskId to roleId for retrieving role details
		roleBO.setRoleId(configBO.getRoleSubTaskId());
		List taskList=configBO.getTaskIdList();
		try {
			AdapterUtil.beginTransaction(adapterServiceAccess);
			AdapterUtil.logBeginTranstn(transactionId,this,"deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO, User user)");
			for(int i=0;i<taskList.size();i++){
				configBO.setSubTaskId(Integer.parseInt((String)taskList.get(i)));
				//Call the adapter deleteAssociationOfSubTask()
				adapterManager.deleteAssociationOfSubTask(configBO, user
		                .getUserId(),adapterServiceAccess);
			}
	       //retrieving role details for updating last updated user id and time stamp of Role
	       roleBO=	(RoleBO) adapterManager.retrieve(roleBO);
	       roleBO.setLastUpdatedUser(audit.getUser());
	       roleBO.setLastUpdatedTimestamp(audit.getTime());
	       //updating last updated user id and timestamp of Role
	       adapterManager.updateRole(roleBO, audit,adapterServiceAccess);
	       AdapterUtil.endTransaction(adapterServiceAccess);
	       AdapterUtil.logTheEndTransaction(transactionId,this,"deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO, User user)");
		}catch(SevereException ex){
			AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId,this,"deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO, User user)");
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in deleteAssociationOfSubTask(RoleSubTaskConfigBO configBO,User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
	    return true;
    }

    /**
     * ] Method for retrieving the non-associated modules of the selected role
     * 
     * @param roleConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public List retrieveRoleModuleAssociation(ModuleBO moduleBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			//Call the adapter retrieveRoleModuleAssociation() and return to the
			// execute service
			return adapter.retrieveRoleModuleAssociation(moduleBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveRoleModuleAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveRoleModuleAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveRoleModuleAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * ] Method for retrieving the non-associated modules of the selected role
     * 
     * @param roleConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public List retrieveRoleModuleAssociation(ModuleSrdaBO moduleSrdaBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			//Call the adapter retrieveRoleModuleAssociation() and return to the
			// execute service
			return adapter.retrieveRoleModuleAssociation(moduleSrdaBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveRoleModuleAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveRoleModuleAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieveRoleModuleAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    /**
     * Method for retrieving the non-associated modules of the selected role
     * @param roleConfigBO
     * @param user
     * @return
     * @throws SevereException
     */
    public List locateModuleNonAssociation(ModuleBO moduleBO, User user)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			//Call adapter  locateModuleNonAssociation() and return list to execute service
			return adapter.locateModuleNonAssociation(moduleBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleNonAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleNonAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locateModuleNonAssociation(ModuleBO moduleBO, User user), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for checking whether the selected role is
     * associated with any module
     * @param roleConfigBO
     * @return
     * @throws SevereException
     */
    public List isRoleAssociated(RoleConfigBO roleConfigBO)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			//Call the adapter isRoleAssociated() and return list to execute service
			return adapter.isRoleAssociated(roleConfigBO);
        }catch (SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleAssociated(RoleConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleAssociated(RoleConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleAssociated(RoleConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * Method for checking whether the selected role is
     * associated with any module
     * @param roleConfigBO
     * @return
     * @throws SevereException
     */
    public List isRoleAssociated(RoleConfigSrdaBO roleConfigBO)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
			//Call the adapter isRoleAssociated() and return list to execute service
			return adapter.isRoleAssociated(roleConfigBO);
        }catch (SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleAssociated(RoleConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleAssociated(RoleConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleAssociated(RoleConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }
    }
    /**
     * Method to check whether the module is associated
     * @param RoleTaskConfigBO
     * @return
     * @throws SevereException
     */
    public List isModuleAssociated(RoleTaskConfigBO configBO)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
        	List associatedList=new ArrayList();
        	List moduleList=configBO.getModuleIdList();
        	for(int i=0;i<moduleList.size();i++){
	    		configBO.setModuleId(Integer.parseInt((String)moduleList.get(i)));
//	    		Call the adapter isModuleAssociated() and return list to execute service
	    		associatedList=adapter.isModuleAssociated(configBO);
	    	 	if(associatedList.size()>0){
	    	 		break;
	    	 	}
	    	 }
			return associatedList;
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleAssociated(RoleTaskConfigBO configBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleAssociated(RoleTaskConfigBO configBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isModuleAssociated(RoleTaskConfigBO configBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
         }
    }

    /**
     * Method to check whether the task ia associated
     * @param roleConfigBO
     * @return
     * @throws SevereException
     */
    public List isRoleModuleTaskAssociated(RoleTaskConfigBO roleConfigBO)
            throws SevereException {
        // Create an instance of the Adapter Manager
        RoleAdapterManager adapter = new RoleAdapterManager();
        try {
        	List associatedList=new ArrayList();
        	List taskList=roleConfigBO.getTaskIdList();
        	for(int i=0;i<taskList.size();i++){
        		roleConfigBO.setTaskId(Integer.parseInt((String)taskList.get(i)));
				//Call the adapter isRoleModuleTaskAssociated() and return list to execute service
	        	associatedList= adapter.isRoleModuleTaskAssociated(roleConfigBO);
	        	//checking if the module is associated with tasks
	        	if(associatedList.size()>0)
	        		break;
        	}
        	return associatedList;
        	//*return adapter.isRoleModuleTaskAssociated(roleConfigBO);
        }catch(SevereException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleModuleTaskAssociated(RoleTaskConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleModuleTaskAssociated(RoleTaskConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isRoleModuleTaskAssociated(RoleTaskConfigBO roleConfigBO), in RoleBusinessObjectBuilder",
                        errorParams, ex);
         }
    }

}