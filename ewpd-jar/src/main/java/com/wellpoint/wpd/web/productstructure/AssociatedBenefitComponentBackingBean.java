/*
 * AssociatedBenefitComponentBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.List;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitComponentPopUpRequest;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitComponentPopUpResponse;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

/**
 * Backing Bean for selecting benefit components from popup.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AssociatedBenefitComponentBackingBean extends
        ProductStructureBackingBean {

    /**
     * ProductStructureVO object for retrieving benefit components.
     */
    private ProductStructureVO productStructureVO;

    /**
     * List contains benefit componnets selected.
     */
    private List selectBenefitComponents = null;


    /**
     * Constructor.
     *  
     */
    public AssociatedBenefitComponentBackingBean() {

    }
    
    private String retrieveAllBenefitComponentRecords;
    
    

	/**
	 * @return Returns the retrieveAllBenefitComponentRecords.
	 */
	public String getRetrieveAllBenefitComponentRecords() {
		Logger.logInfo("Entering the method for getting selected benefit"
                + " components");
        if (selectBenefitComponents == null) {
            RetrieveBenefitComponentPopUpRequest retrieveProductStructureRequest = (RetrieveBenefitComponentPopUpRequest) this
                    .getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_COMPONENT);

            RetrieveBenefitComponentPopUpResponse retrieveProductStructureResponse = null;

            productStructureVO = new ProductStructureVO();
            productStructureVO = this
                    .getProductStructureFromSession(productStructureVO);
            retrieveProductStructureRequest
                    .setProductStructureId(getIdFromSession());
            retrieveProductStructureRequest
                    .setProductStructureVO(productStructureVO);
            retrieveProductStructureResponse = (RetrieveBenefitComponentPopUpResponse) this
                    .executeService(retrieveProductStructureRequest);
            Logger.logInfo("Returning the method for getting selected benefit "
                    + "components");
            if (null != retrieveProductStructureResponse
                    && !retrieveProductStructureResponse
                            .getBenefitComponentList().isEmpty()) {
                selectBenefitComponents = retrieveProductStructureResponse
                        .getBenefitComponentList();
            } else {
                selectBenefitComponents = null;
            }
        }
        this.setSelectBenefitComponents(selectBenefitComponents);

		return retrieveAllBenefitComponentRecords;
	}
	/**
	 * @param retrieveAllBenefitComponentRecords The retrieveAllBenefitComponentRecords to set.
	 */
	public void setRetrieveAllBenefitComponentRecords(
			String retrieveAllBenefitComponentRecords) {
		this.retrieveAllBenefitComponentRecords = retrieveAllBenefitComponentRecords;
	}
    /**
     * Returns the selectBenefitComponents.
     * 
     * @return List
     */
    public List getSelectBenefitComponents() {
        return selectBenefitComponents;
    }


    /**
     * Sets selectBenefitComponents.
     * 
     * @param selectBenefitComponents.
     */
    public void setSelectBenefitComponents(List selectBenefitComponents) {
        this.selectBenefitComponents = selectBenefitComponents;
    }


    /**
     * Returns the productStructureVO.
     * 
     * @return ProductStructureVO.
     */

    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * Sets the productStructureVO.
     * 
     * @param productStructureVO.
     */

    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }

}