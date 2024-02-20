/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductBenefitComponentResponse extends WPDResponse{

	private List benefitComponentList = null;
	
	private DomainDetail domainDetail ;
    
    private BenefitComponentBO benefitComponentDetails;
    
    private List benefitList = null;
    
    private BenefitBO benefitDetails;
    
	
    
    /**
     * Returns the benefitComponentDetails
     * @return BenefitComponentBO benefitComponentDetails.
     */
    public BenefitComponentBO getBenefitComponentDetails() {
        return benefitComponentDetails;
    }
    /**
     * Sets the benefitComponentDetails
     * @param benefitComponentDetails.
     */
    public void setBenefitComponentDetails(
            BenefitComponentBO benefitComponentDetails) {
        this.benefitComponentDetails = benefitComponentDetails;
    }
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
	/**
	 * Returns the benefitComponentList
	 * @return List benefitComponentList.
	 */
	public List getBenefitComponentList() {
		return benefitComponentList;
	}
	/**
	 * Sets the benefitComponentList
	 * @param benefitComponentList.
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}
	
	
	
	/**
	 * @return Returns the benefitDetails.
	 */
	public BenefitBO getBenefitDetails() {
		return benefitDetails;
	}
	/**
	 * @param benefitDetails The benefitDetails to set.
	 */
	public void setBenefitDetails(BenefitBO benefitDetails) {
		this.benefitDetails = benefitDetails;
	}
	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
}
