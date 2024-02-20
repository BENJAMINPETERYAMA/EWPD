package com.wellpoint.wpd.common.catalog.response;


import java.util.List;

import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogDeleteResponse extends WPDResponse {
	private CatalogBO catalogBO;
	
	private List catalogList;

	//private DomainDetail domainDetail = null;

	private boolean success;
	
	/**
	 * @return Returns the catalogList.
	 */
	public List getCatalogList() {
		return catalogList;
	}
	/**
	 * @param catalogList The catalogList to set.
	 */
	public void setCatalogList(List catalogList) {
		this.catalogList = catalogList;
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