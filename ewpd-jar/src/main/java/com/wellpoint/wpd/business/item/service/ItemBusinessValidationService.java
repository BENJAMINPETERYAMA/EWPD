/*
 * ItemBusinessValidationService.java
 *  © 2006 WellPoint, Inc. All Rights
 * Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.item.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.item.builder.ItemBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaHCSBO;
import com.wellpoint.wpd.common.item.request.SaveItemRequest;
import com.wellpoint.wpd.common.item.response.SaveItemResponse;
import com.wellpoint.wpd.common.item.vo.ItemVO;
/**
 * Base class for all item business validations
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ItemBusinessValidationService extends WPDService {

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
 * For saving an item checks whether there is any duplicate entries
 * @param request
 * @return SaveItemResponse
 * @throws SevereException
 */

    public WPDResponse execute(SaveItemRequest request)
            throws ServiceException {
        Logger.logInfo("ItemBusinessService - Entering execute(): Item Create");
        SaveItemResponse createItemResponse = null;
        ItemBusinessObjectBuilder itemBusinessObjectBuilder = new ItemBusinessObjectBuilder();
        if("SRDA".equalsIgnoreCase(request.getSrdaFlag())){
        	ItemSrdaBO itemSrdaBO = getItemObjectSrda(request);
        	ItemSrdaHCSBO itemSrdaHCSBO= getItemObjectSrdaHCSCode(request);
            createItemResponse = (SaveItemResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.CREATE_ITEM_RESPONSE);
            try {
            	if(request.isCreateFlag()){
    	            if (itemBusinessObjectBuilder.isItemDuplicate(itemSrdaBO) || itemBusinessObjectBuilder.isItemDuplicate(itemSrdaHCSBO)) {
    	            		createItemResponse.setErrorFlag(true);
    	            }else{
    	            	createItemResponse.setErrorFlag(false);
    	            }	            
            	}else if(!request.isCreateFlag()){
            		createItemResponse.setErrorFlag(false);
            		ItemSrdaBO retreiveItemSrdaBO = new ItemSrdaBO();
            		retreiveItemSrdaBO = getItemObjectSrda(request);
            		retreiveItemSrdaBO = (ItemSrdaBO) itemBusinessObjectBuilder.retrieve(retreiveItemSrdaBO);
            		if(itemSrdaBO.getDescription().equalsIgnoreCase(retreiveItemSrdaBO.getDescription()) && itemSrdaBO.getPrimaryCode().equalsIgnoreCase(retreiveItemSrdaBO.getPrimaryCode())){
            			createItemResponse.setErrorFlag(false);        			
            		}else if(itemBusinessObjectBuilder.isDescriptionDuplicate(itemSrdaBO)){
            			createItemResponse.setErrorFlag(true);
            		}            	
                }else{
                	createItemResponse.setErrorFlag(false);
                }
            } catch (SevereException e) {
                List logParameters = new ArrayList();
                logParameters.add(request);            
            }
            Logger.logInfo("ItemBusinessService - Returning execute(): Item Create");
            createItemResponse.setItemsrdaBO(itemSrdaBO);
            return createItemResponse;
        
        }else{
        	ItemBO itemBO = getItemObject(request);
            createItemResponse = (SaveItemResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.CREATE_ITEM_RESPONSE);
            try {
            	if(request.isCreateFlag()){
    	            if (itemBusinessObjectBuilder.isItemDuplicate(itemBO) || itemBusinessObjectBuilder.isDescriptionDuplicate(itemBO)) {
    	            		createItemResponse.setErrorFlag(true);
    	            }else{
    	            	createItemResponse.setErrorFlag(false);
    	            }	            
            	}else if(!request.isCreateFlag()){
            		createItemResponse.setErrorFlag(false);
            		ItemBO retreiveItemBO = new ItemBO();
            		retreiveItemBO = getItemObject(request);
            		retreiveItemBO = (ItemBO) itemBusinessObjectBuilder.retrieve(retreiveItemBO);
            		if(itemBO.getDescription().equalsIgnoreCase(retreiveItemBO.getDescription()) && itemBO.getPrimaryCode().equalsIgnoreCase(retreiveItemBO.getPrimaryCode())){
            			createItemResponse.setErrorFlag(false);        			
            		}else if(itemBusinessObjectBuilder.isDescriptionDuplicate(itemBO)){
            			createItemResponse.setErrorFlag(true);
            		}            	
                }else{
                	createItemResponse.setErrorFlag(false);
                }
            } catch (SevereException e) {
                List logParameters = new ArrayList();
                logParameters.add(request);            
            }
            Logger.logInfo("ItemBusinessService - Returning execute(): Item Create");
            createItemResponse.setItemBO(itemBO);
            return createItemResponse;
        
        }
        }
/**
 * Copies the value object from request to business object
 * @param request
 * @return ItemBO
 */
    private ItemBO getItemObject(SaveItemRequest request) {
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
        itemBO.setFrequencyRequired(itemVO.getFrequencyRequired());

        return itemBO;
    }

    private ItemSrdaBO getItemObjectSrda(SaveItemRequest request) {
    	ItemSrdaBO itemSrdaBO = new ItemSrdaBO();
        ItemVO itemVO = request.getItemVO();
        itemSrdaBO.setCatalogId(itemVO.getCatalogId());
        itemSrdaBO.setCatalogName(itemVO.getCatalogName());
        itemSrdaBO.setCreatedTimestamp(itemVO.getCreatedTimestamp());
        itemSrdaBO.setCreatedUser(itemVO.getCreatedUser());
        itemSrdaBO.setDescription(itemVO.getDescription());
        itemSrdaBO.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
        itemSrdaBO.setLastUpdatedUser(itemVO.getLastUpdatedUser());
        itemSrdaBO.setPrimaryCode(itemVO.getPrimaryCode());
        itemSrdaBO.setSecondaryCode(itemVO.getSecondaryCode());
        itemSrdaBO.setFrequencyRequired(itemVO.getFrequencyRequired());

        return itemSrdaBO;
    }
    
    private ItemSrdaHCSBO getItemObjectSrdaHCSCode(SaveItemRequest request) {
    	ItemSrdaHCSBO itemSrdaHCSBO = new ItemSrdaHCSBO();
        ItemVO itemVO = request.getItemVO();
        itemSrdaHCSBO.setCatalogName(itemVO.getHcsCode());
        itemSrdaHCSBO.setCreatedTimestamp(itemVO.getCreatedTimestamp());
        itemSrdaHCSBO.setCreatedUser(itemVO.getCreatedUser());
        itemSrdaHCSBO.setDescription(itemVO.getDescription());
        itemSrdaHCSBO.setLastUpdatedTimestamp(itemVO.getLastUpdatedTimestamp());
        itemSrdaHCSBO.setLastUpdatedUser(itemVO.getLastUpdatedUser());
        itemSrdaHCSBO.setPrimaryCode(itemVO.getPrimaryCode());

        return itemSrdaHCSBO;
    }
    
}