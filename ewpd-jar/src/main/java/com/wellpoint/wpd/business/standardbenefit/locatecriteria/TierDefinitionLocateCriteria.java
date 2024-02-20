/*
 * Created on July 31, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TierDefinitionLocateCriteria extends LocateCriteria {
	private String action;
	private int benefitComponentId;
	private int productId;
	private int benefitDefinitionId;
	
	
   
    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
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
    /**
     * @return Returns the productId.
     */
    public int getProductId() {
        return productId;
    }
    /**
     * @param productId The productId to set.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    /**
     * @return Returns the action.
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action The action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }
}
