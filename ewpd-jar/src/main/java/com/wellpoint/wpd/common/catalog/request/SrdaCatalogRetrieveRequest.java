package com.wellpoint.wpd.common.catalog.request;

import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

public class SrdaCatalogRetrieveRequest  extends WPDRequest{

	private CatalogVO catalogVO;
	   
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
      
    public CatalogVO getCatalogVO() {
        return catalogVO;
    }
   
    public void setCatalogVO(CatalogVO catalogVO) {
        this.catalogVO = catalogVO;
    }
}
