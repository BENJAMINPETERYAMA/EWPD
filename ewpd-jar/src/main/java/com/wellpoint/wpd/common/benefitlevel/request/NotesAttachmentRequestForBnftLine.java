/*
 * NotesAttachmentRequestForBnftLine.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitlevel.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesAttachmentRequestForBnftLine extends WPDRequest{
    
    private List benefitTerms;
    
    private List benefitQualifier;
    
    private String benefitLineID;
    
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
     * Returns the benefitLineID
     * @return String benefitLineID.
     */

    public String getBenefitLineID() {
        return benefitLineID;
    }
    /**
     * Sets the benefitLineID
     * @param benefitLineID.
     */

    public void setBenefitLineID(String benefitLineID) {
        this.benefitLineID = benefitLineID;
    }
 
    /**
     * @return benefitQualifier
     * 
     * Returns the benefitQualifier.
     */
    public List getBenefitQualifier() {
        return benefitQualifier;
    }
    /**
     * @param benefitQualifier
     * 
     * Sets the benefitQualifier.
     */
    public void setBenefitQualifier(List benefitQualifier) {
        this.benefitQualifier = benefitQualifier;
    }
    /**
     * Returns the benefitTerms
     * @return List benefitTerms.
     */

    public List getBenefitTerms() {
        return benefitTerms;
    }
    /**
     * Sets the benefitTerms
     * @param benefitTerms.
     */

    public void setBenefitTerms(List benefitTerms) {
        this.benefitTerms = benefitTerms;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
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
