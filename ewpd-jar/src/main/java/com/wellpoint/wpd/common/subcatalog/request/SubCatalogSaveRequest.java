package com.wellpoint.wpd.common.subcatalog.request;

import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


public class SubCatalogSaveRequest extends WPDRequest {
	 private CatalogVO catalogVO;
	 
	 private int action;
	
	 public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
	
    /**
     * @return action
     * Returns the action.
     */
    public int getAction() {
        return action;
    }
    /**
     * @param action
     * Sets the action.
     */
    public void setAction(int action) {
        this.action = action;
    }
	/**
	 * @return Returns the CatalogVO.
	 */
	public CatalogVO getCatalogVO() {
		return catalogVO;
	}
	/**
	 * @param CatalogVO The CatalogVO to set.
	 */
	public void setCatalogVO(CatalogVO catalogVO) {
		this.catalogVO = catalogVO;
	}
}