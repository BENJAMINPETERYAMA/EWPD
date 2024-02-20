
package com.wellpoint.wpd.common.catalog.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveCatalogResponse extends WPDResponse {
    private CatalogBO catalogBO;
	private DomainDetail domainDetail = null;
	private boolean success;
    
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
	
    public CatalogBO getCatalogBO() {
        return catalogBO;
    }
  
    public void setCatalogBO(CatalogBO catalogBO) {
        this.catalogBO = catalogBO;
    }
    
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
}
