/*
 * ProductStructureBenefitDefinitions.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitDefinitions {

    private List updatedBenefitLines;

    private List deletedBenefitLevels;

    /**
     * Returns the deletedBenefitLevels
     * 
     * @return List deletedBenefitLevels.
     */
    public List getDeletedBenefitLevels() {
        return deletedBenefitLevels;
    }

    /**
     * Sets the deletedBenefitLevels
     * 
     * @param deletedBenefitLevels
     */
    public void setDeletedBenefitLevels(List deletedBenefitLevels) {
        this.deletedBenefitLevels = deletedBenefitLevels;
    }


    /**
     * Returns the updatedBenefitLines
     * 
     * @return List updatedBenefitLines.
     */
    public List getUpdatedBenefitLines() {
        return updatedBenefitLines;
    }


    /**
     * Sets the updatedBenefitLines
     * 
     * @param updatedBenefitLines.
     */
    public void setUpdatedBenefitLines(List updatedBenefitLines) {
        this.updatedBenefitLines = updatedBenefitLines;
    }


    /**
     * Overriding toString method
     * 
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("updatedBenefitLines = ")
                .append(this.updatedBenefitLines);
        buffer.append("deletedBenefitLevels = ").append(
                this.deletedBenefitLevels);
        return buffer.toString();

    }
}