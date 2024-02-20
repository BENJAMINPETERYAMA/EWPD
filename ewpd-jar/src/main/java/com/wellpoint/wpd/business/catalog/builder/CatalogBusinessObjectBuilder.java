/**
* CatalogBusinessObjectBuilder.java 
*  © 2006 WellPoint, Inc. All Rights Reserved.
* 
* This software is the confidential and proprietary information of Wellpoint
* Inc. ("Confidential Information"). You shall not disclose or use Confidential
* Information without the express written agreement of Wellpoint Inc.
* Created on May 16, 2007
*/
package com.wellpoint.wpd.business.catalog.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.catalog.adapter.CatalogAdapterManager;
import com.wellpoint.wpd.business.catalog.locatecriteria.CatalogLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.bo.SrdaCatalogBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * Base class for catalog builder
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class CatalogBusinessObjectBuilder {
    /**
     * Function to persist a CatalogBO. Flag is used to differentiate whether an
     * insertion or updation is done.
     * 
     * @param businessObject
     * @param user
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(CatalogBO catalogBO, User user,
            boolean insertFlag) throws SevereException {
        Logger.logInfo("CatalogBusinessObjectBuilder - Entering createCatlog(): Catalog");
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
        .getFactory(ObjectFactory.AUDIT);
        try{
	
	        Audit audit = auditFactory.getAudit(user);
			if(catalogBO.getDescription().equals("")){
				catalogBO.setDescription(" ");
			}
	        catalogBO.setLastUpdatedUser(audit.getUser());
	        catalogBO.setLastUpdatedTimestamp(audit.getTime());
	
	        if (insertFlag) {
	            catalogBO.setCatalogId(sequenceAdapterManager
	                    .getNextCatalogSequence());
	            catalogBO.setCatalogParentID(null);
	            catalogBO.setCreatedUser(audit.getUser());
	            catalogBO.setCreatedTimestamp(audit.getTime());
	
	            catalogAdapterManager.createCatalog(catalogBO, audit);
	         } else {
	            catalogBO.setCatalogParentid(catalogBO.getCatalogId());
	            catalogBO.setCreatedUser(audit.getUser());
	            catalogBO.setCreatedTimestamp(audit.getTime());
	            catalogAdapterManager.editCatalog(catalogBO, audit);
	        }
        }catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in persist(CatalogBO catalogBO, User user,boolean insertFlag), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in persist(CatalogBO catalogBO, User user,boolean insertFlag), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in persist(CatalogBO catalogBO, User user,boolean insertFlag), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    }   
        
        return true;
    }


    /**
     * Method to retrieve the catalog
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public CatalogBO retrieve(CatalogBO catalogBO, User user)
            throws SevereException {
        Logger.logInfo("CatalogBusinessObjectBuilder - Entering retrieveCatlog(): Catalog");
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        Logger.logInfo("CatalogBusinessObjectBuilder - Returning retrieveCatlog(): Catalog");
        try {
			return catalogAdapterManager.retrieveCatalog(catalogBO);
        }catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieve(CatalogBO catalogBO, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieve(CatalogBO catalogBO, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieve(CatalogBO catalogBO, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    }   
          
    }


    /**
     * Method to delete the catalog
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(CatalogBO catalogBO, User user)
            throws SevereException {
        Logger.logInfo("CatalogBusinessObjectBuilder - Entering deleteCatlog(): Catalog");
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        try{
	        catalogAdapterManager.deleteCatalog(catalogBO, user);
        }catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in delete(CatalogBO catalogBO, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in delete(CatalogBO catalogBO, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in delete(CatalogBO catalogBO, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    }   
        
        Logger.logInfo("CatalogBusinessObjectBuilder - Returning deleteCatlog(): Catalog");
        return true;
    }


    /**
     * Method to check if catalog name is duplicate
     * 
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isNameDuplicate(CatalogBO catalogBO) throws SevereException {
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        List resultList;
		try {
			resultList = catalogAdapterManager.searchForDuplicateName(catalogBO);
		}catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isNameDuplicate(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isNameDuplicate(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isNameDuplicate(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    }   
        
		if (resultList != null && resultList.size() > 0)
            return true;
        return false;
    }


    /**
     * Method to check if an item is associated with a catalog
     * 
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isItemAssociated(CatalogBO catalogBO) throws SevereException {
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        List resultList;
		try {
			resultList = catalogAdapterManager.searchForItem(catalogBO);
		}catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isItemAssociated(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isItemAssociated(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isItemAssociated(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    }   
		if (resultList != null && resultList.size() > 0)
            return true;
        return false;
    }

    /**
     * Method to check whether subcatalog is associated with the catalog
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isCatalogAssociated(CatalogBO catalogBO)throws SevereException {
        // Create an instance of the adaptermanager
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        
        // Call the searchForSubCatalog()
        List subCatalogList;
		try {
			subCatalogList = catalogAdapterManager.searchForSubCatalog(catalogBO);
		}catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isCatalogAssociated(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isCatalogAssociated(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in isCatalogAssociated(CatalogBO catalogBO), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    } 
		if(null != subCatalogList && subCatalogList.size() > 0){
            return true;
        }
            
        return false;
    }

    /**
     * To locate the Catalog *
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws SevereException
     */
    public LocateResults locate(CatalogLocateCriteria catalogLocateCriteria,
            User user) throws SevereException {
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        LocateResults locateResults = new LocateResults();
        List resultList;
		try {
			resultList = catalogAdapterManager.locate(catalogLocateCriteria);
		}catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in locate(CatalogLocateCriteria catalogLocateCriteria,User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in locate(CatalogLocateCriteria catalogLocateCriteria,User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in locate(CatalogLocateCriteria catalogLocateCriteria,User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    }
		locateResults.setLocateResults(resultList);
        locateResults.setTotalResultsCount(resultList.size());
        return locateResults;
    }
    
    /**
     * Method to retrieve the Srda Tables data 
     * 
     * @param businessObject
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public SrdaCatalogBO retrieveSrdaData(SrdaCatalogBO srdaCatalogBO, User user)
            throws SevereException {
        Logger.logInfo("CatalogBusinessObjectBuilder - Entering retrieveCatlog(): SrdaCatalog");
        CatalogAdapterManager catalogAdapterManager = new CatalogAdapterManager();
        Logger.logInfo("CatalogBusinessObjectBuilder - Returning retrieveCatlog(): SrdaCatalog");
        try {
			return catalogAdapterManager.retrieveSrdaCatalog(srdaCatalogBO);
        }catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieveSrdaData(SrdaCatalog srdaCatalog, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		}catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieveSrdaData(SrdaCatalog srdaCatalog, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
		} catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieveSrdaData(SrdaCatalog srdaCatalog, User user), in CatalogBusinessObjectBuilder",
	                    errorParams, ex);
	    }   
          
    }

}

