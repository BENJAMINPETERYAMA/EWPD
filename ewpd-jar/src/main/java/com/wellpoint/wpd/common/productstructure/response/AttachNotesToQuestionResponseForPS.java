/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AttachNotesToQuestionResponseForPS extends WPDResponse {

	
	private int benefitDefinitionId = 0;
	
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
