
package com.wellpoint.wpd.common.refdata.response;

import java.util.List;

import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u13832
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReferenceDataLookupResponse extends WPDResponse {
    private SubCatalogBO subCatalogBO;
    private List subCatalogList;
    private boolean success;
    private String sortOrder;
	private boolean recordsGrtThanMaxSize;
    	/**
	 * @return Returns the subCatalogBO.
	 */
	public SubCatalogBO getSubCatalogBO() {
		return subCatalogBO;
	}
	/**
	 * @param subCatalogBO The subCatalogBO to set.
	 */
	public void setSubCatalogBO(SubCatalogBO subCatalogBO) {
		this.subCatalogBO = subCatalogBO;
	}
	
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
		
	/**
	 * @return Returns the subCatalogList.
	 */
	public List getSubCatalogList() {
		return subCatalogList;
	}
	/**
	 * @param subCatalogList The subCatalogList to set.
	 */
	public void setSubCatalogList(List subCatalogList) {
		this.subCatalogList = subCatalogList;
	}
	/**
	 * @return Returns the sortOrder.
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder The sortOrder to set.
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * @return Returns the recordsGrtThanMaxSize.
	 */
	public boolean isRecordsGrtThanMaxSize() {
		return recordsGrtThanMaxSize;
	}
	/**
	 * @param recordsGrtThanMaxSize The recordsGrtThanMaxSize to set.
	 */
	public void setRecordsGrtThanMaxSize(boolean recordsGrtThanMaxSize) {
		this.recordsGrtThanMaxSize = recordsGrtThanMaxSize;
	}
}
