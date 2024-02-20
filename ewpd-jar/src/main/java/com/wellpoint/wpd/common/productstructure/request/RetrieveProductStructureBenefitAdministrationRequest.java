/*
 * RetrieveProductStructureBenefitAdministrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductStructureBenefitAdministrationRequest extends
        WPDRequest {

    private int adminSysId;

    private int benefitAdminSysId;

    private int benefitComponentId;

    private ProductStructureVO productStructureVO;


    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitComponentId
     *            The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * @return Returns the benefitAdminSysId.
     */
    public int getBenefitAdminSysId() {
        return benefitAdminSysId;
    }


    /**
     * @param benefitAdminSysId
     *            The benefitAdminSysId to set.
     */
    public void setBenefitAdminSysId(int benefitAdminSysId) {
        this.benefitAdminSysId = benefitAdminSysId;
    }


    /**
     * @return Returns the adminSysId.
     */
    public int getAdminSysId() {
        return adminSysId;
    }


    /**
     * @param adminSysId
     *            The adminSysId to set.
     */
    public void setAdminSysId(int adminSysId) {
        this.adminSysId = adminSysId;
    }


    /**
     * @return Returns the productStructureVO.
     */
    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * @param productStructureVO
     *            The productStructureVO to set.
     */
    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

}