/*
 * RetrieveBenefitMandatesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.productstructure.vo.MandatesVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitMandatesRequest  extends ContractRequest  {
    
    MandatesVO mandatesVO;

    ContractVO contractVO;
    
    
    
    /**
     * @return contractVO
     * 
     * Returns the contractVO.
     */
    public ContractVO getContractVO() {
        return contractVO;
    }
    /**
     * @param contractVO
     * 
     * Sets the contractVO.
     */
    public void setContractVO(ContractVO contractVO) {
        this.contractVO = contractVO;
    }
    /**
     * @return mandatesVO
     * 
     * Returns the mandatesVO.
     */
    public MandatesVO getMandatesVO() {
        return mandatesVO;
    }
    /**
     * @param mandatesVO
     * 
     * Sets the mandatesVO.
     */
    public void setMandatesVO(MandatesVO mandatesVO) {
        this.mandatesVO = mandatesVO;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

}
