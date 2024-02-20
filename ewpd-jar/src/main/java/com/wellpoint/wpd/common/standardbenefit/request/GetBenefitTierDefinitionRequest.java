/*
 * Created on Aug 03, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;


import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class GetBenefitTierDefinitionRequest extends WPDRequest{
    
    private int benefitId;
    
    private int benefitDefinitionId;
   
	
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }  	
   
    
    
    /**
     * @return Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * @param benefitId The benefitId to set.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
    /**
     * @return Returns the benefitDefinitionId.
     */
    public int getBenefitDefinitionId() {
        return benefitDefinitionId;
    }
    /**
     * @param benefitDefinitionId The benefitDefinitionId to set.
     */
    public void setBenefitDefinitionId(int benefitDefinitionId) {
        this.benefitDefinitionId = benefitDefinitionId;
    }
}
