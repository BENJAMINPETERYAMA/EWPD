/*
 * Created on Jul 31, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitTierDefinitionBO  {
    
    private int tierDefinitionId;
    private String dataType;
    private String tierDescription;
    private String tierCode;

    /**
     * @return Returns the dataType.
     */
    public String getDataType() {
        return dataType;
    }
    /**
     * @param dataType The dataType to set.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    /**
     * @return Returns the tierDescription.
     */
    public String getTierDescription() {
        return tierDescription;
    }
    /**
     * @param tierDescription The tierDescription to set.
     */
    public void setTierDescription(String tierDescription) {
        this.tierDescription = tierDescription;
    }
    /**
     * @return Returns the tierCode.
     */
    public String getTierCode() {
        return tierCode;
    }
    /**
     * @param tierCode The tierCode to set.
     */
    public void setTierCode(String tierCode) {
        this.tierCode = tierCode;
    }
    /**
     * @return Returns the tierDefinitionId.
     */
    public int getTierDefinitionId() {
        return tierDefinitionId;
    }
    /**
     * @param tierDefinitionId The tierDefinitionId to set.
     */
    public void setTierDefinitionId(int tierDefinitionId) {
        this.tierDefinitionId = tierDefinitionId;
    }
}
