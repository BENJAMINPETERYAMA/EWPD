/*
 * ItemBusinessService.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.item.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.item.builder.ItemBusinessObjectBuilder;
import com.wellpoint.wpd.business.item.locatecriteria.ItemLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.item.bo.ItemFrequencyBO;
import com.wellpoint.wpd.common.item.bo.ItemSoftDeleteBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaHCSBO;
import com.wellpoint.wpd.common.item.request.CatalogLookUpRequest;
import com.wellpoint.wpd.common.item.request.ItemSoftDeleteRequest;
import com.wellpoint.wpd.common.item.request.RetrieveItemRequest;
import com.wellpoint.wpd.common.item.request.SaveItemRequest;
import com.wellpoint.wpd.common.item.request.SearchItemRequest;
import com.wellpoint.wpd.common.item.response.CatalogLookUpResponse;
import com.wellpoint.wpd.common.item.response.ItemSoftDeleteResponse;
import com.wellpoint.wpd.common.item.response.RetrieveItemResponse;
import com.wellpoint.wpd.common.item.response.SaveItemResponse;
import com.wellpoint.wpd.common.item.response.SearchItemResponse;
import com.wellpoint.wpd.common.item.vo.ItemLocateCriteriaVO;
import com.wellpoint.wpd.common.item.vo.ItemVO;
/**
 * Base class for item business service
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ItemBusinessService extends WPDService {

    public ItemBusinessService() {
    }
    /**
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }

    /**
     * Persists or updates the item
     * @param saveItemRequest
     * @return saveItemResponse
     * @throws AdapterException
     * @throws ServiceException
     */
    public WPDResponse execute(SaveItemRequest saveItemRequest)
            throws ServiceException {
        Logger.logInfo("ItemBusinessService - Entering execute(): Item Save");
        SaveItemResponse saveItemResponse = (SaveItemResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CREATE_ITEM_RESPONSE);
        List messages = new ArrayList();
        ItemFrequencyBO itemFrequencyBO = null;
        boolean flag = false;
        int catalogId;
        try {
            saveItemResponse = (SaveItemResponse) new ValidationServiceController().execute(saveItemRequest);
            if (!saveItemResponse.isErrorFlag()) {
            if("SRDA".equalsIgnoreCase(saveItemRequest.getSrdaFlag())){
                ItemSrdaBO itemsrdaBO = copyBusinessObjectFromValueObjectSrda(saveItemRequest
                        .getItemVO());
                ItemSrdaHCSBO itemsrdaHCSBO = copyBusinessObjectFromValueObjectHCSSrda(saveItemRequest
                        .getItemVO());
              
                ItemBusinessObjectBuilder builder = new ItemBusinessObjectBuilder();                
                if (saveItemRequest.isCreateFlag()) {
                    
                	if(null!=saveItemRequest.getItemVO().getHcsCode() && !saveItemRequest.getItemVO().getHcsCode().isEmpty()){
                		builder.persist(itemsrdaHCSBO, saveItemRequest.getUser(), true);
                		itemsrdaHCSBO = (ItemSrdaHCSBO) builder.retrieve(itemsrdaHCSBO);
                		saveItemResponse.setItemSrdaHCSBO(itemsrdaHCSBO);
                	}else{	
                		builder.persist(itemsrdaBO, saveItemRequest.getUser(), true);
                		itemsrdaBO = (ItemSrdaBO) builder.retrieve(itemsrdaBO);
                		saveItemResponse.setItemsrdaBO(itemsrdaBO);
                		
                	}
                
                    
                    messages.add(new InformationalMessage(
                            "item.save.success.info"));
                }
                if (!saveItemRequest.isCreateFlag()) {
                	if(null!=saveItemRequest.getItemVO().getHcsCode() && !saveItemRequest.getItemVO().getHcsCode().isEmpty()){
                		builder.persist(itemsrdaHCSBO, saveItemRequest.getUser(), false);
                		itemsrdaHCSBO = (ItemSrdaHCSBO) builder.retrieve(itemsrdaHCSBO);
                		saveItemResponse.setItemSrdaHCSBO(itemsrdaHCSBO);
                	}else{
                		builder.persist(itemsrdaBO, saveItemRequest.getUser(), false);
                		itemsrdaBO = (ItemSrdaBO) builder.retrieve(itemsrdaBO);
                		 saveItemResponse.setItemsrdaBO(itemsrdaBO);
                	}
                   
                   
                    messages.add(new InformationalMessage(
                            "item.update.success.info"));
                }
            }
            	else{
                ItemBO itemBO = copyBusinessObjectFromValueObject(saveItemRequest
                        .getItemVO());
                //CARS START
                //DESCRIPTION : Sets the catalog Id and checks it for qualifier.
                catalogId = itemBO.getCatalogId();
                if (catalogId == BusinessConstants.QUALIFIER_CODE) {
					itemFrequencyBO = copyBusinessObjectFromValueObject(itemBO);
				}//CARS END
                ItemBusinessObjectBuilder builder = new ItemBusinessObjectBuilder();                
                if (saveItemRequest.isCreateFlag()) {
                    
                	flag = builder.persist(itemBO, saveItemRequest.getUser(), true);
                	if (flag
							&& (BusinessConstants.QUALIFIER_CODE == itemBO
									.getCatalogId())) {
						builder.persistFrequencyInfo(itemFrequencyBO,
								saveItemRequest.getUser(), true);
					}
                    itemBO = (ItemBO) builder.retrieve(itemBO);
                    //CARS START
                    //DESCRIPTION : Checks catalog id equals qualifier code. 
                    if (catalogId == BusinessConstants.QUALIFIER_CODE) {
						itemFrequencyBO = builder
								.retrieveFrequencyInfo(itemFrequencyBO);
						itemBO.setFrequencyRequired(itemFrequencyBO
								.getFrequencyRequired());
					}
                    //CARS END
                    saveItemResponse.setItemBO(itemBO);
                    messages.add(new InformationalMessage(
                            "item.save.success.info"));
                }
                if (!saveItemRequest.isCreateFlag()) {
                	flag = builder.persist(itemBO, saveItemRequest.getUser(), false);
                	if (flag
							&& (BusinessConstants.QUALIFIER_CODE == itemBO
									.getCatalogId())) {
						builder.persistFrequencyInfo(itemFrequencyBO,
								saveItemRequest.getUser(), false);
					}
                     itemBO = (ItemBO) builder.retrieve(itemBO);
                    //CARS START
                    //DESCRIPTION : Checks catalog id equals qualifier code. 
                    if (catalogId == BusinessConstants.QUALIFIER_CODE) {
						itemFrequencyBO = builder
								.retrieveFrequencyInfo(itemFrequencyBO);
						itemBO.setFrequencyRequired(itemFrequencyBO
								.getFrequencyRequired());
					}
                    //CARS END
                    saveItemResponse.setItemBO(itemBO);
                    messages.add(new InformationalMessage(
                            "item.update.success.info"));
                }
       }
}
             else {
                messages.add(new ErrorMessage("item.already.exists"));
            }
            saveItemResponse.setMessages(messages);
        } catch (SevereException ex) {
            Logger.logInfo(ex.getMessage());
            List logParameters = new ArrayList();
            logParameters.add(saveItemRequest);
            String logMessage = "Failed while processing SaveItemRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger.logInfo("ItemBusinessService - Returning execute(): Item Save");
        return saveItemResponse;
    }

    private ItemSrdaHCSBO copyBusinessObjectFromValueObjectHCSSrda(ItemVO itemVO) {
    	ItemSrdaHCSBO itemSrdaHCSBO = new ItemSrdaHCSBO();
    	itemSrdaHCSBO.setCatalogName(itemVO.getHcsCode());
    	itemSrdaHCSBO.setCreatedTimestamp(itemVO.getCreatedTimestamp());
    	itemSrdaHCSBO.setCreatedUser(itemVO.getCreatedUser());
    	itemSrdaHCSBO.setDescription(itemVO.getDescription());
    	itemSrdaHCSBO.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
    	itemSrdaHCSBO.setPrimaryCode(itemVO.getPrimaryCode());
    	itemSrdaHCSBO.setLastUpdatedUser(itemVO.getLastUpdatedUser());
        return itemSrdaHCSBO;
    }
	/**
     * Search for the items
     * 
     * @param itemRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SearchItemRequest itemRequest)
            throws ServiceException {
        Logger.logInfo("ItemBusinessService - Entering execute(): Item Retrieve");
        List locateResultList = null;
        int locateResultCount = 0;
        List errorList = new ArrayList();
        SearchItemResponse response = null;
        ItemLocateCriteriaVO itemLocateCriteriaVO = itemRequest.getCriteriaVO();
        ItemLocateCriteria itemLocateCriteria = getItemLocateCriteriaBO(itemLocateCriteriaVO);

        response = (SearchItemResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SEARCH_ITEM_RESPONSE);
        try {
            ItemBusinessObjectBuilder builder = new ItemBusinessObjectBuilder();
            LocateResults locateResults = builder.locate(itemLocateCriteria,
                    itemRequest.getUser());
            if (null != locateResults) {
                locateResultList = locateResults.getLocateResults();
                locateResultCount = locateResultList.size();
                if (locateResultCount > 0 ) {
                    response.setItemList(locateResults.getLocateResults());
                } else if (locateResultCount == 0) {
                    errorList.add(new InformationalMessage(
                            BusinessConstants.SEARCH_RESULT_NOT_FOUND));
                    response.setMessages(errorList);

                }
            }
        } catch (SevereException ex) {
            List logParameters = new ArrayList();
            logParameters.add(itemRequest);
            String logMessage = "Failed while processing SearchItemRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger.logInfo("ItemBusinessService - Returning execute(): Item Retrieve");
        return response;

    }

    /**
     * Sets the values from Locate Criteria VO to BO
     * 
     * @param itemLocateCriteriaVO
     * @return itemLocateCriteria
     */
    private ItemLocateCriteria getItemLocateCriteriaBO(
            ItemLocateCriteriaVO itemLocateCriteriaVO) {
        ItemLocateCriteria itemLocateCriteria = new ItemLocateCriteria();
        itemLocateCriteria
                .setPrimaryCode(itemLocateCriteriaVO.getPrimaryCode());
        itemLocateCriteria.setSecondaryCode(itemLocateCriteriaVO
                .getSecondaryCode());
        itemLocateCriteria.setCatalogIdList(itemLocateCriteriaVO.getCatalogIdList());
        itemLocateCriteria.setSrdaFlag(itemLocateCriteriaVO.getSrdaFlag());
        itemLocateCriteria.setDescription(itemLocateCriteriaVO.getDescription());
        return itemLocateCriteria;
    }

   

    /**
     * Retrieves the list of catalog
     * 
     * @param request
     * @return catalogLookupResponse
     * @throws ServiceException
     */

    public WPDResponse execute(CatalogLookUpRequest request)
            throws ServiceException {
        Logger.logInfo("ItemBusinessService - Entering execute(): Catalog Retrieve");
        CatalogLookUpResponse response = (CatalogLookUpResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.CATALOG_LOOKUP_RESPONSE);
        ItemBusinessObjectBuilder builder = new ItemBusinessObjectBuilder();
        List catalog = null;
        try {
            catalog = builder.retrieve(request.getSrdaFlag());
        } catch (SevereException ex) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            throw new ServiceException("Adapter exception", null, ex);
        }
        Logger.logInfo("ItemBusinessService - Returning execute(): Catalog LookUp");
        response.setCatalogList(catalog);
        return response;
    }

    /**
     * Retrieves the ItemBO
     * 
     * @param request
     * @return retrieveItemResponse
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveItemRequest request)
            throws ServiceException {
        Logger.logInfo("ItemBusinessService - Entering execute(): Item Retrieve");
 
        ItemBusinessObjectBuilder builder = new ItemBusinessObjectBuilder();
 ItemBO itemBO = null;
        ItemSrdaBO itemSrdaBo=null;
        ItemSrdaHCSBO itemSrdaHCSBo=null;
        if("SRDA".equalsIgnoreCase(request.getSrdaFlag())){
        	if(null != request.getHcsCode()){
        		itemSrdaHCSBo = getItemSrdaHCSObject(request);
        	}else{
        		itemSrdaBo = getItemSrdaObject(request);
        	}
        	 
        }else{
        	 itemBO = getItemObject(request);
        }
        RetrieveItemResponse response = (RetrieveItemResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_ITEM_REPONSE);
        try {
          // itemSrdaBO= builder.retrieve(itemSrdaBO);
            //CARS START
        	if("SRDA".equalsIgnoreCase(request.getSrdaFlag())){
        		if(null != request.getHcsCode()){
        			itemSrdaHCSBo = (ItemSrdaHCSBO) builder.retrieve(itemSrdaHCSBo);
        			response.setItemsrdaHCSBO(itemSrdaHCSBo);
        		}else{
        			itemSrdaBo = (ItemSrdaBO) builder.retrieve(itemSrdaBo);
        			response.setItemsrdaBO(itemSrdaBo);
        		}
            }
            else{
            	 itemBO = (ItemBO) builder.retrieve(itemBO);

            if((BusinessConstants.QUALIFIER).equalsIgnoreCase(itemBO.getCatalogName())){
            	ItemFrequencyBO itemFrequencyBO = new ItemFrequencyBO();
            	itemFrequencyBO = copyBusinessObjectFromValueObject(itemBO);
            	builder.retrieveFrequencyInfo(itemFrequencyBO);
            	itemBO.setFrequencyRequired(itemFrequencyBO.getFrequencyRequired());
}
            response.setItemBO(itemBO);
            }
            //CARS END
        } catch (SevereException ex) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            throw new ServiceException("AdapterException", null, ex);
        }
        Logger.logInfo("ItemBusinessService - Returning execute(): Item Retrieve");
        
        return response;
    }
    
    
private ItemFrequencyBO copyBusinessObjectFromValueObject(
			ItemSrdaBO itemBO) {
    	   ItemFrequencyBO itemFrequencyBO = new ItemFrequencyBO();
           itemFrequencyBO.setCatalogId(itemBO.getCatalogId());
           itemFrequencyBO.setPrimaryCode(itemBO.getPrimaryCode());
           itemFrequencyBO.setFrequencyRequired(itemBO.getFrequencyRequired());
           return itemFrequencyBO;
	//	return null;
	}
	private ItemSrdaBO getItemSrdaObject(RetrieveItemRequest request) {
		// TODO Auto-generated method stub
    	ItemSrdaBO itemSrdaBo = new ItemSrdaBO();

        ItemVO itemVO = request.getItemVO();
        itemSrdaBo.setCatalogId(itemVO.getCatalogId());
        itemSrdaBo.setCatalogName(itemVO.getCatalogName());
        itemSrdaBo.setCreatedTimestamp(itemVO.getCreatedTimestamp());
        itemSrdaBo.setCreatedUser(itemVO.getCreatedUser());
        itemSrdaBo.setDescription(itemVO.getDescription());
        itemSrdaBo.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
        itemSrdaBo.setLastUpdatedUser(itemVO.getLastUpdatedUser());
        itemSrdaBo.setPrimaryCode(itemVO.getPrimaryCode());
        itemSrdaBo.setSecondaryCode(itemVO.getSecondaryCode());

        return itemSrdaBo;
		
	}
	
	private ItemSrdaHCSBO getItemSrdaHCSObject(RetrieveItemRequest request) {
		// TODO Auto-generated method stub
    	ItemSrdaHCSBO itemSrdaHCSBo = new ItemSrdaHCSBO();

        ItemVO itemVO = request.getItemVO();
        itemSrdaHCSBo.setCatalogName(request.getHcsCode());
        itemSrdaHCSBo.setCreatedTimestamp(itemVO.getCreatedTimestamp());
        itemSrdaHCSBo.setCreatedUser(itemVO.getCreatedUser());
        itemSrdaHCSBo.setDescription(itemVO.getDescription());
        itemSrdaHCSBo.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
        itemSrdaHCSBo.setLastUpdatedUser(itemVO.getLastUpdatedUser());
        itemSrdaHCSBo.setPrimaryCode(itemVO.getPrimaryCode());
        return itemSrdaHCSBo;
		
	}
    /**
     * Method to update the item delete status flag for SoftDelete.
     * Used by DataFeed module. 
     * @param request
     * @return DeleteStatusEditResponse
     * @throws ServiceException
     */
    public WPDResponse execute(ItemSoftDeleteRequest request) throws ServiceException{
        Logger.logInfo("ItemBusinessService - Entering execute(): DeleteStatusEdit");
        
        // Create an instance of the builder
        ItemBusinessObjectBuilder builder = new ItemBusinessObjectBuilder();
        
        // Get the Response object from the WPDResponse Factory 
        ItemSoftDeleteResponse response = (ItemSoftDeleteResponse) WPDResponseFactory.
       			getResponse(WPDResponseFactory.DELETE_STATUS_EDIT_RESPONSE);
        
        // Set the values from the VO in request to the BO
        ItemSoftDeleteBO itemStatusBO = getItemStatusBO(request);
        
        try{
            // Call the update method in the builder
            builder.persist(itemStatusBO, request.getUser());
        }catch(SevereException ex){
            List logParameters = new ArrayList();
            logParameters.add(request);
            throw new ServiceException("AdapterException", null, ex);
        }
        Logger.logInfo("ItemBusinessService - Returning execute(): DeleteStatusEdit");
        // Return the response
        return response;
    }


    /**
     * Method to set the values of status, and item keys for
     * soft delete functionality of item
     * 
     * @param request
     * @return ItemStatusBO
     */
    private ItemSoftDeleteBO getItemStatusBO(ItemSoftDeleteRequest request) {
        // Create an instance of the BO
        ItemSoftDeleteBO itemStatusBO = new ItemSoftDeleteBO();
        
        ItemVO itemVO = request.getItemVO();
        
        // Set the values from the request to the BO
        itemStatusBO.setCatalogId(itemVO.getCatalogId());
        itemStatusBO.setPrimaryCode(itemVO.getPrimaryCode());
        itemStatusBO.setStatus(itemVO.getStatus());
        
        // Return the BO
        return itemStatusBO;
    }

    /**
     * Copies the value object to the business object
     * 
     * @param itemVO
     * @return itemBO
     */
    private ItemBO copyBusinessObjectFromValueObject(ItemVO itemVO) {

        ItemBO itemBO = new ItemBO();
        itemBO.setCatalogId(itemVO.getCatalogId());
        itemBO.setCreatedTimestamp(itemVO.getCreatedTimestamp());
        itemBO.setCreatedUser(itemVO.getCreatedUser());
        itemBO.setDescription(itemVO.getDescription());
        itemBO.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
        itemBO.setPrimaryCode(itemVO.getPrimaryCode());
        itemBO.setSecondaryCode(itemVO.getSecondaryCode());
        itemBO.setLastUpdatedUser(itemVO.getLastUpdatedUser());
        itemBO.setCatalogName(itemVO.getCatalogName());
        itemBO.setFrequencyRequired(itemVO.getFrequencyRequired());
        return itemBO;
    }
 private ItemSrdaBO copyBusinessObjectFromValueObjectSrda(ItemVO itemVO) {
    	ItemSrdaBO itemSrdaBO = new ItemSrdaBO();
    	itemSrdaBO.setCatalogId(itemVO.getCatalogId());
    	itemSrdaBO.setCreatedTimestamp(itemVO.getCreatedTimestamp());
    	itemSrdaBO.setCreatedUser(itemVO.getCreatedUser());
    	itemSrdaBO.setDescription(itemVO.getDescription());
    	itemSrdaBO.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
    	itemSrdaBO.setPrimaryCode(itemVO.getPrimaryCode());
    	itemSrdaBO.setSecondaryCode(itemVO.getSecondaryCode());
    	itemSrdaBO.setLastUpdatedUser(itemVO.getLastUpdatedUser());
    	itemSrdaBO.setCatalogName(itemVO.getCatalogName());
        return itemSrdaBO;
    }
    //CARS START
    /**
     * Copies the value object to the business object
     * 
     * @param itemVO
     * @return itemBO
     */
    private ItemFrequencyBO copyBusinessObjectFromValueObject(ItemBO itemBO) {
    	
        ItemFrequencyBO itemFrequencyBO = new ItemFrequencyBO();
        itemFrequencyBO.setCatalogId(itemBO.getCatalogId());
        itemFrequencyBO.setPrimaryCode(itemBO.getPrimaryCode());
        itemFrequencyBO.setFrequencyRequired(itemBO.getFrequencyRequired());
        return itemFrequencyBO;
    }
    //CARS END
    /**
     * Sets the value from request to business object
     * 
     * @param request
     * @return itemBO
     */

    private ItemBO getItemObject(RetrieveItemRequest request) {
        ItemBO itemBO = new ItemBO();

        ItemVO itemVO = request.getItemVO();
        itemBO.setCatalogId(itemVO.getCatalogId());
        itemBO.setCatalogName(itemVO.getCatalogName());
        itemBO.setCreatedTimestamp(itemVO.getCreatedTimestamp());
        itemBO.setCreatedUser(itemVO.getCreatedUser());
        itemBO.setDescription(itemVO.getDescription());
        itemBO.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
        itemBO.setLastUpdatedUser(itemVO.getLastUpdatedUser());
        itemBO.setPrimaryCode(itemVO.getPrimaryCode());
        itemBO.setSecondaryCode(itemVO.getSecondaryCode());

        return itemBO;
    }

    

}