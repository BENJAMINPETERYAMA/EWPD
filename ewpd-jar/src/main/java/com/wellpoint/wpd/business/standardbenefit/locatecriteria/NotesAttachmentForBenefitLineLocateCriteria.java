/*
 * NotesAttachmentForBenefitLineLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesAttachmentForBenefitLineLocateCriteria extends LocateCriteria{

    private String benefitLineId;
    
    private List benefitQualifierCode;
    
    private List benefitTermCode;
    
    private String noteFilterString;
    
    private int benefitDefinitionId;
    /**
     * @return benefitDefinitionId
     * 
     * Returns the benefitDefinitionId.
     */
    public int getBenefitDefinitionId() {
        return benefitDefinitionId;
    }
    /**
     * @param benefitDefinitionId
     * 
     * Sets the benefitDefinitionId.
     */
    public void setBenefitDefinitionId(int benefitDefinitionId) {
        this.benefitDefinitionId = benefitDefinitionId;
    }
    /**
     * Returns the benefitLineId
     * @return String benefitLineId.
     */

    public String getBenefitLineId() {
        return benefitLineId;
    }
    /**
     * Sets the benefitLineId
     * @param benefitLineId.
     */

    public void setBenefitLineId(String benefitLineId) {
        this.benefitLineId = benefitLineId;
    }
    /**
     * @return benefitQualifierCode
     * 
     * Returns the benefitQualifierCode.
     */
    public List getBenefitQualifierCode() {
        return benefitQualifierCode;
    }
    /**
     * @param benefitQualifierCode
     * 
     * Sets the benefitQualifierCode.
     */
    public void setBenefitQualifierCode(List benefitQualifierCode) {
        this.benefitQualifierCode = benefitQualifierCode;
    }
    /**
     * Returns the benefitTermCode
     * @return List benefitTermCode.
     */

    public List getBenefitTermCode() {
        return benefitTermCode;
    }
    /**
     * Sets the benefitTermCode
     * @param benefitTermCode.
     */

    public void setBenefitTermCode(List benefitTermCode) {
        this.benefitTermCode = benefitTermCode;
    }
    /**
     * @return noteFilterString
     * 
     * Returns the noteFilterString.
     */
    public String getNoteFilterString() {
        return noteFilterString;
    }
    /**
     * @param noteFilterString
     * 
     * Sets the noteFilterString.
     */
    public void setNoteFilterString(String noteFilterString) {
        this.noteFilterString = noteFilterString;
    }
}
