/*
 * Created on Jul 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.subcatalog.request;

import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

public class SaveItemAssociationRequest extends WPDRequest{
    private CatalogVO catalogVO;
    
    public void validate() throws ValidationException{}

    
    /**
     * @return Returns the catalogVO.
     * @return CatalogVO catalogVO
     */
    public CatalogVO getCatalogVO() {
        return catalogVO;
    }
    /**
     * Sets the catalogVO
     * @param catalogVO
     */
    public void setCatalogVO(CatalogVO catalogVO) {
        this.catalogVO = catalogVO;
    }
}
