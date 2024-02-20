/*
 * ItemBusinessObjectBuilder.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.item.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.item.adapter.ItemAdapterManager;
import com.wellpoint.wpd.business.item.locatecriteria.ItemLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.item.bo.ItemFrequencyBO;
import com.wellpoint.wpd.common.item.bo.ItemSoftDeleteBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaHCSBO;
/**
 * Base class for item builder
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ItemBusinessObjectBuilder {

    /**
     * Persists or update the item based on create flag
     * @param businessObject
     * @param user
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(ItemBO businessObject, User user, boolean insertFlag)
            throws SevereException {

        Logger.logInfo("ItemBusinessObjectBuilder - Entering persist(): Item");
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try{
	
	        businessObject.setCreatedUser(audit.getUser());
	        businessObject.setCreatedTimestamp(audit.getTime());
	        businessObject.setLastUpdatedTimestamp(audit.getTime());
	        businessObject.setLastUpdatedUser(audit.getUser());
	
	        if (insertFlag) {
	                itemAdapterManager.createItem(businessObject, audit);	                
	            }
	         else {
	            itemAdapterManager.updateItem(businessObject, audit);
	        }
	
        }catch(SevereException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(AdapterException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(Exception ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }
        Logger.logInfo("ItemBusinessObjectBuilder - Returning persist(): Item");

        return true;
    }    
    
    /**
     * Persists or update the item based on create flag
     * @param businessObject
     * @param user
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(ItemSrdaBO businessObject, User user, boolean insertFlag)
            throws SevereException {

        Logger.logInfo("ItemBusinessObjectBuilder - Entering persist(): Item");
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try{
	
	        businessObject.setCreatedUser(audit.getUser());
	        businessObject.setCreatedTimestamp(audit.getTime());
	        businessObject.setLastUpdatedTimestamp(audit.getTime());
	        businessObject.setLastUpdatedUser(audit.getUser());
	
	        if (insertFlag) {
	                itemAdapterManager.createItem(businessObject, audit);	                
	            }
	         else {
	            itemAdapterManager.updateItem(businessObject, audit);
	        }
	
        }catch(SevereException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSrdaBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(AdapterException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSrdaBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(Exception ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSrdaBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }
        Logger.logInfo("ItemBusinessObjectBuilder - Returning persist(): Item");

        return true;
    }    
    
    
    /**
     * Persists or update the item based on create flag
     * @param businessObject
     * @param user
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(ItemSrdaHCSBO businessObject, User user, boolean insertFlag)
            throws SevereException {

        Logger.logInfo("ItemBusinessObjectBuilder - Entering persist(): Item");
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();

        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);

        try{
	
	        businessObject.setCreatedUser(audit.getUser());
	        businessObject.setCreatedTimestamp(audit.getTime());
	        businessObject.setLastUpdatedTimestamp(audit.getTime());
	        businessObject.setLastUpdatedUser(audit.getUser());
	
	        if (insertFlag) {
	                itemAdapterManager.createItem(businessObject, audit);	                
	            }
	         else {
	            itemAdapterManager.updateItem(businessObject, audit);
	        }
	
        }catch(SevereException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSrdaBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(AdapterException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSrdaBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(Exception ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSrdaBO businessObject, User user, boolean insertFlag) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }
        Logger.logInfo("ItemBusinessObjectBuilder - Returning persist(): Item");

        return true;
    }    
    /**
     * Method to update the delete status for Item Soft Delete
     * @param itemStatusBO
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(ItemSoftDeleteBO itemStatusBO, User user)
            throws SevereException {
        Logger
                .logInfo("ItemBusinessObjectBuilder - Entering persist(): DeleteStatusEdit");

        // Create an instance of the adapter manager
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();
        
        // Get the audit instance
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        
        // Begin a transaction
        try{
	
	        // Set the user and time stamp details to the BO
	        itemStatusBO.setCreatedUser(audit.getUser());
	        itemStatusBO.setCreatedTimestamp(audit.getTime());
	        itemStatusBO.setLastUpdatedTimestamp(audit.getTime());
	        itemStatusBO.setLastUpdatedUser(audit.getUser());
	
	        // Call the adapter update method
	        itemAdapterManager.updateStatus(itemStatusBO, user);
	        
        }catch(SevereException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSoftDeleteBO itemStatusBO, User user) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(AdapterException ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSoftDeleteBO itemStatusBO, User user) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }catch(Exception ex){
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist(ItemSoftDeleteBO itemStatusBO, User user) in ItemBusinessObjectBuilder",
					errorParams, ex);
        }
        Logger.logInfo("ItemBusinessObjectBuilder - Returning persist(): DeleteStatusEdit");
        return true;
    }

    /**
     * Retrieves the Item object  
     * @param itemBO
     * @return ItemBO
     * @throws SevereException
     */

    public Object retrieve(Object object) throws SevereException {
        ItemAdapterManager adapterManager = new ItemAdapterManager();
        try {
			return adapterManager.retrieve(object);
        }catch(SevereException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }
    }

    /**
     * To locate the Item based upon the LocateCriteria
     * @return LocateResults
     * @throws SevereException
     */
    public LocateResults locate(ItemLocateCriteria itemLocateCriteria, User user)
            throws SevereException {
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();
        LocateResults locateResults = new LocateResults();
        List resultList;
        
		try {
			resultList = itemAdapterManager.locate(itemLocateCriteria);
			 if("SRDA".equalsIgnoreCase(itemLocateCriteria.getSrdaFlag())){
				 itemDisplayListSrda(resultList);
				 
			 }else{
				 itemDisplayList(resultList);
			 }
		}catch(SevereException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate (ItemLocateCriteria itemLocateCriteria, User user), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate (ItemLocateCriteria itemLocateCriteria, User user), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in locate (ItemLocateCriteria itemLocateCriteria, User user), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }
		locateResults.setLocateResults(resultList);
        locateResults.setTotalResultsCount(resultList.size());
        return locateResults;
    }

    private void itemDisplayList(List resultList) {
    	ItemBO item;
    	if(null != resultList){
		    for (Iterator iter = resultList.iterator(); iter.hasNext(); )
		    {
		    	item  = (ItemBO) iter.next();
		      String description = "";
		      if(null != item.getDescription()){
		       if (item.getDescription().length() > 25) {
		          description = item.getDescription();
		          description = description.substring(0, 24)
		                  .concat("...");
		          item.setDescription(description);
		       }
		      }
		    
		    }
		    }
	}
	private void itemDisplayListSrda(List resultList) {
		ItemSrdaBO item;
    	if(null != resultList){
		    for (Iterator iter = resultList.iterator(); iter.hasNext(); )
		    {
		    	item  = (ItemSrdaBO) iter.next();
		      String description = "";
		      if(null != item.getDescription()){
		       if (item.getDescription().length() > 25) {
		          description = item.getDescription();
		          description = description.substring(0, 24)
		                  .concat("...");
		          item.setDescription(description);
		       }
		      }
		    
		    }
		    }
	}
	/**
     * For validating if there is any duplicate entries 
     * @param itemBO
     * @return boolean
     * @throws SevereException
     */

    public boolean isItemDuplicate(ItemBO itemBO) throws SevereException {
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();
        List resultList;
		try {
			resultList = itemAdapterManager.searchForDuplicateItem(itemBO);
		}catch(SevereException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }
		if (resultList != null && resultList.size() > 0)
            return true;
        return false;
    }

    
    
    public boolean isItemDuplicate(ItemSrdaHCSBO itemSrdaBO) throws SevereException {
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();
        List resultList;
		try {
			resultList = itemAdapterManager.searchForDuplicateHCSItem(itemSrdaBO);
		}catch(SevereException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemSrdaBO itemSrdaBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemSrdaBO itemSrdaBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }
		if (resultList != null && resultList.size() > 0)
            return true;
        return false;
    }
    
    public boolean isItemDuplicate(ItemSrdaBO itemSrdaBO) throws SevereException {
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();
        List resultList;
		try {
			resultList = itemAdapterManager.searchForDuplicateItem(itemSrdaBO);
		}catch(SevereException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemSrdaBO itemSrdaBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemSrdaBO itemSrdaBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in isItemDuplicate (ItemBO itemBO), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }
		if (resultList != null && resultList.size() > 0)
            return true;
        return false;
    }
    
    
    /**
     * retrieves the catalogBO as list
     * @return List
     * @throws SevereException
     */

    public List retrieve(String srdaFlag) throws SevereException {
        ItemAdapterManager adapterManager = new ItemAdapterManager();
        List resultList;
		try {
			resultList = adapterManager.retrieveCatalog(srdaFlag);
		}catch(SevereException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }catch(AdapterException ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        } catch (Exception ex){
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in retrieve(), in ItemBusinessObjectBuilder",
                        errorParams, ex);
        }
		return resultList;
    }
    /**
     * Insert and Update in the IT table
     * @param itemFrequencyBO
     * @param user
     * @param insertFlag
     * @return
     * @throws AdapterException
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persistFrequencyInfo(ItemFrequencyBO itemFrequencyBO,User user, boolean insertFlag) throws SevereException  {
    	  ItemAdapterManager adapterManager = new ItemAdapterManager();
    	  AuditFactory auditFactory = (AuditFactory) ObjectFactory
          .getFactory(ObjectFactory.AUDIT);
          Audit audit = auditFactory.getAudit(user);
          try {	          
				adapterManager.createITtableEntry(itemFrequencyBO,audit,insertFlag);
          }catch (AdapterException ex) {
				List errorParams = new ArrayList();
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in persistFrequencyInfo(ItemFrequencyBO itemFrequencyBO,User user, boolean insertFlag) in ItemBusinessObjectBuilder",
						errorParams, ex);
	      }
          
    	return true;
    }
    /**
     * Retrieves the Item object  
     * @param itemBO
     * @return ItemBO
     * @throws SevereException
     */
    public ItemFrequencyBO retrieveFrequencyInfo(ItemFrequencyBO itemFrequencyBO) throws SevereException {
        ItemAdapterManager adapterManager = new ItemAdapterManager();
        try {
			adapterManager.retrieveFrequencyInfo(itemFrequencyBO);    
      }catch(AdapterException ex){
				List errorParams = new ArrayList();
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
				            "Exception occured in retrieveFrequencyInfo(ItemFrequencyBO itemFrequencyBO), in ItemBusinessObjectBuilder",
				            errorParams, ex);
      }
		return itemFrequencyBO;
    }
    /**
     * This method checks whether catalog have a duplicate description for an item
     * @param itemBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isDescriptionDuplicate(ItemBO itemBO) throws SevereException{
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();
        List resultList;
    	resultList = itemAdapterManager.duplicateDescriptionItem(itemBO);
    	if (resultList != null && resultList.size() > 0)
            return true;
        return false;
    }   
    
    public boolean isDescriptionDuplicate(ItemSrdaBO itemSrdaBO) throws SevereException{
        ItemAdapterManager itemAdapterManager = new ItemAdapterManager();
        List resultList;
    	resultList = itemAdapterManager.duplicateDescriptionItem(itemSrdaBO);
    	if (resultList != null && resultList.size() > 0)
            return true;
        return false;
    }  
}