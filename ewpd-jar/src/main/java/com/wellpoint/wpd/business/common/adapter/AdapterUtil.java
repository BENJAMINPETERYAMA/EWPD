/*
 * AdapterUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.adapter.exception.NoBusinessObjectFoundException;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdapterUtil {

    
    public static void beginTransaction(AdapterServicesAccess adapterServicesAccess) throws SevereException{
        try {
            adapterServicesAccess.beginTransaction();
        } catch (AdapterException adapterException) {
        	Logger.logError("Adapter Exception occured while beginTrasaction");
        	Logger.logError(adapterException);
            throw new SevereException("Adapter Exception occured while beginTrasaction", null, adapterException);
        } catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while beginTrasaction");
        	Logger.logError(ex);
        	throw ex;
        }
    }
    
    public static void abortTransaction(AdapterServicesAccess adapterServicesAccess) throws SevereException{
        try {
            adapterServicesAccess.abortTransaction();
        } catch (AdapterException adapterException) {
        	Logger.logError("Adapter Exception occured while abortTransaction");
        	Logger.logError(adapterException);
            throw new SevereException("Adapter Exception occured while abortTrasaction", null, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while abortTransaction");
        	Logger.logError(ex);
        	throw ex;
        }
    }
    
    public static void endTransaction(AdapterServicesAccess adapterServicesAccess) throws SevereException{
        try {
            adapterServicesAccess.endTransaction();
        } catch (AdapterException adapterException) {
        	Logger.logError("Adapter Exception occured while endTransaction");
        	Logger.logError(adapterException);
            throw new SevereException("Adapter Exception occured while endTrasaction", null, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while endTransaction");
        	Logger.logError(ex);
        	throw ex;
        }
    }
    
    
    /**
     * This method perform retrieve operation by calling adapter. 
     * This will return business object if the operation is successful and 
     * will return null if the object is not found in DB
     * @param object	Business Object to be retrieved.
     * @return			Retrieved Business object.
     * @throws ServiceException
     */
    public static Object performRetrieve(Object object) throws SevereException {
        try {
            getAdapterService().retrieveObject(object, object.getClass().getName());
        }catch (NoBusinessObjectFoundException exception) {
            return null;
        }catch (AdapterException exception) {
            logAndThrowException(object,exception);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
        return object;
    }
    
    /**
     * This method perform retrieve operation by calling adapter. 
     * This will return business object if the operation is successful and 
     * will return null if the object is not found in DB
     * @param object					Business Object to be retrieved.
     * @param adapterServicesAccess		AdapterServiceAccess which will be used for adapter operation.
     * @return							Retrieved Business object.
     * @throws ServiceException
     */
    public static Object performRetrieve(Object object, AdapterServicesAccess adapterServicesAccess) throws SevereException {
        try {
            adapterServicesAccess.retrieveObject(object, object.getClass().getName());
        }catch (NoBusinessObjectFoundException exception) {
            return null;
        }catch (AdapterException exception) {
            Logger.logInfo("Adapter Exception occured.. Aborting transaction");
            //abortTransaction(adapterServicesAccess);
            logAndThrowException(object,exception);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
        return object;
    }

    /**
     * This method will perform persist operation by calling adapter.
     * @param obj		Business object to be persisted.
     * @param userId	UserId of currently operating user.
     * @throws ServiceException
     */
    public static void performInsert(Object obj, String userId) throws SevereException{
        BusinessTransactionContext transactionContext = getTransactionContext(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_CREATE, userId);
        try {
            getAdapterService().persistObject(obj, obj.getClass().getName(),transactionContext);
        } catch (AdapterException adapterException) {
            logAndThrowException(obj, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
    }
    
    /**
     * This method will perform persist operation by calling adapter.
     * @param obj		Business object to be persisted.
     * @param userId	UserId of currently operating user.
     * @param adapterServicesAccess	AdapterServiceAccess which will be used for adapter operation.
     * @throws ServiceException
     */
    public static void performInsert(Object obj, String userId, AdapterServicesAccess adapterServicesAccess) throws SevereException{
        BusinessTransactionContext transactionContext = getTransactionContext(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_CREATE, userId);
        try {
            adapterServicesAccess.persistObject(obj, obj.getClass().getName(),transactionContext);
        } catch (AdapterException adapterException) {
            Logger.logInfo("Adapter Exception occured.. Aborting transaction");
            //abortTransaction(adapterServicesAccess);
            logAndThrowException(obj, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
    }

    /**
     * This method will perform update operation by calling adapter.
     * @param obj		Business object to be persisted.
     * @param userId	UserId of currently operating user.
     * @throws ServiceException
     */
    public static void performUpdate(Object obj, String userId) throws SevereException{
        BusinessTransactionContext transactionContext = getTransactionContext(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_EDIT, userId);
        try {
            getAdapterService().persistObject(obj, obj.getClass().getName(),transactionContext);
        } catch (AdapterException adapterException) {
            logAndThrowException(obj, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
    }

    /**
     * This method will perform update operation by calling adapter.
     * @param obj		Business object to be persisted.
     * @param userId	UserId of currently operating user.
     * @param adapterServicesAccess	AdapterServiceAccess which will be used for adapter operation.
     * @throws ServiceException
     */
    public static void performUpdate(Object obj, String userId, AdapterServicesAccess adapterServicesAccess) throws SevereException{
        BusinessTransactionContext transactionContext = getTransactionContext(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_EDIT, userId);
        try {
            adapterServicesAccess.persistObject(obj, obj.getClass().getName(),transactionContext);
        } catch (AdapterException adapterException) {
            Logger.logInfo("Adapter Exception occured.. Aborting transaction");
            //abortTransaction(adapterServicesAccess);
            logAndThrowException(obj, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
    }
    
    /**
     * This method will perfomr remove operation of business object
     * by invoking adapter method.
     * @param obj		Business object to be deleted from db.
     * @param userId	UserId of currently operating user.
     * @throws ServiceException
     */
    public static void performRemove(Object obj, String userId) throws SevereException{
        BusinessTransactionContext transactionContext = getTransactionContext(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_DELETE, userId);
        try {
            getAdapterService().removeObject(obj, obj.getClass().getName(),transactionContext);
        } catch (AdapterException adapterException) {
            logAndThrowException(obj, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
    }
    
    /**
     * This method will perfomr remove operation of business object
     * by invoking adapter method.
     * @param obj		Business object to be deleted from db.
     * @param userId	UserId of currently operating user.
     * @param adapterServicesAccess		AdapterServiceAccess which will be used for adapter operation.
     * @throws ServiceException
     */
    public static void performRemove(Object obj, String userId, AdapterServicesAccess adapterServicesAccess) throws SevereException{
        BusinessTransactionContext transactionContext = getTransactionContext(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_DELETE, userId);
        try {
            adapterServicesAccess.removeObject(obj, obj.getClass().getName(),transactionContext);
        } catch (AdapterException adapterException) {
            Logger.logInfo("Adapter Exception occured.. Aborting transaction");
            //abortTransaction(adapterServicesAccess);
            logAndThrowException(obj, adapterException);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
    }
    
    /**
     * This will perform search operation using adapter according to the given searchCriteria
     * @param searchCriteria	Search criteria object.
     * @return
     * @throws ServiceException
     */
    public static SearchResults performSearch(SearchCriteria searchCriteria) throws SevereException {
        SearchResults searchResults = null;
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException e) {
            logAndThrowExceptionForSearch(searchCriteria.getBusinessObjectName(),searchCriteria.getSearchQueryName(),e);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
        return searchResults;
    } 

    /**
     * This will perform search operation using adapter according to the given searchCriteria
     * @param searchCriteria	Search criteria object.
     * @param adapterServicesAccess		AdapterServiceAccess which will be used for adapter operation. 
     * @return
     * @throws ServiceException
     */
    public static SearchResults performSearch(SearchCriteria searchCriteria, AdapterServicesAccess adapterServicesAccess) throws SevereException {
        SearchResults searchResults = null;
        try {
            searchResults = adapterServicesAccess.searchObject(searchCriteria);
        } catch (AdapterException e) {
            Logger.logInfo("Adapter Exception occured.. Aborting transaction");
            //abortTransaction(adapterServicesAccess);
            logAndThrowExceptionForSearch(searchCriteria.getBusinessObjectName(),searchCriteria.getSearchQueryName(),e);
        }catch (RuntimeException ex) {
        	Logger.logError("RunTimeException occured while AdapterCall");
        	Logger.logError(ex);
        	throw ex;
        }
        return searchResults;
    }
    
    /**
     * This will created and return AdapterSerchacriteria object.
     * @param businessObjectName	
     * @param queryName
     * @param criteriaValues
     * @return
     */
    public static SearchCriteria getAdapterSearchCriteria(String businessObjectName, String queryName, HashMap criteriaValues){
        SearchCriteria searchCriteria = null;
        searchCriteria = getAdapterSearchCriteria(businessObjectName, queryName, criteriaValues, Integer.MAX_VALUE);
        return searchCriteria;
    }
    
    /**
     * This will created and return AdapterSerchacriteria object.
     * @param businessObjectName
     * @param queryName
     * @param criteriaValues
     * @param maxResultSize
     * @return
     */
    public static SearchCriteria getAdapterSearchCriteria(String businessObjectName, String queryName, HashMap criteriaValues, int maxResultSize){
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(criteriaValues);
        searchCriteria.setBusinessObjectName(businessObjectName);
		searchCriteria.setSearchQueryName(queryName);
		searchCriteria.setMaxSearchResultSize(maxResultSize);
		searchCriteria.setSearchDomain(SearchCriteria.SEARCH_DOMAIN_MEDICAL);
        return searchCriteria;
    }
    
    /**
     * 
     * @param operation
     * @param userId
     * @return
     */
    private static BusinessTransactionContext getTransactionContext(String operation, String userId){
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(operation);
        btc.setBusinessTransactionUser(userId);
        return btc;
    }
    
    /**
     * 
     * @return
     */
    public static AdapterServicesAccess getAdapterService(){
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }
    
    public static AdapterServicesAccess getAdapterServiceForCP2000(){
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.getAdapterServicesAccess("cp2000");
        return adapterServicesAccess;
    }
    
    /**
     * 
     * @param obj
     * @param adapterException
     * @throws ServiceException
     */
    private static void logAndThrowException(Object obj, AdapterException adapterException) throws SevereException{
        List errorParams = new ArrayList();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        Logger.logError("Adapter exception occured...........");
        Logger.logError(adapterException);
        throw new SevereException("Adapter Exception occured", errorParams, adapterException);

    }
    
    /**
     * 
     * @param businessObjectName
     * @param queryName
     * @param adapterException
     * @throws ServiceException
     */
    private static void logAndThrowExceptionForSearch(String businessObjectName, String queryName, AdapterException adapterException) throws SevereException{
        List errorParams = new ArrayList();
        errorParams.add(businessObjectName);
        errorParams.add(queryName);
        Logger.logError(adapterException);
        throw new SevereException("Adapter Exception occured", errorParams, adapterException);
    }
    
    // Trasaction logging methods
	private static long transactionId = 0;

	/**
	 * Logging begin Transaction with unique transaction Id 
	 */
	public synchronized static long getTransactionId(){
		return ++transactionId;
	}
	
	/**
	 * Logging begin Transaction with unique transaction Id 
	 */
	public static void logBeginTranstn(long transactionId, Object builder, String methodId ) {
		logTransaction("BEGIN",transactionId,builder,methodId);
	}
	
	/**
	 * Logging end Transaction with unique transaction Id 
	 */
	public static void logTheEndTransaction(long transactionId, Object builder, String methodId) {
		logTransaction("END",transactionId,builder,methodId);
	}
	
	/**
	 * Logging abort Transaction with unique transaction Id 
	 */
	public static void logAbortTxn(long transactionId, Object builder, String methodId) {
		logTransaction("ABORT",transactionId,builder,methodId);
	}
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy KK:mm:ss a zz"); 
	
	private static void logTransaction(String status, long transactionId, Object builder, String methodId) {
		Logger.logInfo("Transaction logging#$!LOG!$# [!"+ status +"!] - TXNID#@ = [!" + transactionId +  "!] CLSNAME#@ = [!" + builder.getClass().getName() + "!] METHODID#@ = [!" + methodId + "!] TIME#@ = [!" + System.currentTimeMillis() + "!] DATE#@ = [!" + simpleDateFormat.format(new Date()) + "!]" );
	}

}
