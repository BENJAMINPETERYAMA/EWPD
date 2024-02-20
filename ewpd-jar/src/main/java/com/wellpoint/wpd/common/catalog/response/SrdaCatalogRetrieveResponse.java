package com.wellpoint.wpd.common.catalog.response;

import com.wellpoint.wpd.common.catalog.bo.SrdaCatalogBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

public class SrdaCatalogRetrieveResponse extends WPDResponse {

	private SrdaCatalogBO srdaCatalogBO;

	private DomainDetail domainDetail = null;

	private boolean success;

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

	/**
	 * @return Returns the standardBenefitBO.
	 */
	public SrdaCatalogBO getSrdaCatalogBO() {
		return srdaCatalogBO;
	}

	/**
	 * @param standardBenefitBO
	 *            The standardBenefitBO to set.
	 */
	public void setSrdaCatalogBO(SrdaCatalogBO srdaCatalogBO) {
		this.srdaCatalogBO = srdaCatalogBO;
	}

	/**
	 * @return Returns the domainDetail.
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	/**
	 * @param domainDetail
	 *            The domainDetail to set.
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}
	/**
	 * @param b
	 */

}
