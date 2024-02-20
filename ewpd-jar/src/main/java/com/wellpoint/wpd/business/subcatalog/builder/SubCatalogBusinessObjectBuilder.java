/*
 * SubcatalogBusinessObjectBuilder.java
 *  © 2006 WellPoint, Inc. All Rights
 * Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.subcatalog.builder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.subcatalog.adapter.SubCatalogAdapterManager;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.ItemLocateCriteria;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.ReferenceDataLocateCriteria;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.SubCatalogLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
/**
* Base class for subcatalog builder
* @author US Technology Resources - www.ustri.com <br />
* @version $Id: $
*/

public class SubCatalogBusinessObjectBuilder {
   /**
    * Function to persist a SubCatalogBO. Flag is used to differentiate whether an
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
    SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
    SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
    AdapterServicesAccess subCatalogAdapterServiceAccess = AdapterUtil.getAdapterService(); // new
    long transactionId = AdapterUtil.getTransactionId();
    AuditFactory auditFactory = (AuditFactory) ObjectFactory
            .getFactory(ObjectFactory.AUDIT);
    Audit audit = auditFactory.getAudit(user);
	if(catalogBO.getDescription().equals("")){
		catalogBO.setDescription(" ");
	}
	try{
		AdapterUtil.beginTransaction(subCatalogAdapterServiceAccess);//for domain util service
		AdapterUtil.logBeginTranstn(transactionId,this,"persist(CatalogBO catalogBO, User user,boolean insertFlag)");
		
		catalogBO.setCreatedUser(audit.getUser());
		catalogBO.setCreatedTimestamp(audit.getTime());
		catalogBO.setLastUpdatedUser(audit.getUser());
		catalogBO.setLastUpdatedTimestamp(audit.getTime());
		 if (insertFlag) {
		 	catalogBO.setCatalogId(sequenceAdapterManager
	                .getNextCatalogSequence());
		 	subCatalogAdapterManager.createSubCatalog(catalogBO, audit);
	
		 }else{
			 subCatalogAdapterManager.updateSubCatalog(catalogBO, audit);
		 }
	     DomainDetail businessDomain = getBusinessDomainValues(catalogBO);
	     DomainUtil.persistAssociatedDomains(businessDomain,
	             subCatalogAdapterServiceAccess);
	     
	     AdapterUtil.endTransaction(subCatalogAdapterServiceAccess);
	     AdapterUtil.logTheEndTransaction(transactionId,this,"persist(CatalogBO catalogBO, User user,boolean insertFlag)");
	}catch (SevereException ex) {
		AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
		AdapterUtil.logAbortTxn(transactionId,this,"persist(CatalogBO catalogBO, User user,boolean insertFlag)");
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in persist(CatalogBO catalogBO, User user,boolean insertFlag), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	}catch(AdapterException ex){
		AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
		AdapterUtil.logAbortTxn(transactionId,this,"persist(CatalogBO catalogBO, User user,boolean insertFlag)");
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in persist(CatalogBO catalogBO, User user,boolean insertFlag), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	}catch (Exception ex) {
		AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
		AdapterUtil.logAbortTxn(transactionId,this,"persist(CatalogBO catalogBO, User user,boolean insertFlag)");
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in persist(CatalogBO catalogBO, User user,boolean insertFlag), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
    }
     return true;
   }
   
      /**
    * Method to delete the sub-catalog
    * 
    * @param businessObject
    * @param user
    * @return boolean
    * @throws SevereException
    */
   public boolean delete(CatalogBO catalogBO, User user)
           throws SevereException {
       Logger.logInfo("Sub-CatalogBusinessObjectBuilder - Entering deleteSub-Catlog(): Sub-Catalog");
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       AdapterServicesAccess subCatalogAdapterServiceAccess = AdapterUtil
               .getAdapterService();
       long transactionId = AdapterUtil.getTransactionId();
       try{
       	   AdapterUtil.beginTransaction(subCatalogAdapterServiceAccess);//for domain util service
       	   AdapterUtil.logBeginTranstn(transactionId,this,"delete(CatalogBO catalogBO, User user)");
       	   
	       subCatalogAdapterManager.deleteSubCatalog(catalogBO, user);
	       DomainUtil.removeAssociatedDomains("SubCatalog", catalogBO.getCatalogId(),
	               user.getUserId(), subCatalogAdapterServiceAccess);
	       
	       AdapterUtil.endTransaction(subCatalogAdapterServiceAccess);
	       AdapterUtil.logTheEndTransaction(transactionId,this,"delete(CatalogBO catalogBO, User user)");
       }catch (SevereException ex) {
       		AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
       		AdapterUtil.logAbortTxn(transactionId,this,"delete(CatalogBO catalogBO, User user)");
    		List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                        "Exception occured in delete(CatalogBO catalogBO, User user), in SubCatalogBusinessObjectBuilder",
                        errorParams, ex);
       }catch(AdapterException ex){
	       	AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
	   		AdapterUtil.logAbortTxn(transactionId,this,"delete(CatalogBO catalogBO, User user)");
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in delete(CatalogBO catalogBO, User user), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
       }catch(Exception ex){
	       	AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
	   		AdapterUtil.logAbortTxn(transactionId,this,"delete(CatalogBO catalogBO, User user)");
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in delete(CatalogBO catalogBO, User user), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
       }
       
