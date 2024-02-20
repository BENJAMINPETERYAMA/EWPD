/**
* CatalogBusinessValidationService.java 
*  © 2006 WellPoint, Inc. All Rights Reserved.
* 
* This software is the confidential and proprietary information of Wellpoint
* Inc. ("Confidential Information"). You shall not disclose or use Confidential
* Information without the express written agreement of Wellpoint Inc.
* Created on May 16, 2007
*/
package com.wellpoint.wpd.business.catalog.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.catalog.builder.CatalogBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.request.CatalogDeleteRequest;
import com.wellpoint.wpd.common.catalog.request.EditCatalogRequest;
import com.wellpoint.wpd.common.catalog.request.SaveCatalogRequest;
import com.wellpoint.wpd.common.catalog.response.CatalogDeleteResponse;
import com.wellpoint.wpd.common.catalog.response.EditCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SaveCatalogResponse;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
/**
 * Base class for all catalog business vaidations
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogBusinessValidationService extends
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
     * Method to check whether a duplicate catalog exist before creating a new one
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SaveCatalogRequest request)
            throws ServiceException {
        Logger.logInfo("CatalogBenefitBusinessValidationService - Entering execute():  Catalog Create");
        SaveCatalogResponse saveCatalogResponse = null;
        CatalogBO catalogBO = getCatalogObject(request);
        saveCatalogResponse = (SaveCatalogResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_CATALOG_RESPONSE);
        List messageList = new ArrayList();
        try {
            if (isCatalogNameDuplicate(catalogBO)) {
                messageList.add(new ErrorMessage("catalog.name.duplicate"));
                saveCatalogResponse.setSuccess(false);
            } else {
                saveCatalogResponse.setSuccess(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogCreateRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        saveCatalogResponse.setCatalogBO(catalogBO);
        saveCatalogResponse.setMessages(messageList);
        Logger.logInfo("CatalogBenefitBusinessValidationService - Returning execute(): Catalog Create");
        return saveCatalogResponse;
    }


    /**
     * Method to check whether a duplicate catalog exist before editing a catalog
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
   public WPDResponse execute(EditCatalogRequest request)
            throws ServiceException {
        Logger.logInfo("CatalogBenefitBusinessValidationService - Entering execute():  Catalog Edit");
        EditCatalogResponse editCatalogResponse = null;
        CatalogBO catalogBO = getCatalogObject(request);
        editCatalogResponse = (EditCatalogResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.EDIT_CATALOG_RESPONSE);
        List messageList = new ArrayList();
        try {
            if (isItemAssociated(catalogBO)) {
                editCatalogResponse.setSuccess(false);
            } else {
                editCatalogResponse.setSuccess(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogEditRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        editCatalogResponse.setCatalogBO(catalogBO);
        editCatalogResponse.setMessages(messageList);
        Logger.logInfo("CatalogBenefitBusinessValidationService - Returning execute(): Catalog Edit");
        return editCatalogResponse;
    }


    /**
     * Method to check whether catalog associated to any item before deleting it
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(CatalogDeleteRequest request)
            throws ServiceException {
        Logger.logInfo("CatalogBenefitBusinessValidationService - Entering execute():  Catalog Delete");
        CatalogDeleteResponse catalogDeleteResponse = null;
        CatalogBO catalogBO = getCatalogObject(request);
        catalogDeleteResponse = (CatalogDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_CATALOG_RESPONSE);
        List messageList = new ArrayList();
        try {
        	//checking if item or subcatalog is associated
            if (isItemAssociated(catalogBO) || isSubCatalogAssocaited(catalogBO)) {
                messageList.add(new ErrorMessage("catalog.item.associated"));
                catalogDeleteResponse.setSuccess(false);
            } else {
                catalogDeleteResponse.setSuccess(true);
            }
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        catalogDeleteResponse.setMessages(messageList);
        Logger.logInfo("CatalogBenefitBusinessValidationService - Returning execute(): Catalog Delete");
        return catalogDeleteResponse;
    }


    /**
     * @param catalogBO
     * @return
     * @throws SevereException
     */
    private boolean isSubCatalogAssocaited(CatalogBO catalogBO) throws SevereException {
        // Create an instance of the builder
        try {
			CatalogBusinessObjectBuilder builder = new CatalogBusinessObjectBuilder();
			return builder.isCatalogAssociated(catalogBO);
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            String logMessage = "Failed while processing CatalogDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
    }


    /**
     * Method to check if the catalog name is duplicate
     * 
     * @param catalogBO
     * @return boolean
     * @throws SevereException
     */
    private boolean isCatalogNameDuplicate(CatalogBO catalogBO)
            throws SevereException {
        CatalogBusinessObjectBuilder catalogBusinessObjectBuilder = new CatalogBusinessObjectBuilder();
        try {
			return catalogBusinessObjectBuilder.isNameDuplicate(catalogBO);
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            String logMessage = "Failed while processing CatalogDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
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
        CatalogBusinessObjectBuilder catalogBusinessObjectBuilder = new CatalogBusinessObjectBuilder();
        try {
			return catalogBusinessObjectBuilder.isItemAssociated(catalogBO);
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            String logMessage = "Failed while processing CatalogDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
    }


    private CatalogBO getCatalogObject(CatalogDeleteRequest request) {
        CatalogBO catalogBO = new CatalogBO();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        catalogBO.setCatalogId(catalogVO.getCatalogId());
        return catalogBO;
    }


    private CatalogBO getCatalogObject(EditCatalogRequest request) {
        CatalogBO catalogBO = new CatalogBO();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        catalogBO.setCatalogId(catalogVO.getCatalogId());
        catalogBO.setCatalogDatatype(catalogVO.getCatalogDatatype());
        catalogBO.setCatalogSize(catalogVO.getCatalogSize());
        return catalogBO;
    }


    private CatalogBO getCatalogObject(SaveCatalogRequest request) {
        CatalogBO catalogBO = new CatalogBO();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        String name = catalogVO.getCatalogName().trim();
        StringBuffer nameBuffer = new StringBuffer();
		String[] nameArray = name.split(" ");
		for(int i = 0; i < nameArray.length; i++){
			if(null != nameArray[i] && !"".equals(nameArray[i])){
				nameBuffer.append(nameArray[i]);
				nameBuffer.append(" ");
			}
		}
        catalogBO.setCatalogName((nameBuffer).toString().trim());
        return catalogBO;
    }

}