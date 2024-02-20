package com.wellpoint.wpd.common.subcatalog.request;

import com.wellpoint.wpd.business.subcatalog.locatecriteria.SubCatalogLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubCatalogDeleteRequest extends WPDRequest {

    private SubCatalogVO subCatalogVO;
    private SubCatalogLocateCriteria subCatalogLocateCriteria;

    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    
	
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
	/**
	 * @return Returns the subCatalogLocateCriteria.
	 */
	public SubCatalogLocateCriteria getSubCatalogLocateCriteria() {
		return subCatalogLocateCriteria;
	}
	/**
	 * @param subCatalogLocateCriteria The subCatalogLocateCriteria to set.
	 */
	public void setSubCatalogLocateCriteria(
			SubCatalogLocateCriteria subCatalogLocateCriteria) {
		this.subCatalogLocateCriteria = subCatalogLocateCriteria;
	}

  }