package com.wellpoint.wpd.common.subcatalog.response;

import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubCatalogSaveResponse extends WPDResponse
{
	 private CatalogBO catalogBO;
     private DomainDetail domainDetail = null;
	 private boolean success;
	/**
	 * @return Returns the domainDetail.
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}
	/**
	 * @param domainDetail The domainDetail to set.
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}
	/**
	 * @return Returns the CatalogBO.
	 */
	public CatalogBO getCatalogBO() {
		return catalogBO;
	}
	/**
	 * @param CatalogBO The CatalogBO to set.
	 */
	public void setCatalogBO(CatalogBO catalogBO) {
		this.catalogBO = catalogBO;
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
}