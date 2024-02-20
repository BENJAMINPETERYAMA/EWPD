/*
 * SubcatalogBusinessValidationService.java
 *  © 2006 WellPoint, Inc. All Rights
 * Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.subcatalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.subcatalog.builder.SubCatalogBusinessObjectBuilder;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.ReferenceDataLocateCriteria;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.SubCatalogLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.refdata.request.RefDataDomainValidationRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataDomainValidationResponse;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogDeleteRequest;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogSaveRequest;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogDeleteResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogSaveResponse;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.util.TimeHandler;

/**
 * Base class for subcatalog validation service
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubCatalogBusinessValidationService extends
        WPDBusinessValidationService {

    List messageList = null;


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }


    /**
     * Method to check whether a duplicate subCatalog exist before creating a new one
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SubCatalogSaveRequest request)
            throws ServiceException {
    	Logger.logInfo("Sub-CatalogBusinessValidationService - Entering execute():  Sub-Catalog Create");
    	SubCatalogSaveResponse subCatalogSaveResponse=null;
    	SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
    	CatalogBO catalogBO=getSubCatalogObject(request);
    	subCatalogSaveResponse = (SubCatalogSaveResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_SUB_CATALOG_RESPONSE);
        List messageList = new ArrayList();
        boolean validationRequired = true;
        try {
        	//for subcatalog update,checking if item is associated
            if(request.getAction() == 2){
                CatalogBO testCatalogBO = subCatalogBusinessObjectBuilder.retrieve(catalogBO);
                if(testCatalogBO.getCatalogParentid() != catalogBO.getCatalogParentid()){
                    if(isItemAssociatedForSubCatalog(catalogBO)){
                        messageList.add(new ErrorMessage("subcatalog.cannot.be.changed"));
                        subCatalogSaveResponse.setSuccess(false);
                        subCatalogSaveResponse.setCatalogBO(catalogBO);
                        subCatalogSaveResponse.setMessages(messageList);
                        return subCatalogSaveResponse;
                    }
                }
                //if catalogParentId's are same
                else{
                    validationRequired = false;
                }
            }
            if(validationRequired){
                 //for insertion,
                //'isSubCatalogDuplicate()',checking if parent Catalog and domain is duplicate
                   
            	//'isSubCatalogNameCatalogDuplicate',checking if subcatalog name and parent catalog is duplicate
            	if ((isSubCatalogDuplicate(catalogBO))|| (isSubCatalogNameCatalogDuplicate(catalogBO))) {
            	    ErrorMessage errorMessage = new ErrorMessage("sub.catalog.duplicate");
	                messageList.add(errorMessage);
	                subCatalogSaveResponse.setSuccess(false);
                     
	            }  
	            else
	            {
	                subCatalogSaveResponse.setSuccess(true);
	            }
            }else{
                subCatalogSaveResponse.setSuccess(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing SubCatalogCreateRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        subCatalogSaveResponse.setCatalogBO(catalogBO);
        subCatalogSaveResponse.setMessages(messageList);
        Logger.logInfo("SubCatalogBusinessValidationService - Returning execute(): Sub-Catalog Create");
    	return subCatalogSaveResponse;
    }
    
    /**
     * Method to check whether items associated to subcatalog
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isItemAssociatedForSubCatalog(CatalogBO catalogBO) throws SevereException {
        SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
        SubCatalogLocateCriteria subCatalogLocateCriteria = new SubCatalogLocateCriteria();
        subCatalogLocateCriteria.setSubCatalogId(catalogBO.getCatalogId());
        LocateResults locateResults = null;
        
        try {
			locateResults = subCatalogBusinessObjectBuilder.locateItemAssociation(subCatalogLocateCriteria);
        } catch (SevereException e) {
            throw new ServiceException(null, null, e);
        }

        
        //if locateResults is not null then 'true' is returned
        if(null != locateResults && null != locateResults.getLocateResults() && !locateResults.getLocateResults().isEmpty()){
            return true;
        }
        //if locateResults is null then 'true' is returned
        return false;
    }


    /**
     * Method to check whether sub-catalog associated to any item before deleting it
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SubCatalogDeleteRequest request)
            throws ServiceException {
        Logger.logInfo("SubCatalogBusinessValidationService - Entering execute():  Catalog Delete");
        SubCatalogDeleteResponse subCatalogDeleteResponse = null;
        CatalogBO catalogBO = getSubCatalogObject(request);
        subCatalogDeleteResponse = (SubCatalogDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_SUB_CATALOG_RESPONSE);
        List messageList = new ArrayList();
        try {
        	//method for checking if item is associated
            if (isItemAssociatedForSubCatalog(catalogBO)) {
                messageList.add(new ErrorMessage("subcatalog.cannot.be.deleted"));
                subCatalogDeleteResponse.setSuccess(false);
            } else {
            	subCatalogDeleteResponse.setSuccess(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        subCatalogDeleteResponse.setMessages(messageList);
        Logger.logInfo("CatalogBenefitBusinessValidationService - Returning execute(): Catalog Delete");
        return subCatalogDeleteResponse;
    }
    /**
     * Method to check business domain is valid for reference data
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(RefDataDomainValidationRequest request)
            throws ServiceException {
    	Logger.logInfo("SubCatalogBusinessValidationService - Entering execute():  RefDataDomainValidation");
        RefDataDomainValidationResponse refDataDomainValidationResponse = (RefDataDomainValidationResponse)WPDResponseFactory.getResponse(WPDResponseFactory.REFERENCE_DOMAIN_VALIDATION_RESPONSE);
        SubCatalogBusinessObjectBuilder builder = new SubCatalogBusinessObjectBuilder();
        ProductBusinessObjectBuilder productBuilder = new ProductBusinessObjectBuilder();
        HashMap locateResults = null;
        SubCatalogVO subCatalogVO = request.getSubCatalogVO();
       
			        try{
			            //Getting the values of domains for the given entityID 
			            subCatalogVO = getBusinessDomains(subCatalogVO);
			            //Setting the retrieved domain values to the request and locatecriteria
			            request.setSubCatalogVO(subCatalogVO);

			            //Getting the values of domainlist and parentcatalog names for retrieving from subcatalog
			            //association table
			            ReferenceDataLocateCriteria referenceDataLocateCriteria = (ReferenceDataLocateCriteria)getReferenceDataLocateCriteria(request);
			            //Get the subcatalog associated items for the given domain values
			            locateResults = getReferenceDataAll(referenceDataLocateCriteria);
			            //Calling the validation method which compares the selected item list and 
			            //list retrieved based on domain values
			            refDataDomainValidationResponse = getValidationResult(request.getSelectedItemMap(),locateResults);
			            
			        }catch(SevereException e){
			            List logParameters = new ArrayList();
			            logParameters.add(request);
			            String logMessage = "Failed while processing ReferenceDataDomainValidationRequest";
			            throw new ServiceException(logMessage, logParameters, e);
			         }
		           
        
       Logger.logInfo("SubCatalogBusinessValidationService - Returning execute():  RefDataDomainValidation");
       return refDataDomainValidationResponse;
    }
    
    /**
     * @param request
     * @param refDataDomainValidationResponse
     * @param subCatalogBusinessObjectBuilder
     * @throws SevereException
     */
    private HashMap getReferenceDataAll(ReferenceDataLocateCriteria referenceDataLocateCriteria) throws SevereException {
        HashMap locateResults;
		
			SubCatalogBusinessObjectBuilder builder = new SubCatalogBusinessObjectBuilder();
		try {
			locateResults = builder.locateRefData(referenceDataLocateCriteria);
		 } catch (SevereException e) {
            throw new ServiceException(null,null, e);
        }

         return locateResults;
    }
    /*
     * Get the business domains based on the entity id and entity type
     */
    private SubCatalogVO getBusinessDomains(SubCatalogVO subCatalogVO) throws SevereException{
        DomainDetail domainList;
		try {
			domainList = (DomainDetail)DomainUtil.retrieveDomainDetailInfo(subCatalogVO.getEntityType(), 
			        subCatalogVO.getEntityId());
		} catch (SevereException e) {
            throw new ServiceException(null,null, e);
        }
		subCatalogVO.setBeList(BusinessUtil.getbusinessEntityList(domainList.getDomainList()));
        subCatalogVO.setBgList(BusinessUtil.getBusinessGroupList(domainList.getDomainList()));
        subCatalogVO.setLobList(BusinessUtil.getLobList(domainList.getDomainList()));
        return subCatalogVO;
    }
   
    /**
     * Method to check if an item is associated with a catalog
     * 
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isItemAssociated(CatalogBO catalogBO)
            throws SevereException {
        SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
        try {
			return subCatalogBusinessObjectBuilder.isItemAssociated(catalogBO);
        } catch (SevereException e) {
            throw new ServiceException(null,null, e);
        }
    }
    
    /**
     * Method to check if checking if parent Catalog and domain is duplicate
     * 
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isSubCatalogDuplicate(CatalogBO catalogBO)
            throws SevereException {
    	 SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
        try {
			return subCatalogBusinessObjectBuilder.isSubCatalogDuplicate(catalogBO);
        } catch (SevereException e) {
            throw new ServiceException(null,null, e);
        }
    }
    
    /**
     * Method to check  if subcatalog name and parent catalog is duplicate
     * 
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isSubCatalogNameCatalogDuplicate(CatalogBO catalogBO)
            throws SevereException {
    	 SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
        try {
			return subCatalogBusinessObjectBuilder.isSubCatalogNameCatalogDuplicate(catalogBO);
        } catch (SevereException e) {
            throw new ServiceException(null,null, e);
        }
    }
    
    /**
     * Method for copying the value object to business object
     * @param request
     * @return CatalogBO
     */
    private CatalogBO getSubCatalogObject(SubCatalogSaveRequest request) {
    	CatalogBO catalogBO = new CatalogBO();
    	CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
    	catalogBO.setCatalogName(catalogVO.getCatalogName());
    	catalogBO.setCatalogParentid(catalogVO.getCatalogParentid());
        List lineOfBusiness = catalogVO.getLobList();
        List businessEntity = catalogVO.getBusinessEntityList();
        List businessGroup = catalogVO.getBusinessGroupList();
        //CARS START
        List marketBusinessUnit = catalogVO.getMarketBusinessUnitList();
        //CARS END
        //if update,then set catalogId to catalogBO
        if(request.getAction() == 2){
            catalogBO.setCatalogId(request.getCatalogVO().getCatalogId());
        }
        catalogBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));
        return catalogBO;
    }
    /**
     * Method for copying the value object to business object
     * @param request
     * @return CatalogBO
     */
    private CatalogBO getSubCatalogObject(SubCatalogDeleteRequest request) {
        CatalogBO catalogBO = new CatalogBO();
        SubCatalogVO subCatalogVO = (SubCatalogVO) request.getSubCatalogVO();
        catalogBO.setCatalogId(subCatalogVO.getSelectedSubCatalogId());
        return catalogBO;

    }

    /**
     * Method for getting the referencedata locate criteria
     * @param request
     * @return ReferenceDataLocateCriteria
     */
     private ReferenceDataLocateCriteria getReferenceDataLocateCriteria(
     		RefDataDomainValidationRequest request) {
     	 SubCatalogVO subCatalogVO = request.getSubCatalogVO();
         // Create an instance of the locate criteria
     	 ReferenceDataLocateCriteria locateCriteria = new ReferenceDataLocateCriteria();
    	 locateCriteria.setLobList(subCatalogVO.getLobList());
    	 locateCriteria.setBeList(subCatalogVO.getBeList());
    	 locateCriteria.setBgList(subCatalogVO.getBgList());
    	 locateCriteria.setParentCatalogList(request.getParentCatalogList());
 	     return locateCriteria;
     }
     /*
      * Method for validating the newly selected list
      * for the new domain values
      * The method skips the validation if selected or general list
      * is empty.
      */
     private RefDataDomainValidationResponse getValidationResult(HashMap selectedMap,HashMap generalMap){
         boolean result = true;
         RefDataDomainValidationResponse response = new RefDataDomainValidationResponse();
         Set keySet = selectedMap.keySet();
         Iterator it1 = keySet.iterator();
         StringBuffer message = new StringBuffer();
         
         while(it1.hasNext()){
             String catalogName = (String)it1.next();
             //Return the values of particular parentcatalog retrieved as key
             List selectedItemList = (List)selectedMap.get(catalogName);
             List generalList = (List)generalMap.get(catalogName);
             //Checks whether the selected item is present in the general retrieved list
             if(null!=selectedItemList && null!=generalList &&!selectedItemList.isEmpty() && !generalList.isEmpty()){
                 
                 for(int i = 0;i < selectedItemList.size();i++){
                     if(null != selectedItemList.get(i)){
                         List primaryCodeList = new ArrayList();
                         for(int j=0;j<generalList.size();j++){
                             SubCatalogBO subCatalogBO = (SubCatalogBO) generalList.get(j);
                             primaryCodeList.add(subCatalogBO.getPrimaryCode());
                         }
                         result = primaryCodeList.contains(selectedItemList.get(i));
                     }
                     //Appending the list of catalogs which failed validation
                     //for message display
                     if(!result){
                          message.append(catalogName.toUpperCase());
                          response.setSuccess(result);
                          response.setErrorMessage(message);
                  	      return response;
                     }
                 }
             }
         }
         response.setSuccess(result);
         response.setErrorMessage(message);
 	    return response;
 	}
}

	 