/*
 * MandateListBOImpl.java
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
public class MandateListBOImpl {
    
    // variable declaaration
    private int mandateId;
    private String mandateDescription;

    /**
     * @return Returns the mandateDescription.
     */
    public String getMandateDescription() {
        return mandateDescription;
    }
    /**
     * @param mandateDescription The mandateDescription to set.
     */
    public void setMandateDescription(String mandateDescription) {
        this.mandateDescription = mandateDescription;
    }
    /**
     * @return Returns the mandateId.
     */
    public int getMandateId() {
        return mandateId;
    }
    /**
     * @param mandateId The mandateId to set.
     */
    public void setMandateId(int mandateId) {
        this.mandateId = mandateId;
    }
}
