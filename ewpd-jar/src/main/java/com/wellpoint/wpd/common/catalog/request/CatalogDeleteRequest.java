
package com.wellpoint.wpd.common.catalog.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.catalog.vo.CatalogLocateCriteriaVO;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogDeleteRequest extends WPDRequest {

    private CatalogVO catalogVO;
    private CatalogLocateCriteriaVO catalogLocateCriteriaVO;
    private int action;
 
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    
    /**
	 * @return Returns the catalogLocateCriteriaVO.
	 */
	public CatalogLocateCriteriaVO getCatalogLocateCriteriaVO() {
		return catalogLocateCriteriaVO;
	}
	/**
	 * @param catalogLocateCriteriaVO The catalogLocateCriteriaVO to set.
	 */
	public void setCatalogLocateCriteriaVO(
			CatalogLocateCriteriaVO catalogLocateCriteriaVO) {
		this.catalogLocateCriteriaVO = catalogLocateCriteriaVO;
	}
	
    public CatalogVO getCatalogVO() {
        return catalogVO;
    }
   
    public void setCatalogVO(CatalogVO catalogVO) {
        this.catalogVO = catalogVO;
    }
	
}
