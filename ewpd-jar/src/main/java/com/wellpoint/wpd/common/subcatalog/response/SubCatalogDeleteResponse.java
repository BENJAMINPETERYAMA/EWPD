package com.wellpoint.wpd.common.subcatalog.response;


import java.util.List;
//import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubCatalogDeleteResponse extends WPDResponse {
	private CatalogBO catalogBO;
	
	private List subCatalogList;

	//private DomainDetail domainDetail = null;

	private boolean success;
	
	
	
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
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public CatalogBO getCatalogBO() {
		return catalogBO;
	}

	public void setCatalogBO(CatalogBO catalogBO) {
		this.catalogBO = catalogBO;
	}

	/*public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	*//**
	 * @param domainDetail
	 *            The domainDetail to set.
	 *//*
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}*/

}