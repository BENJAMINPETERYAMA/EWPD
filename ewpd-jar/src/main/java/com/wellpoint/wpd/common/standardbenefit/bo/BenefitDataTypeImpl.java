/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author U14647
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitDataTypeImpl implements BenefitDataType {
    private String code;
    
    private String description;
    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return Returns the decription.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param decription The decription to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
