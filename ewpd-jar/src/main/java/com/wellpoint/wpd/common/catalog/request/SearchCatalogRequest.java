/*
 * Created on May 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.catalog.request;

import com.wellpoint.wpd.common.catalog.vo.CatalogLocateCriteriaVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchCatalogRequest extends WPDRequest{
    private CatalogLocateCriteriaVO catalogLocateCriteriaVO;

    /*
     * validate
     */
    public void validate() throws ValidationException {
    }
    /**
     * Returns the catalogLocateCriteriaVO
     * @return CatalogLocateCriteriaVO catalogLocateCriteriaVO.
     */

    public CatalogLocateCriteriaVO getCatalogLocateCriteriaVO() {
        return catalogLocateCriteriaVO;
    }
    /**
     * Sets the catalogLocateCriteriaVO
     * @param catalogLocateCriteriaVO.
     */

    public void setCatalogLocateCriteriaVO(
            CatalogLocateCriteriaVO catalogLocateCriteriaVO) {
        this.catalogLocateCriteriaVO = catalogLocateCriteriaVO;
    }
}