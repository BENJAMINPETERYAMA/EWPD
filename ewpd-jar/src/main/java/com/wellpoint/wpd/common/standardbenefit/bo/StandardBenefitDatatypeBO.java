/*
 * StandardBenefitDatatypeBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitDatatypeBO {
    

	private int standardBenefitKey;
    private String dataTypeName;
    private int dataTypeId;
    private String selectedItemType;
    private String selectedItemCode;

    
    /**
     * Returns the dataTypeId
     * @return int dataTypeId.
     */
    public int getDataTypeId() {
        return dataTypeId;
    }
    /**
     * Sets the dataTypeId
     * @param dataTypeId.
     */
    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
    /**
     * Returns the dataTypeName
     * @return String dataTypeName.
     */
    public String getDataTypeName() {
        return dataTypeName;
    }
    /**
     * Sets the dataTypeName
     * @param dataTypeName.
     */
    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }
    /**
     * Returns the selectedItemCode
     * @return String selectedItemCode.
     */
    public String getSelectedItemCode() {
        return selectedItemCode;
    }
    /**
     * Sets the selectedItemCode
     * @param selectedItemCode.
     */
    public void setSelectedItemCode(String selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }
    /**
     * Returns the selectedItemType
     * @return String selectedItemType.
     */
    public String getSelectedItemType() {
        return selectedItemType;
    }
    /**
     * Sets the selectedItemType
     * @param selectedItemType.
     */
    public void setSelectedItemType(String selectedItemType) {
        this.selectedItemType = selectedItemType;
    }
    /**
     * Returns the standardBenefitKey
     * @return int standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * Sets the standardBenefitKey
     * @param standardBenefitKey.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
}
