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

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubCatalogSearchRequest extends WPDRequest {

	private SubCatalogVO subCatalogVO;
	
	/**
	 * @return Returns the subCatalogVO.
	 */
	public SubCatalogVO getSubCatalogVO() {
		return subCatalogVO;
	}
	/**
	 * @param subCatalogVO The subCatalogVO to set.
	 */
	public void setSubCatalogVO(SubCatalogVO subCatalogVO) {
		this.subCatalogVO = subCatalogVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}
