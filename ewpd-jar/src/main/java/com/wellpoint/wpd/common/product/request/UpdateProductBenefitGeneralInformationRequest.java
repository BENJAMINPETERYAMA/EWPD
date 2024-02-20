/*
 * Created on Oct 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.product.vo.ProductBenefitGeneralInformationVO;


/**
 * The purpose of this Request class is to save the Product Benefit 
 * General Information.
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class UpdateProductBenefitGeneralInformationRequest extends ProductRequest{
    
    private ProductBenefitGeneralInformationVO productBenefitGeneralInformationVO;   
	
    /**
	 * @return Returns the productBenefitGeneralInformationVO.
	 */
	public ProductBenefitGeneralInformationVO getProductBenefitGeneralInformationVO() {
		return productBenefitGeneralInformationVO;
	}
	/**
	 * @param productBenefitGeneralInformationVO The productBenefitGeneralInformationVO to set.
	 */
	public void setProductBenefitGeneralInformationVO(
			ProductBenefitGeneralInformationVO productBenefitGeneralInformationVO) {
		this.productBenefitGeneralInformationVO = productBenefitGeneralInformationVO;
	}
}