       Logger.logInfo("SubCatalogBusinessObjectBuilder - Returning deleteSub-Catlog(): Sub-Catalog");
       return true;
   }
   /**
    * Method to delete the item association with sub-catalog
    * @param subCatalogBO
    * @param user
    * @return
    * @throws SevereException
    */
   public boolean deleteItemAssociation(SubCatalogBO subCatalogBO,User user) throws SevereException{
       Logger.logInfo("SubCatalogBusinessObjectBuilder - Entering deleteSub-CatalogAssociation(): Sub-Catalog");
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       AdapterServicesAccess adapterServiceAccess = AdapterUtil
       													.getAdapterService();
       long transactionId = AdapterUtil.getTransactionId();
       CatalogBO catalogBO=new CatalogBO();
       catalogBO.setCatalogId(subCatalogBO.getSubCatalogId());
       AuditFactory auditFactory = (AuditFactory) ObjectFactory
       .getFactory(ObjectFactory.AUDIT);
       Audit audit = auditFactory.getAudit(user);
       List parentCatalogList=new ArrayList();
       parentCatalogList=subCatalogBO.getParentCatalogList();
       try{
       		AdapterUtil.beginTransaction(adapterServiceAccess);
       		AdapterUtil.logBeginTranstn(transactionId, this,"deleteItemAssociation(SubCatalogBO subCatalogBO,User user)");
       		//Setting parentCatalogId from parentCatalogList for multiple deletion. 
       		for(int i=0;i<parentCatalogList.size();i++){
       			subCatalogBO.setSubCatalogSysId(Integer.parseInt((String) parentCatalogList.get(i)));
       			subCatalogAdapterManager.deleteItemAssociationForMultipleTnxs(subCatalogBO,user,adapterServiceAccess);
       		}
	       	//retrieving the subCatalog details for updating last updated user and timestamp 
		    catalogBO = subCatalogAdapterManager.retrieve(catalogBO);
		    catalogBO.setLastUpdatedTimestamp(audit.getTime());
		    catalogBO.setLastUpdatedUser(audit.getUser());
		    catalogBO.setCatalogParentID(new Integer(catalogBO.getCatalogParentid()).toString());
		    //for updation of last updated user and last updated timestamp
		    subCatalogAdapterManager.updateSubCatalogForMultipleTxns(catalogBO, audit,adapterServiceAccess);
		    AdapterUtil.endTransaction(adapterServiceAccess);
	        AdapterUtil.logTheEndTransaction(transactionId, this,"deleteItemAssociation(SubCatalogBO subCatalogBO,User user)");
       }catch (SevereException ex) {
       		AdapterUtil.abortTransaction(adapterServiceAccess);
       		AdapterUtil.logAbortTxn(transactionId, this,"deleteItemAssociation(SubCatalogBO subCatalogBO,User user)");
	       	List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in deleteItemAssociation(SubCatalogBO subCatalogBO,User user), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
        }catch(AdapterException ex){
        	AdapterUtil.abortTransaction(adapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId, this,"deleteItemAssociation(SubCatalogBO subCatalogBO,User user)");
	       	List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in deleteItemAssociation(SubCatalogBO subCatalogBO,User user), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
       }catch(Exception ex){
       		AdapterUtil.abortTransaction(adapterServiceAccess);
       		AdapterUtil.logAbortTxn(transactionId, this,"deleteItemAssociation(SubCatalogBO subCatalogBO,User user)");
	       	List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in deleteItemAssociation(SubCatalogBO subCatalogBO,User user), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
	   }
       	Logger.logInfo("SubCatalogBusinessObjectBuilder - Returning deleteSub-CatalogAssociation(): Sub-Catalog");
       return true;
   }
   
   /**
    * Method to check if an item is associated with a catalog
    * 
    * @param catalogBO
    * @return boolean
    * @throws SevereException
    */
   public boolean isItemAssociated(CatalogBO catalogBO) throws SevereException {
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       List resultList;
	try {
		resultList = subCatalogAdapterManager.searchForItem(catalogBO);
	}catch(SevereException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in isItemAssociated(CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	}catch(AdapterException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in isItemAssociated(CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	} catch (Exception ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in isItemAssociated(CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);    }
	if (resultList != null && resultList.size() > 0)
           return true;
       return false;
   }

   
   /**
    * Method to locate the sub catalogs based on the locate criteria
    * @return List
 * @throws SevereException
    */
   public List locate(SubCatalogLocateCriteria subCatalogLocateCriteria) throws SevereException{
       
       // Create an instance of the Adapter Manager
       SubCatalogAdapterManager adapterManager = new SubCatalogAdapterManager();
       
       try {
		// Call the adapter locate() and return to the business service
		   return adapterManager.locate(subCatalogLocateCriteria);
       }catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in locate (SubCatalogLocateCriteria subCatalogLocateCriteria), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
       }catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in locate (SubCatalogLocateCriteria subCatalogLocateCriteria), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
       } catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in locate (SubCatalogLocateCriteria subCatalogLocateCriteria), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex); 
	   }
   }
   
   /**
    * Method to locate the sub catalogs based on the locate criteria
    * action 1:to retrieve based upon the parent catalog name
    * action 2:to retrieve based upon the business domains
    * action 3:retrieve based upon the entityId and entityType
    * action 4:for reference popup - retrieve based upon entered criteria
    * action 5:for reference popup - retrieve based upon the combo of term and pva
    * @return List
    * @throws SevereException
    */
   public List locate(ReferenceDataLocateCriteria referenceDataLocateCriteria) throws SevereException{
       
       // Create an instance of the Adapter Manager
       SubCatalogAdapterManager adapterManager = new SubCatalogAdapterManager();
       // Call the adapter locate() and return to the business service
       List refDataList;
	try {
		if(referenceDataLocateCriteria.getAction() == 3){
		   		if(null!= referenceDataLocateCriteria.getEntityType() 
		   					&& referenceDataLocateCriteria.getEntityType().equals("migration"))
		   			getBusinessDomainsForMigration(referenceDataLocateCriteria);
		   		else
		   			getBusinessDomains(referenceDataLocateCriteria);
		   }
		   else if(referenceDataLocateCriteria.getAction() == 4||referenceDataLocateCriteria.getAction() == -1){
		   	   if(referenceDataLocateCriteria.getAction() == -1){
		   	   	return new ArrayList();
		   	   }
		       	if(null!= referenceDataLocateCriteria.getSearchValueForPopUp()){
		       		getBusinessDomains(referenceDataLocateCriteria);
		       	}
		   }else if(referenceDataLocateCriteria.getAction() == 5){
			   		if(null!= referenceDataLocateCriteria.getEntityType() && referenceDataLocateCriteria.getEntityType().equals("stdbenefit") ){
			   			getBusinessDomains(referenceDataLocateCriteria);
			   		}					
			}else if(referenceDataLocateCriteria.getAction() == 13){
		   		if(null!= referenceDataLocateCriteria.getEntityType() 
	   					&& referenceDataLocateCriteria.getEntityType().equals("migration"))
	   			getBusinessDomainsForMigration(referenceDataLocateCriteria);
	   		else
	   			getBusinessDomains(referenceDataLocateCriteria);
	   }
		   refDataList = adapterManager.locate(referenceDataLocateCriteria);
	}catch(SevereException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in locate(ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	}catch(AdapterException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in locate(ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	} catch (Exception ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in locate(ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);    }
       // DROP_DOWN_ACTION is added for WTT.
       if(( referenceDataLocateCriteria.getAction() == 1 ||referenceDataLocateCriteria.getAction() == 10 ||referenceDataLocateCriteria.getAction() == 31 || 
       		referenceDataLocateCriteria.getAction() == 7  || referenceDataLocateCriteria.getAction() == 27  || referenceDataLocateCriteria.getAction() == 8 || 
			referenceDataLocateCriteria.getAction() == 99 || referenceDataLocateCriteria.getAction() == BusinessConstants.DROP_DOWN_ACTION )
			&& null != refDataList && !refDataList.isEmpty()
		  ){
           List refDataNewList = new ArrayList();
           for(int i = 0 ; i < refDataList.size() ; i++){
               ItemBO individualItemBO = (ItemBO)refDataList.get(i);
               SubCatalogBO subCatalogBO = new SubCatalogBO();
               subCatalogBO.setPrimaryCode(individualItemBO.getPrimaryCode());
               subCatalogBO.setParentCatalogId(individualItemBO.getCatalogId());
               subCatalogBO.setDescription(individualItemBO.getDescription());
               subCatalogBO.setSubCatalogName(individualItemBO.getSecondaryCode());
               refDataNewList.add(subCatalogBO);
           }
           return refDataNewList;
       }
       return refDataList;
   }
   
   /**
    * Method to return the list of items associated with the subcatalog
    * @param subCatalogLocateCriteria
    * @param user
    * @return
    * @throws SevereException
    */
   public LocateResults locateItemAssociation(SubCatalogLocateCriteria subCatalogLocateCriteria) throws SevereException{
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       LocateResults locateResults = new LocateResults();
       
       List resultList;
	try {
		resultList = subCatalogAdapterManager.locateItemAssociation(subCatalogLocateCriteria);
	}catch(SevereException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in LocateItemAssociation(SubCatalogLocateCriteria subCatalogLocateCriteria), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	}catch(AdapterException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in LocateItemAssociation(SubCatalogLocateCriteria subCatalogLocateCriteria), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
	} catch (Exception ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in LocateItemAssociation(SubCatalogLocateCriteria subCatalogLocateCriteria), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
    }
	locateResults.setLocateResults(resultList);
       return locateResults;
   }
   
   /**
    * Method to retrieve a subcatalog from the db
    * @param catalogBO
    * @return
 * @throws SevereException
    */
   public CatalogBO retrieve(CatalogBO catalogBO) throws SevereException{
       
       // Create an instance of the Adapter
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       
       try {
		// Return the bo with the subcatalog details
		   return subCatalogAdapterManager.retrieve(catalogBO);
       }catch(SevereException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieve (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
       }catch(AdapterException ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieve (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
       } catch (Exception ex){
			List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                    "Exception occured in retrieve (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
	                    errorParams, ex);
        }
   }
   
   /**
    * @param catalogBO
    * @return
    */
   private DomainDetail getBusinessDomainValues(CatalogBO catalogBO) {
       DomainDetail businessDomain = new DomainDetail();
       businessDomain.setEntityType("SubCatalog");
       businessDomain.setEntityName(catalogBO.getCatalogName());
       businessDomain.setEntityParentId(catalogBO.getCatalogId());
       businessDomain.setDomainList(catalogBO.getBusinessDomains());
       businessDomain.setLastUpdatedUser(catalogBO.getLastUpdatedUser());
       businessDomain.setLastUpdatedTimeStamp(catalogBO
               .getLastUpdatedTimestamp());
       return businessDomain;
   }
   
   /**
    * Method to check if checking if parent Catalog and domain is duplicate
    * 
    * @param CatalogBO
    * @return boolean
    * @throws SevereException
    */
   public boolean isSubCatalogDuplicate(CatalogBO catalogBO) throws SevereException {
   	SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       List domains = catalogBO.getBusinessDomains();
	   List lob = BusinessUtil.getLobList(domains);
	   List be = BusinessUtil.getbusinessEntityList(domains);
	   List bg = BusinessUtil.getBusinessGroupList(domains);
	   //CARS START
	   List mbu = BusinessUtil.getMarketBusinessUnitList(domains);
	   //CARS END
       List resultList;
			try {
				resultList = subCatalogAdapterManager
			           .searchForDuplicateSubCatalog(catalogBO, lob, be, bg,mbu);
			}catch(SevereException ex){
				List errorParams = new ArrayList();
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                    "Exception occured in isSubCatalogDuplicate (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
		                    errorParams, ex);
			}catch(AdapterException ex){
				List errorParams = new ArrayList();
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                    "Exception occured in isSubCatalogDuplicate (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
		                    errorParams, ex);
			} catch (Exception ex){
				List errorParams = new ArrayList();
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                    "Exception occured in isSubCatalogDuplicate (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
		                    errorParams, ex);
	       }
		if (resultList != null && resultList.size() > 0)
	           return true;
	       return false;
   }
   
   /**
    * Method to check if  subcatalog name and parent catalog is duplicate
    * 
    * @param CatalogBO
    * @return boolean
    * @throws SevereException
    */
   public boolean  isSubCatalogNameCatalogDuplicate(CatalogBO catalogBO) throws SevereException {
   	SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
    List resultList;
	try {
		resultList = subCatalogAdapterManager.searchForDuplicateSubCatalogAndParentCatalog(catalogBO);
	}catch(SevereException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in isSubCatalogNameCatalogDuplicate (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
    }catch(AdapterException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in isSubCatalogNameCatalogDuplicate (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
    } catch (Exception ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in isSubCatalogNameCatalogDuplicate (CatalogBO catalogBO), in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
    }
	if (resultList != null && resultList.size() > 0)
        return true;
    return false;
   }
  
   
   /**
    * Method to return the list of items associated with the subCatalog
    * @param itemLocateCriteria
    * @param user
    * @return
    * @throws SevereException
    */
   public LocateResults locate(ItemLocateCriteria itemLocateCriteria)throws SevereException{
       LocateResults locateResults = new LocateResults();
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       List resultList;
	try {
		resultList = subCatalogAdapterManager.locate(itemLocateCriteria);
	}catch(SevereException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in locate ItemLocateCriteria itemLocateCriteria, in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
     }catch(AdapterException ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in locate ItemLocateCriteria itemLocateCriteria, in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
     } catch (Exception ex){
		List errorParams = new ArrayList();
        String obj = ex.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                    "Exception occured in locate ItemLocateCriteria itemLocateCriteria, in SubCatalogBusinessObjectBuilder",
                    errorParams, ex);
     }
	locateResults.setLocateResults(resultList);
       locateResults.setTotalResultsCount(resultList.size());
       
       return locateResults;
   }
   /**
    * Method for creating the item association with the sub-catalog
    * @param itemList
    * @param subCatalogBO
    * @param user
    * @return
    * @throws SevereException
    * @throws AdapterException
    */
   public boolean persist(List itemList, SubCatalogBO subCatalogBO,User user) throws SevereException{
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
       AdapterServicesAccess subCatalogAdapterServiceAccess = AdapterUtil.getAdapterService();
       long transactionId = AdapterUtil.getTransactionId();
       CatalogBO catalogBO=new CatalogBO();
       catalogBO.setCatalogId(subCatalogBO.getSubCatalogId());
      // catalogBO.setCatalogParentID(new Integer(subCatalogBO.getParentCatalogId()).toString());
       AuditFactory auditFactory = (AuditFactory) ObjectFactory
       .getFactory(ObjectFactory.AUDIT);
       Audit audit = auditFactory.getAudit(user);
       
       subCatalogBO.setCreatedTimestamp(audit.getTime());
       subCatalogBO.setCreatedUser(audit.getUser());
       subCatalogBO.setLastUpdatedTimestamp(audit.getTime());
       subCatalogBO.setLastUpdatedUser(audit.getUser());
       
       try {
       	AdapterUtil.beginTransaction(subCatalogAdapterServiceAccess);
       	AdapterUtil.logBeginTranstn(transactionId,this,"persist(List itemList, SubCatalogBO subCatalogBO,User user)");
    
	       for(int i=0 ; i < itemList.size(); i++){
	           subCatalogBO.setPrimaryCode((String)itemList.get(i));
		       subCatalogBO.setSubCatalogSysId(sequenceAdapterManager.getNextSubCatalogSequence());
		       subCatalogAdapterManager.createSubCatalogAssociation(subCatalogBO,audit,subCatalogAdapterServiceAccess);
		      }
	       //retrieving the subCatalog details for updating last updated user and timestamp 
	       catalogBO = subCatalogAdapterManager.retrieve(catalogBO);
	       catalogBO.setLastUpdatedTimestamp(audit.getTime());
	       catalogBO.setLastUpdatedUser(audit.getUser());
	       catalogBO.setCatalogParentID(new Integer(catalogBO.getCatalogParentid()).toString());
	       //for updation of last updated user and last updated timestamp
	       subCatalogAdapterManager.updateSubCatalogForMultipleTxns(catalogBO, audit,subCatalogAdapterServiceAccess);
	       
	       AdapterUtil.endTransaction(subCatalogAdapterServiceAccess);
	       AdapterUtil.logTheEndTransaction(transactionId,this,"persist(List itemList, SubCatalogBO subCatalogBO,User user)");
       } catch (SevereException ex) {
   			AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
   			AdapterUtil.logAbortTxn(transactionId,this,"persist(List itemList, SubCatalogBO subCatalogBO,User user)");
   			List errorParams = new ArrayList();
   	        String obj = ex.getClass().getName();
   	        errorParams.add(obj);
   	        errorParams.add(obj.getClass().getName());
   	        throw new SevereException(
   	                    "Exception occured in persist(List itemList, SubCatalogBO subCatalogBO,User user), in SubCatalogBusinessObjectBuilder",
   	                    errorParams, ex);
       } catch (AdapterException ex) {
       		AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
       		AdapterUtil.logAbortTxn(transactionId,this,"persist(List itemList, SubCatalogBO subCatalogBO,User user)");
   			List errorParams = new ArrayList();
   	        String obj = ex.getClass().getName();
   	        errorParams.add(obj);
   	        errorParams.add(obj.getClass().getName());
   	        throw new SevereException(
   	                    "Exception occured in persist(List itemList, SubCatalogBO subCatalogBO,User user), in SubCatalogBusinessObjectBuilder",
   	                    errorParams, ex); 
   	    } catch (Exception ex) {
       		AdapterUtil.abortTransaction(subCatalogAdapterServiceAccess);
       		AdapterUtil.logAbortTxn(transactionId,this,"persist(List itemList, SubCatalogBO subCatalogBO,User user)");
   			List errorParams = new ArrayList();
   	        String obj = ex.getClass().getName();
   	        errorParams.add(obj);
   	        errorParams.add(obj.getClass().getName());
   	        throw new SevereException(
   	                    "Exception occured in persist(List itemList, SubCatalogBO subCatalogBO,User user), in SubCatalogBusinessObjectBuilder",
   	                    errorParams, ex);
       }
       
       return true;
   } 
   
  
   /**
    * Method for getting the business domains
    * @param referenceDataLocateCriteria
    * @return
    * @throws SevereException
    */
   public boolean getBusinessDomains(ReferenceDataLocateCriteria referenceDataLocateCriteria) throws SevereException{

   		try {
			DomainDetail domainList = (DomainDetail)DomainUtil.retrieveDomainDetailInfo(referenceDataLocateCriteria.getEntityType(), 
			        referenceDataLocateCriteria.getEntityId());
			referenceDataLocateCriteria.setBeList(BusinessUtil.getbusinessEntityList(domainList.getDomainList()));
			referenceDataLocateCriteria.setBgList(BusinessUtil.getBusinessGroupList(domainList.getDomainList()));
			referenceDataLocateCriteria.setLobList(BusinessUtil.getLobList(domainList.getDomainList()));
   		} catch (SevereException ex){
   			List errorParams = new ArrayList();
   	        String obj = ex.getClass().getName();
   	        errorParams.add(obj);
   	        errorParams.add(obj.getClass().getName());
   	        throw new SevereException(
   	                    "Exception occured in getBusinessDomains(ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
   	                    errorParams, ex);
        }catch(Exception ex){
   			List errorParams = new ArrayList();
   	        String obj = ex.getClass().getName();
   	        errorParams.add(obj);
   	        errorParams.add(obj.getClass().getName());
   	        throw new SevereException(
   	                    "Exception occured in getBusinessDomains(ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
   	                    errorParams, ex);
        }
       return true;
   }
   /**
    * Method to obtain the business domain values for migration
    * @param referenceDataLocateCriteria
    * @return
    * @throws SevereException
    */
   public boolean getBusinessDomainsForMigration(ReferenceDataLocateCriteria referenceDataLocateCriteria)throws SevereException{
  // 	referenceDataLocateCriteria.setEntityId(736);
  	List domainList;
	try {
		domainList = DomainUtil.getDomainsForMigration(referenceDataLocateCriteria.getEntityId());
	} catch (SevereException ex){
			List errorParams = new ArrayList();
   	        String obj = ex.getClass().getName();
   	        errorParams.add(obj);
   	        errorParams.add(obj.getClass().getName());
   	        throw new SevereException(
   	                    "Exception occured in getBusinessDomains(ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
   	                    errorParams, ex);
   	 }catch(Exception ex){
			List errorParams = new ArrayList();
   	        String obj = ex.getClass().getName();
   	        errorParams.add(obj);
   	        errorParams.add(obj.getClass().getName());
   	        throw new SevereException(
   	                    "Exception occured in getBusinessDomains(ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
   	                    errorParams, ex);
   	 }
		List lobList = new ArrayList();
	  	List beList = new ArrayList();
	  	List bgList = new ArrayList();
	  	if(null!=domainList && !domainList.isEmpty()){
	  		for(int i=0;i<domainList.size();i++){
	  			MigrationDomainInfo migrationDomainInfo = (MigrationDomainInfo)domainList.get(i);
	  			lobList.add(migrationDomainInfo.getLobDesc());
	  			beList.add(migrationDomainInfo.getBusinessEntityDesc());
	  			bgList.add(migrationDomainInfo.getBusinessGroupDesc());
	  		}
  	}
  		
   	return true;
   }
   /**
    * Method to locate the sub catalogs based on the locate criteria
    * @return List
    * @throws SevereException
    */
   public HashMap locateRefData(ReferenceDataLocateCriteria referenceDataLocateCriteria) throws SevereException{
       
       // Create an instance of the Adapter Manager
       SubCatalogAdapterManager subCatalogAdapterManager = new SubCatalogAdapterManager();
       List parentCatalogList=referenceDataLocateCriteria.getParentCatalogList();
       HashMap resultMap = new HashMap();
       if(null!=parentCatalogList)
       {
           for(int i=0;i<parentCatalogList.size();i++)
           {
               referenceDataLocateCriteria.setParentCatalog(parentCatalogList.get(i).toString());
                List result;
				try {
					result = subCatalogAdapterManager.locateRefData(referenceDataLocateCriteria);
				}catch(SevereException ex){
					List errorParams = new ArrayList();
			        String obj = ex.getClass().getName();
			        errorParams.add(obj);
			        errorParams.add(obj.getClass().getName());
			        throw new SevereException(
			                    "Exception occured in locateRefData (ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
			                    errorParams, ex);
			    }catch(AdapterException ex){
					List errorParams = new ArrayList();
			        String obj = ex.getClass().getName();
			        errorParams.add(obj);
			        errorParams.add(obj.getClass().getName());
			        throw new SevereException(
			                    "Exception occured in locateRefData (ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
			                    errorParams, ex);
			     }catch (Exception ex){
					List errorParams = new ArrayList();
			        String obj = ex.getClass().getName();
			        errorParams.add(obj);
			        errorParams.add(obj.getClass().getName());
			        throw new SevereException(
			                    "Exception occured in locateRefData (ReferenceDataLocateCriteria referenceDataLocateCriteria), in SubCatalogBusinessObjectBuilder",
			                    errorParams, ex);
			     }
				resultMap.put(parentCatalogList.get(i).toString(),result);
           }
       }
      
       return resultMap;
   }
   
   
  
}