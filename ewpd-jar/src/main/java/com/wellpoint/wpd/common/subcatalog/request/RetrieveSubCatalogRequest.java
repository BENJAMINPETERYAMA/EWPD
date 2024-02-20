/*
 * RetrieveUbCatalogRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.subcatalog.request;

import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveSubCatalogRequest extends WPDRequest {

    private CatalogVO catalogVO;
    
    private SubCatalogVO subCatalogVO;
    
    private int action;
    
    /**
     * @return catalogVO
     * Returns the catalogVO.
     */
    public CatalogVO getCatalogVO() {
        return catalogVO;
    }
    /**
     * @param catalogVO
     * Sets the catalogVO.
     */
    public void setCatalogVO(CatalogVO catalogVO) {
        this.catalogVO = catalogVO;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * @return Returns the action.
     * @return int action
     */
    public int getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param action
     */
    public void setAction(int action) {
        this.action = action;
    }
    
    
    /**
     * @return Returns the subCatalogVO.
     * @return SubCatalogVO subCatalogVO
     */
    public SubCatalogVO getSubCatalogVO() {
        return subCatalogVO;
    }
    /**
     * Sets the subCatalogVO
     * @param subCatalogVO
     */
    public void setSubCatalogVO(SubCatalogVO subCatalogVO) {
        this.subCatalogVO = subCatalogVO;
    }
}
