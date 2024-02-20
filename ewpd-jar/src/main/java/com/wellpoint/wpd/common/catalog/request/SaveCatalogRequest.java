
package com.wellpoint.wpd.common.catalog.request;


import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveCatalogRequest extends WPDRequest {

    private CatalogVO catalogVO;
    private int action;
    
 
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
    	
        if (this.getCatalogVO().getCatalogName() == null || "".equals(this.getCatalogVO().getCatalogName())) {
        	throw new ValidationException("Catalog Name is Missing", null, null);
        }
       if (this.getCatalogVO().getCatalogDatatype()== null || "".equals(this.getCatalogVO().getCatalogDatatype())) {
       	throw new ValidationException("Catalog DataType is Missing", null, null);
        }
        if (new Integer(this.getCatalogVO().getCatalogSize()).toString()== null || "".equals(new Integer(this.getCatalogVO().getCatalogSize()).toString())) {
        	throw new ValidationException("Catalog Size is Missing", null, null);
        }
        

    }
    
    public CatalogVO getCatalogVO() {
        return catalogVO;
    }
   
    public void setCatalogVO(CatalogVO catalogVO) {
        this.catalogVO = catalogVO;
    }
	
	public int getAction() {
		return action;
	}
	
	public void setAction(int action) {
		this.action = action;
	}
	
}
