/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.AdminBO;


/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveContractProductAdminResponse extends WPDResponse{

	private List adminList = null;
	
	private DomainDetail domainDetail ;
    
	/**
	 * @return Returns the adminDetails.
	 */
	public AdminBO getAdminDetails() {
		return adminDetails;
	}
	/**
	 * @param adminDetails The adminDetails to set.
	 */
	public void setAdminDetails(AdminBO adminDetails) {
		this.adminDetails = adminDetails;
	}
	/**
	 * @return Returns the adminList.
	 */
	public List getAdminList() {
		return adminList;
	}
	/**
	 * @param adminList The adminList to set.
	 */
	public void setAdminList(List adminList) {
		this.adminList = adminList;
	}
    private AdminBO adminDetails;
	
    
    
    /**
     * Returns the domainDetail
     * @return DomainDetail domainDetail.
     */
    public DomainDetail getDomainDetail() {
        return domainDetail;
    }
    /**
     * Sets the domainDetail
     * @param domainDetail.
     */
    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
	
	
}
