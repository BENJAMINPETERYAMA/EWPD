/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.subcatalog.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;


public class ItemAssociationDeleteRequest extends WPDRequest{
    
    public void validate() throws ValidationException{}
    private SubCatalogVO subCatalogVO;
    
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
