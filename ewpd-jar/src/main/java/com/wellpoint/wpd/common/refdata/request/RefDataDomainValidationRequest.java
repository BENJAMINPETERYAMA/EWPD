/*
 * RefDataDomainValidationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.refdata.request;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RefDataDomainValidationRequest extends WPDRequest {

    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    private SubCatalogVO subCatalogVO;
	
	private List parentCatalogList;
	
	private HashMap selectedItemMap;
	
    /**
     * @return selectedItemMap
     * 
     * Returns the selectedItemMap.
     */
    public HashMap getSelectedItemMap() {
        return selectedItemMap;
    }
    /**
     * @param selectedItemMap
     * 
     * Sets the selectedItemMap.
     */
    public void setSelectedItemMap(HashMap selectedItemMap) {
        this.selectedItemMap = selectedItemMap;
    }
	public static final int PRODUCT=1;
	
		
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
     * @return parentCatalogList
     * 
     * Returns the parentCatalogList.
     */
    public List getParentCatalogList() {
        return parentCatalogList;
    }
    /**
     * @param parentCatalogList
     * 
     * Sets the parentCatalogList.
     */
    public void setParentCatalogList(List parentCatalogList) {
        this.parentCatalogList = parentCatalogList;
    }


}
